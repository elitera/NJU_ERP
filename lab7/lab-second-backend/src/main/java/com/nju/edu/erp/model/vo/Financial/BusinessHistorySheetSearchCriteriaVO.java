package com.nju.edu.erp.model.vo.Financial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessHistorySheetSearchCriteriaVO {

    /**
     * 起始时间
     */
    private String beginDateStr;

    /**
     * 结束时间
     */
    private String endDateStr;

    /**
     * 单据类型列表：例如 "JHD"、"FKD"...
     */
    private List<String> types;

    /**
     * 客户
     */
    private String customer;

    /**
     * 业务员
     */
    private String operator;
}
