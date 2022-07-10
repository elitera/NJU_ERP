DROP TABLE IF EXISTS `employee_punch`;
CREATE TABLE `employee_punch`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '打卡记录',
                                   `name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打卡员工用户名',
                                   `punch_time` date NULL DEFAULT NULL COMMENT '打卡时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
                             `id` int(11) NOT NULL COMMENT '员工编号',
                             `gender` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
                             `birthday` DATE NULL DEFAULT NULL COMMENT '生日',
                             `name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                             `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
                             `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工岗位',
                             `basic_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '员工基本工资',
                             `post_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '员工岗位工资',
                             `salary_granting_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资发放方式',
                             `salary_calculating_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '薪资计算方式',
                             `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工账户',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;