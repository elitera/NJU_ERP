package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Financial.SaleDetailsSheetSearchCriteriaVO;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;

import java.util.List;

public interface SaleDetailsService {

    /**
     * 按照搜索条件查看销售明细表
     * @param searchCriteria 搜索条件
     */
    List<SalesDetailsSheetVO> showSaleDetailsBySomeConditions(SaleDetailsSheetSearchCriteriaVO searchCriteria);
}
