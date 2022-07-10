package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.SaleDetailsSheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;

import java.util.Date;
import java.util.List;

// 制定销售退货单 + 销售经理审批/总经理二级审批 + 更新客户表 + 更新库存
public interface SaleReturnsService {
    /**
     * 制定销售退货单
     *
     * @param saleReturnsSheetVO 销售退货单
     */
    void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO);

    /**
     * 根据状态获取销售退货单(state == null 则获取所有销售退货单)
     *
     * @param state 销售退货单状态
     * @return 销售退货单
     */
    List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state);

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单id
     * @param state                  销售退货单修改后的状态
     */
    void approval(String saleReturnsSheetId, SaleReturnsSheetState state);

//    /**
//     * 获取某个销售人员某段时间内的销售退货单
//     * @param beginDate 开始时间
//     * @param endDate 结束时间
//     * @return
//     */
//    List<SalesDetailsSheetVO> getSalesSheetsByTime(Date beginDate, Date endDate);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByName(String name);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByOperator(String operator);
//
//    List<SalesDetailsSheetVO> getSalesSheetsByCustomer(String customer);

    List<String> findSheetIdByOperator(String operator);

    List<String> findSheetIdByTime(Date beginDate, Date endDate);

    /**
     * 获取某个销售人员某段时间内的销售退货单
     * @param searchCriteria 搜索条件
     * @return 符合条件的销售明细表列表
     */
    List<SalesDetailsSheetVO> findSaleSheetsBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

    SaleReturnsSheetVO findSheetIdById(String id);
}