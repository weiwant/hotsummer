/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:35:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for special_teacher
-- ----------------------------
DROP TABLE IF EXISTS `special_teacher`;
CREATE TABLE `special_teacher` (
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师姓名',
  `author_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序情况/位次',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'BB类型',
  `teaching_scores` double DEFAULT NULL COMMENT '教分',
  KEY `project_key` (`project_id`),
  KEY `teacher_key` (`teacher_name`),
  CONSTRAINT `project_key` FOREIGN KEY (`project_id`) REFERENCES `special_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
