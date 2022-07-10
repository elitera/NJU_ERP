//TEST
const TEST_GET = "/api/test/get";
const TEST_POST = "/api/test/post";

const AUTH = "/api/user/auth"
const LOGIN = "/api/user/login"
const REGISTER = '/api/user/register'

// 商品分类管理
const COMMODITY_CLASSIFICATION_ALL = '/api/category/queryAll'
const COMMODITY_CLASSIFICATION_CREATE = '/api/category/create'
const COMMODITY_CLASSIFICATION_UPDATE = '/api/category/update'
const COMMODITY_CLASSIFICATION_DELETE = '/api/category/delete'

// 商品管理
const COMMODITY_ALL = '/api/product/queryAll';
const COMMODITY_CREATE = '/api/product/create';
const COMMODITY_UPDATE = '/api/product/update';
const COMMODITY_DELETE = '/api/product/delete';


const WAREHOUSE_INPUT = '/api/warehouse/input';
const WAREHOUSE_OUTPUT_PRE = '/api/warehouse/product/count';
const WAREHOUSE_OUTPUT = '/api/warehouse/output';
const WAREHOUSE_GET_INPUTSHEET = '/api/warehouse/inputSheet/state';
const WAREHOUSE_GET_OUTPUTSHEET = '/api/warehouse/outputSheet/state';
const WAREHOUSE_IO_DEATIL_BY_TIME = '/api/warehouse/sheetContent/time';
const WAREHOUSE_IPQ_BY_TIME = '/api/warehouse/inputSheet/time/quantity';
const WAREHOUSE_OPQ_BY_TIME = '/api/warehouse/outputSheet/time/quantity';
const WAREHOUSE_OUTPUTSHEET_APPROVE = '/api/warehouse/outputSheet/approve';
const WAREHOUSE_INPUTSHEET_APPROVE = '/api/warehouse/inputSheet/approve';
const WAREHOUSE_DAILY_COUNT = '/api/warehouse/warehouse/counting';
const WAREHOUSE_GET_EXCEL = '/api/warehouse/warehouse/exportExcel';

// 销售管理
// 进货管理
const PURCHASE_ALL = '/api/purchase/sheet-show';
const PURCHASE_FIND_SHEET_BY_ID = '/api/purchase/find-sheet';
const PURCHASE_CREATE = '/api/purchase/sheet-make';
const PURCHASE_FIRST_APPROVAL = '/api/purchase/first-approval';
const PURCHASE_SECOND_APPROVAL = '/api/purchase/second-approval';
// 进货退货管理
const PURCHASE_RETURN_ALL = '/api/purchase-returns/sheet-show';
const PURCHASE_RETURN_CREATE = '/api/purchase-returns/sheet-make';
const PURCHASE_RETURN_FIRST_APPROVAL = '/api/purchase-returns/first-approval';
const PURCHASE_RETURN_SECOND_APPROVAL = '/api/purchase-returns/second-approval';
// 销售管理
const SALE_ALL = '/api/sale/sheet-show';
const SALE_CREATE = '/api/sale/sheet-make';
const SALE_FIRST_APPROVAL = '/api/sale/first-approval';
const SALE_SECOND_APPROVAL = '/api/sale/second-approval';
const SALE_FIND_SHEET_BY_ID = '/api/sale/find-sheet';
// 客户管理
const CUSTOMER_QUERY = '/api/customer/findByType';
const CUSTOMER_ADD = '/api/customer/add';
const CUSTOMER_UPDATE = '/api/customer/update';
const CUSTOMER_DELETE = '/api/customer/delete';
const SALE_PURCHASE_ALL = '/api/purchase/sheet-show';
const SALE_PURCHASE_CREATE = '/api/purchase/sheet-make';
const SALE_CUSTOMER_QUERY = '/api/customer/findByType';
const SALE_CUSTOMER_MAX = '/api/sale/maxAmountCustomer';

//账户管理
const ACCOUNT_QUERY = 'api/financial/show-all-account';//显示所有账户
const ACCOUNT_ADD = 'api/financial/addAccount';
const ACCOUNT_DELETE = 'api/financial/deleteAccount';
const ACCOUNT_EDIT = 'api/financial/updateAccount';
const ACCOUNT_SEARCH = 'api/financial/searchAccount';

//财务管理
const CHARGE_BILL_MAKE = 'api/financial/receipt-sheet-make';
const PAYMENT_BILL_MAKE = 'api/financial/payment-order-make';
const CASH_BILL_MAKE = 'api/financial/cash-expense-sheet';
const SALARY_SHEET_MAKE = 'api/financial/payroll-make';

