package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.ReceiptSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.ReceiptVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface ReceiptService {
    /**
     * 制定收款单
     * @param userVO
     * @param receiptVO
     */
    void makeReceipt(UserVO userVO, ReceiptVO receiptVO);

    List<String> findReceiptSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

    void approval(String receiptId, ReceiptSheetState state);

    ReceiptVO showReceiptById(String id);
}
