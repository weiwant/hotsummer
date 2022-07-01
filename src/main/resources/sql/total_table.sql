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

 Date: 29/06/2022 21:25:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for total_table
-- ----------------------------
DROP TABLE IF EXISTS `total_table`;
CREATE TABLE `total_table`  (
  `academic_work_id` int NULL DEFAULT NULL,
  `paper_work_id` int NULL DEFAULT NULL,
  `examination_work_id` int NULL DEFAULT NULL,
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `foreign_academic`(`academic_work_id` ASC) USING BTREE,
  INDEX `foreign_paper`(`paper_work_id` ASC) USING BTREE,
  INDEX `foreign_examination`(`examination_work_id` ASC) USING BTREE,
  CONSTRAINT `foreign_academic` FOREIGN KEY (`academic_work_id`) REFERENCES `teaching_workload_statistics` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_examination` FOREIGN KEY (`examination_work_id`) REFERENCES `examination_workload` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_paper` FOREIGN KEY (`paper_work_id`) REFERENCES `paper_coaching_workload` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of total_table
-- ----------------------------
INSERT INTO `total_table` VALUES (1, NULL, NULL, '2022', 'test');
INSERT INTO `total_table` VALUES (NULL, 1, NULL, '2022', 'test');

SET FOREIGN_KEY_CHECKS = 1;
