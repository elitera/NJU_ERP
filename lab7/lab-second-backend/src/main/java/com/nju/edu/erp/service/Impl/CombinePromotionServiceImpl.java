package com.nju.edu.erp.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.nju.edu.erp.dao.CombinePromotionDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CombinePromotionPO;
import com.nju.edu.erp.model.vo.promotion.CombinePromotionVO;
import com.nju.edu.erp.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("combine")
@Slf4j
public class CombinePromotionServiceImpl implements PromotionService {

    private final CombinePromotionDao promotionDao;

    @Autowired
    public CombinePromotionServiceImpl(CombinePromotionDao promotionDao) {
        this.promotionDao = promotionDao;
    }

    @Override
    public void deletePromotionById(int id) {
        CombinePromotionPO promotionPO = promotionDao.getPromotionById(id);
        if (promotionPO == null) {
            throw new MyServiceException("1111", "促销策略不存在");
        }
        promotionDao.deletePromotionById(id);
    }

    @Override
    public void addPromotion(Object promotionVO) {
        String str = JSONObject.toJSONString(promotionVO);
        CombinePromotionVO vo = JSONObject.parseObject(str, CombinePromotionVO.class);
        CombinePromotionPO po = new CombinePromotionPO();
        BeanUtils.copyProperties(vo, po);
        StringBuilder pids = new StringBuilder();
        for (String pid : vo.getPidList()) {
            pids.append(pid).append(" ");
        }
        po.setPidCombination(pids.deleteCharAt(pids.length()-1).toString());
        promotionDao.createPromotion(po);
    }

    @Override
    public List<Object> getPromotions() {
        List<CombinePromotionPO> promotionPOS = promotionDao.showPromotions();
        List<Object> promotionVOS = new ArrayList<>();
        for (CombinePromotionPO promotionPO : promotionPOS) {
            CombinePromotionVO promotionVO = new CombinePromotionVO();
            BeanUtils.copyProperties(promotionPO, promotionVO);
            promotionVO.setPidList(Arrays.asList(promotionPO.getPidCombination().split(" ")));
            promotionVOS.add(promotionVO);
        }
        return promotionVOS;
    }
}