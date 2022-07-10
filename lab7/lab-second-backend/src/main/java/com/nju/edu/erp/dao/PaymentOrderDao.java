package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.PaymentOrderPO;
import com.nju.edu.erp.model.po.ReceiptPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PaymentOrderDao {

    PaymentOrderPO getLatestSheet();

    void saveSheet(PaymentOrderPO paymentOrderPO);

    List<String> findSheetIdByOperator(String operator);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);

    void approval(String paymentOrderId, PaymentSheetState state);

    PaymentOrderPO findSheetById(String id);
}
