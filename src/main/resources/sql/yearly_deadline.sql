/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : abc

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/07/2022 22:50:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yearly_deadline
-- ----------------------------
DROP TABLE IF EXISTS `yearly_deadline`;
CREATE TABLE `yearly_deadline`  (
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deadline` date NULL DEFAULT NULL,
  PRIMARY KEY (`year`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yearly_deadline
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
