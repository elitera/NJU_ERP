package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.TermSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TermSheetDao {

    void saveBatchSheetContent(List<TermSheetPO> termSheetPOS);

    List<TermSheetPO> findTermSheetsBySheetId(String id);
}
