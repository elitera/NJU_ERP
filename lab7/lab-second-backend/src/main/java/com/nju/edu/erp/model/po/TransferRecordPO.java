package com.nju.edu.erp.model.po;

import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRecordPO {

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 收款单id
     */
    private String receiptSheetId;
    /**
     * 银行账户
     */
    private String bankAccountName;
    /**
     * 转账金额
     */
    private BigDecimal transferAmount;
    /**
     * 备注
     */
    private String remark;
}
