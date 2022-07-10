package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.dao.ReceiptDao;
import com.nju.edu.erp.dao.TransferRecordDao;
import com.nju.edu.erp.enums.sheetState.ReceiptSheetState;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.ReceiptPO;
import com.nju.edu.erp.model.po.TransferRecordPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import com.nju.edu.erp.model.vo.Financial.ReceiptVO;
import com.nju.edu.erp.model.vo.Financial.TransferRecordVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.ReceiptService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptDao receiptDao;

    private final TransferRecordDao transferRecordDao;

    private final BankAccountDao bankAccountDao;

    public ReceiptServiceImpl(ReceiptDao receiptDao, TransferRecordDao transferRecordDao, BankAccountDao bankAccountDao) {
        this.receiptDao = receiptDao;
        this.transferRecordDao = transferRecordDao;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void makeReceipt(UserVO userVO, ReceiptVO receiptVO) {
        ReceiptPO receiptPO = new ReceiptPO();
        BeanUtils.copyProperties(receiptVO, receiptPO);
        receiptPO.setOperator(userVO.getName());
        receiptPO.setCreateTime(new Date());
        ReceiptPO latest = receiptDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
        receiptPO.setId(id);
        receiptPO.setState(ReceiptSheetState.PENDING);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<TransferRecordPO> transferRecordPOS = new ArrayList<>();
        for (TransferRecordVO transferRecordVO : receiptVO.getTransferList()) {
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
        receiptPO.setTotalAmount(totalAmount);
        receiptDao.saveSheet(receiptPO);
    }

    @Override
    public List<String> findReceiptSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
        return receiptDao.findSheetIdBySomeConditions(searchCriteriaPO);
    }

    @Override
    public void approval(String receiptId, ReceiptSheetState state) {
        receiptDao.approval(receiptId, state);
    }

    @Override
    public ReceiptVO showReceiptById(String id) {
        ReceiptVO result = new ReceiptVO();
        ReceiptPO receiptPO = receiptDao.selectReceiptById(id);
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
        BeanUtils.copyProperties(receiptPO, result);
        result.setTransferList(transferRecordVOS);
        return result;
    }
}
