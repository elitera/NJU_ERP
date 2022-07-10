import request from "@/network/request";
const testAPI = require("@/apis")

export const queryAccount = config => request._post(testAPI.ACCOUNT_QUERY, config);
export const showDetail = config => request._post(testAPI.SHOW_SALE_DETAILS_BY_SOME_CONDITIONS, config);
export const showHistory = config => request._post(testAPI.SHOW_BUSINESS_HISTORY_BY_SOME_CONDITIONS, config);
export const showStatement = config => request._get(testAPI.SHOW_STATEMENT_OF_OPERATION, config);

export const accountAdd = config => request._post(testAPI.ACCOUNT_ADD, config);
export const accountDelete = config => request._get(testAPI.ACCOUNT_DELETE, config);
export const accountEdit = config => request._post(testAPI.ACCOUNT_EDIT, config);
export const accountSearch = config => request._get(testAPI.ACCOUNT_SEARCH, config);

export const chargeBillMake = config => request._post(testAPI.CHARGE_BILL_MAKE, config);
export const paymentBillMake = config => request._post(testAPI.PAYMENT_BILL_MAKE, config);
export const cashBillMake = config => request._post(testAPI.CASH_BILL_MAKE, config);

export const receiptApproval = config => request._get(testAPI.RECEIPT_APPROVAL, config);
export const cashExpenseApproval = config => request._get(testAPI.CASH_EXPENSE_APPROVAL, config);
export const paymentOrderApproval = config => request._get(testAPI.PAYMENT_ORDER_APPROVAL, config);
export const payrollById = config => request._get(testAPI.PAYROLL_BY_ID, config);
export const paymentOrderById = config => request._get(testAPI.PAYMENT_ORDER_BY_ID, config);
export const receiptSheetById = config => request._get(testAPI.RECEIPT_SHEET_BY_ID, config);
export const payrollApproval = config => request._get(testAPI.PAYROLL_APPROVAL, config)
export const salarySheetMake = config => request._post(testAPI.SALARY_SHEET_MAKE, config);
