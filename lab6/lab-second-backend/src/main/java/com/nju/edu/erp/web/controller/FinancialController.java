package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.*;
import com.nju.edu.erp.model.vo.Financial.*;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/financial")
public class FinancialController {

    private final AccountService accountService;

    private final PaymentOrderService paymentOrderService;

    private final ReceiptService receiptService;

    private final PayrollService payrollService;

    private final CashExpenseService cashExpenseService;

    private final BusinessHistoryService businessHistoryService;

    private final OperationStatementService operationStatementService;

    private final SaleDetailsService saleDetailsService;

    private final OpeningAccountService openingAccountService;


    @Autowired
    public FinancialController(AccountService accountService, PaymentOrderService paymentOrderService, ReceiptService receiptService,
                               PayrollService payrollService, CashExpenseService cashExpenseService,
                               BusinessHistoryService businessHistoryService, OperationStatementService operationStatementService,
                               SaleDetailsService saleDetailsService, OpeningAccountService openingAccountService) {
        this.accountService = accountService;
        this.paymentOrderService = paymentOrderService;
        this.receiptService = receiptService;
        this.payrollService = payrollService;
        this.cashExpenseService = cashExpenseService;
        this.businessHistoryService = businessHistoryService;
        this.operationStatementService = operationStatementService;
        this.saleDetailsService = saleDetailsService;
        this.openingAccountService = openingAccountService;
    }

