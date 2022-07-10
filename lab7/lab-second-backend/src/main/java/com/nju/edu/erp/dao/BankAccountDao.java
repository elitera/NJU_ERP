package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BankAccountDao {

    void insertBankAccount(BankAccountPO bankAccountPO);

    void deleteBankAccountByName(String name);

    List<BankAccountPO> selectBankAccountByName(String name);

    void updateBankAccount(BankAccountPO bankAccountPO);

    List<BankAccountVO> selectAll();
}
