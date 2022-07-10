package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.enums.sheetState.PayrollState;
import com.nju.edu.erp.model.vo.Financial.BankAccountVO;
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
public class PayrollPO {

    /**
     * 单据编号
     */
    private String OrderId;
    /**
     * 员工编号
     */
    private Integer stuffId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 银行账户信息
     */
    private String bankAccountName;
    /**
     * 应发工资
     */
    private BigDecimal rowWages;
    /**
     * 个人所得税
     */
    private BigDecimal individualIncomeTax;
    /**
     * 失业保险
     */
    private BigDecimal unemploymentInsurance;
    /**
     * 住房公积金
     */
    private BigDecimal housingProvidentFund;
    /**
     * 扣除税款
     */
    private BigDecimal tax;
    /**
     * 实发工资
     */
    private BigDecimal realWages;
    /**
     * 单据状态
     */
    private PayrollState state;
    /**
     * 创建时间
     */
    private Date createTime;
}
