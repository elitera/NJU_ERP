#银行账户
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account`  (
	`bank_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci PRIMARY KEY COMMENT '银行账户名称',
    `balance` decimal(10, 2) NOT NULL COMMENT '账户余额'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- select * from `bank_account`;
-- SELECT * FROM bank_account WHERE bank_account_name like '%72493%';
insert into bank_account
values('aaa', 100);

# 收款单——财务人员
DROP TABLE IF EXISTS `receipt_sheet`;
CREATE TABLE `receipt_sheet`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SKD + 日期 + index = 收款单编号',
  `supplier` int(11) NULL DEFAULT NULL COMMENT '供应商名字',
  `seller` int(11) NULL DEFAULT NULL COMMENT '销售商名字',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员名字',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '总额汇总',
  `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# 转账列表
DROP TABLE IF EXISTS `transfer_record`;
CREATE TABLE `transfer_record` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`receipt_sheet_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对应收款单编号',
    `bank_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行账户名称',
    `transfer_amount` decimal(10, 2) NOT NULL COMMENT '转账金额',
    `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# 付款单——财务人员
DROP TABLE IF EXISTS `payment_order_sheet`;
CREATE TABLE `payment_order_sheet`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'FKD + 日期 + index = 收款单编号',
  `supplier` int(11) NULL DEFAULT NULL COMMENT '供应商名字',
  `seller` int(11) NULL DEFAULT NULL COMMENT '销售商名字',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员名字',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '总额汇总',
  `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# 现金费用单——财务人员
DROP TABLE IF EXISTS `cash_expense_sheet`;
CREATE TABLE `cash_expense_sheet`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'XJFKD + 日期 + index = 付款单编号',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员名字',
  `bank_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行账户名称',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '总额',
  `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# 转账列表
DROP TABLE IF EXISTS `term_sheet_record`;
CREATE TABLE `term_sheet_record` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`payment_order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对应付款单编号',
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条目名',
    `money` decimal(10, 2) NOT NULL COMMENT '金额',
    `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- select * from `term_sheet_record`;
-- select * from `payment_order_sheet`;

# 工资单
DROP TABLE IF EXISTS `payroll`;
CREATE TABLE `payroll` (
	`order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GZD + 日期 + index = 工资单编号',
	`stuff_id` int(11) NOT NULL COMMENT '员工编号',
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
    `bank_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行账户名字',
    `row_wages` decimal(10, 2) NOT NULL COMMENT '应发工资',
    `individual_income_tax` decimal(10, 2) NOT NULL COMMENT '个人所得税',
    `unemployment_insurance` decimal(10, 2) NOT NULL COMMENT '失业保险',
    `housing_provident_fund` decimal(10, 2) NOT NULL COMMENT '住房公积金',
    `tax` decimal(10, 2) NOT NULL COMMENT '扣除税款',
    `real_wages` decimal(10, 2) NOT NULL COMMENT '金额',
    `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
	`create_time` datetime(0) NOT NULL COMMENT '创建时间',
     PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sale_returns_sheet
-- ----------------------------
DROP TABLE IF EXISTS `sale_returns_sheet`;
CREATE TABLE `sale_returns_sheet`  (
        `id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售退货单id',
        `sale_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联的销售单id',
        `operator` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作员',
        `state` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据状态',
        `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
        `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货的总金额',
        `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注'
      ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sale_returns_sheet_content
-- ----------------------------
DROP TABLE IF EXISTS `sale_returns_sheet_content`;
CREATE TABLE `sale_returns_sheet_content`  (
        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
        `purchase_returns_sheet_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售退货单id',
        `pid` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品id',
        `quantity` int(11) NULL DEFAULT NULL COMMENT '数量',
        `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该商品的总金额',
        `unit_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该商品的单价',
        `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
        PRIMARY KEY (`id`) USING BTREE
      ) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


# --select * from sale_sheet;
# --select * from sale_sheet_content;
# --select * from purchase_sheet;
# --select * from purchase_returns_sheet;
# --select * from purchase_returns_sheet_content;
# --select * from warehouse;
# --select * from warehouse_input_sheet;
# --select * from warehouse_input_sheet_content;
# --select * from warehouse;
# --SELECT s.create_time as createTime, p.`name` as name, p.`type` as type, c.quantity as quantity, c.unit_price as unitPrize, c.total_price as totalPrize
# --        FROM sale_sheet s, sale_sheet_content c, product p
# --        WHERE s.id = c.sale_sheet_id and c.pid = p.id;














