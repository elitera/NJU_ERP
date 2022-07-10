package com.nju.edu.erp.model.vo.Financial;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentOrderVO {

    /**
     * 付款单单据编号（格式为：FKD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 供应商id
     */
    private Integer supplier;
    /**
     * 销售商id
     */
    private Integer seller;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 转账列表
     */
    private List<TransferRecordVO> transferList;
    /**
     * 总额汇总
     */
    private BigDecimal TotalAmount;
    /**
     * 单据状态, 新建单据时前端传null
     */
    private PaymentSheetState state;
}
