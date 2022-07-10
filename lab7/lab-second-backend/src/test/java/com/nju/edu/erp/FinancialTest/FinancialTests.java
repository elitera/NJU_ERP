package com.nju.edu.erp.FinancialTest;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.ReceiptSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.Financial.*;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.service.Impl.FinancialServiceImpl;
import com.nju.edu.erp.service.Impl.SaleServiceImpl;
import com.nju.edu.erp.utils.IdGenerator;
import com.nju.edu.erp.web.controller.FinancialController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class FinancialTests {

    @Autowired
    FinancialService financialService;
    @Autowired
    ReceiptDao receiptDao;
    @Autowired
    TransferRecordDao transferRecordDao;
    @Autowired
    SaleService saleService;
    @Autowired
    PaymentOrderDao paymentOrderDao;
    @Autowired
    OpeningAccountService openingAccountService;
    @Autowired
    AccountService accountService;
    @Autowired
    PaymentOrderService paymentOrderService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    PayrollService payrollService;
    @Autowired
    CashExpenseService cashExpenseService;
    @Autowired
    BusinessHistoryService businessHistoryService;
    @Autowired
    OperationStatementService operationStatementService;
    @Autowired
    SaleDetailsService saleDetailsService;
    @Autowired
    CashExpenseSheetDao cashExpenseSheetDao;
    @Autowired
    PayrollDao payrollDao;

    /**
     * 财务人员
     */
    private final UserVO caiwurenyuan = UserVO.builder()
            .name("caiwurenyuan")
            .role(Role.FINANCIAL_STAFF)
            .build();

    /**
     * 银行账户
     */
    private final BankAccountVO bankAccount1 = BankAccountVO.builder()
            .bankAccountName("ba1")
            .balance(1000)
            .build();
    private final BankAccountVO bankAccount2 = BankAccountVO.builder()
            .bankAccountName("ba2")
            .balance(1001)
            .build();

    /**
     * 转账列表————收款单
     */
    private final TransferRecordVO transferRecordVO1 = TransferRecordVO.builder()
            .bankAccount(bankAccount1)
            .remark("Test1-Record1")
            .transferAmount(BigDecimal.valueOf(100))
            .build();
    private final TransferRecordVO transferRecordVO2 = TransferRecordVO.builder()
            .bankAccount(bankAccount2)
            .remark("Test1-Record2")
            .transferAmount(BigDecimal.valueOf(200))
            .build();

    /**
     * 条目清单————付款单
     */
    private final TermSheetVO termSheetVO1 = TermSheetVO.builder()
            .name("aaa")
            .money(BigDecimal.valueOf(100))
            .remark("remark-aaa")
            .build();
    private final TermSheetVO termSheetVO2 = TermSheetVO.builder()
            .name("bbb")
            .money(BigDecimal.valueOf(200))
            .remark("remark-bbb")
            .build();

    @Test
    public void warehouseServiceTest() {
        if (financialService == null) {
            System.out.println("service也是空的");
        } else {
            System.out.println("service不是空的");
        }
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void ReceiptTest() { // 测试收款单是否生成成功
        accountService.addBankAccount(bankAccount1);
        accountService.addBankAccount(bankAccount2);
        List<TransferRecordVO> transferRecordVOS = new ArrayList<>();
        transferRecordVOS.add(transferRecordVO1);
        transferRecordVOS.add(transferRecordVO2);
        ReceiptVO receiptVO = ReceiptVO.builder()
                .supplier(33)
                .seller(44)
                .operator("operator-01")
                .transferList(transferRecordVOS)
                .build();
        ReceiptPO prevSheet = receiptDao.getLatestSheet();
        String realSheetId = IdGenerator.generateSheetId(prevSheet == null ? null : prevSheet.getId(), "SKD");

        receiptService.makeReceipt(caiwurenyuan, receiptVO);
        ReceiptPO latestSheet = receiptDao.getLatestSheet();
        Assertions.assertNotNull(latestSheet);
        Assertions.assertEquals(realSheetId, latestSheet.getId());
        Assertions.assertEquals(0, latestSheet.getOperator().compareTo("caiwurenyuan"));

        String sheetId = latestSheet.getId();
        Assertions.assertNotNull(sheetId);
        List<TransferRecordPO> content = transferRecordDao.findTransferBySheetId(sheetId);
        content.sort(Comparator.comparing(TransferRecordPO::getId));
        Assertions.assertEquals(2, content.size());
        Assertions.assertEquals("ba1", content.get(0).getBankAccountName());
        Assertions.assertEquals(0, content.get(0).getTransferAmount().compareTo(BigDecimal.valueOf(100.00)));
        Assertions.assertEquals("ba2", content.get(1).getBankAccountName());
        Assertions.assertEquals(0, content.get(1).getTransferAmount().compareTo(BigDecimal.valueOf(200.00)));

        System.out.println("BBBBBBBBBBefore");
        System.out.println(receiptService.showReceiptById(latestSheet.getId()));
        receiptService.approval(latestSheet.getId(), ReceiptSheetState.SUCCESS);
        System.out.println(receiptService.showReceiptById(latestSheet.getId()));
        System.out.println("AAAAAAAAAAAAAfter");
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void PaymentOrderTest() { // 测试收款单是否生成成功
        List<TransferRecordVO> transferRecordVOS = new ArrayList<>();
        transferRecordVOS.add(transferRecordVO1);
        transferRecordVOS.add(transferRecordVO2);
        PaymentOrderVO paymentOrderVO = PaymentOrderVO.builder()
                .supplier(33)
                .seller(44)
                .operator("operator-01")
                .transferList(transferRecordVOS)
                .build();
        PaymentOrderPO prevSheet = paymentOrderDao.getLatestSheet();
        String realSheetId = IdGenerator.generateSheetId(prevSheet == null ? null : prevSheet.getId(), "FKD");

        paymentOrderService.makePaymentOrder(caiwurenyuan, paymentOrderVO);
        PaymentOrderPO latestSheet = paymentOrderDao.getLatestSheet();
        Assertions.assertNotNull(latestSheet);
        Assertions.assertEquals(realSheetId, latestSheet.getId());
        Assertions.assertEquals(0, latestSheet.getOperator().compareTo("caiwurenyuan"));

        String sheetId = latestSheet.getId();
        Assertions.assertNotNull(sheetId);
        List<TransferRecordPO> content = transferRecordDao.findTransferBySheetId(sheetId);
        content.sort(Comparator.comparing(TransferRecordPO::getId));
        Assertions.assertEquals(2, content.size());
        Assertions.assertEquals("ba1", content.get(0).getBankAccountName());
        Assertions.assertEquals(0, content.get(0).getTransferAmount().compareTo(BigDecimal.valueOf(100.00)));
        Assertions.assertEquals("ba2", content.get(1).getBankAccountName());
        Assertions.assertEquals(0, content.get(1).getTransferAmount().compareTo(BigDecimal.valueOf(200.00)));

        System.out.println(paymentOrderService.showPaymentOrderById(latestSheet.getId()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void paymentOrderTest() { // 测试添加现金费用单
        List<TermSheetVO> termSheets = new ArrayList<>();
        termSheets.add(termSheetVO1);
        termSheets.add(termSheetVO2);
        CashExpenseSheetVO cashExpenseSheetVO = CashExpenseSheetVO.builder()
                .bankAccount(bankAccount1)
                .termSheetList(termSheets)
                .build();
        cashExpenseService.makeCashExpenseSheet(caiwurenyuan, cashExpenseSheetVO);
        System.out.println(cashExpenseService.showCashExpenseById(cashExpenseSheetDao.getLatestSheet().getId()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void bankAccountTest() { // 测试银行账户增删改查功能
        BankAccountVO bankAccount1_update = BankAccountVO.builder()
                .bankAccountName("ba1")
                .balance(3000)
                .build();
        accountService.addBankAccount(bankAccount1);
        accountService.addBankAccount(bankAccount2);

        List<BankAccountPO> bankAccountPOS = accountService.selectBankAccount("ba");
        Assertions.assertNotNull(bankAccountPOS);
        Assertions.assertEquals(2, bankAccountPOS.size());

        accountService.updateBankAccount(bankAccount1_update);
        List<BankAccountPO> bankAccountPOS1 = accountService.selectBankAccount("ba1");
        Assertions.assertNotNull(bankAccountPOS1);
        Assertions.assertEquals(1, bankAccountPOS1.size());
        Assertions.assertEquals(3000, bankAccountPOS1.get(0).getBalance());

        accountService.deleteBankAccount("ba1");
        List<BankAccountPO> bankAccountPOS2 = accountService.selectBankAccount("ba1");
        Assertions.assertEquals(0, bankAccountPOS2.size());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void showAllBankAccountTest() { // 测试银行账户增删改查功能
        System.out.println(accountService.showAllBankAccount());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void createPayrollTest() { // 测试制定工资单功能
        PayrollVO payrollVO1 = PayrollVO.builder()
                .stuffId(1)
                .name("zhangsan")
                .bankAccount(bankAccount1)
                .realWages(BigDecimal.valueOf(12000))
                .individualIncomeTax(BigDecimal.valueOf(1200))
                .unemploymentInsurance(BigDecimal.valueOf(100))
                .housingProvidentFund(BigDecimal.valueOf(200))
                .tax(BigDecimal.valueOf(1500))
                .rowWages(BigDecimal.valueOf(10500))
                .build();
        payrollService.makePayroll(caiwurenyuan, payrollVO1);
        System.out.println(payrollService.showPayrollById(payrollDao.getLatestSheet().getOrderId()));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void selectSalesDetailsTest() { // 测试查看销售明细表
        SaleDetailsSheetSearchCriteriaVO searchCriteriaVO1 = SaleDetailsSheetSearchCriteriaVO.builder()
                .beginDateStr("2022-05-24")
                .endDateStr("2022-06-24")
                .build();
        SaleDetailsSheetSearchCriteriaVO searchCriteriaVO2 = SaleDetailsSheetSearchCriteriaVO.builder()
                .beginDateStr("2022-05-24")
                .build();
        SaleDetailsSheetSearchCriteriaVO searchCriteriaVO3 = SaleDetailsSheetSearchCriteriaVO.builder()
                .beginDateStr("2022-05-24")
                .endDateStr("2022-06-24")
                .name("戴尔电脑")
                .build();
        SaleDetailsSheetSearchCriteriaVO searchCriteriaVO4 = SaleDetailsSheetSearchCriteriaVO.builder()
                .beginDateStr("2022-05-24")
                .endDateStr("2022-06-24")
                .name("戴尔")
                .build();
        SaleDetailsSheetSearchCriteriaVO searchCriteriaVO5 = SaleDetailsSheetSearchCriteriaVO.builder()
                .beginDateStr("2022-05-24")
                .endDateStr("2022-06-24")
                .name("戴尔")
                .operator("xiaoshoujingli")
                .build();

        SaleDetailsSheetSearchCriteriaVO searchCriteria = searchCriteriaVO5;

        String beginDateStr = searchCriteria.getBeginDateStr();
        String endDateStr = searchCriteria.getEndDateStr();
        String customer = searchCriteria.getCustomer();
        String operator = searchCriteria.getOperator();
        String name = searchCriteria.getName();

        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date beginDate, endDate;
        try {
            beginDate = ft1.parse(beginDateStr);
        } catch (Exception e) {
            try {
                beginDate = ft2.parse(beginDateStr);
            } catch (Exception e2) {
                beginDate = null;
            }
        }
        try {
            endDate = ft1.parse(endDateStr);
        } catch (Exception e) {
            try {
                endDate = ft2.parse(endDateStr);
            } catch (Exception e2) {
                endDate = null;
            }
        }
        SaleDetailsSheetSearchCriteriaPO searchCriteriaPO = new SaleDetailsSheetSearchCriteriaPO();
        searchCriteriaPO.setBeginDate(beginDate);
        searchCriteriaPO.setEndDate(endDate);

        if (Objects.equals(customer, "null")) {
            searchCriteriaPO.setSupplier(null);
        } else {
            searchCriteriaPO.setSupplier(customer);
        }

        if (Objects.equals(name, "null")) {
            searchCriteriaPO.setName(null);
        } else {
            searchCriteriaPO.setName(name);
        }

        if (Objects.equals(operator, "null")) {
            searchCriteriaPO.setOperator(null);
        } else {
            searchCriteriaPO.setOperator(operator);
        }

        System.out.println(saleService.findSaleSheetsBySomeConditions(searchCriteriaPO));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void selectBusinessHistoryTest() { // 测试查看经营历程表
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = null;
        try {
            begin = ft1.parse("2022-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BusinessHistorySheetSearchCriteriaPO searchCriteriaPO = BusinessHistorySheetSearchCriteriaPO.builder()
                .beginDate(begin)
                .build();
        List<String> ids = saleService.findSheetIdBySomeConditions(searchCriteriaPO);
        List<SaleSheetVO> saleSheetVOS = new ArrayList<>();
        for (String id : ids) {
            saleSheetVOS.add(saleService.getSaleSheetById(id));
        }
        System.out.println(ids);
        System.out.println(saleSheetVOS);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void openingAccountServiceTest() { // 测试期初建账
        System.out.println(openingAccountService.openingAccountEstablishment());
    }
}





























