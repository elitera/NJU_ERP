package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.CashExpenseSheetPO;
import com.nju.edu.erp.model.po.PaymentOrderPO;
import com.nju.edu.erp.model.po.ReceiptPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface CashExpenseSheetDao {

    CashExpenseSheetPO getLatestSheet();

    void saveSheet(CashExpenseSheetPO cashExpenseSheetPO);

    List<String> findSheetIdByOperator(String operator);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);

    void approval(String cashExpenseId, CashExpenseSheetState state);

    CashExpenseSheetPO findSheetById(String id);
}
