/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-06-29 16:08:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for paper_coaching_workload
-- ----------------------------
DROP TABLE IF EXISTS `paper_coaching_workload`;
CREATE TABLE `paper_coaching_workload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workload_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作量类型',
  `academic_year` varchar(30) DEFAULT NULL COMMENT '学年',
  `semester` double(255,0) DEFAULT NULL COMMENT '学期',
  `course_name` varchar(30) DEFAULT NULL COMMENT '课程名称',
  `main_teacher_name` varchar(10) DEFAULT NULL COMMENT '上课教师姓名',
  `student_amount` double(255,0) DEFAULT NULL COMMENT '计算用人数',
  `original_teaching_scores` double(255,0) DEFAULT '0' COMMENT '原始教分',
  `teaching_coefficient` double(255,0) DEFAULT NULL COMMENT 'BA1系数',
  `final_teaching_scores` double(255,0) DEFAULT NULL COMMENT '教分（BA1/3/15）',
  `nutural_year` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自然年',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_coaching_workload
-- ----------------------------
INSERT INTO `paper_coaching_workload` VALUES ('1', '论文指导', '2020-2021', '2', '软件工程概论', '李晓剑', '2', '6', '2', '4', '2021');
INSERT INTO `paper_coaching_workload` VALUES ('2', '论文指导', '2020-2021', '1', '软件需求建模', '李晓剑', '3', '6', '2', '6', '2020');
INSERT INTO `paper_coaching_workload` VALUES ('3', '论文指导', '2019-2020', '2', '软件架构', '刘峰', '5', '11', '2', '10', '2020');
