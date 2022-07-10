package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.TransferRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TransferRecordDao {

    void saveBatchSheetContent(List<TransferRecordPO> transferRecordPOS);

    List<TransferRecordPO> findTransferBySheetId(String sheetId);

}
