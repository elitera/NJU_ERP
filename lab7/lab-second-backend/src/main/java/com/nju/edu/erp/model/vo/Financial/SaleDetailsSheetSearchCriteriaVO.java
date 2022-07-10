package com.nju.edu.erp.model.vo.Financial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailsSheetSearchCriteriaVO {

    /**
     * 起始时间
     */
    private String beginDateStr;

    /**
     * 结束时间
     */
    private String endDateStr;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 商品名
     */
    private String name;

    /**
     * 客户
     */
    private String customer;

}
