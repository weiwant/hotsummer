/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:34:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for examination_workload
-- ----------------------------
DROP TABLE IF EXISTS `examination_workload`;
CREATE TABLE `examination_workload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `academic_year` varchar(255) DEFAULT NULL COMMENT '学年',
  `semester` double(255,0) DEFAULT NULL COMMENT '学期',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `main_teacher_name` varchar(255) DEFAULT NULL COMMENT '上课老师',
  `calculating_class_hours` double(255,0) DEFAULT NULL COMMENT '计算用学时',
  `original_teaching_scores` double(255,0) DEFAULT NULL COMMENT '原始教分',
  `teaching_coefficient` double(255,0) DEFAULT NULL COMMENT 'BA1系数',
  `final_teaching_scores` double(255,0) DEFAULT NULL COMMENT '教分（BA1\\ 3\\ 15）',
  `natural_year` varchar(255) DEFAULT NULL COMMENT '自然年',
  `workload_attribute` varchar(255) DEFAULT NULL COMMENT '工作性质',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
