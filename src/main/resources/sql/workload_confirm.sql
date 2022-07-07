/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : abc

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/07/2022 22:48:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for workload_confirm
-- ----------------------------
DROP TABLE IF EXISTS `workload_confirm`;
CREATE TABLE `workload_confirm`  (
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `confirm` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`natural_year`, `teacher_name`) USING BTREE,
  INDEX `teacher`(`teacher_name` ASC) USING BTREE,
  CONSTRAINT `teacher` FOREIGN KEY (`teacher_name`) REFERENCES `userlogin` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workload_confirm
-- ----------------------------
INSERT INTO `workload_confirm` VALUES ('2022', '123', 1);
INSERT INTO `workload_confirm` VALUES ('2022', '456', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '789', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '丁建利', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '丁立新', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '何国良', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '何政', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '余伟', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '傅佑铭', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '冯晶', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '刘丹丹', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '刘华俊', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '刘峰', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '刘斌', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '刘纪平', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '叶刚', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '吕慧', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '吴小莹', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '吴志健', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '周国富', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '周浩', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '唐学兵', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '喻丹丹', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '国软外教4', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '孔若杉', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '安扬', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '宋伟', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '常军', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '张乐飞', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '彭蓉', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '徐武平', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '怀念', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '文卫东', 0);
INSERT INTO `workload_confirm` VALUES ('2022', '方颖', 0);

SET FOREIGN_KEY_CHECKS = 1;
