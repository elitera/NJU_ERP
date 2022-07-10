import Vue from "vue";
import VueRouter from "vue-router";
import { ROLE, PATH } from "../common/const";

// import ChargeBillMake from "@/views/finance/ChargeBillMake";
// import PaymentBillMake from "@/views/finance/PaymentBillMake";
// import CashBillMake from "@/views/finance/CashBillMake";
// import ConditionSheet from "@/views/sheetView/conditionSheet";
// import ProgressSheet from "@/views/sheetView/progressSheet";
// import SaleSheet from "@/views/sheetView/saleSheet";


const Error = () => import("../components/content/Error");
const Login = () => import("../views/auth/Login");
const Register = () => import("../views/auth/register");
const Home = () => import("../views/Home");
const CommodityManagement = () =>
  import("../views/commodity/CommodityManagement");
const CommodityClassification = () =>
  import("../views/commodity/CommodityClassification");
const InventoryManagement = () => import("../views/inventory/InventoryManagement");
const InventoryCheck = () => import("../views/inventory/InventoryCheck");
const InventoryOperation = () =>
  import("../views/inventory/InventoryOperation");
const InventoryIn = () => import("../views/inventory/InventoryIn");
const InventoryOut = () => import("../views/inventory/InventoryOut");
const InventoryLoss = () => import("../views/inventory/InventoryLoss");
const InventoryOverflow = () => import("../views/inventory/InventoryOverflow");
const InventoryPresent = () => import("../views/inventory/InventoryPresent");
const InventoryView = () => import("../views/inventory/InventoryView");
const InventoryWarning = () => import("../views/inventory/InventoryWarning");
const PurchaseView = () => import("../views/purchase/PurchaseView");
const PurchaseReturnView = () => import("../views/purchase/PurchaseReturnView");
const EmployeeClockIn = () => import("../views/HumanResource/EmployeeClockIn");
const EmployeeSalary = () => import("../views/HumanResource/EmployeeSalary");
const EmployeeView = () => import("../views/HumanResource/EmployeeView");
const SaleView = () => import("../views/sale/SaleView");
const CustomerView = () => import ("../views/purchase/CustomerView");
const Approval = () => import("../views/approval/Approval");
const PUNCH = () => import("../views/HumanResource/punchForEmp");
const FinanceView = () => import("../views/finance/FinanceView");
const AccountManagement = () => import("../views/finance/AccountManagement");
const Promotion = () => import("../views/purchase/promotion");
const ChargeBillMake = () => import("../views/finance/ChargeBillMake");
const PaymentBillMake = () => import("../views/finance/PaymentBillMake");
const CashBillMake = () => import("../views/finance/CashBillMake");
const ConditionSheet = () => import("@/views/sheetView/conditionSheet");
const ProgressSheet = () => import ("@/views/sheetView/progressSheet");
const SaleSheet = () => import("@/views/sheetView/saleSheet");
const SalarySheetMake = () => import("@/views/finance/SalarySheetMake");



Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Home
  },
  {
    path: "/error",
    component: Error
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/register",
    //component: () => import("@/views/auth/register.vue")
    component: Register
  },
  // 商品管理
  {
    path: PATH.COMMODITY_CLASSIFICATION.path,
    component: CommodityClassification,
    meta: { requiresAuth: PATH.COMMODITY_CLASSIFICATION.requiresAuth }
  },
  {
    path: PATH.COMMODITY_MANAGEMENT.path,
    component: CommodityManagement,
    meta: { requiresAuth: PATH.COMMODITY_MANAGEMENT.requiresAuth }
  },
  // 库存管理
  {
    path: PATH.INVENTORY_MANAGEMENT.path,
    component: InventoryManagement,
    meta: { requiresAuth: PATH.INVENTORY_MANAGEMENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_CHECK.path,
    component: InventoryCheck,
    meta: { requiresAuth: PATH.INVENTORY_CHECK.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OPERATION.path,
    component: InventoryOperation,
    name: "InventoryOperation",
    meta: { requiresAuth: PATH.INVENTORY_OPERATION.requiresAuth }
  },
  {
    path: PATH.INVENTORY_IN.path,
    component: InventoryIn,
    name: "InventoryIn",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OUT.path,
    component: InventoryOut,
    name: "InventoryOut",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_LOSS.path,
    component: InventoryLoss,
    name: "InventoryLoss",
    meta: { requiresAuth: PATH.INVENTORY_LOSS.requiresAuth }
  },
  {
    path: PATH.INVENTORY_OVERFLOW.path,
    component: InventoryOverflow,
    name: "InventoryOverflow",
    meta: { requiresAuth: PATH.INVENTORY_OVERFLOW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_PRESENT.path,
    component: InventoryPresent,
    name: "InventoryPresent",
    meta: { requiresAuth: PATH.INVENTORY_PRESENT.requiresAuth }
  },
  {
    path: PATH.INVENTORY_VIEW.path,
    component: InventoryView,
    meta: { requiresAuth: PATH.INVENTORY_VIEW.requiresAuth }
  },
  {
    path: PATH.INVENTORY_WARNING.path,
    component: InventoryWarning,
    name: "InventoryWarning",
    meta: { requiresAuth: PATH.INVENTORY_WARNING.requiresAuth }
  },
  // 销售管理
  {
    path: PATH.PURCHASE_VIEW.path,
    component: PurchaseView,
    name: "PurchaseView",
    meta: { requiresAuth: PATH.PURCHASE_VIEW.requiresAuth }
  },
  {
    path: PATH.PURCHASE_RETURN_VIEW.path,
    component: PurchaseReturnView,
    name: "PurchaseReturnView",
    meta: { requiresAuth: PATH.PURCHASE_RETURN_VIEW.requiresAuth }
  },
  {
    path: PATH.SALE_VIEW.path,
    component: SaleView,
    name: "SaleView",
    meta: { requiresAuth: PATH.SALE_VIEW.requiresAuth }
  },
  {
    path: PATH.CUSTOMER_VIEW.path,
    component: CustomerView,
    name: "CustomerView",
    meta: { requiresAuth: PATH.CUSTOMER_VIEW.requiresAuth }
  },
  // 审批
  {
    path: PATH.GM_APPROVAL.path,
    component: Approval,
    meta: { requiresAuth: PATH.GM_APPROVAL.requiresAuth }
  },

  //账户管理
  {
    path: PATH.ACCOUNT_MANAGEMENT.path,
    component: AccountManagement,
    meta: { requiresAuth: PATH.ACCOUNT_MANAGEMENT.requiresAuth }
  },

  // 财务人员
  {
    path: PATH.FINANCE_VIEW.path,
    component: FinanceView,
    meta: { requiresAuth: PATH.FINANCE_VIEW.requiresAuth }
  },

  {
    path: PATH.CHARGE_BILL_MAKE.path,
    component: ChargeBillMake,
    meta: { requiresAuth: PATH.CHARGE_BILL_MAKE.requiresAuth }
  },

  {
    path: PATH.PAYMENT_BILL_MAKE.path,
    component: PaymentBillMake,
    meta: { requiresAuth: PATH.PAYMENT_BILL_MAKE.requiresAuth }
  },

  {
    path: PATH.CASH_BILL_MAKE.path,
    component: CashBillMake,
    meta: { requiresAuth: PATH.CASH_BILL_MAKE.requiresAuth }
  },

  {
    path: PATH.SALARY_SHEET_MAKE.path,
    component: SalarySheetMake,
    meta: { requiresAuth: PATH.SALARY_SHEET_MAKE.requiresAuth }
  },


  //人力资源
  {
    path: PATH.EMPLOYEE_VIEW.path,
    component: EmployeeView,
    meta: {requiresAuth: PATH.EMPLOYEE_VIEW.requiresAuth }
  },
  {
    path: PATH.EMPLOYEE_SALARY.path,
    component: EmployeeSalary,
    meta: {requiresAuth: PATH.EMPLOYEE_SALARY.requiresAuth }
  },{
    path: PATH.EMPLOYEE_CLOCK_IN.path,
    component: EmployeeClockIn,
    meta: {requiresAuth: PATH.EMPLOYEE_CLOCK_IN.requiresAuth }
  },{
    path: PATH.PUNCH.path,
    component: PUNCH,
    meta: {requiresAuth: PATH.PUNCH.requiresAuth }
  },

    //表单查看
  {
    path: PATH.CONDITION_SHEET.path,
    component: ConditionSheet,
    meta: {requiresAuth: PATH.CONDITION_SHEET.requiresAuth }
  },
  {
    path: PATH.PROGRESS_SHEET.path,
    component: ProgressSheet,
    meta: {requiresAuth: PATH.PROGRESS_SHEET.requiresAuth }
  },
  {
    path: PATH.SALE_SHEET.path,
    component: SaleSheet,
    meta: {requiresAuth: PATH.SALE_SHEET.requiresAuth }
  },

  {
    path: PATH.PROMOTION.path,
    component: Promotion,
    meta: {requiresAuth: PATH.PROMOTION.requiresAuth}
  },

  // -----------------------未找到页面-----------------------------
  {
    path: "*",
    redirect: "/error"
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
  next();
  return;
  console.log("你爹来咯")
  console.log(to.path);
  if (to.path === "/error" || to.path === "/login") {
    next();
  } else if (to.path === "/") {
    let token = sessionStorage.getItem("token");
    let role = sessionStorage.getItem("role");
    console.log(role)
    if (token == null || role == null) next("/login");
    else next();
  } else if (to.meta.requiresAuth) {
    //console.log(to.path)
    if (
      to.meta.requiresAuth.some(
        role => role.toString() === sessionStorage.getItem("role")
      )
    ) {
      //有权限
      // console.log("获得访问权限");
      next()
    } else {
      // console.log("无权限访问");
      next("/"); //无权限,跳回主页
    }
  } else {
    // 非法路径, 直接next (跳转error)
    next();
  }
});

export default router;
