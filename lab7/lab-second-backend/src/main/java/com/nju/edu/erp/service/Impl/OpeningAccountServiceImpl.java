package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.Financial.OpeningAccountVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OpeningAccountServiceImpl implements OpeningAccountService {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final FinancialService financialService;

    private final CustomerService customerService;

    private final PurchaseService purchaseService;

    private final PurchaseReturnsService purchaseReturnsService;

    private final SaleService saleService;

    private final SaleReturnsService saleReturnsService;

    private final AccountService accountService;

    @Autowired
    OpeningAccountServiceImpl(CategoryService categoryService, ProductService productService, FinancialService financialService,
                              CustomerService customerService, PurchaseService purchaseService, PurchaseReturnsService purchaseReturnsService, SaleService saleService, SaleReturnsService saleReturnsService, AccountService accountService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.financialService = financialService;
        this.customerService = customerService;
        this.purchaseService = purchaseService;
        this.purchaseReturnsService = purchaseReturnsService;
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
        this.accountService = accountService;
    }

    @Override
    public OpeningAccountVO openingAccountEstablishment() {
        OpeningAccountVO openingAccountVO = new OpeningAccountVO();
        openingAccountVO.setBankAccountVOS(accountService.showAllBankAccount());
        openingAccountVO.setCategoryVOS(categoryService.queryAllCategory());
        openingAccountVO.setCustomerVOS(customerService.queryAllCustomer());
        List<ProductInfoVO> productInfoVOS = productService.queryAllProduct();
        Date now = new Date(System.currentTimeMillis());
        Date yearBefore = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 365);
        for (ProductInfoVO productInfoVO : productInfoVOS) {
            productInfoVO.setRecentRp(null);
            productInfoVO.setRecentPp(null);
            String productInfoVOId = productInfoVO.getId();
            BigDecimal totalPp = BigDecimal.ZERO;
            BigDecimal totalRp = BigDecimal.ZERO;
            int PpCount = 0, RpCount = 0;
            for (String id : purchaseService.findSheetIdByTime(yearBefore, now)) {
                PurchaseSheetVO purchaseSheetVO = purchaseService.getPurchaseSheetById(id);
                for (PurchaseSheetContentVO purchaseSheetContentVO : purchaseSheetVO.getPurchaseSheetContent()) {
                    if (Objects.equals(purchaseSheetContentVO.getPid(), productInfoVOId)) {
                        totalPp = totalPp.add(purchaseSheetContentVO.getTotalPrice());
                        PpCount += purchaseSheetContentVO.getQuantity();
                    }
                }
            }
            for (String id : purchaseReturnsService.findSheetIdByTime(yearBefore, now)) {
                PurchaseReturnsSheetVO purchaseReturnsSheetVO = purchaseReturnsService.getPurchaseRetuenSheetById(id);
                for (PurchaseReturnsSheetContentVO purchaseReturnsSheetContentVO : purchaseReturnsSheetVO.getPurchaseReturnsSheetContent()) {
                    if (Objects.equals(purchaseReturnsSheetContentVO.getPid(), productInfoVOId)) {
                        totalPp = totalPp.subtract(purchaseReturnsSheetContentVO.getTotalPrice());
                        PpCount -= purchaseReturnsSheetContentVO.getQuantity();
                    }
                }
            }
            if(PpCount > 0) {
                productInfoVO.setPurchasePrice(BigDecimal.valueOf(totalPp.scale() / PpCount));
            }
            else{
                productInfoVO.setPurchasePrice(BigDecimal.valueOf(-1));
            }
            for (String id : saleService.findSheetIdByTime(yearBefore, now)) {
                SaleSheetVO saleSheetVO = saleService.getSaleSheetById(id);
                for (SaleSheetContentVO saleSheetContentVO : saleSheetVO.getSaleSheetContent()) {
                    if (Objects.equals(saleSheetContentVO.getPid(), productInfoVOId)) {
                        totalRp = totalRp.add(saleSheetContentVO.getTotalPrice());
                        RpCount += saleSheetContentVO.getQuantity();
                    }
                }
            }
            for (String id : saleReturnsService.findSheetIdByTime(yearBefore, now)) {
                SaleReturnsSheetVO saleReturnsSheetVO = saleReturnsService.findSheetIdById(id);
                for (SaleReturnsSheetContentVO saleReturnsSheetContentVO : saleReturnsSheetVO.getSaleReturnsSheetContent()) {
                    if (Objects.equals(saleReturnsSheetContentVO.getPid(), productInfoVOId)) {
                        totalRp = totalRp.subtract(saleReturnsSheetContentVO.getTotalPrice());
                        RpCount -= saleReturnsSheetContentVO.getQuantity();
                    }
                }
            }
            if(RpCount > 0) {
                productInfoVO.setRetailPrice(BigDecimal.valueOf(totalRp.scale() / RpCount));
            }
            else{
                productInfoVO.setRetailPrice(BigDecimal.valueOf(-1));
            }
        }
        openingAccountVO.setProductInfoVOS(productInfoVOS);
        return openingAccountVO;
    }

}
