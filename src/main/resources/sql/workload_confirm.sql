/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:38:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for workload_confirm
-- ----------------------------
DROP TABLE IF EXISTS `workload_confirm`;
CREATE TABLE `workload_confirm` (
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `confirm` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`natural_year`,`teacher_name`) USING BTREE,
  KEY `teacher` (`teacher_name`) USING BTREE,
  CONSTRAINT `teacher` FOREIGN KEY (`teacher_name`) REFERENCES `userlogin` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
