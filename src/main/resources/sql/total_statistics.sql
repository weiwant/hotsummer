/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 10:01:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for total_statistics
-- ----------------------------
DROP TABLE IF EXISTS `total_statistics`;
CREATE TABLE `total_statistics` (
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ba1` double DEFAULT NULL,
  `ba2` double DEFAULT NULL,
  `ba3` double DEFAULT NULL,
  `bb1` double DEFAULT NULL,
  `bb2` double DEFAULT NULL,
  `bb3` double DEFAULT NULL,
  `bb4` double DEFAULT NULL,
  `bb5` double DEFAULT NULL,
  `bb6` double DEFAULT NULL,
  `bb7` double DEFAULT NULL,
  `bb8` double DEFAULT NULL,
  `bb9` double DEFAULT NULL,
  `bb10` double DEFAULT NULL,
  `bb11` double DEFAULT NULL,
  `bb12` double DEFAULT NULL,
  `bb13` double DEFAULT NULL,
  `bb14` double DEFAULT NULL,
  `bb15` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  KEY `teacher_foreignkey` (`teacher_name`) USING BTREE,
  CONSTRAINT `teacher_foreignkey` FOREIGN KEY (`teacher_name`) REFERENCES `userlogin` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of total_statistics
-- ----------------------------
INSERT INTO `total_statistics` VALUES (null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
