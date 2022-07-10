# 详细设计文档

## 1 引言

### 1.1 编制目的

本报告详细完成对灯具进销存管理系统的概要设计，从而达到指导详细设计和开发的目的，同时实现和测试人员以及用户的沟通。
本报告面向开发人员、测试人员和用户编写，是了解系统的导航

### 1.2 词汇表

| 词汇名称 | 词汇含义       | 备注 |
| -------- | -------------- | ---- |
| ERP      | 进销存管理系统 | 无   |

### 1.3 参考资料

*

## 2 产品描述

参考灯具进销存系统用例文档和灯具进销存需求规格说明文档中对产品的概括描述。

## 3 体系结构设计概述

参考参考灯具进销存系统概要设计文档中对体系结构设计的概括描述。

## 4 结构视角
###4.1 业务逻辑层的分解

业务逻辑层的开发包图参照软禁啊体系结构文档

####4.1.1 financialbl模块
#####（1）模块概述

financialbl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
financialbl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

根据体系结构的设计，我们将系统分为展示层、业务逻辑层和数据层。每一层之间为了增加灵活性，我们会添加接口。
比如在展示层和业务逻辑层之间，我们添加service.financialbl.AccountService接口；业务逻辑层和数据层之间添加dao.financialdao.AccountDao接口。
为了隔离业务逻辑职责和逻辑控制职责，我们增加了FinancialContoller, 这样FinancialController会将对银行账户的逻辑处理委托给AccountService对象。
AccountPO是作为销售记录的 持久化对象被添加到设计模型中去的。<br>

除此以外，该模块中还包括收款单、付款单、现金费用单、工资单、销售明细表、经营历程表、经营情况表的类似的分层处理逻辑。<br>