//员工管理
const EMPLOYEE_SHOW = 'api/employee/show'
const EMPLOYEE_ADD = 'api/employee/add';
const EMPLOYEE_UPDATE = 'api/employee/update';
const EMPLOYEE_DELETE = 'api/employee/delete';
const ADD_PUNCH = 'api/employee/add-punch';
const SHOW_PUNCH ='api/employee/show-punch';
//促销策略
const ADD_PROMOTION_TOTAL = 'api/promotion/add-total';
const ADD_PROMOTION_CUSTOMER = 'api/promotion/add-customer';
const ADD_PROMOTION_SPECIAL = 'api/promotion/add-special';
const DELETE_PROMOTION = 'api/promotion/delete';
const SHOW_PROMOTION = 'api/promotion/show';

//单据查看
const SHOW_SALE_DETAILS_BY_SOME_CONDITIONS = 'api/financial/show-sale-details';
const SHOW_BUSINESS_HISTORY_BY_SOME_CONDITIONS = 'api/financial/show-business-history';
const SHOW_STATEMENT_OF_OPERATION = 'api/financial/show-operation-statement';

//审核管理
const RECEIPT_APPROVAL = 'api/financial/receipt-approval';
const CASH_EXPENSE_APPROVAL = 'api/financial/cash-expense-approval';
const PAYMENT_ORDER_APPROVAL = 'api/financial/payment-order-approval';
const RECEIPT_SHEET_BY_ID = 'api/financial/receipt-sheet-by-id';
const PAYMENT_ORDER_BY_ID = 'api/financial/payment-order-by-id';
const PAYROLL_BY_ID = 'api/financial/payroll-by-id';
const PAYROLL_APPROVAL ='api/financial//payroll-approval';

module.exports = {
  TEST_GET,
  TEST_POST,

  AUTH,
  LOGIN,
  REGISTER,
  COMMODITY_CLASSIFICATION_ALL,
  COMMODITY_CLASSIFICATION_CREATE,
  COMMODITY_CLASSIFICATION_UPDATE,
  COMMODITY_CLASSIFICATION_DELETE,

  COMMODITY_ALL,
  COMMODITY_CREATE,
  COMMODITY_UPDATE,
  COMMODITY_DELETE,

  WAREHOUSE_INPUT,
  WAREHOUSE_OUTPUT_PRE,
  WAREHOUSE_OUTPUT,
  WAREHOUSE_GET_INPUTSHEET,
  WAREHOUSE_GET_OUTPUTSHEET,
  WAREHOUSE_IO_DEATIL_BY_TIME,
  WAREHOUSE_IPQ_BY_TIME,
  WAREHOUSE_OPQ_BY_TIME,
  WAREHOUSE_OUTPUTSHEET_APPROVE,
  WAREHOUSE_INPUTSHEET_APPROVE,
  WAREHOUSE_DAILY_COUNT,
  WAREHOUSE_GET_EXCEL,

  PURCHASE_ALL,
  PURCHASE_CREATE,
  PURCHASE_FIRST_APPROVAL,
  PURCHASE_SECOND_APPROVAL,
  PURCHASE_RETURN_ALL,
  PURCHASE_RETURN_CREATE,
  PURCHASE_RETURN_FIRST_APPROVAL,
  PURCHASE_RETURN_SECOND_APPROVAL,
  PURCHASE_FIND_SHEET_BY_ID,

  SALE_ALL,
  SALE_CREATE,
  SALE_FIRST_APPROVAL,
  SALE_SECOND_APPROVAL,
  SALE_CUSTOMER_QUERY,
  SALE_CUSTOMER_MAX,
  SALE_FIND_SHEET_BY_ID,
  CUSTOMER_QUERY,
  CUSTOMER_ADD,
  CUSTOMER_UPDATE,
  CUSTOMER_DELETE,

  // 账户管理
  ACCOUNT_QUERY,
  ACCOUNT_ADD,
  ACCOUNT_DELETE,
  ACCOUNT_EDIT,
  ACCOUNT_SEARCH,

  // 财务人员
  CHARGE_BILL_MAKE,
  PAYMENT_BILL_MAKE,
  CASH_BILL_MAKE,
  SALARY_SHEET_MAKE,

  EMPLOYEE_SHOW,
  EMPLOYEE_UPDATE,
  EMPLOYEE_ADD,
  EMPLOYEE_DELETE,
  ADD_PUNCH,
  SHOW_PUNCH,

  ADD_PROMOTION_SPECIAL,
  ADD_PROMOTION_CUSTOMER,
  ADD_PROMOTION_TOTAL,
  DELETE_PROMOTION,
  SHOW_PROMOTION,

  SHOW_SALE_DETAILS_BY_SOME_CONDITIONS,
  SHOW_BUSINESS_HISTORY_BY_SOME_CONDITIONS,
  SHOW_STATEMENT_OF_OPERATION,

  RECEIPT_APPROVAL,
  CASH_EXPENSE_APPROVAL,
  PAYMENT_ORDER_APPROVAL,

  RECEIPT_SHEET_BY_ID,
  PAYMENT_ORDER_BY_ID,
  PAYROLL_BY_ID,
  PAYROLL_APPROVAL
};
