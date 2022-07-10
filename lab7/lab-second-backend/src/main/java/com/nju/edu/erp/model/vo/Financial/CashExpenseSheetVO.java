package com.nju.edu.erp.model.vo.Financial;

import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
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
public class CashExpenseSheetVO {

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
    private BankAccountVO bankAccount;
    /**
     * 条目清单
     */
    private List<TermSheetVO> termSheetList;
    /**
     * 总额
     */
    private BigDecimal TotalAmount;
    /**
     * 单据状态, 新建单据时前端传null
     */
    private CashExpenseSheetState state;
}
