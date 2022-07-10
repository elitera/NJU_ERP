package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.PayrollState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.PayrollPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PayrollDao {

    void saveSheet(PayrollPO payrollPO);

    PayrollPO getLatestSheet();

    List<String> findSheetIdByOperator(String operator);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);

    void approval(String payrollId, PayrollState state);

    PayrollPO findSheetById(String id);
}
