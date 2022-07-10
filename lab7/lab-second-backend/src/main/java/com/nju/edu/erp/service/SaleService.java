package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.po.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.po.SaleDetailsSheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SaleService {

    /**
     * 指定销售单
     * @param userVO
     * @param saleSheetVO
     */
    void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO);

    /**
     * 根据单据状态获取销售单
     * @param state
     * @return
     */
    List<SaleSheetVO> getSaleSheetByState(SaleSheetState state);

    /**
     * 审批单据
     * @param saleSheetId
     * @param state
     */
    void approval(String saleSheetId, SaleSheetState state);

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return
     */
    CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr);

    /**
     * 根据销售单Id搜索销售单信息
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    SaleSheetVO getSaleSheetById(String saleSheetId);

//    /**
//     * 获取某个销售人员某段时间内的销售单
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
     * 获取某个销售人员某段时间内的销售单
     * @param searchCriteria 搜索条件
     * @return 符合条件的销售明细表列表
     */
    List<SalesDetailsSheetVO> findSaleSheetsBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria);

    List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO);
}
