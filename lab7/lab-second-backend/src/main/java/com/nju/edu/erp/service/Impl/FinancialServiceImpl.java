package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.sheetState.CashExpenseSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.enums.sheetState.PayrollState;
import com.nju.edu.erp.enums.sheetState.ReceiptSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Financial.*;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.FinancialService;
import com.nju.edu.erp.service.SaleReturnsService;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinancialServiceImpl implements FinancialService {

    private final ReceiptDao receiptDao;

    private final TransferRecordDao transferRecordDao;

    private final BankAccountDao bankAccountDao;

    private final PaymentOrderDao paymentOrderDao;

    private final TermSheetDao termSheetDao;

    private final PayrollDao payrollDao;

    private final CashExpenseSheetDao cashExpenseSheetDao;

    @Autowired
    public FinancialServiceImpl(ReceiptDao receiptDao, TransferRecordDao transferRecordDao, BankAccountDao bankAccountDao,
                                PaymentOrderDao paymentOrderDao, TermSheetDao termSheetDao, PayrollDao payrollDao,
                                SaleService saleService, SaleReturnsService saleReturnsService, CashExpenseSheetDao cashExpenseSheetDao) {
        this.receiptDao = receiptDao;
        this.transferRecordDao = transferRecordDao;
        this.bankAccountDao = bankAccountDao;
        this.paymentOrderDao = paymentOrderDao;
        this.termSheetDao = termSheetDao;
        this.payrollDao = payrollDao;
        this.cashExpenseSheetDao = cashExpenseSheetDao;
    }

//    @Override
//    public void addBankAccount(BankAccountVO bankAccountVO) {
//        BankAccountPO bankAccountPO = new BankAccountPO();
//        BeanUtils.copyProperties(bankAccountVO, bankAccountPO);
//        bankAccountDao.insertBankAccount(bankAccountPO);
//    }
//
//    @Override
//    public void deleteBankAccount(String name) {
//        bankAccountDao.deleteBankAccountByName(name);
//    }
//
//    @Override
//    public void updateBankAccount(BankAccountVO bankAccountVO) {
//        BankAccountPO bankAccountPO = new BankAccountPO();
//        BeanUtils.copyProperties(bankAccountVO, bankAccountPO);
//        bankAccountDao.updateBankAccount(bankAccountPO);
//    }
//
//    @Override
//    public List<BankAccountPO> selectBankAccount(String name) {
//        return bankAccountDao.selectBankAccountByName(name);
//    }


//    @Override
//    public void makeReceipt(UserVO userVO, ReceiptVO receiptVO) {
//        ReceiptPO receiptPO = new ReceiptPO();
//        BeanUtils.copyProperties(receiptVO, receiptPO);
//        receiptPO.setOperator(userVO.getName());
//        receiptPO.setCreateTime(new Date());
//        ReceiptPO latest = receiptDao.getLatestSheet();
//        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
//        receiptPO.setId(id);
//        receiptPO.setState(ReceiptSheetState.PENDING);
//        BigDecimal totalAmount = BigDecimal.ZERO;
//        List<TransferRecordPO> transferRecordPOS = new ArrayList<>();
//        for (TransferRecordVO transferRecordVO : receiptVO.getTransferList()) {
//            TransferRecordPO transferRecordPO = new TransferRecordPO();
//            BeanUtils.copyProperties(transferRecordVO, transferRecordPO);
//            transferRecordPO.setReceiptSheetId(id);
//            String bankAccountName = transferRecordVO.getBankAccount().getBankAccountName();
//            transferRecordPO.setBankAccountName(bankAccountName);
//            BigDecimal transferAmount = transferRecordPO.getTransferAmount();
//            totalAmount = totalAmount.add(transferAmount);
//            transferRecordPOS.add(transferRecordPO);
//        }
//        transferRecordDao.saveBatchSheetContent(transferRecordPOS);
//        receiptPO.setTotalAmount(totalAmount);
//        receiptDao.saveSheet(receiptPO);
//    }
//
//    @Override
//    public void makePaymentOrder(UserVO userVO, PaymentOrderVO paymentOrderVO) {
//        PaymentOrderPO paymentOrderPO = new PaymentOrderPO();
//        BeanUtils.copyProperties(paymentOrderVO, paymentOrderPO);
//        paymentOrderPO.setOperator(userVO.getName());
//        paymentOrderPO.setCreateTime(new Date());
//        PaymentOrderPO latest = paymentOrderDao.getLatestSheet();
//        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "FKD");
//        paymentOrderPO.setId(id);
//        paymentOrderPO.setState(PaymentSheetState.PENDING);
//        BigDecimal totalAmount = BigDecimal.ZERO;
//        List<TransferRecordPO> transferRecordPOS = new ArrayList<>();
//        for (TransferRecordVO transferRecordVO : paymentOrderVO.getTransferList()) {
//            TransferRecordPO transferRecordPO = new TransferRecordPO();
//            BeanUtils.copyProperties(transferRecordVO, transferRecordPO);
//            transferRecordPO.setReceiptSheetId(id);
//            String bankAccountName = transferRecordVO.getBankAccount().getBankAccountName();
//            transferRecordPO.setBankAccountName(bankAccountName);
//            BigDecimal transferAmount = transferRecordPO.getTransferAmount();
//            totalAmount = totalAmount.add(transferAmount);
//            transferRecordPOS.add(transferRecordPO);
//        }
//        transferRecordDao.saveBatchSheetContent(transferRecordPOS);
//        paymentOrderPO.setTotalAmount(totalAmount);
//        paymentOrderDao.saveSheet(paymentOrderPO);
//    }
//
//    @Override
//    public void makeCashExpenseSheet(UserVO userVO, CashExpenseSheetVO cashExpenseSheetVO) {
//        CashExpenseSheetPO cashExpenseSheetPO = new CashExpenseSheetPO();
//        BeanUtils.copyProperties(cashExpenseSheetVO, cashExpenseSheetPO);
//        cashExpenseSheetPO.setOperator(userVO.getName());
//        cashExpenseSheetPO.setCreateTime(new Date());
//        CashExpenseSheetPO latest = cashExpenseSheetDao.getLatestSheet();
//        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XJFYD");
//        cashExpenseSheetPO.setId(id);
//        cashExpenseSheetPO.setState(CashExpenseSheetState.PENDING);
//        cashExpenseSheetPO.setBankAccountName(cashExpenseSheetVO.getBankAccount().getBankAccountName());
//        BigDecimal totalAmount = BigDecimal.ZERO;
//        List<TermSheetPO> termSheetPOS = new ArrayList<>();
//        for (TermSheetVO termSheetVO : cashExpenseSheetVO.getTermSheetList()) {
//            TermSheetPO termSheetPO = new TermSheetPO();
//            BeanUtils.copyProperties(termSheetVO, termSheetPO);
//            termSheetPO.setPaymentOrderId(id);
//            BigDecimal money = termSheetVO.getMoney();
//            totalAmount = totalAmount.add(money);
//            termSheetPOS.add(termSheetPO);
//        }
//        termSheetDao.saveBatchSheetContent(termSheetPOS);
//        cashExpenseSheetPO.setTotalAmount(totalAmount);
//        cashExpenseSheetDao.saveSheet(cashExpenseSheetPO);

//    }
//
//    @Override
//    public List<BankAccountVO> showAllBankAccount() {
//        return bankAccountDao.selectAll();
//    }

//    @Override
//    public void makePayroll(UserVO userVO, PayrollVO payrollVO) {
//        PayrollPO payrollPO = new PayrollPO();
//        BeanUtils.copyProperties(payrollVO, payrollPO);
//        payrollPO.setBankAccountName(payrollVO.getBankAccount().getBankAccountName());
//        PayrollPO latest = payrollDao.getLatestSheet();
//        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getOrderId(), "GZD");
//        payrollPO.setOrderId(id);
//        payrollPO.setState(PayrollState.PENDING);
//        payrollPO.setCreateTime(new Date());
//        payrollDao.saveSheet(payrollPO);
//    }

    @Override
    public List<String> findPaymentOrderSheetIdByOperator(String operator) {
        return paymentOrderDao.findSheetIdByOperator(operator);
    }

    @Override
    public List<String> findReceiptSheetIdByOperator(String operator) {
        return receiptDao.findSheetIdByOperator(operator);
    }

    @Override
    public List<String> findPayrollSheetIdByOperator(String operator) {
        return payrollDao.findSheetIdByOperator(operator);
    }

    @Override
    public List<String> findPaymentOrderSheetIdByTime(Date beginDate, Date endDate) {
        return paymentOrderDao.findSheetIdByTime(beginDate, endDate);
    }

    @Override
    public List<String> findReceiptSheetIdByTime(Date beginDate, Date endDate) {
        return receiptDao.findSheetIdByTime(beginDate, endDate);
    }

    @Override
    public List<String> findPayrollSheetIdByTime(Date beginDate, Date endDate) {
        return payrollDao.findSheetIdByTime(beginDate, endDate);
    }
//
//    @Override
//    public List<String> findReceiptSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
//        return receiptDao.findSheetIdBySomeConditions(searchCriteriaPO);
//    }
//
//    @Override
//    public List<String> findPaymentOrderSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
//        return paymentOrderDao.findSheetIdBySomeConditions(searchCriteriaPO);
//    }

//    @Override
//    public List<String> findCashExpenseSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
//        return cashExpenseSheetDao.findSheetIdBySomeConditions(searchCriteriaPO);
//    }

//    @Override
//    public List<String> findPayrollSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO) {
//        return payrollDao.findSheetIdBySomeConditions(searchCriteriaPO);
//    }

//    @Override
//    public List<SalesDetailsSheetVO> showSaleDetailsByTime(String beginDateStr, String endDateStr) {
//        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date beginDate, endDate;
//        try {
//            beginDate = ft1.parse(beginDateStr);
//            endDate = ft1.parse(endDateStr);
//        } catch (ParseException e) {
//            try{
//                beginDate = ft2.parse(beginDateStr);
//                endDate = ft2.parse(endDateStr);
//            } catch (ParseException e2) {
//                return null;
//            }
//        }
//        if (beginDate.after(endDate))
//            return null;
//        List<SalesDetailsSheetVO> salesSheetsByTime = saleService.getSalesSheetsByTime(beginDate, endDate);
//        List<SalesDetailsSheetVO> salesReturnSheetsByTime = saleReturnsService.getSalesSheetsByTime(beginDate, endDate);
//        List<SalesDetailsSheetVO> result = new ArrayList<>();
//        result.addAll(salesSheetsByTime);
//        result.addAll(salesReturnSheetsByTime);
//        return result;
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> showSaleDetailsByName(String name) {
//        List<SalesDetailsSheetVO> salesSheetsByTime = saleService.getSalesSheetsByName(name);
//        List<SalesDetailsSheetVO> salesReturnSheetsByTime = saleReturnsService.getSalesSheetsByName(name);
//        List<SalesDetailsSheetVO> result = new ArrayList<>();
//        result.addAll(salesSheetsByTime);
//        result.addAll(salesReturnSheetsByTime);
//        return result;
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> showSaleDetailsByOperator(String operator) {
//        List<SalesDetailsSheetVO> salesSheetsByTime = saleService.getSalesSheetsByOperator(operator);
//        List<SalesDetailsSheetVO> salesReturnSheetsByTime = saleReturnsService.getSalesSheetsByOperator(operator);
//        List<SalesDetailsSheetVO> result = new ArrayList<>();
//        result.addAll(salesSheetsByTime);
//        result.addAll(salesReturnSheetsByTime);
//        return result;
//    }
//
//    @Override
//    public List<SalesDetailsSheetVO> showSaleDetailsByCustomer(String customer) {
//        List<SalesDetailsSheetVO> salesSheetsByTime = saleService.getSalesSheetsByCustomer(customer);
//        List<SalesDetailsSheetVO> salesReturnSheetsByTime = saleReturnsService.getSalesSheetsByCustomer(customer);
//        List<SalesDetailsSheetVO> result = new ArrayList<>();
//        result.addAll(salesSheetsByTime);
//        result.addAll(salesReturnSheetsByTime);
//        return result;
//    }

//    @Override
//    public List<String> showBusinessHistoryByOperator(String operator) {
//        return null;
//    }

}
