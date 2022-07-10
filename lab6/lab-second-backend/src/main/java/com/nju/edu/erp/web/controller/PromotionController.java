package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.promotion.CombinePromotionVO;
import com.nju.edu.erp.model.vo.promotion.CustomerPromotionVO;
import com.nju.edu.erp.model.vo.promotion.TotalPromotionVO;
import com.nju.edu.erp.service.strategy.PromotionStrategy;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/promotion")
public class PromotionController {

    private final PromotionStrategy promotionStrategy;

    @Autowired
    public PromotionController(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    @PostMapping("/add-total")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response addTotalPromotion(@RequestBody TotalPromotionVO promotionVO) {
        promotionStrategy.addPromotion("total", promotionVO);
        return Response.buildSuccess();
    }

    @PostMapping("/add-customer")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response addCustomerPromotion(@RequestBody CustomerPromotionVO promotionVO) {
        promotionStrategy.addPromotion("customer", promotionVO);
        return Response.buildSuccess();
    }

    @PostMapping("/add-combine")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response addCombinePromotion(@RequestBody CombinePromotionVO promotionVO) {
        promotionStrategy.addPromotion("combine", promotionVO);
        return Response.buildSuccess();
    }


    @GetMapping("/delete")
    @Authorized(roles = {Role.GM, Role.ADMIN})
    public Response deletePromotion(@RequestParam("promotionType") String promotionType, @RequestParam("promotionId") int promotionId) {
        promotionStrategy.deletePromotionById(promotionType, promotionId);
        return Response.buildSuccess();
    }

    @GetMapping("/show")
    @Authorized(roles = {Role.GM, Role.SALE_STAFF, Role.SALE_MANAGER, Role.ADMIN})
    public Response showPromotion(@RequestParam("promotionType") String promotionType) {
        return Response.buildSuccess(promotionStrategy.getPromotions(promotionType));
    }
}