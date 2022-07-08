/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:35:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teaching_workload_statistics
-- ----------------------------
DROP TABLE IF EXISTS `teaching_workload_statistics`;
CREATE TABLE `teaching_workload_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `academic_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '学年',
  `semester` double(255,0) DEFAULT '1' COMMENT '学期',
  `course_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '课程号',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '课程名',
  `teaching_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '教学班',
  `teaching_school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '开课学院',
  `plan_school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划学院',
  `credit` double DEFAULT NULL COMMENT '学分',
  `course_nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程性质',
  `student_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年级',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业',
  `main_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '上课教师名字',
  `main_teacher_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '职称',
  `student_amount` double(255,0) DEFAULT NULL COMMENT '上课人数',
  `theoretical_class_hours` double(255,0) DEFAULT NULL COMMENT '理论课时',
  `computer_class_hours` double(255,0) DEFAULT NULL COMMENT '上机课时',
  `experimental_class_hours` double(255,0) DEFAULT NULL COMMENT '实验课时',
  `practical_class_hours` double(255,0) DEFAULT NULL COMMENT '实践课时',
  `calculating_class_hours` double(255,0) DEFAULT NULL COMMENT '计算用课时',
  `joint_department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合课单位',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `experiment_arrangement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实验安排',
  `other_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他教师名',
  `original_teaching_scores` double DEFAULT '0' COMMENT '原始教分',
  `teaching_coefficient` double DEFAULT '0' COMMENT 'BA1系数',
  `final_teaching_scores` double DEFAULT NULL COMMENT '教分',
  `assistant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '辅助',
  `class_nature_explanation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程性质解释',
  `special_class_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否为特殊班级',
  `special_language_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否全英教学',
  `discount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '折扣',
  `no_discount_teaching_coefficient` double DEFAULT '0' COMMENT '折扣前BA1系数',
  `laboratory_verification_results` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '实验室核对结果',
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自然年',
  `workload_nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作量性质',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
