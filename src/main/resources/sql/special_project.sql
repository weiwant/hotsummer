/*
Navicat MySQL Data Transfer

Source Server         : linkWithHotsummer
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-07-05 11:29:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for special_project
-- ----------------------------
DROP TABLE IF EXISTS `special_project`;
CREATE TABLE `special_project` (
  `id` int(11) NOT NULL COMMENT '主键',
  `report_time` date DEFAULT NULL COMMENT '申报时间',
  `declarant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申报人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of special_project
-- ----------------------------
