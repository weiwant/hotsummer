/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-12-28 22:56:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `title` varchar(255) DEFAULT NULL,
  `sender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `responder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sendDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sendContent` varchar(255) DEFAULT NULL,
  `responseDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `responseContent` varchar(255) DEFAULT NULL,
  `responseStatus` int(11) DEFAULT '0' COMMENT '0为未回复，1为已回复',
  PRIMARY KEY (`id`),
  KEY `教师id` (`sender`),
  KEY `管理员id` (`responder`),
  CONSTRAINT `教师id` FOREIGN KEY (`sender`) REFERENCES `userlogin` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `管理员id` FOREIGN KEY (`responder`) REFERENCES `userlogin` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '2022', '2022工作量，数据有误', '00006672', '00007553', null, '数据有误', null, '已核实', '0');
INSERT INTO `message` VALUES ('2', '2022', '2022工作量表反馈', '00003683', '00010978', '2022-12-27', '数据有误', '2022-12-28', '已核实修改', '1');
INSERT INTO `message` VALUES ('3', '2022', '2022工作量表反馈意见', '00008721', '00010978', '2022-12-20', '实验课时不对', '2022-12-28', '已修改,请查看', '1');
