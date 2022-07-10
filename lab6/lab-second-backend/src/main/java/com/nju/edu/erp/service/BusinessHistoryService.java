package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Financial.BusinessHistorySheetSearchCriteriaVO;
import com.nju.edu.erp.web.Response;

import java.util.List;

public interface BusinessHistoryService {

    /**
     * 按照搜索条件查看经营历程表
     *
     * @param searchCriteria 搜索条件
     */
    List<String> showBusinessHistoryBySomeConditions(BusinessHistorySheetSearchCriteriaVO searchCriteria);
}
