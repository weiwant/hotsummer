/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-08 22:38:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yearly_deadline
-- ----------------------------
DROP TABLE IF EXISTS `yearly_deadline`;
CREATE TABLE `yearly_deadline` (
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deadline` date DEFAULT NULL,
  PRIMARY KEY (`year`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
