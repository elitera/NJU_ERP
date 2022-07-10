package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Financial.OperatingConditionsSheetVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.RequestParam;

public interface OperationStatementService {

    /**
     * 按照时间区间查看经营情况表
     * @param beginDateStr 开始时间字符串 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @param endDateStr 结束时间字符串 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     */
    OperatingConditionsSheetVO showStatementOfOperation(String beginDateStr, String endDateStr);
}
