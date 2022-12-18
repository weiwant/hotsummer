/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : tms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 12/12/2022 21:20:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `faculty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `professionrank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职级',
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`id`) REFERENCES `userlogin` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00002123');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '教授', '四级', '00003683');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '高级工程师', '五级', '00003722');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '三级', '00005589');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '三级', '00005608');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '教授', '四级', '00005906');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00005933');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '副教授', '六级', '00006309');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '副教授', '五级', '00006442');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '讲师', '八级', '00006444');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '教授', '二级', '00006459');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '八级', '00006549');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '讲师', '八级', '00006672');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '七级', '00006892');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '讲师', '九级', '00006990');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00007047');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '助理研究员', '八级', '00007088');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '二级', '00007107');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00007127');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '教授', '四级', '00007452');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00007477');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00007531');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '教授', '四级', '00007543');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '三级', '00007553');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副研究员', '六级', '00007571');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00007584');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '七级', '00007603');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '三级', '00007728');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副研究员', '六级', '00007732');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '三级', '00007748');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '讲师', '九级', '00007851');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '七级', '00007855');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '六级', '00007964');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '三级', '00008005');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '六级', '00008253');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '六级', '00008334');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00008433');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00008434');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '六级', '00008435');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '教授', '四级', '00008436');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '七级', '00008442');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '讲师', '八级', '00008444');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00008618');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00008626');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00008629');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00008661');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00008672');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '六级', '00008721');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '副教授', '七级', '00008723');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00008749');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00008760');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '七级', '00008761');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副研究员', '六级', '00008830');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00008842');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00008924');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00008925');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '教授', '四级', '00008955');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '六级', '00008956');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '研究员', '四级', '00008975');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '六级', '00009059');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '七级', '00009060');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '讲师', '九级', '00009061');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00009062');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00009087');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '七级', '00009088');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '教授', '四级', '00009089');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '讲师', '八级', '00009090');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '七级', '00009091');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '讲师', '九级', '00009092');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '九级', '00009154');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '九级', '00009206');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00009210');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00009214');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00009239');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '九级', '00009240');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '七级', '00009262');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '七级', '00009263');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00009308');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00009309');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '助理研究员', '八级', '00009313');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00009329');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '九级', '00009390');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '七级', '00009392');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '六级', '00009417');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00009418');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副研究员', '七级', '00009421');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00009707');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '六级', '00009741');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00009742');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '讲师', '八级', '00010892');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00010893');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00010903');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00010905');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00010906');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '五级', '00010910');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '七级', '00010912');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00010915');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '五级', '00010917');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '八级', '00010918');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00010920');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '讲师', '十级', '00010923');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00010925');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '八级', '00010927');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '讲师', '八级', '00010928');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00010929');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '六级', '00010930');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '工程师', '八级', '00010944');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00010946');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '副教授', '六级', '00010949');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '讲师', '八级', '00010950');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '八级', '00010967');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '讲师', '八级', '00010968');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '九级', '00010969');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '副教授', '六级', '00010978');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '九级', '00010982');
INSERT INTO `userinfo` VALUES ('男', '公共课教学中心', '副教授', '五级', '00010984');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '九级', '00011001');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '副教授', '七级', '00030044');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '六级', '00030213');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00030214');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00030382');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副教授', '七级', '00030426');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00030432');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00030618');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '副教授', '七级', '00030619');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '副教授', '六级', '00030679');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '六级', '00030790');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '六级', '00030832');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00030840');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00030867');
INSERT INTO `userinfo` VALUES ('女', '软件工程系', '教授', '四级', '00031247');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00031248');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '聘期制讲师', '九级', '00031629');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '讲师', '博士后', '00031676');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00032946');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '特聘副研究员', '博士后', '00033000');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00033324');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '博士后', '博士后', '00033355');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '特聘副研究员', '七级', '00033385');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '特聘副研究员', '七级', '00033394');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00033402');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00033404');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00033502');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '四级', '00033606');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '博士后', '博士后', '00033743');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '七级', '00033807');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '副教授', '七级', '00033822');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '特聘副研究员', '七级', '00033905');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00033919');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '博士后', '博士后', '00033939');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '特聘副研究员', '七级', '00034041');
INSERT INTO `userinfo` VALUES (NULL, '计算机科学系', '博士后', '博士后', '00034043');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '博士后', '博士后', '00034145');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00034184');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00034291');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '特聘副研究员', '七级', '00034292');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '讲师', '八级', '00200186');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '三级', '00200192');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '五级', '00200208');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00200209');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00200211');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '六级', '00200213');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '四级', '00200214');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '五级', '00200218');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '五级', '00200222');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '六级', '00200225');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '教授', '三级', '00200243');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00200244');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '讲师', '八级', '00200270');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '二级', '00200272');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00200296');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '教授', '二级', '00200310');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '六级', '00200312');
INSERT INTO `userinfo` VALUES ('女', '计算机科学系', '副教授', '七级', '00200313');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '副教授', '五级', '00200324');
INSERT INTO `userinfo` VALUES ('男', '人工智能系', '副研究员', '七级', '00200511');
INSERT INTO `userinfo` VALUES ('男', '软件工程系', '讲师', '九级', '00200581');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '副教授', '五级', '00201571');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '教授', '四级', '00201761');
INSERT INTO `userinfo` VALUES ('男', '计算机科学系', '研究员', '四级', '00201776');
INSERT INTO `userinfo` VALUES ('女', '人工智能系', '副教授', '六级', '00201795');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '讲师', '九级', '00300463');
INSERT INTO `userinfo` VALUES ('女', '公共课教学中心', '副教授', '六级', '00300577');

SET FOREIGN_KEY_CHECKS = 1;
