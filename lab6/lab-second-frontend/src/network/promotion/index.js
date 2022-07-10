import request from "@/network/request"
const testAPI = require("@/apis")


export const addPromotionTotal = config => request._post(testAPI.ADD_PROMOTION_TOTAL, config)
export const addPromotionCustomer = config => request._post(testAPI.ADD_PROMOTION_CUSTOMER, config)
export const addPromotionSpecial = config => request._post(testAPI.ADD_PROMOTION_SPECIAL, config)
export const deletePromotion = config => request._get(testAPI.DELETE_PROMOTION, config)
export const showPromotion = config => request._get(testAPI.SHOW_PROMOTION, config)

