/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:36:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for total_table
-- ----------------------------
DROP TABLE IF EXISTS `total_table`;
CREATE TABLE `total_table` (
  `academic_work_id` int(11) DEFAULT NULL,
  `paper_work_id` int(11) DEFAULT NULL,
  `examination_work_id` int(11) DEFAULT NULL,
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  KEY `foreign_academic` (`academic_work_id`) USING BTREE,
  KEY `foreign_paper` (`paper_work_id`) USING BTREE,
  KEY `foreign_examination` (`examination_work_id`) USING BTREE,
  CONSTRAINT `foreign_academic` FOREIGN KEY (`academic_work_id`) REFERENCES `teaching_workload_statistics` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_examination` FOREIGN KEY (`examination_work_id`) REFERENCES `examination_workload` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foreign_paper` FOREIGN KEY (`paper_work_id`) REFERENCES `paper_coaching_workload` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
