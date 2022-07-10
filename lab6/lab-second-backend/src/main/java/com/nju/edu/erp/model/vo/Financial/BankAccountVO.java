package com.nju.edu.erp.model.vo.Financial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountVO {

    /**
     * 银行账户名称
     */
    private String bankAccountName;
    /**
     * 银行账户余额
     */
    private Integer balance;
}
