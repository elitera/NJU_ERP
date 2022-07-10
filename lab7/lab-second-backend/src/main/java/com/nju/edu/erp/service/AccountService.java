package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;

import java.util.List;

public interface AccountService {
    /**
     * 增加银行账户
     * @param bankAccountVO
     */
    void addBankAccount(BankAccountVO bankAccountVO);

    /**
     * 依据账户名称删除银行账户
     * @param name
     */
    void deleteBankAccount(String name);

    /**
     * 依据账户ID修改银行账户
     * @param bankAccountVO
     */
    void updateBankAccount(BankAccountVO bankAccountVO);

    /**
     * 查询账户
     * @param name
     */
    List<BankAccountPO> selectBankAccount(String name);

    /**
     * 展示所有银行账户
     */
    List<BankAccountVO> showAllBankAccount();
}
