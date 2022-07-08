/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:35:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for special_project
-- ----------------------------
DROP TABLE IF EXISTS `special_project`;
CREATE TABLE `special_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `report_time` date DEFAULT NULL COMMENT '申报时间',
  `declarant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申报人',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'BB类型',
  `achievement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成果/作品/参赛项目/参评项目/论文/专著名称',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '级别',
  `project_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程/项目/奖项/竞赛/论文指导类别',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程/项目/奖项/竞赛详细名称',
  `award_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '获奖等级',
  `award_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '获奖/获评/出版日期',
  `project_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目进展',
  `award_apartment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授奖单位',
  `publication_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '刊物/出版社名称',
  `publications_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '刊物期数/出版版次',
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ISBN号',
  `brief_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容简介',
  `receiving_honor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所获荣誉',
  `guiding_student_team` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生团队名',
  `guiding_student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生姓名',
  `guiding_student_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生学号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核状态',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
