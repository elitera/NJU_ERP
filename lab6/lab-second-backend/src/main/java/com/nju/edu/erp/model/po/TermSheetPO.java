package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermSheetPO {

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 对应现金费用单id
     */
    private String paymentOrderId;
    /**
     * 条目名
     */
    private String name;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 备注
     */
    private String remark;
}
