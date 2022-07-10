package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailsSheetSearchCriteriaPO {

    /**
     * 起始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

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
    private String supplier;
}
