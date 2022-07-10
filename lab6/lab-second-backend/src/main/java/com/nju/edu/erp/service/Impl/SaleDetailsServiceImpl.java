package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.utils.DateUtil;
import com.nju.edu.erp.model.po.SaleDetailsSheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.SaleDetailsSheetSearchCriteriaVO;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;
import com.nju.edu.erp.service.SaleDetailsService;
import com.nju.edu.erp.service.SaleReturnsService;
import com.nju.edu.erp.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaleDetailsServiceImpl implements SaleDetailsService {

    private final SaleService saleService;

    private final SaleReturnsService saleReturnsService;

    public SaleDetailsServiceImpl(SaleService saleService, SaleReturnsService saleReturnsService) {
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
    }

    @Override
    public List<SalesDetailsSheetVO> showSaleDetailsBySomeConditions(SaleDetailsSheetSearchCriteriaVO searchCriteria)  {
        String beginDateStr = searchCriteria.getBeginDateStr();
        String endDateStr = searchCriteria.getEndDateStr();
        String customer = searchCriteria.getCustomer();
        String operator = searchCriteria.getOperator();
        String name = searchCriteria.getName();

        SaleDetailsSheetSearchCriteriaPO searchCriteriaPO = new SaleDetailsSheetSearchCriteriaPO();

        Date beginDate = DateUtil.string2DateUtil(beginDateStr);
        Date endDate = DateUtil.string2DateUtil(endDateStr);
        if (beginDate != null && endDate != null && beginDate.after(endDate))
            return null;
        searchCriteriaPO.setBeginDate(beginDate);
        searchCriteriaPO.setEndDate(endDate);

        if(Objects.equals(customer, "null")){
            searchCriteriaPO.setSupplier(null);
        }
        else{
            searchCriteriaPO.setSupplier(customer);
        }

        if(Objects.equals(name, "null")){
            searchCriteriaPO.setName(null);
        }
        else{
            searchCriteriaPO.setName(name);
        }

        if(Objects.equals(operator, "null")){
            searchCriteriaPO.setOperator(null);
        }
        else{
            searchCriteriaPO.setOperator(operator);
        }

        List<SalesDetailsSheetVO> salesSheetsByTime = saleService.findSaleSheetsBySomeConditions(searchCriteriaPO);
        List<SalesDetailsSheetVO> salesReturnSheetsByTime = saleReturnsService.findSaleSheetsBySomeConditions(searchCriteriaPO);
        List<SalesDetailsSheetVO> result = new ArrayList<>();
        result.addAll(salesSheetsByTime);
        result.addAll(salesReturnSheetsByTime);

        result.sort(Comparator.comparing(SalesDetailsSheetVO::getCreateTime));
        return result;
    }
}
