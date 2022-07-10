package com.nju.edu.erp.model.vo.Financial;


import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.vo.CategoryVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpeningAccountVO {

    /**
     * 所有商品分类
     */
    private List<CategoryVO> categoryVOS;

    /**
     * 所有商品
     */
    private List<ProductInfoVO> productInfoVOS;

    /**
     * 客户信息
     */
    private List<CustomerVO> customerVOS;

    /**
     * 银行账户信息
     */
    private List<BankAccountVO> bankAccountVOS;
}
