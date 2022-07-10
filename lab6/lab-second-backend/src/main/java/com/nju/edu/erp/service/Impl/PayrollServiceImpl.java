package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.dao.PayrollDao;
import com.nju.edu.erp.enums.sheetState.PayrollState;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.PayrollPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import com.nju.edu.erp.model.vo.Financial.PayrollVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.PayrollService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollDao payrollDao;

    private final BankAccountDao bankAccountDao;

    public PayrollServiceImpl(PayrollDao payrollDao, BankAccountDao bankAccountDao) {
        this.payrollDao = payrollDao;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void makePayroll(UserVO userVO, PayrollVO payrollVO) {
        PayrollPO payrollPO = new PayrollPO();
        BeanUtils.copyProperties(payrollVO, payrollPO);
        payrollPO.setBankAccountName(payrollVO.getBankAccount().getBankAccountName());
        PayrollPO latest = payrollDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getOrderId(), "GZD");
        payrollPO.setOrderId(id);
        payrollPO.setState(PayrollState.PENDING);
        payrollPO.setCreateTime(new Date());
        payrollDao.saveSheet(payrollPO);
    }

    @Override
    public List<String> findPayrollSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
        return payrollDao.findSheetIdBySomeConditions(searchCriteriaPO);

    }

    @Override
    public void approval(String payrollId, PayrollState state) {
        payrollDao.approval(payrollId, state);
    }

    @Override
    public PayrollVO showPayrollById(String id) {
        PayrollVO result = new PayrollVO();
        PayrollPO payrollPO = payrollDao.findSheetById(id);
        BeanUtils.copyProperties(payrollPO, result);
        List<BankAccountPO> bankAccountPOS = bankAccountDao.selectBankAccountByName(payrollPO.getBankAccountName());
        BankAccountVO bankAccountVO = new BankAccountVO();
        try {
            BeanUtils.copyProperties(bankAccountPOS.get(0), bankAccountVO);
        } catch (Exception e){
            bankAccountVO.setBankAccountName("null");
            bankAccountVO.setBalance(null);
        }
        result.setBankAccount(bankAccountVO);
        return result;
    }
}
