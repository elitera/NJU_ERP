package com.nju.edu.erp.dao;


import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface SaleSheetDao {

    /**
     * 获取最近一条销售单
     * @return
     */
    SaleSheetPO getLatestSheet();

    /**
     * 存入一条销售单记录
     * @param toSave 一条销售单记录
     * @return 影响的行数
     */
    int saveSheet(SaleSheetPO toSave);

    /**
     * 把销售单上的具体内容存入数据库
     * @param saleSheetContent 入销售单上的具体内容
     */
    int saveBatchSheetContent(List<SaleSheetContentPO> saleSheetContent);

    /**
     * 查找所有销售单
     */
    List<SaleSheetPO> findAllSheet();

    /**
     * 查找指定id的销售单
     * @param id
     * @return
     */
    SaleSheetPO findSheetById(String id);

    List<SaleSheetPO> findSheetByState(SaleSheetState state);

    List<String> findSheetIdByOperator(String operator);

    /**
     * 查找指定销售单下具体的商品内容
     * @param sheetId
     */
    List<SaleSheetContentPO> findContentBySheetId(String sheetId);

    /**
     * 更新指定销售单的状态
     * @param sheetId
     * @param state
     * @return
     */
    int updateSheetState(String sheetId, SaleSheetState state);

    /**
     * 根据当前状态更新销售单状态
     * @param sheetId
     * @param prev
     * @param state
     * @return
     */
    int updateSheetStateOnPrev(String sheetId, SaleSheetState prev, SaleSheetState state);


    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman, Date beginTime,Date endTime);

//    List<SalesDetailsSheetVO> getSalesSheetsByTime(Date beginDate, Date endDate);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByName(String name);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByOperator(String operator);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByCustomer(String customer);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    /**
     * 查询符合条件的经营历程表
     * @param searchCriteria
     * @return
     */
    List<SalesDetailsSheetVO> findSaleSheetBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria);

    /**
     * 查询符合条件的销售出货单（此DAO对应单据）
     * @param searchCriteriaPO
     * @return
     */
    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);
}
