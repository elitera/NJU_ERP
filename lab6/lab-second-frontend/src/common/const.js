// 部署url
export const REQUEST_BASE_URL_PROD = "http://ismzl.com/";
// 开发url （mock的地址
export const REQUEST_BASE_URL_DEV = "http://localhost:8080";

export const ROLE = {
  INVENTORY_MANAGER: "INVENTORY_MANAGER", //库存管理人员
  SALE_STAFF: "SALE_STAFF", // 进货销售人员
  SALE_MANAGER: "SALE_MANAGER", //销售经理
  FINANCIAL_STAFF: "FINANCIAL_STAFF", // 财务人员
  HR: "HR", // 人力资源人员
  GM: "GM", // 总经理
  ADMIN: "ADMIN" // 最高权限
};


export const PATH = {
  // INVENTORY_MANAGER
  COMMODITY_CLASSIFICATION: {
    path: '/commodityClassification',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  COMMODITY_MANAGEMENT: {
    path: '/commodityManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_MANAGEMENT: {
    path: '/inventoryManagement',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OPERATION: {
    path: '/inventoryOperation',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_CHECK: {
    path: '/inventoryCheck',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_IN: {
    path: '/inventoryIn',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OUT: {
    path: '/inventoryOut',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_LOSS: {
    path: '/inventoryLoss',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_WARNING: {
    path: '/inventoryWarning',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_OVERFLOW: {
    path: '/inventoryOverflow',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_PRESENT: {
    path: '/inventoryPresent',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },
  INVENTORY_VIEW: {
    path: '/inventoryView',
    requiresAuth: [ROLE.INVENTORY_MANAGER,ROLE.ADMIN]
  },

  // SALE_STAFF & SALE_MANAGER
  PURCHASE_VIEW: {
    path: '/purchaseView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  PURCHASE_RETURN_VIEW: {
    path: '/purchaseReturnView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  SALE_VIEW: {
    path: '/saleView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },
  CUSTOMER_VIEW: {
    path: '/customerView',
    requiresAuth: [ROLE.SALE_STAFF,ROLE.SALE_MANAGER,ROLE.GM,ROLE.ADMIN]
  },

  // GM
  GM_APPROVAL: {
    path: '/approval',
    requiresAuth: [ROLE.GM,ROLE.ADMIN]
  },

  // ADMIN
  ACCOUNT_MANAGEMENT: {
    path: '/accountManagement',
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  //FINANCIAL_STAFF
  FINANCE_VIEW: {
    path: '/financeView', // view
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  CHARGE_BILL_MAKE: {
    path: '/chargeBillMake', //制定收款单
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  PAYMENT_BILL_MAKE: {
    path: '/paymentBillMake', //制定付款单
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  CASH_BILL_MAKE: {
    path: '/cashBillMake', //制定付款单
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  SALARY_SHEET_MAKE: {
    path: '/salarySheetMake', //制定工资单
    requiresAuth: [ROLE.FINANCIAL_STAFF],
  },

  //HR
  EMPLOYEE_VIEW: {
    path: '/employeeView',
    requiresAuth: [ROLE.HR, ROLE.ADMIN]
  },
  EMPLOYEE_SALARY: {
    path: '/employeeSalary',
    requiresAuth: [ROLE.HR, ROLE.GM, ROLE.ADMIN]
  },
  EMPLOYEE_CLOCK_IN: {
    path: '/employeeClockIn',
    requiresAuth: [ROLE.HR, ROLE.ADMIN]
  },
  PUNCH:{
    path: '/punchForEmp',
    requiresAuth: [ROLE.ADMIN,ROLE.HR,ROLE.SALE_MANAGER,ROLE.INVENTORY_MANAGER,ROLE.FINANCIAL_STAFF,ROLE.SALE_STAFF]
  },

  //sheetView
  CONDITION_SHEET:{
    path: '/conditionSheet',
    requiresAuth: [ROLE.ADMIN,ROLE.GM, ROLE.FINANCIAL_STAFF]
  },
  PROGRESS_SHEET:{
    path: '/progressSheet',
    requiresAuth: [ROLE.ADMIN,ROLE.GM, ROLE.FINANCIAL_STAFF]
  },
  SALE_SHEET:{
    path: '/saleSheet',
    requiresAuth: [ROLE.ADMIN,ROLE.GM, ROLE.FINANCIAL_STAFF]
  },

  PROMOTION:{
    path: '/promotion',
    requiresAuth: [ROLE.ADMIN,ROLE.GM, ROLE.SALE_MANAGER, ROLE.SALE_STAFF]
  },
}
