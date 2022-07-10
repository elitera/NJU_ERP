package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PayrollState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.PayrollVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface PayrollService {

    void makePayroll(UserVO userVO, PayrollVO payrollVO);

    List<String> findPayrollSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

    void approval(String payrollId, PayrollState state);

    PayrollVO showPayrollById(String id);
}