##### (3) 模块内部的接口规范
<table>
<caption>表9 financialbl模块的接口规范</caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
    <tr>
        <td rowspan="3">Account.addBankAccount</td>
        <td>语法</td>
        <td>void addBankAccount(BankAccountVO bankAccountVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>系统保存新帐户信息</td>
    </tr>
    <tr>
        <td rowspan="3">Account.deleteBankAccount</td>
        <td>语法</td>
        <td> void deleteBankAccount(String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>依据账户名称删除银行账户</td>
    </tr>
    <tr>
        <td rowspan="3">Account.updateBankAccount</td>
        <td>语法</td>
        <td>void updateBankAccount(BankAccountVO bankAccountVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>依据账户ID持久化更新银行账户</td>
    </tr>
    <tr>
        <td rowspan="3">Account.selectBankAccount</td>
        <td>语法</td>
        <td>List selectBankAccount(String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>根据名称模糊查找并返回所有符合条件的账户</td>
    </tr>
    <tr>
        <td rowspan="3">Account.showAllBankAccount</td>
        <td>语法</td>
        <td>List showAllBankAccount()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有账户信息</td>
    </tr>
    <tr>
        <td rowspan="3">BusinessHistory.showBusinessHistoryBySomeConditions</td>
        <td>语法</td>
        <td>List showBusinessHistoryBySomeConditions(BusinessHistorySheetSearchCriteriaVO searchCriteria)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>已在经营过程中产生各类单据</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
    <tr>
        <td rowspan="3">CashExpense.makeCashExpenseSheet</td>
        <td>语法</td>
        <td>void makeCashExpenseSheet(UserVO userVO, CashExpenseSheetVO cashExpenseSheetVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>系统生成并持久化现金费用单</td>
    </tr>
    <tr>
        <td rowspan="3">CashExpense.findCashExpenseSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List findCashExpenseSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的现金费用单</td>
    </tr>
    <tr>
        <td rowspan="3">OpeningAccount.openingAccountEstablishment</td>
        <td>语法</td>
        <td>OpeningAccountVO openingAccountEstablishment()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td></td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回期初建账信息（所有商品分类、所有商品、客户信息、银行账户信息）</td>
    </tr>
    <tr>
        <td rowspan="3">OperationStatement.showStatementOfOperation</td>
        <td>语法</td>
        <td>OperatingConditionsSheetVO showStatementOfOperation(String beginDateStr, String endDateStr)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>传入的时间字符串符合“yyyy-MM-dd HH:mm:ss”格式，且不为空</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回指定时间区间内的经营情况表</td>
    </tr>
    <tr>
        <td rowspan="3">PaymentOrder.makePaymentOrder</td>
        <td>语法</td>
        <td>void makePaymentOrder(UserVO userVO, PaymentOrderVO paymentOrderVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>系统生成并持久化付款单</td>
    </tr>
    <tr>
        <td rowspan="3">PaymentOrder.findPaymentOrderSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List findPaymentOrderSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的付款单</td>
    </tr>
    <tr>
        <td rowspan="3">Payroll.makePayroll</td>
        <td>语法</td>
        <td>void makePayroll(UserVO userVO, PayrollVO payrollVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>系统生成并持久化工资单</td>
    </tr>
    <tr>
        <td rowspan="3">Payroll.findPayrollSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List findPayrollSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的工资单</td>
    </tr>
    <tr>
        <td rowspan="3">Receipt.makeReceipt</td>
        <td>语法</td>
        <td>void makeReceipt(UserVO userVO, ReceiptVO receiptVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>系统生成并持久化收款单</td>
    </tr>
    <tr>
        <td rowspan="3">Receipt.findReceiptSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List findReceiptSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的收款单</td>
    </tr>
    <tr>
        <td rowspan="3">SaleDetails.showSaleDetailsBySomeConditions</td>
        <td>语法</td>
        <td>List showSaleDetailsBySomeConditions(SaleDetailsSheetSearchCriteriaVO searchCriteria)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>操作人为财务人员或最高权限</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的销售明细表</td>
    </tr>

</table>


<table>
    <tr>
        <td colspan="2"><strong><div class="text" style=" text-align:center;">需要的接口（需接口）</div></strong></td>
    </tr>
    <tr>
        <td>服务名</td>
        <td>服务</td>
    </tr>
    <tr>
        <td>BankAccountDao.insertBankAccount(BankAccountPO bankAccountPO)</td>
        <td>持久化一条银行账户信息</td>
    </tr>
    <tr>
        <td>BankAccountDao.deleteBankAccountByName(String name)</td>
        <td>根据名称删除一条银行账户信息</td>
    </tr>
    <tr>
        <td>BankAccountDao.updateBankAccount(BankAccountPO bankAccountPO)</td>
        <td>更新一条银行账户信息</td>
    </tr>
    <tr>
        <td>BankAccountDao.selectBankAccountByName(String name)</td>
        <td>查询并返回名称模糊匹配到的所有银行账户信息</td>
    </tr>
    <tr>
        <td>BankAccountDao.selectAll()</td>
        <td>查询并返回所有银行账户信息</td>
    </tr>
    <tr>
        <td>SaleService.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>saleReturnsService.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>purchaseService.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>purchaseReturnsService.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>receiptService.findReceiptSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>paymentOrderService.findPaymentOrderSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>cashExpenseService.findCashExpenseSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>payrollService.findPayrollSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的表单</td>
    </tr>
    <tr>
        <td>UserDataService.findByKeyWord(String keyWord)</td>
        <td>根据字段名和值进行查找多个持久化对象</td>
    </tr>
     <tr>
        <td>TermSheetDao.saveBatchSheetContent(List termSheetPOS)</td>
        <td>插入多个持久化对象</td>
    </tr>
    <tr>
        <td>CashExpenseSheetDao.saveSheet(CashExpenseSheetPO cashExpenseSheetPO)</td>
        <td>插入单一持久化对象</td>
    </tr>
     <tr>
        <td>CashExpenseSheetDao.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
    <tr>
        <td>SaleService.findSheetIdByTime(String beginDate, String endDate)</td>
        <td>返回所有符合条件的持久化对象</td>
    </tr>
    <tr>
        <td>TransferRecordDao.saveBatchSheetContent(List transferRecordPOS)</td>
        <td>插入多个持久化对象</td>
    </tr>
    <tr>
        <td>PaymentOrderDao.saveSheet(PaymentOrderPO paymentOrderPO)</td>
        <td>插入单一持久化对象</td>
    </tr>
     <tr>
        <td>PaymentOrderDao.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
     <tr>
        <td>PayrollDao.saveSheet(PayrollPO payrollPO)</td>
        <td>插入单一持久化对象</td>
    </tr>
     <tr>
        <td>PayrollDao.getLatestSheet()</td>
        <td>返回上一个工资单</td>
    </tr>
     <tr>
        <td>IdGenerator.generateSheetId(String id, String prefix)</td>
        <td>根据上一份单据生成</td>
    </tr>
     <tr>
        <td>ReceiptDao.getLatestSheet()</td>
        <td>返回上一个收款单</td>
    </tr>
     <tr>
        <td>TransferRecordDao.saveBatchSheetContent(TransferRecordPOS transferRecordPOS)</td>
        <td>插入多个持久化对象</td>
    </tr>
     <tr>
        <td>ReceiptDao.saveSheet(ReceiptPO receiptPO)</td>
        <td>插入单一持久化对象</td>
    </tr>
     <tr>
        <td>ReceiptDao.findSheetIdBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
     <tr>
        <td>SaleService.findSaleSheetsBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
     <tr>
        <td>SaleReturnsService.findSaleSheetsBySomeConditions(SearchCriteriaPO searchCriteriaPO)</td>
        <td>返回所有符合条件（时间区间、单据类型、客户、业务员）的单据</td>
    </tr>
</table>

##### （4）业务逻辑层的动态模型
![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/bankAccount_make_201870189.png)
<div style=" text-align:center;">图16 添加银行账户的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/CashExpense_make_201870189.png)
<div style=" text-align:center;">图17 添加现金费用单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/paymentOrder_make_201870189.png)
<div style=" text-align:center;">图 添加付款单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/payroll_make_201870189.png)
<div style=" text-align:center;">图 添加工资单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/receipt_make_201870189.png)
<div style=" text-align:center;">图 添加收款单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/payrollApproval_201870189.png)
<div style=" text-align:center;">图 总经理审批工资单的顺序图</div>
ps: 其他财务类单据审批流程基本相同，故略

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/SaleDetails_show_201870189.png)
<div style=" text-align:center;">图 展示经营历程表的顺序图</div>
ps: 查看销售明细表、经营历程表与之基本相同，故略

##### （5)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


####4.1.2 purchasebl模块
#####（1）模块概述

purchasebl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
purchasebl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

基本同 4.1.1 (2)，在此略。

##### (3) 模块内部的接口规范

<table>
<caption><div class="text" style=" text-align:center;">表10 purchasebl模块的接口规范</div></strong> </caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.makePurchaseSheet</td>
        <td>语法</td>
        <td>void makePurchaseSheet(UserVO userVO, PurchaseSheetVO purchaseSheetVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.getPurchaseSheetByState</td>
        <td>语法</td>
        <td>List<PurchaseSheetVO> getPurchaseSheetByState(PurchaseSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.approval</td>
        <td>语法</td>
        <td>void approval(String purchaseSheetId, PurchaseSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.getPurchaseSheetById</td>
        <td>语法</td>
        <td>PurchaseSheetVO getPurchaseSheetById(String purchaseSheetId)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.findSheetIdByOperator</td>
        <td>语法</td>
        <td>List<String> findSheetIdByOperator(String operator)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.findSheetIdByTime</td>
        <td>语法</td>
        <td>List<String> findSheetIdByTime(Date beginDate, Date endDate)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseService.findSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.makePurchaseReturnsSheet</td>
        <td>语法</td>
        <td>void makePurchaseReturnsSheet(UserVO userVO, PurchaseReturnsSheetVO purchaseReturnsSheetVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.getPurchaseReturnsSheetByState</td>
        <td>语法</td>
        <td> List<PurchaseReturnsSheetVO> getPurchaseReturnsSheetByState(PurchaseReturnsSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.approval</td>
        <td>语法</td>
        <td>void approval(String purchaseReturnsSheetId, PurchaseReturnsSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.findSheetIdByOperator</td>
        <td>语法</td>
        <td> List<String> findSheetIdByOperator(String operator)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.findSheetIdByTime</td>
        <td>语法</td>
        <td>List<String> findSheetIdByTime(Date beginDate, Date endDate)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.findSheetIdBySomeConditions</td>
        <td>语法</td>
        <td> List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PurchaseReturnsService.getPurchaseRetuenSheetById</td>
        <td>语法</td>
        <td>PurchaseReturnsSheetVO getPurchaseRetuenSheetById(String id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
</table>


<table>
    <tr>
        <td colspan="2"><strong><div class="text" style=" text-align:center;">需要的接口（需接口）</div></strong></td>
    </tr>
    <tr>
        <td>服务名</td>
        <td>服务</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.saveBatch(pContentPOList)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.save(purchaseReturnsSheetPO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findContentByPurchaseSheetId</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>IdGenerator.generateSheetId(String id, String prefix)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findAll()</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findAllByState(state)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findContentByPurchaseReturnsSheetId(po.getId())</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.updateState(purchaseReturnsSheetId, state)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findOneById(purchaseReturnsSheetId)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findBatchId(purchaseReturnsSheetId)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findContentByPurchaseReturnsSheetId(purchaseReturnsSheetId)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>warehouseDao.findOneByPidAndBatchId(pid, batchId)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>warehouseDao.deductQuantity(warehousePO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td> productService.updateProduct(productInfoVO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>PurchaseReturnsSheetDao.updateState(String purchaseReturnsSheetId, PurchaseReturnsSheetState state)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findSheetIdByOperator(operator)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findSheetIdByTime(beginDate, endDate)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseReturnsSheetDao.findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.getLatest()</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.saveBatch(pContentPOList)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.save(purchaseSheetPO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findAll()</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findAllByState(state)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findContentByPurchaseSheetId(po.getId())</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findSheetIdByOperator(operator)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findSheetIdByTime(beginDate, endDate)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>purchaseSheetDao.findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
</table>

##### （4）业务逻辑层的动态模型
![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/purchase_sheet_make_201870189.png)
<div style=" text-align:center;">图 添加进货单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/purchase_sheet_show_by_id_201870189.png)
<div style=" text-align:center;">图 根据单号展示进货单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/p_return_make_201870189.png)
<div style=" text-align:center;">图 进货退货单制定的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/purchase_sheet_approval_201870189.png)
<div style=" text-align:center;">图 进货单审批的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/p_return_approval_201870189.png)
<div style=" text-align:center;">图 进货退货单审批的顺序图</div>

##### （5)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


####4.1.3 salebl模块
#####（1）模块概述

salebl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
salebl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

基本同 4.1.1 (2)，在此略。

##### (3) 模块内部的接口规范

<table>
<caption><div class="text" style=" text-align:center;">表14 salebl模块的接口规范</div></strong> </caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
<tr>
        <td rowspan="3">PromotionService.deletePromotionById</td>
        <td>语法</td>
        <td>void deletePromotionById(int id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PromotionService.addPromotion</td>
        <td>语法</td>
        <td>void addPromotion(Object promotionVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">PromotionService.getPromotions</td>
        <td>语法</td>
        <td>List<Object> getPromotions()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.makeSaleReturnsSheet</td>
        <td>语法</td>
        <td>void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.getSaleReturnsSheetByState</td>
        <td>语法</td>
        <td>List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.approval</td>
        <td>语法</td>
        <td>void approval(String saleReturnsSheetId, SaleReturnsSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.findSheetIdByOperator</td>
        <td>语法</td>
        <td>List<String> findSheetIdByOperator(String operator)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.findSheetIdByTime</td>
        <td>语法</td>
        <td>List<String> findSheetIdByTime(Date beginDate, Date endDate)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.findSaleSheetsBySomeConditions</td>
        <td>语法</td>
        <td>List<SalesDetailsSheetVO> findSaleSheetsBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.findSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO businessHistorySheetSearchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleReturnsService.</td>
        <td>语法</td>
        <td>SaleReturnsSheetVO findSheetIdById(String id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.makeSaleSheet</td>
        <td>语法</td>
        <td>void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.getSaleSheetByState</td>
        <td>语法</td>
        <td>List<SaleSheetVO> getSaleSheetByState(SaleSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.approval</td>
        <td>语法</td>
        <td>void approval(String saleSheetId, SaleSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.getMaxAmountCustomerOfSalesmanByTime</td>
        <td>语法</td>
        <td>CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.getSaleSheetById</td>
        <td>语法</td>
        <td>SaleSheetVO getSaleSheetById(String saleSheetId)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.findSheetIdByOperator</td>
        <td>语法</td>
        <td>List<String> findSheetIdByOperator(String operator)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.findSheetIdByTime</td>
        <td>语法</td>
        <td>List<String> findSheetIdByTime(Date beginDate, Date endDate)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.findSaleSheetsBySomeConditions</td>
        <td>语法</td>
        <td>List<SalesDetailsSheetVO> findSaleSheetsBySomeConditions(SaleDetailsSheetSearchCriteriaPO searchCriteria)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">SaleService.findSheetIdBySomeConditions</td>
        <td>语法</td>
        <td>List<String> findSheetIdBySomeConditions(BusinessHistorySheetSearchCriteriaPO searchCriteriaPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
</table>


##### （4）业务逻辑层的动态模型
![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/sale_make_201870189.png)
<div style=" text-align:center;">图 添加销售单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/sale_by_state_201870189.png)
<div style=" text-align:center;">图 根据状态查看销售单的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/sale_approval_201870189.png)
<div style=" text-align:center;">图 销售单审批的顺序图</div>

##### （5)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


####4.1.4 warehousebl模块
#####（1）模块概述

warehousebl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
warehousebl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

基本同 4.1.1 (2)，在此略。

##### (3) 模块内部的接口规范

<table>
<caption><div class="text" style=" text-align:center;">表13 warehousebl模块的接口规范</div></strong> </caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
<tr>
        <td rowspan="3">CategoryService.createCategory</td>
        <td>语法</td>
        <td>    CategoryVO createCategory(Integer parentId, String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CategoryService.queryAllCategory</td>
        <td>语法</td>
        <td>    List<CategoryVO> queryAllCategory()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CategoryService.updateCategory</td>
        <td>语法</td>
        <td>    CategoryVO updateCategory(Integer id, String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CategoryService.deleteCategory</td>
        <td>语法</td>
        <td>    void deleteCategory(Integer id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.createProduct</td>
        <td>语法</td>
        <td>    ProductInfoVO createProduct(CreateProductVO inputVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.updateProduct</td>
        <td>语法</td>
        <td>    ProductInfoVO updateProduct(ProductInfoVO productInfoVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.queryAllProduct</td>
        <td>语法</td>
        <td>    List<ProductInfoVO> queryAllProduct()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.deleteById</td>
        <td>语法</td>
        <td>    void deleteById(String id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.getOneProductByPid</td>
        <td>语法</td>
        <td>    ProductInfoVO getOneProductByPid(String pid)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">ProductService.productWarehousing</td>
        <td>语法</td>
        <td>    void productWarehousing(WarehouseInputFormVO warehouseInputFormVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.productOutOfWarehouse</td>
        <td>语法</td>
        <td>    void productOutOfWarehouse(WarehouseOutputFormVO warehouseOutputFormListVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWareProductInfo</td>
        <td>语法</td>
        <td>    List<WarehouseOneProductInfoVO> getWareProductInfo(GetWareProductInfoParamsVO params)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.approvalInputSheet</td>
        <td>语法</td>
        <td>    void approvalInputSheet(UserVO user, String warehouseInputSheetId, WarehouseInputSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWareHouseInputSheetByState</td>
        <td>语法</td>
        <td>    List<WarehouseInputSheetPO> getWareHouseInputSheetByState(WarehouseInputSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWareHouseOutSheetByState</td>
        <td>语法</td>
        <td>    List<WarehouseOutputSheetPO> getWareHouseOutSheetByState(WarehouseOutputSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.approvalOutputSheet</td>
        <td>语法</td>
        <td>    void approvalOutputSheet(UserVO user, String sheetId, WarehouseOutputSheetState state)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWHISheetContentById</td>
        <td>语法</td>
        <td>    List<WarehouseInputSheetContentPO> getWHISheetContentById(String sheetId)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWHOSheetContentById</td>
        <td>语法</td>
        <td>    List<WarehouseOutputSheetContentPO> getWHOSheetContentById(String sheetId)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWarehouseIODetailByTime</td>
        <td>语法</td>
        <td>    List<WarehouseIODetailPO> getWarehouseIODetailByTime(String beginDateStr,String endDateStr) throws ParseException</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWarehouseInputProductQuantityByTime</td>
        <td>语法</td>
        <td>    int getWarehouseInputProductQuantityByTime(String beginDateStr,String endDateStr)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">WarehouseService.getWarehouseOutProductQuantityByTime</td>
        <td>语法</td>
        <td>    int getWarehouseOutProductQuantityByTime(String beginDateStr,String endDateStr)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>    <tr>
        <td rowspan="3">WarehouseService.warehouseCounting</td>
        <td>语法</td>
        <td>    List<WarehouseCountingVO> warehouseCounting()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
</table>

##### （4）业务逻辑层的动态模型
![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/warehouse_product_201870189.png)
<div style=" text-align:center;">图 查看仓库产品的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/warehouse_approval_201870189.png)
<div style=" text-align:center;">图 入库单审批的顺序图</div>

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/quantity_count_201870189.png)
<div style=" text-align:center;">图 库存查看（一个时间段内的出库数量合计）的顺序图</div>

##### （5)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


####4.1.5 rolebl模块
#####（1）模块概述

rolebl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
rolebl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

基本同 4.1.1 (2)，在此略。

##### (3) 模块内部的接口规范

<table>
<caption><div class="text" style=" text-align:center;">表12 rolebl模块的接口规范</div></strong> </caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
<tr>
        <td rowspan="3">CustomerService.updateCustomer</td>
        <td>语法</td>
        <td>void updateCustomer(CustomerPO customerPO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.getCustomersByType</td>
        <td>语法</td>
        <td>List<CustomerPO> getCustomersByType(CustomerType type)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.findCustomerById</td>
        <td>语法</td>
        <td>CustomerPO findCustomerById(Integer supplier)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.addCustomer</td>
        <td>语法</td>
        <td>void addCustomer(CustomerVO customerVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.deleteCustomerById</td>
        <td>语法</td>
        <td>void deleteCustomerById(Integer id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.updateCustomer</td>
        <td>语法</td>
        <td>void updateCustomer(CustomerVO customerVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">CustomerService.queryAllCustomer</td>
        <td>语法</td>
        <td>List<CustomerVO> queryAllCustomer()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.addEmployee</td>
        <td>语法</td>
        <td>void addEmployee(EmployeeVO employeeVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.deleteEmployeeById</td>
        <td>语法</td>
        <td>void deleteEmployeeById(Integer id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.updateEmployee</td>
        <td>语法</td>
        <td>void updateEmployee(EmployeeVO employeeVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.addPunch</td>
        <td>语法</td>
        <td>void addPunch(EmployeePunchVO employeePunchVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.getALLEmployees</td>
        <td>语法</td>
        <td>List<EmployeeVO> getALLEmployees()</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.getEmployeeSalaryGrantingModeById</td>
        <td>语法</td>
        <td>String getEmployeeSalaryGrantingModeById(Integer id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.getEmployeeSalaryCalculatingModeById</td>
        <td>语法</td>
        <td>String getEmployeeSalaryCalculatingModeById(Integer id)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.getPunchedTimesByEmployeeName</td>
        <td>语法</td>
        <td>int getPunchedTimesByEmployeeName(String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">EmployeeService.showPunchByEmployeeName</td>
        <td>语法</td>
        <td>List<EmployeePunchVO> showPunchByEmployeeName(String name)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
</table>


<table>
    <tr>
        <td colspan="2"><strong><div class="text" style=" text-align:center;">需要的接口（需接口）</div></strong></td>
    </tr>
    <tr>
        <td>服务名</td>
        <td>服务</td>
    </tr>
    <tr>
        <td>CustomerDataService.generateId()</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
</table>

##### （4)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


####4.1.6 userbl模块
#####（1）模块概述

userbl模块承担的需求参见需求规格说明文档功能需求及相关非功能需求<br>
userbl模块的职责及接口参见软件系统结构描述文档表

#####（2）整体结构

基本同 4.1.1 (2)，在此略。

##### (3) 模块内部的接口规范

<table>
<caption><div class="text" style=" text-align:center;">表11 userbl模块的接口规范</div></strong> </caption>  
    <tr>
        <td colspan="3"><strong><div class="text" style=" text-align:center;">提供的服务（供接口）</div></strong></td>
    </tr>
<tr>
        <td rowspan="3">UserService.login</td>
        <td>语法</td>
        <td>Map login(UserVO userVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">UserService.register</td>
        <td>语法</td>
        <td>void register(UserVO userVO)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
    <tr>
        <td rowspan="3">UserService.auth</td>
        <td>语法</td>
        <td>UserVO auth(String token)</td>
    </tr>
    <tr>
        <td>前置条件</td>
        <td>启动客户管理任务</td>
    </tr>
    <tr>
        <td>后置条件</td>
        <td>返回查询的客户信息的结果</td>
    </tr>
</table>


<table>
    <tr>
        <td colspan="2"><strong><div class="text" style=" text-align:center;">需要的接口（需接口）</div></strong></td>
    </tr>
    <tr>
        <td>服务名</td>
        <td>服务</td>
    </tr>
    <tr>
        <td>User findByUsernameAndPassword(String username, String password)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>int createUser(User user)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
    <tr>
        <td>User findByUsername(String username)</td>
        <td>生成一条与之前不同的客户编号</td>
    </tr>
</table>

##### （4)业务逻辑层的设计原理
采用委托式控制风格，每个界面需要访问的业务逻辑由各个控制器委托给不同领域对象


## 5 依赖视角
下图分别是客户端和服务器端各自的包之间的依赖关系

![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/client_develop_201870192.png)
<div style=" text-align:center;"> 客户端依赖关系图 </div>


![seec-logo](https://seec-homework.oss-cn-shanghai.aliyuncs.com/server_develop_201870189.png)
<div style=" text-align:center;"> 服务器端依赖关系图  </div>









































