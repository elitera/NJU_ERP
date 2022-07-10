package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import com.nju.edu.erp.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final BankAccountDao bankAccountDao;

    public AccountServiceImpl(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void addBankAccount(BankAccountVO bankAccountVO) {
        BankAccountPO bankAccountPO = new BankAccountPO();
        BeanUtils.copyProperties(bankAccountVO, bankAccountPO);
        bankAccountDao.insertBankAccount(bankAccountPO);
    }

    @Override
    public void deleteBankAccount(String name) {
        bankAccountDao.deleteBankAccountByName(name);
    }

    @Override
    public void updateBankAccount(BankAccountVO bankAccountVO) {
        BankAccountPO bankAccountPO = new BankAccountPO();
        BeanUtils.copyProperties(bankAccountVO, bankAccountPO);
        bankAccountDao.updateBankAccount(bankAccountPO);
    }

    @Override
    public List<BankAccountPO> selectBankAccount(String name) {
        return bankAccountDao.selectBankAccountByName(name);
    }

    @Override
    public List<BankAccountVO> showAllBankAccount() {
        return bankAccountDao.selectAll();
    }


}
