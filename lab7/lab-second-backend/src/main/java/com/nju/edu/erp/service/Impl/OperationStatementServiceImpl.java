package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.utils.DateUtil;
import com.nju.edu.erp.model.vo.Financial.OperatingConditionsSheetVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.service.OperationStatementService;
import com.nju.edu.erp.service.SaleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OperationStatementServiceImpl implements OperationStatementService {

    private final SaleService saleService;

    public OperationStatementServiceImpl(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public OperatingConditionsSheetVO showStatementOfOperation(String beginDateStr, String endDateStr) {
        Date beginDate = DateUtil.string2DateUtil(beginDateStr);
        Date endDate = DateUtil.string2DateUtil(endDateStr);
        OperatingConditionsSheetVO operatingConditionsSheetVO = new OperatingConditionsSheetVO();

        BigDecimal salesIncome = BigDecimal.valueOf(0);
        BigDecimal overflowIncome = BigDecimal.valueOf(0);
        BigDecimal costPriceAdjustmentIncome = BigDecimal.valueOf(0);
        BigDecimal purchaseReturnDifference = BigDecimal.valueOf(0);
        BigDecimal cashCoupon = BigDecimal.valueOf(0);
        BigDecimal costOfSales = BigDecimal.valueOf(0);
        BigDecimal destroy = BigDecimal.valueOf(0);
        BigDecimal gift = BigDecimal.valueOf(0);
        BigDecimal laborCost = BigDecimal.valueOf(0);
        BigDecimal totalIncome = BigDecimal.valueOf(0);
        BigDecimal allowance = BigDecimal.valueOf(0);
        BigDecimal totalExpenditure = BigDecimal.valueOf(0);
        BigDecimal profit = BigDecimal.valueOf(0);


        List<String> ids = saleService.findSheetIdByTime(beginDate, endDate);
        for (String id : ids) {
            SaleSheetVO saleSheetVO = saleService.getSaleSheetById(id);
            salesIncome = salesIncome.add(saleSheetVO.getFinalAmount());  // 销售收入
            allowance = allowance.add(saleSheetVO.getDiscount()); // 折让
            for (SaleSheetContentVO saleSheetContentVO : saleSheetVO.getSaleSheetContent()) {
                costOfSales = costOfSales.add(saleSheetContentVO.getUnitPrice());
            }
        }
        // Todo: 处理逻辑

        operatingConditionsSheetVO.setSalesIncome(salesIncome);
        operatingConditionsSheetVO.setOverflowIncome(overflowIncome);
        operatingConditionsSheetVO.setCostPriceAdjustmentIncome(costPriceAdjustmentIncome);
        operatingConditionsSheetVO.setPurchaseReturnDifference(purchaseReturnDifference);
        operatingConditionsSheetVO.setCashCoupon(cashCoupon);
        operatingConditionsSheetVO.setCostOfSales(costOfSales);
        operatingConditionsSheetVO.setDestroy(destroy);
        operatingConditionsSheetVO.setGift(gift);
        operatingConditionsSheetVO.setLaborCost(laborCost);
        operatingConditionsSheetVO.setAllowance(allowance);
        operatingConditionsSheetVO.setTotalExpenditure(totalExpenditure);
        operatingConditionsSheetVO.setProfit(profit);
        return operatingConditionsSheetVO;
    }
}
