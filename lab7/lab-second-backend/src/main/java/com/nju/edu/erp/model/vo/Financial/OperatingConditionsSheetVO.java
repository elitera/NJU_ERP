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
public class OperatingConditionsSheetVO {

    /**
     * 销售收入
     */
    private BigDecimal salesIncome;

    /**
     * 商品报溢收入
     */
    private BigDecimal overflowIncome;

    /**
     * 成本调价收入
     */
    private BigDecimal costPriceAdjustmentIncome;

    /**
     * 进货退货差价
     */
    private BigDecimal purchaseReturnDifference;

    /**
     * 代金券与实际收款差额收入
     */
    private BigDecimal cashCoupon;

    /**
     * 销售成本
     */
    private BigDecimal costOfSales;

    /**
     * 商品报损
     */
    private BigDecimal destroy;

    /**
     * 商品赠出
     */
    private BigDecimal gift;

    /**
     * 人力成本
     */
    private BigDecimal laborCost;

    /**
     * 收入类：折让后总收入
     */
    private BigDecimal totalIncome;

    /**
     * 折让
     */
    private BigDecimal allowance;

    /**
     * 总支出
     */
    private BigDecimal totalExpenditure;

    /**
     * 利润
     */
    private BigDecimal profit;
}