    /**
     * ??????????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/addAccount")
    public Response addBankAccount(@RequestBody BankAccountVO bankAccount)  {
        accountService.addBankAccount(bankAccount);
        return Response.buildSuccess();
    }

    /**
     * ????????????????????????????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping(value = "/deleteAccount")
    public Response deleteBankAccount(@RequestParam(value = "name", required = false) String name)  {
        accountService.deleteBankAccount(name);
        return Response.buildSuccess();
    }

    /**
     * ??????????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/updateAccount")
    public Response updateBankAccount(@RequestBody BankAccountVO bankAccount)  {
        accountService.updateBankAccount(bankAccount);
        return Response.buildSuccess();
    }

    /**
     * ??????????????????????????????????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping(value = "/searchAccount")
    public Response selectBankAccount(@RequestParam(value = "name", required = false) String name)  {
        return Response.buildSuccess(accountService.selectBankAccount(name));
    }

    /**
     * ???????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/receipt-sheet-make")
    public Response makeReceipt(UserVO userVO, @RequestBody ReceiptVO receiptVO)  {
        receiptService.makeReceipt(userVO, receiptVO);
        return Response.buildSuccess();
    }

    /**
     * ??????id???????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/receipt-sheet-by-id")
    public Response showReceiptById(@RequestParam(value = "id", required = true) String id)  {
        ReceiptVO result = receiptService.showReceiptById(id);
        return Response.buildSuccess(result);
    }

    /**
     * ???????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/payment-order-make")
    public Response makePaymentOrder(UserVO userVO, @RequestBody PaymentOrderVO paymentOrderVO)  {
        paymentOrderService.makePaymentOrder(userVO, paymentOrderVO);
        return Response.buildSuccess();
    }

    /**
     * ??????id???????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/payment-order-by-id")
    public Response showPaymentOrderById(@RequestParam(value = "id", required = true) String id)  {
        PaymentOrderVO result = paymentOrderService.showPaymentOrderById(id);
        return Response.buildSuccess(result);
    }

    /**
     * ?????????????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/cash-expense-sheet")
    public Response makeCashExpenseSheet(UserVO userVO, @RequestBody CashExpenseSheetVO cashExpenseSheetVO)  {
        cashExpenseService.makeCashExpenseSheet(userVO, cashExpenseSheetVO);
        return Response.buildSuccess();
    }

    /**
     * ?????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/cash-expense-by-id")
    public Response showCashExpenseSheetById(@RequestParam(value = "id", required = true) String id)  {
        CashExpenseSheetVO result = cashExpenseService.showCashExpenseById(id);
        return Response.buildSuccess(result);
    }

    /**
     * ????????????????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/show-all-account")
    public Response showAllBankAccount()  {
        return Response.buildSuccess(accountService.showAllBankAccount());
    }

    /**
     * ???????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/payroll-make")
    public Response makePayroll(UserVO userVO, @RequestBody PayrollVO payrollVO)  {
        payrollService.makePayroll(userVO, payrollVO);
        return Response.buildSuccess();
    }

    /**
     * ???????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @PostMapping(value = "/payroll-by-id")
    public Response showPayrollById(@RequestParam(value = "id", required = true) String id)  {
        PayrollVO payrollVO = payrollService.showPayrollById(id);
        return Response.buildSuccess(payrollVO);
    }

    /**
     * ???????????????????????????????????????
     * @param searchCriteria ????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN, Role.GM})
    @PostMapping(value = "/show-sale-details")
    public Response showSaleDetailsBySomeConditions(@RequestBody SaleDetailsSheetSearchCriteriaVO searchCriteria)  {
        return Response.buildSuccess(saleDetailsService.showSaleDetailsBySomeConditions(searchCriteria));
    }

    /**
     * ???????????????????????????????????????
     * @param searchCriteria ????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN, Role.GM})
    @PostMapping(value = "/show-business-history")
    public Response showBusinessHistoryBySomeConditions(@RequestBody BusinessHistorySheetSearchCriteriaVO searchCriteria)  {
        return Response.buildSuccess(businessHistoryService.showBusinessHistoryBySomeConditions(searchCriteria));
    }

    /**
     * ???????????????????????????????????????
     * @param beginDateStr ????????????????????? ????????????yyyy-MM-dd HH:mm:ss????????????2022-05-12 11:38:30???
     * @param endDateStr ????????????????????? ????????????yyyy-MM-dd HH:mm:ss????????????2022-05-12 11:38:30???
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN, Role.GM})
    @GetMapping(value = "/show-operation-statement")
    public Response showStatementOfOperation(@RequestParam(value = "begin", required = true) String beginDateStr, @RequestParam(value = "end", required = true) String endDateStr) {
        return Response.buildSuccess(operationStatementService.showStatementOfOperation(beginDateStr, endDateStr));
    }

    /**
     * ????????????
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF})
    @PostMapping(value = "/opening-account-establishment")
    public Response openingAccountEstablishment() {
        OpeningAccountVO openingAccountVO = openingAccountService.openingAccountEstablishment();
        return Response.buildSuccess(openingAccountVO);
    }

    /**
     * ????????????????????????
     * @param payrollId ?????????id
     * @param state ??????????????????("????????????"/"????????????")
     */
    @GetMapping(value = "/payroll-approval")
    @Authorized (roles = {Role.SALE_MANAGER, Role.ADMIN})
    public Response payrollApproval(@RequestParam("payrollId") String payrollId,
                                  @RequestParam("state") PayrollState state)  {
        if(state.equals(PayrollState.FAILURE) || state.equals(PayrollState.SUCCESS)) {
            payrollService.approval(payrollId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","????????????"); // code???????????????????????????
        }
    }

    /**
     * ????????????????????????
     * @param paymentOrderId ?????????id
     * @param state ??????????????????("????????????"/"????????????")
     */
    @GetMapping( "/payment-order-approval")
    @Authorized (roles = {Role.GM, Role.ADMIN})
    public Response paymentOrderApproval(@RequestParam(value = "paymentOrderId") String paymentOrderId,
                                    @RequestParam(value = "state") PaymentSheetState state)  {
        if(state.equals(PaymentSheetState.FAILURE) || state.equals(PaymentSheetState.SUCCESS)) {
            paymentOrderService.approval(paymentOrderId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","????????????"); // code???????????????????????????
        }
    }

    /**
     * ????????????????????????
     * @param receiptId ?????????id
     * @param state ??????????????????("????????????"/"????????????")
     */
    @GetMapping("/receipt-approval")
    @Authorized (roles = {Role.GM, Role.ADMIN})
    public Response receiptApproval(@RequestParam(value = "receiptId") String receiptId,
                                         @RequestParam(value = "state") ReceiptSheetState state)  {
        if(state.equals(ReceiptSheetState.FAILURE) || state.equals(ReceiptSheetState.SUCCESS)) {
            receiptService.approval(receiptId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","????????????"); // code???????????????????????????
        }
    }

    /**
     * ??????????????????????????????
     * @param cashExpenseId ???????????????id
     * @param state ??????????????????("????????????"/"????????????")
     */
    @GetMapping( "/cash-expense-approval")
    @Authorized (roles = {Role.GM, Role.ADMIN})
    public Response cashExpenseApproval(@RequestParam("cashExpenseId") String cashExpenseId,
                                        @RequestParam("state") CashExpenseSheetState state)  {
        if(state.equals(CashExpenseSheetState.FAILURE) || state.equals(CashExpenseSheetState.SUCCESS)) {
            cashExpenseService.approval(cashExpenseId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","????????????"); // code???????????????????????????
        }
    }
}
