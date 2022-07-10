package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.BankAccountDao;
import com.nju.edu.erp.model.po.BankAccountPO;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.*;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.Date;
import java.util.List;

public interface FinancialService {

//    /**
//     * 增加银行账户
//     * @param bankAccountVO
//     */
//    void addBankAccount(BankAccountVO bankAccountVO);
//
//    /**
//     * 依据账户名称删除银行账户
//     * @param name
//     */
//    void deleteBankAccount(String name);
//
//    /**
//     * 依据账户ID修改银行账户
//     * @param bankAccountVO
//     */
//    void updateBankAccount(BankAccountVO bankAccountVO);
//
//    /**
//     * 查询账户
//     * @param name
//     */
//    List<BankAccountPO> selectBankAccount(String name);

//    /**
//     * 制定收款单
//     * @param userVO
//     * @param receiptVO
//     */
//    void makeReceipt(UserVO userVO, ReceiptVO receiptVO);

//    /**
//     * 制定付款单
//     * @param userVO
//     * @param paymentOrderVO
//     */
//    void makePaymentOrder(UserVO userVO, PaymentOrderVO paymentOrderVO);

//    /**
//     * 制定现金费用单
//     * @param userVO
//     * @param cashExpenseSheetVO
//     */
//    void makeCashExpenseSheet(UserVO userVO, CashExpenseSheetVO cashExpenseSheetVO);

//    /**
//     * 展示所有银行账户
//     */
//    List<BankAccountVO> showAllBankAccount();

//    void makePayroll(UserVO userVO, PayrollVO payrollVO);

    List<String> findPaymentOrderSheetIdByOperator(String operator);

    List<String> findReceiptSheetIdByOperator(String operator);

    List<String> findPayrollSheetIdByOperator(String operator);

    List<String> findPaymentOrderSheetIdByTime(Date beginDate, Date endDate);

    List<String> findReceiptSheetIdByTime(Date beginDate, Date endDate);

    List<String> findPayrollSheetIdByTime(Date beginDate, Date endDate);

//    List<String> findReceiptSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

//    List<String> findPaymentOrderSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

//    List<String> findCashExpenseSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

//    List<String> findPayrollSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO);

//    List<SalesDetailsSheetVO> showSaleDetailsByTime(String beginDateStr, String endDateStr);
//
//    List<SalesDetailsSheetVO> showSaleDetailsByName(String name);
//
//    List<SalesDetailsSheetVO> showSaleDetailsByOperator(String operator);
//
//    List<SalesDetailsSheetVO> showSaleDetailsByCustomer(String customer);
//
//    /**
//     * 按照业务员查看经营历程表
//     * @param operator
//     * @return 各类单据的编号
//     */
//    List<String> showBusinessHistoryByOperator(String operator);
}
