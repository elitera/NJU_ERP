DROP TABLE IF EXISTS `total_promotion_strategy`;
CREATE TABLE `total_promotion_strategy`  (
                                             `id` int(11) NOT NULL COMMENT '方法编号',
                                             `condition` decimal(10, 2) NULL DEFAULT NULL COMMENT '状况',
                                             `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '大小',
                                             `begin_time` DATE NULL DEFAULT NULL COMMENT '开始时间',
                                             `end_time` DATE NULL DEFAULT NULL COMMENT '结束时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `customer_promotion_strategy`;
CREATE TABLE `customer_promotion_strategy`  (
                                                `id` int(11) NOT NULL COMMENT '方法编号',
                                                `level` int(11) NOT NULL COMMENT '级别',
                                                `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '折扣',
                                                `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '大小',
                                                `begin_time` DATE NULL DEFAULT NULL COMMENT '开始时间',
                                                `end_time` DATE NULL DEFAULT NULL COMMENT '结束时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `combine_promotion_strategy`;
CREATE TABLE `combine_promotion_strategy`  (
                                               `id` int(11) NOT NULL COMMENT '方法编号',
                                               `pid_combination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品',
                                               `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '大小',
                                               `begin_time` DATE NULL DEFAULT NULL COMMENT '开始时间',
                                               `end_time` DATE NULL DEFAULT NULL COMMENT '结束时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;