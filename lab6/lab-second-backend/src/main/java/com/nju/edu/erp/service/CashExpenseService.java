package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.CashExpenseSheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface CashExpenseService {
    /**
     * 制定现金费用单
     * @param userVO
     * @param cashExpenseSheetVO
     */
    void makeCashExpenseSheet(UserVO userVO, CashExpenseSheetVO cashExpenseSheetVO);

    List<String> findCashExpenseSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

    void approval(String cashExpenseId, CashExpenseSheetState state);

    CashExpenseSheetVO showCashExpenseById(String id);
}
