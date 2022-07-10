package com.nju.edu.erp.model.vo.Financial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRecordVO {

    /**
     * 银行账户
     */
    private BankAccountVO bankAccount;
    /**
     * 转账金额
     */
    private BigDecimal transferAmount;
    /**
     * 备注
     */
    private String remark;
}
