/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:34:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for paper_coaching_workload
-- ----------------------------
DROP TABLE IF EXISTS `paper_coaching_workload`;
CREATE TABLE `paper_coaching_workload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workload_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作量类型',
  `academic_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学年',
  `semester` double(255,0) DEFAULT NULL COMMENT '学期',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  `main_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上课教师姓名',
  `student_amount` double(255,0) DEFAULT NULL COMMENT '计算用人数',
  `original_teaching_scores` double(255,0) DEFAULT '0' COMMENT '原始教分',
  `teaching_coefficient` double(255,0) DEFAULT NULL COMMENT 'BA1系数',
  `final_teaching_scores` double(255,0) DEFAULT NULL COMMENT '教分（BA1/3/15）',
  `nutural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自然年',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
