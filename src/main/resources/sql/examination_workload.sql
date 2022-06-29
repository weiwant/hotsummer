/*
Navicat MySQL Data Transfer

Source Server         : linkWithHotsummer
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-06-29 15:57:34
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_workload
-- ----------------------------
INSERT INTO `examination_workload` VALUES ('1', '2019-2020', '3', '组合数学', '李', '4', '36', '2', '72', '2020', '考务');
INSERT INTO `examination_workload` VALUES ('2', '2019-2020', '3', 'web应用开发', '李', '4', '42', '1', '42', '2020', '考务');
INSERT INTO `examination_workload` VALUES ('3', '2019-2020', '2', 'web应用开发', '黄', '5', '54', '1', '54', '2020', '考务');
