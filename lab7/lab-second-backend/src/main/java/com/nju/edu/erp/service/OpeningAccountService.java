package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.Financial.OpeningAccountVO;

public interface OpeningAccountService {

    /**
     * 生成期初建账信息
     * @return
     */
    OpeningAccountVO openingAccountEstablishment();

}
