/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-11-12 20:48:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `identify` int(1) NOT NULL DEFAULT '2',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('202093', '丁建利', '123456', '0');
INSERT INTO `userlogin` VALUES ('212384', '丁立新', '123456', '0');
INSERT INTO `userlogin` VALUES ('187653', '何国良', '123456', '0');
INSERT INTO `userlogin` VALUES ('127365', '何政', '123456', '0');
INSERT INTO `userlogin` VALUES ('203987', '余伟', '123456', '0');
INSERT INTO `userlogin` VALUES ('201983', '傅佑铭', '123456', '0');
INSERT INTO `userlogin` VALUES ('298311', '冯晶', '123456', '0');
INSERT INTO `userlogin` VALUES ('233121', '刘丹丹', '123456', '0');
INSERT INTO `userlogin` VALUES ('223212', '刘华俊', '123456', '0');
INSERT INTO `userlogin` VALUES ('199453', '刘峰', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '刘斌', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '刘纪平', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '叶刚', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '吕慧', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '吴小莹', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '吴志健', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '周国富', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '周浩', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '唐学兵', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '喻丹丹', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '国软外教4', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '孔若杉', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '安扬', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '宋伟', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '常军', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '张乐飞', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '彭蓉', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '徐武平', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '怀念', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '文卫东', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '方颖', '123456', '0');
INSERT INTO `userlogin` VALUES ('', '李老师', '123', '1');
INSERT INTO `userlogin` VALUES ('', '王老师', '456', '2');
INSERT INTO `userlogin` VALUES ('', '赵老师', '789', '3');
