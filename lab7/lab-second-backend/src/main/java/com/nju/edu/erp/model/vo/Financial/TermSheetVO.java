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
public class TermSheetVO {

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
