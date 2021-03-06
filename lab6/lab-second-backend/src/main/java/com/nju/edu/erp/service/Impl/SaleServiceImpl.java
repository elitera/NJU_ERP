package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Financial.SalesDetailsSheetVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleSheetDao saleSheetDao;

    private final ProductDao productDao;

    private final CustomerDao customerDao;

    private final ProductService productService;

    private final CustomerService customerService;

    private final WarehouseService warehouseService;

    @Autowired
    public SaleServiceImpl(SaleSheetDao saleSheetDao, ProductDao productDao, CustomerDao customerDao, ProductService productService, CustomerService customerService, WarehouseService warehouseService) {
        this.saleSheetDao = saleSheetDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.productService = productService;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
    }

    @Override
    @Transactional
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO) {
        // TODO
        // ???????????????????????????SaleSheet???????????????content???SaleSheetContent??????????????????????????????????????????????????????????????????
        // ?????????service???dao???????????????????????????????????????????????????????????????
        SaleSheetPO saleSheetPO = new SaleSheetPO();
        BeanUtils.copyProperties(saleSheetVO, saleSheetPO);
        saleSheetPO.setOperator(userVO.getName());
        saleSheetPO.setCreateTime(new Date());
        SaleSheetPO latest = saleSheetDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSD");
        saleSheetPO.setId(id);
        saleSheetPO.setState(SaleSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContentPOS = new ArrayList<>();
        for (SaleSheetContentVO saleSheetContentVO : saleSheetVO.getSaleSheetContent()){
            SaleSheetContentPO saleSheetContentPO = new SaleSheetContentPO();
            BeanUtils.copyProperties(saleSheetContentVO, saleSheetContentPO);
            saleSheetContentPO.setSaleSheetId(id);
            BigDecimal unitPrice = saleSheetContentPO.getUnitPrice();
            if (unitPrice == null) {
                ProductPO productPO = productDao.findById(saleSheetContentVO.getPid());
                unitPrice = productPO.getPurchasePrice();
                saleSheetContentPO.setUnitPrice(unitPrice);
            }
            saleSheetContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(saleSheetContentPO.getQuantity())));
            saleSheetContentPOS.add(saleSheetContentPO);
            totalAmount = totalAmount.add(saleSheetContentPO.getTotalPrice());
        }
        saleSheetDao.saveBatchSheetContent(saleSheetContentPOS);
        saleSheetPO.setRawTotalAmount(totalAmount);
        BigDecimal finalAmount = totalAmount.multiply(saleSheetPO.getDiscount()).subtract(saleSheetPO.getVoucherAmount());
        saleSheetPO.setFinalAmount(finalAmount);
        saleSheetDao.saveSheet(saleSheetPO);
    }

    @Override
    @Transactional
    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state) {
        // TODO
        // ?????????????????????????????????????????????VO??????SaleSheetContent???
        // ?????????dao?????????????????????????????????????????????
        List<SaleSheetVO> saleSheetVOS = new ArrayList<>();
        List<SaleSheetPO> saleSheetPOS;
        if (state == null){
            saleSheetPOS = saleSheetDao.findAllSheet();
        }else{
            saleSheetPOS = saleSheetDao.findSheetByState(state);
        }
        for (SaleSheetPO saleSheetPO : saleSheetPOS){
            SaleSheetVO saleSheetVO = new SaleSheetVO();
            BeanUtils.copyProperties(saleSheetPO, saleSheetVO);
            List<SaleSheetContentPO> saleSheetContentPOS = saleSheetDao.findContentBySheetId(saleSheetPO.getId());
            List<SaleSheetContentVO> saleSheetContentVOS = new ArrayList<>();
            for (SaleSheetContentPO saleSheetContentPO : saleSheetContentPOS) {
                SaleSheetContentVO saleSheetContentVO = new SaleSheetContentVO();
                BeanUtils.copyProperties(saleSheetContentPO, saleSheetContentVO);
                saleSheetContentVOS.add(saleSheetContentVO);
            }
            saleSheetVO.setSaleSheetContent(saleSheetContentVOS);
            saleSheetVOS.add(saleSheetVO);
        }
        return saleSheetVOS;
    }

    /**
     * ???????????????id????????????(state == "???????????????"/"????????????"/"????????????")
     * ???controller?????????????????????
     *
     * @param saleSheetId ?????????id
     * @param state       ???????????????????????????
     */
    @Override
    @Transactional
    public void approval(String saleSheetId, SaleSheetState state) {
        // TODO
        // ?????????service???dao???????????????????????????????????????????????????????????????
        /* ??????????????????
            1. ????????????????????????????????????
                 1. ??????????????????
                 2. ???????????????
                 3. ???????????????
                 4. ??????????????????
            2. ?????????????????????????????????????????????????????? ????????????????????????????????????????????????
         */
        if (state.equals(SaleSheetState.FAILURE)){
            SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
            if (saleSheetPO.getState() == SaleSheetState.SUCCESS) throw new RuntimeException("??????????????????");
            int effectLines = saleSheetDao.updateSheetState(saleSheetId, state);
            if (effectLines == 0) throw new RuntimeException("??????????????????");
        }else {
            SaleSheetState prevState;
            if (state.equals(SaleSheetState.SUCCESS)){
                prevState = SaleSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleSheetState.PENDING_LEVEL_2)) {
                prevState = SaleSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("??????????????????");
            }
            int effectLines = saleSheetDao.updateSheetStateOnPrev(saleSheetId, prevState, state);
            if(effectLines == 0) throw new RuntimeException("??????????????????");
            if (state.equals(SaleSheetState.SUCCESS)){
                //??????????????????????????????
                List<SaleSheetContentPO> saleSheetContentPOS = saleSheetDao.findContentBySheetId(saleSheetId);
                List<WarehouseOutputFormContentVO> warehouseOutputFormContentVOS = new ArrayList<>();
                for (SaleSheetContentPO saleSheetContentPO : saleSheetContentPOS) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setId(saleSheetContentPO.getPid());
                    productInfoVO.setRecentRp(saleSheetContentPO.getUnitPrice());
                    productService.updateProduct(productInfoVO);

                    WarehouseOutputFormContentVO warehouseOutputFormContentVO = new WarehouseOutputFormContentVO();
                    warehouseOutputFormContentVO.setSalePrice(saleSheetContentPO.getUnitPrice());
                    warehouseOutputFormContentVO.setQuantity(saleSheetContentPO.getQuantity());
                    warehouseOutputFormContentVO.setRemark(saleSheetContentPO.getRemark());
                    warehouseOutputFormContentVO.setPid(saleSheetContentPO.getPid());
                    warehouseOutputFormContentVOS.add(warehouseOutputFormContentVO);
                }

                //???????????????(?????????????????????
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
                CustomerPO customerPO = customerService.findCustomerById(saleSheetPO.getSupplier());
                customerPO.setReceivable(customerPO.getReceivable().add(saleSheetPO.getFinalAmount()));
                customerService.updateCustomer(customerPO);

                //?????????????????????
                WarehouseOutputFormVO warehouseOutputFormVO = new WarehouseOutputFormVO();
                warehouseOutputFormVO.setOperator(null);
                warehouseOutputFormVO.setSaleSheetId(saleSheetId);
                warehouseOutputFormVO.setList(warehouseOutputFormContentVOS);
                warehouseService.productOutOfWarehouse(warehouseOutputFormVO);
            }
        }
    }

    /**
     * ?????????????????????????????????????????????????????????????????????(?????????????????????,??????????????????????????????,????????????????????????????????????????????????)
     * @param salesman ?????????????????????
     * @param beginDateStr ?????????????????????
     * @param endDateStr ?????????????????????
     * @return
     */
    public CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date beginTime =dateFormat.parse(beginDateStr);
            Date endTime=dateFormat.parse(endDateStr);
            if(beginTime.compareTo(endTime)>0){
                return null;
            }else{
                return saleSheetDao.getMaxAmountCustomerOfSalesmanByTime(salesman,beginTime,endTime);
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ???????????????Id?????????????????????
     * @param saleSheetId ?????????Id
     * @return ?????????????????????
     */
    @Override
    public SaleSheetVO getSaleSheetById(String saleSheetId) {
        SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
        if(saleSheetPO == null) return null;
        List<SaleSheetContentPO> contentPO = saleSheetDao.findContentBySheetId(saleSheetId);
        SaleSheetVO sVO = new SaleSheetVO();
        BeanUtils.copyProperties(saleSheetPO, sVO);
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        for (SaleSheetContentPO content:
                contentPO) {
            SaleSheetContentVO sContentVO = new SaleSheetContentVO();
            BeanUtils.copyProperties(content, sContentVO);
            saleSheetContentVOList.add(sContentVO);
        }
        sVO.setSaleSheetContent(saleSheetContentVOList);
        return sVO;
    }

//    @Override
//    public List<SalesDetailsSheetVO> getSalesSheetsByTime(Date beginDate, Date endDate) {
//        return saleSheetDao.getSalesSheetsByTime(beginDate, endDate);
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> getSalesSheetsByName(String name) {
//        return saleSheetDao.getSalesSheetsByName(name);
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> getSalesSheetsByOperator(String operator) {
//        return saleSheetDao.getSalesSheetsByOperator(operator);
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> getSalesSheetsByCustomer(String customer) {
//        return saleSheetDao.getSalesSheetsByCustomer(customer);
//    }

    @Override
    public List<String> findSheetIdByOperator(String operator) {
        return saleSheetDao.findSheetIdByOperator(operator);
    }

    @Override
    public List<String> findSheetIdByTime(Date beginDate, Date endDate) {
        return saleSheetDao.findSheetIdByTime(beginDate, endDate);
    }

    @Override
    public List<SalesDetailsSheetVO> findSaleSheetsBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria) {
        return saleSheetDao.findSaleSheetBySomeConditions(searchCriteria);
    }

    @Override
    public List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
        return saleSheetDao.findSheetIdBySomeConditions(searchCriteriaPO);
    }
}
