package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.PaymentOrderVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PaymentOrderService {
    /**
     * 制定付款单
     * @param userVO
     * @param paymentOrderVO
     */
    void makePaymentOrder(UserVO userVO, PaymentOrderVO paymentOrderVO);

    List<String> findPaymentOrderSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

    void approval(String paymentOrderId, PaymentSheetState state);

    PaymentOrderVO showPaymentOrderById(String id);
}
