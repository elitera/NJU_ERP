package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashExpenseSheetPO {

    /**
     * 现金费用单单据编号（格式为：XJFYD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 银行账户
     */
    private String bankAccountName;
    /**
     * 总额
     */
    private BigDecimal totalAmount;
    /**
     * 单据状态
     */
    private CashExpenseSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;
}
