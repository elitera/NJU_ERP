import request from "@/network/request";
const testAPI = require("@/apis")

export const employeeShow = config => request._get(testAPI.EMPLOYEE_SHOW, config);
export const employeeAdd = config => request._post(testAPI.EMPLOYEE_ADD, config);
export const employeeUpdate = config => request._post(testAPI.EMPLOYEE_UPDATE, config);
export const employeeDelete = config => request._get(testAPI.EMPLOYEE_DELETE, config);
export const addPunch = config => request._post(testAPI.ADD_PUNCH, config);
export const showPunch = config => request._get(testAPI.SHOW_PUNCH, config);

