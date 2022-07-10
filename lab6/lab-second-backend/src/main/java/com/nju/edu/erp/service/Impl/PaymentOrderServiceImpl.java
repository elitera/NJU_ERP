package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.dao.PaymentOrderDao;
import com.nju.edu.erp.dao.TransferRecordDao;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.PaymentOrderPO;
import com.nju.edu.erp.model.po.TransferRecordPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import com.nju.edu.erp.model.vo.Financial.PaymentOrderVO;
import com.nju.edu.erp.model.vo.Financial.TransferRecordVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PaymentOrderService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final PaymentOrderDao paymentOrderDao;

    private final TransferRecordDao transferRecordDao;

    private final BankAccountDao bankAccountDao;

    public PaymentOrderServiceImpl(PaymentOrderDao paymentOrderDao, TransferRecordDao transferRecordDao, BankAccountDao bankAccountDao) {
        this.paymentOrderDao = paymentOrderDao;
        this.transferRecordDao = transferRecordDao;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void makePaymentOrder(UserVO userVO, PaymentOrderVO paymentOrderVO) {
        PaymentOrderPO paymentOrderPO = new PaymentOrderPO();
        BeanUtils.copyProperties(paymentOrderVO, paymentOrderPO);
        paymentOrderPO.setOperator(userVO.getName());
        paymentOrderPO.setCreateTime(new Date());
        PaymentOrderPO latest = paymentOrderDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "FKD");
        paymentOrderPO.setId(id);
        paymentOrderPO.setState(PaymentSheetState.PENDING);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<TransferRecordPO> transferRecordPOS = new ArrayList<>();
        for (TransferRecordVO transferRecordVO : paymentOrderVO.getTransferList()) {
            TransferRecordPO transferRecordPO = new TransferRecordPO();
            BeanUtils.copyProperties(transferRecordVO, transferRecordPO);
            transferRecordPO.setReceiptSheetId(id);
            String bankAccountName = transferRecordVO.getBankAccount().getBankAccountName();
            transferRecordPO.setBankAccountName(bankAccountName);
            BigDecimal transferAmount = transferRecordPO.getTransferAmount();
            totalAmount = totalAmount.add(transferAmount);
            transferRecordPOS.add(transferRecordPO);
        }
        transferRecordDao.saveBatchSheetContent(transferRecordPOS);
        paymentOrderPO.setTotalAmount(totalAmount);
        paymentOrderDao.saveSheet(paymentOrderPO);
    }

    @Override
    public List<String> findPaymentOrderSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
        return paymentOrderDao.findSheetIdBySomeConditions(searchCriteriaPO);
    }

    @Override
    public void approval(String paymentOrderId, PaymentSheetState state) {
        paymentOrderDao.approval(paymentOrderId, state);
    }

    @Override
    public PaymentOrderVO showPaymentOrderById(String id) {
        PaymentOrderVO result = new PaymentOrderVO();
        PaymentOrderPO paymentOrderPO = paymentOrderDao.findSheetById(id);
        BeanUtils.copyProperties(paymentOrderPO, result);
        List<TransferRecordPO> transferRecordPOS = transferRecordDao.findTransferBySheetId(id);
        List<TransferRecordVO> transferRecordVOS = new ArrayList<>();
        for (TransferRecordPO transferRecordPO : transferRecordPOS) {
            TransferRecordVO transferRecordVO = new TransferRecordVO();
            BeanUtils.copyProperties(transferRecordPO, transferRecordVO);
            List<BankAccountPO> bankAccountPOS = bankAccountDao.selectBankAccountByName(transferRecordPO.getBankAccountName());
            BankAccountVO bankAccountVO = new BankAccountVO();
            try {
                BeanUtils.copyProperties(bankAccountPOS.get(0), bankAccountVO);
            } catch (Exception e){
                bankAccountVO.setBankAccountName("null");
                bankAccountVO.setBalance(null);
            }
            transferRecordVO.setBankAccount(bankAccountVO);
            transferRecordVOS.add(transferRecordVO);
        }
        result.setTransferList(transferRecordVOS);
        return result;
    }
}
