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

 Date: 29/06/2022 16:18:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for paper_coaching_workload
-- ----------------------------
DROP TABLE IF EXISTS `paper_coaching_workload`;
CREATE TABLE `paper_coaching_workload`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `workload_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作量类型',
  `academic_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学年',
  `semester` double(255, 0) NULL DEFAULT NULL COMMENT '学期',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `main_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上课教师姓名',
  `student_amount` double(255, 0) NULL DEFAULT NULL COMMENT '计算用人数',
  `original_teaching_scores` double(255, 0) NULL DEFAULT 0 COMMENT '原始教分',
  `teaching_coefficient` double(255, 0) NULL DEFAULT NULL COMMENT 'BA1系数',
  `final_teaching_scores` double(255, 0) NULL DEFAULT NULL COMMENT '教分（BA1/3/15）',
  `nutural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自然年',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_coaching_workload
-- ----------------------------
INSERT INTO `paper_coaching_workload` VALUES (1, '论文指导', '2020-2021', 2, '软件工程概论', '李晓剑', 2, 6, 2, 4, '2021');
INSERT INTO `paper_coaching_workload` VALUES (2, '论文指导', '2020-2021', 1, '软件需求建模', '李晓剑', 3, 6, 2, 6, '2020');
INSERT INTO `paper_coaching_workload` VALUES (3, '论文指导', '2019-2020', 2, '软件架构', '刘峰', 5, 11, 2, 10, '2020');

SET FOREIGN_KEY_CHECKS = 1;
