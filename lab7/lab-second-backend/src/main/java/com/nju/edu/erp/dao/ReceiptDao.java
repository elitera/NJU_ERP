package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.ReceiptSheetState;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Financial.ReceiptVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ReceiptDao {

    /**
     * 存入一条收款单记录
     * @param toSave 一条收款单记录
     * @return 影响的行数
     */
    int saveSheet(ReceiptPO toSave);

    ReceiptPO getLatestSheet();

    List<String> findSheetIdByOperator(String operator);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);

    void approval(String receiptId, ReceiptSheetState state);

    ReceiptPO selectReceiptById(String id);
}
