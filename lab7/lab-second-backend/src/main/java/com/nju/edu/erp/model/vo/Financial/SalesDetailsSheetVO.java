package com.nju.edu.erp.model.vo.Financial;

import com.nju.edu.erp.enums.sheetState.SaleSheetState;
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
public class SalesDetailsSheetVO {

    /**
     * 时间（精确到天）
     */
    private Date createTime;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品型号
     */
    private String type;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 总金额
     */
    private BigDecimal totalPrice;
}
