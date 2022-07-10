package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.dao.CashExpenseSheetDao;
import com.nju.edu.erp.dao.TermSheetDao;
import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.CashExpenseSheetPO;
import com.nju.edu.erp.model.po.TermSheetPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import com.nju.edu.erp.model.vo.Financial.CashExpenseSheetVO;
import com.nju.edu.erp.model.vo.Financial.TermSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CashExpenseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashExpenseServiceImpl implements CashExpenseService {

    private final CashExpenseSheetDao cashExpenseSheetDao;

    private final TermSheetDao termSheetDao;

    private final BankAccountDao bankAccountDao;

    public CashExpenseServiceImpl(CashExpenseSheetDao cashExpenseSheetDao, TermSheetDao termSheetDao, BankAccountDao bankAccountDao) {
        this.cashExpenseSheetDao = cashExpenseSheetDao;
        this.termSheetDao = termSheetDao;
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void makeCashExpenseSheet(UserVO userVO, CashExpenseSheetVO cashExpenseSheetVO) {
        CashExpenseSheetPO cashExpenseSheetPO = new CashExpenseSheetPO();
        BeanUtils.copyProperties(cashExpenseSheetVO, cashExpenseSheetPO);
        cashExpenseSheetPO.setOperator(userVO.getName());
        cashExpenseSheetPO.setCreateTime(new Date());
        CashExpenseSheetPO latest = cashExpenseSheetDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XJFYD");
        cashExpenseSheetPO.setId(id);
        cashExpenseSheetPO.setState(CashExpenseSheetState.PENDING);
        cashExpenseSheetPO.setBankAccountName(cashExpenseSheetVO.getBankAccount().getBankAccountName());
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<TermSheetPO> termSheetPOS = new ArrayList<>();
        for (TermSheetVO termSheetVO : cashExpenseSheetVO.getTermSheetList()) {
            TermSheetPO termSheetPO = new TermSheetPO();
            BeanUtils.copyProperties(termSheetVO, termSheetPO);
            termSheetPO.setPaymentOrderId(id);
            BigDecimal money = termSheetVO.getMoney();
            totalAmount = totalAmount.add(money);
            termSheetPOS.add(termSheetPO);
        }
        termSheetDao.saveBatchSheetContent(termSheetPOS);
        cashExpenseSheetPO.setTotalAmount(totalAmount);
        cashExpenseSheetDao.saveSheet(cashExpenseSheetPO);
    }

    @Override
    public List<String> findCashExpenseSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
        return cashExpenseSheetDao.findSheetIdBySomeConditions(searchCriteriaPO);
    }

    @Override
    public void approval(String cashExpenseId, CashExpenseSheetState state) {
        cashExpenseSheetDao.approval(cashExpenseId, state);
    }

    @Override
    public CashExpenseSheetVO showCashExpenseById(String id) {
        CashExpenseSheetVO result = new CashExpenseSheetVO();
        CashExpenseSheetPO cashExpenseSheetPO = cashExpenseSheetDao.findSheetById(id);
        BeanUtils.copyProperties(cashExpenseSheetPO, result);
        List<BankAccountPO> bankAccountPOS = bankAccountDao.selectBankAccountByName(cashExpenseSheetPO.getBankAccountName());
        BankAccountVO bankAccountVO = new BankAccountVO();
        try {
            BeanUtils.copyProperties(bankAccountPOS.get(0), bankAccountVO);
        } catch (Exception e){
            bankAccountVO.setBankAccountName("null");
            bankAccountVO.setBalance(null);
        }
        result.setBankAccount(bankAccountVO);
        List<TermSheetPO> termSheetPOS = termSheetDao.findTermSheetsBySheetId(id);
        List<TermSheetVO> termSheetVOS = new ArrayList<>();
        for (TermSheetPO termSheetPO : termSheetPOS) {
            TermSheetVO termSheetVO = new TermSheetVO();
            BeanUtils.copyProperties(termSheetPO, termSheetVO);
            termSheetVOS.add(termSheetVO);
        }
        result.setTermSheetList(termSheetVOS);
        return result;
    }
}
