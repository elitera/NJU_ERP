package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.utils.DateUtil;
import com.nju.edu.erp.model.po.BusinessHistorySheetSearchCriteriaPO;
import com.nju.edu.erp.model.vo.Financial.BusinessHistorySheetSearchCriteriaVO;
import com.nju.edu.erp.service.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class BusinessHistoryServiceImpl implements BusinessHistoryService {

    private final SaleService saleService;

    private final SaleReturnsService saleReturnsService;

    private final PayrollService payrollService;

    private final PurchaseService purchaseService;

    private final PurchaseReturnsService purchaseReturnsService;

    private final ReceiptService receiptService;

    private final PaymentOrderService paymentOrderService;

    private final CashExpenseService cashExpenseService;

    public BusinessHistoryServiceImpl(SaleService saleService, SaleReturnsService saleReturnsService, PayrollService payrollService,
                                      PurchaseService purchaseService, PurchaseReturnsService purchaseReturnsService,
                                      ReceiptService receiptService, PaymentOrderService paymentOrderService,
                                      CashExpenseService cashExpenseService) {
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
        this.payrollService = payrollService;
        this.purchaseService = purchaseService;
        this.purchaseReturnsService = purchaseReturnsService;
        this.receiptService = receiptService;
        this.paymentOrderService = paymentOrderService;
        this.cashExpenseService = cashExpenseService;
    }

    /**
     * 按照搜索条件查看经营历程表
     *
     * @param searchCriteria 搜索条件
     */
    @Override
    public List<String> showBusinessHistoryBySomeConditions(BusinessHistorySheetSearchCriteriaVO searchCriteria) {
        String[] typeStr = {
                "XSD", "XSTHD",
                "JHD", "JHTHD",
                "FKD", "SKD", "XJFYD", "GZD"
        };
        Boolean[] flags = new Boolean[typeStr.length];
        Map<Integer, Function<BusinessHistorySheetSearchCriteriaPO, List<String>>> functions = new HashMap<>();
        functions.put(0, saleService::findSheetIdBySomeConditions);
        functions.put(1, saleReturnsService::findSheetIdBySomeConditions);
        functions.put(2, purchaseService::findSheetIdBySomeConditions);
        functions.put(3, purchaseReturnsService::findSheetIdBySomeConditions);
        functions.put(4, receiptService::findReceiptSheetIdBySomeConditions);
        functions.put(5, paymentOrderService::findPaymentOrderSheetIdBySomeConditions);
        functions.put(6, cashExpenseService::findCashExpenseSheetIdBySomeConditions);
        functions.put(7, payrollService::findPayrollSheetIdBySomeConditions);

        String beginDateStr = searchCriteria.getBeginDateStr();
        String endDateStr = searchCriteria.getEndDateStr();
        String customer = searchCriteria.getCustomer();
        String operator = searchCriteria.getOperator();
        List<String> types = searchCriteria.getTypes();

        if (types == null || types.size() == 0 || (types.size() == 1 && Objects.equals(types.get(0), "null"))) {
            for (int i = 0; i < typeStr.length; i++)
                flags[i] = true;
        } else {
            for (int i = 0; i < typeStr.length; i++)
                flags[i] = types.contains(typeStr[i]);
        }

        BusinessHistorySheetSearchCriteriaPO searchCriteriaPO = new BusinessHistorySheetSearchCriteriaPO();

        Date beginDate = DateUtil.string2DateUtil(beginDateStr);
        Date endDate = DateUtil.string2DateUtil(endDateStr);
        if (beginDate != null && endDate != null && beginDate.after(endDate))
            return null;
        searchCriteriaPO.setBeginDate(beginDate);
        searchCriteriaPO.setEndDate(endDate);

        if (Objects.equals(customer, "null")) {
            searchCriteriaPO.setCustomer(null);
        } else {
            searchCriteriaPO.setCustomer(customer);
        }

        if (Objects.equals(operator, "null")) {
            searchCriteriaPO.setOperator(null);
        } else {
            searchCriteriaPO.setOperator(operator);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < typeStr.length; i++) {
            if (flags[i]) {
                result.addAll(functions.get(i).apply(searchCriteriaPO));
            }
        }
        result.sort(Comparator.comparing(String::toString));
        return result;
    }
}
