/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-06-28 15:44:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `identify` int(1) NOT NULL DEFAULT '2',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('123', '123', '1');
INSERT INTO `userlogin` VALUES ('丁建利', '123456', '2');
INSERT INTO `userlogin` VALUES ('丁立新', '123456', '2');
INSERT INTO `userlogin` VALUES ('伍春香', '123456', '2');
INSERT INTO `userlogin` VALUES ('何发智', '123456', '2');
INSERT INTO `userlogin` VALUES ('何国良', '123456', '2');
INSERT INTO `userlogin` VALUES ('何政', '123456', '2');
INSERT INTO `userlogin` VALUES ('何炎祥', '123456', '2');
INSERT INTO `userlogin` VALUES ('何璐璐', '123456', '2');
INSERT INTO `userlogin` VALUES ('余伟', '123456', '2');
INSERT INTO `userlogin` VALUES ('余纯武', '123456', '2');
INSERT INTO `userlogin` VALUES ('傅佑铭', '123456', '2');
INSERT INTO `userlogin` VALUES ('冯晶', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘丹丹', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘华俊', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘威威', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘娟', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘婧', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘峰', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘敏忠', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘斌', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘树波', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘浩文', '123456', '2');
INSERT INTO `userlogin` VALUES ('刘纪平', '123456', '2');
INSERT INTO `userlogin` VALUES ('叶刚', '123456', '2');
INSERT INTO `userlogin` VALUES ('吕慧', '123456', '2');
INSERT INTO `userlogin` VALUES ('吴小莹', '123456', '2');
INSERT INTO `userlogin` VALUES ('吴志健', '123456', '2');
INSERT INTO `userlogin` VALUES ('周国富', '123456', '2');
INSERT INTO `userlogin` VALUES ('周浩', '123456', '2');
INSERT INTO `userlogin` VALUES ('唐学兵', '123456', '2');
INSERT INTO `userlogin` VALUES ('喻丹丹', '123456', '2');
INSERT INTO `userlogin` VALUES ('国软外教4', '123456', '2');
INSERT INTO `userlogin` VALUES ('夏启明', '123456', '2');
INSERT INTO `userlogin` VALUES ('姚昱', '123456', '2');
INSERT INTO `userlogin` VALUES ('孔若杉', '123456', '2');
INSERT INTO `userlogin` VALUES ('孙世磊', '123456', '2');
INSERT INTO `userlogin` VALUES ('安扬', '123456', '2');
INSERT INTO `userlogin` VALUES ('宋伟', '123456', '2');
INSERT INTO `userlogin` VALUES ('宋成芳', '123456', '2');
INSERT INTO `userlogin` VALUES ('常军', '123456', '2');
INSERT INTO `userlogin` VALUES ('应时', '123456', '2');
INSERT INTO `userlogin` VALUES ('张乐飞', '123456', '2');
INSERT INTO `userlogin` VALUES ('张健', '123456', '2');
INSERT INTO `userlogin` VALUES ('张昀', '123456', '2');
INSERT INTO `userlogin` VALUES ('张沪寅', '123456', '2');
INSERT INTO `userlogin` VALUES ('张锡宁', '123456', '2');
INSERT INTO `userlogin` VALUES ('彭敏', '123456', '2');
INSERT INTO `userlogin` VALUES ('彭智勇', '123456', '2');
INSERT INTO `userlogin` VALUES ('彭煜玮', '123456', '2');
INSERT INTO `userlogin` VALUES ('彭蓉', '123456', '2');
INSERT INTO `userlogin` VALUES ('徐佑军', '123456', '2');
INSERT INTO `userlogin` VALUES ('徐武平', '123456', '2');
INSERT INTO `userlogin` VALUES ('徐爱萍', '123456', '2');
INSERT INTO `userlogin` VALUES ('怀念', '123456', '2');
INSERT INTO `userlogin` VALUES ('文卫东', '123456', '2');
INSERT INTO `userlogin` VALUES ('方颖', '123456', '2');
INSERT INTO `userlogin` VALUES ('曾平', '123456', '2');
INSERT INTO `userlogin` VALUES ('曾慧', '123456', '2');
INSERT INTO `userlogin` VALUES ('曾承', '123456', '2');
