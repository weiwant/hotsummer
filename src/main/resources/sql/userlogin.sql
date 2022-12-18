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

 Date: 12/12/2022 21:20:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin`  (
  `userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `identify` int NOT NULL DEFAULT 2,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('00033939', 'Muhammad Waseem', '855833', 2);
INSERT INTO `userlogin` VALUES ('00006672', '丁建利', '276811', 2);
INSERT INTO `userlogin` VALUES ('00007127', '丁立新', '134037', 2);
INSERT INTO `userlogin` VALUES ('00033743', '万国佳', '225412', 2);
INSERT INTO `userlogin` VALUES ('00200225', '伍春香', '011225', 2);
INSERT INTO `userlogin` VALUES ('00007553', '何发智', '164317', 2);
INSERT INTO `userlogin` VALUES ('00008721', '何国良', '21833X', 2);
INSERT INTO `userlogin` VALUES ('00008723', '何扬帆', '274024', 2);
INSERT INTO `userlogin` VALUES ('00009210', '何政', '230019', 2);
INSERT INTO `userlogin` VALUES ('00007851', '何璐璐', '314082', 2);
INSERT INTO `userlogin` VALUES ('00010918', '余一清', '015072', 2);
INSERT INTO `userlogin` VALUES ('00030214', '余伟', '288719', 2);
INSERT INTO `userlogin` VALUES ('00007584', '余纯武', '303218', 2);
INSERT INTO `userlogin` VALUES ('00009154', '傅佑铭', '111211', 2);
INSERT INTO `userlogin` VALUES ('00010978', '关焕梅', '104848', 2);
INSERT INTO `userlogin` VALUES ('00008925', '冯晶', '290216', 2);
INSERT INTO `userlogin` VALUES ('00009060', '刘丹丹', '014028', 2);
INSERT INTO `userlogin` VALUES ('00030213', '刘华俊', '120077', 2);
INSERT INTO `userlogin` VALUES ('00032946', '刘威威', '110015', 2);
INSERT INTO `userlogin` VALUES ('00006459', '刘娟', '111220', 2);
INSERT INTO `userlogin` VALUES ('00007855', '刘婧', '153687', 2);
INSERT INTO `userlogin` VALUES ('00008760', '刘峰', '195814', 2);
INSERT INTO `userlogin` VALUES ('00010927', '刘敏忠', '154894', 2);
INSERT INTO `userlogin` VALUES ('00200270', '刘斌', '194098', 2);
INSERT INTO `userlogin` VALUES ('00010915', '刘树波', '254959', 2);
INSERT INTO `userlogin` VALUES ('00009061', '刘浩文', '177656', 2);
INSERT INTO `userlogin` VALUES ('00011001', '刘珺', '054824', 2);
INSERT INTO `userlogin` VALUES ('00010944', '刘纪平', '074817', 2);
INSERT INTO `userlogin` VALUES ('00033355', '刘舟', '150033', 2);
INSERT INTO `userlogin` VALUES ('00007748', '刘进', '214074', 2);
INSERT INTO `userlogin` VALUES ('00009313', '叶刚', '177714', 2);
INSERT INTO `userlogin` VALUES ('00033502', '叶茫', '22823X', 2);
INSERT INTO `userlogin` VALUES ('00006990', '吕慧', '110020', 2);
INSERT INTO `userlogin` VALUES ('00030044', '吴小莹', '291221', 2);
INSERT INTO `userlogin` VALUES ('00005608', '吴志健', '244836', 2);
INSERT INTO `userlogin` VALUES ('00008253', '周国富', '270039', 2);
INSERT INTO `userlogin` VALUES ('00009240', '周浩', '02497X', 2);
INSERT INTO `userlogin` VALUES ('00010969', '周雅洁', '160026', 2);
INSERT INTO `userlogin` VALUES ('00010893', '唐学兵', '094850', 2);
INSERT INTO `userlogin` VALUES ('00200213', '喻丹丹', '09404X', 2);
INSERT INTO `userlogin` VALUES ('00007732', '夏启明', '074032', 2);
INSERT INTO `userlogin` VALUES ('00030432', '夏桂松', '239139', 2);
INSERT INTO `userlogin` VALUES ('00006892', '姚昱', '281241', 2);
INSERT INTO `userlogin` VALUES ('00008842', '孔若杉', '244017', 2);
INSERT INTO `userlogin` VALUES ('00008975', '孙世磊', '120310', 2);
INSERT INTO `userlogin` VALUES ('00010967', '孟德鸿', '140042', 2);
INSERT INTO `userlogin` VALUES ('00200218', '安杨', '195642', 2);
INSERT INTO `userlogin` VALUES ('00009059', '宋伟', '191018', 2);
INSERT INTO `userlogin` VALUES ('00300463', '宋麟', '117325', 2);
INSERT INTO `userlogin` VALUES ('00010892', '常军', '041911', 2);
INSERT INTO `userlogin` VALUES ('00005589', '应时', '017715', 2);
INSERT INTO `userlogin` VALUES ('00003683', '康卓', '174819', 2);
INSERT INTO `userlogin` VALUES ('00033905', '张丽华', '231421', 2);
INSERT INTO `userlogin` VALUES ('00030618', '张乐飞', '205510', 2);
INSERT INTO `userlogin` VALUES ('00007047', '张健', '270016', 2);
INSERT INTO `userlogin` VALUES ('00010968', '张华', '114812', 2);
INSERT INTO `userlogin` VALUES ('00009263', '张昀', '190821', 2);
INSERT INTO `userlogin` VALUES ('00200272', '张沪寅', '214832', 2);
INSERT INTO `userlogin` VALUES ('00009214', '张锡宁', '020034', 2);
INSERT INTO `userlogin` VALUES ('00005906', '彭敏', '100540', 2);
INSERT INTO `userlogin` VALUES ('00007107', '彭智勇', '182471', 2);
INSERT INTO `userlogin` VALUES ('00009062', '彭煜玮', '215135', 2);
INSERT INTO `userlogin` VALUES ('00007543', '彭蓉', '172424', 2);
INSERT INTO `userlogin` VALUES ('00010906', '徐佑军', '020370', 2);
INSERT INTO `userlogin` VALUES ('00200312', '徐武平', '243134', 2);
INSERT INTO `userlogin` VALUES ('00008830', '怀念', '044979', 2);
INSERT INTO `userlogin` VALUES ('00008672', '文卫东', '147717', 2);
INSERT INTO `userlogin` VALUES ('00200222', '曾平', '264869', 2);
INSERT INTO `userlogin` VALUES ('00200208', '曾慧', '274047', 2);
INSERT INTO `userlogin` VALUES ('00007964', '曾承', '124299', 2);
INSERT INTO `userlogin` VALUES ('00030790', '朱卫平', '200690', 2);
INSERT INTO `userlogin` VALUES ('00201761', '朱荣', '20361X', 2);
INSERT INTO `userlogin` VALUES ('00008005', '李兵', '30083X', 2);
INSERT INTO `userlogin` VALUES ('00010905', '李宗福', '044835', 2);
INSERT INTO `userlogin` VALUES ('00010930', '李小红', '033721', 2);
INSERT INTO `userlogin` VALUES ('00008749', '李文海', '090511', 2);
INSERT INTO `userlogin` VALUES ('00200511', '李明', '180555', 2);
INSERT INTO `userlogin` VALUES ('00200244', '李春葆', '194152', 2);
INSERT INTO `userlogin` VALUES ('00009088', '李晓剑', '054256', 2);
INSERT INTO `userlogin` VALUES ('00010949', '李晖', '174437', 2);
INSERT INTO `userlogin` VALUES ('00010903', '李晶', '044875', 2);
INSERT INTO `userlogin` VALUES ('00030832', '李清安', '052013', 2);
INSERT INTO `userlogin` VALUES ('00010929', '李石君', '022517', 2);
INSERT INTO `userlogin` VALUES ('00034292', '李祖超', '206139', 2);
INSERT INTO `userlogin` VALUES ('00006309', '李艳', '260027', 2);
INSERT INTO `userlogin` VALUES ('00010928', '李蓉蓉', '080148', 2);
INSERT INTO `userlogin` VALUES ('00008924', '李雪飞', '040513', 2);
INSERT INTO `userlogin` VALUES ('00009329', '李飞', '014473', 2);
INSERT INTO `userlogin` VALUES ('00007603', '杜卓敏', '234461', 2);
INSERT INTO `userlogin` VALUES ('00009742', '杜博', '280037', 2);
INSERT INTO `userlogin` VALUES ('00300577', '杨先娣', '23502X', 2);
INSERT INTO `userlogin` VALUES ('00201795', '杨玉红', '070089', 2);
INSERT INTO `userlogin` VALUES ('00003722', '杨运伟', '184919', 2);
INSERT INTO `userlogin` VALUES ('00200211', '杭小庆', '144878', 2);
INSERT INTO `userlogin` VALUES ('00033394', '林雨恬', '084526', 2);
INSERT INTO `userlogin` VALUES ('00008435', '林馥', '222011', 2);
INSERT INTO `userlogin` VALUES ('00008442', '桂浩', '012834', 2);
INSERT INTO `userlogin` VALUES ('00030426', '梁超', '274131', 2);
INSERT INTO `userlogin` VALUES ('00007531', '梁鹏', '305012', 2);
INSERT INTO `userlogin` VALUES ('00034291', '武宇', '010372', 2);
INSERT INTO `userlogin` VALUES ('00200324', '武小平', '062938', 2);
INSERT INTO `userlogin` VALUES ('00033919', '江佳伟', '066414', 2);
INSERT INTO `userlogin` VALUES ('00009087', '汪鼎文', '132610', 2);
INSERT INTO `userlogin` VALUES ('00007452', '涂卫平', '03002X', 2);
INSERT INTO `userlogin` VALUES ('00200186', '熊小兵', '051733', 2);
INSERT INTO `userlogin` VALUES ('00006549', '熊素萍', '254827', 2);
INSERT INTO `userlogin` VALUES ('00009308', '牛晓光', '140019', 2);
INSERT INTO `userlogin` VALUES ('00031248', '玄跻峰', '200615', 2);
INSERT INTO `userlogin` VALUES ('00007728', '王中元', '152752', 2);
INSERT INTO `userlogin` VALUES ('00009091', '王健', '012514', 2);
INSERT INTO `userlogin` VALUES ('00033000', '王增茂', '021314', 2);
INSERT INTO `userlogin` VALUES ('00009089', '王峰', '130109', 2);
INSERT INTO `userlogin` VALUES ('00009390', '王晓晨', '120013', 2);
INSERT INTO `userlogin` VALUES ('00033606', '王正', '193278', 2);
INSERT INTO `userlogin` VALUES ('00010920', '王汉飞', '104410', 2);
INSERT INTO `userlogin` VALUES ('00008626', '王玉林', '092158', 2);
INSERT INTO `userlogin` VALUES ('00008444', '王玲玲', '281624', 2);
INSERT INTO `userlogin` VALUES ('00034184', '王皓', '065815', 2);
INSERT INTO `userlogin` VALUES ('00009090', '王翀', '022049', 2);
INSERT INTO `userlogin` VALUES ('00033807', '王胜', '234818', 2);
INSERT INTO `userlogin` VALUES ('00008761', '王黎维', '153220', 2);
INSERT INTO `userlogin` VALUES ('00031676', '甘庭', '082035', 2);
INSERT INTO `userlogin` VALUES ('00008629', '田纲', '164011', 2);
INSERT INTO `userlogin` VALUES ('00031629', '瞿涛', '120315', 2);
INSERT INTO `userlogin` VALUES ('00030679', '祝园园', '111042', 2);
INSERT INTO `userlogin` VALUES ('00033404', '程大钊', '210519', 2);
INSERT INTO `userlogin` VALUES ('00200192', '章登义', '074078', 2);
INSERT INTO `userlogin` VALUES ('00201571', '罗云芳', '214048', 2);
INSERT INTO `userlogin` VALUES ('00033402', '罗勇', '184336', 2);
INSERT INTO `userlogin` VALUES ('00009206', '罗飞', '130817', 2);
INSERT INTO `userlogin` VALUES ('00200296', '肖忠付', '025330', 2);
INSERT INTO `userlogin` VALUES ('00008434', '肖春霞', '03463X', 2);
INSERT INTO `userlogin` VALUES ('00030619', '肖晶', '064182', 2);
INSERT INTO `userlogin` VALUES ('00034041', '胡创', '130039', 2);
INSERT INTO `userlogin` VALUES ('00200214', '胡启平', '104098', 2);
INSERT INTO `userlogin` VALUES ('00200581', '胡志雄', '254177', 2);
INSERT INTO `userlogin` VALUES ('00008433', '胡文斌', '137613', 2);
INSERT INTO `userlogin` VALUES ('00200310', '胡瑞敏', '013317', 2);
INSERT INTO `userlogin` VALUES ('00005933', '胡继承', '014015', 2);
INSERT INTO `userlogin` VALUES ('00009417', '苏科华', '205393', 2);
INSERT INTO `userlogin` VALUES ('00007477', '董文永', '225019', 2);
INSERT INTO `userlogin` VALUES ('00006444', '蒋华', '087719', 2);
INSERT INTO `userlogin` VALUES ('00200313', '蒋晶珏', '073641', 2);
INSERT INTO `userlogin` VALUES ('00008661', '蔡恒进', '05041X', 2);
INSERT INTO `userlogin` VALUES ('00010910', '蔡朝晖', '145788', 2);
INSERT INTO `userlogin` VALUES ('00009418', '蔡贤涛', '190050', 2);
INSERT INTO `userlogin` VALUES ('00033385', '薛楠', '200015', 2);
INSERT INTO `userlogin` VALUES ('00200209', '袁志勇', '174013', 2);
INSERT INTO `userlogin` VALUES ('00010946', '袁梦霆', '050336', 2);
INSERT INTO `userlogin` VALUES ('00033324', '许永超', '155916', 2);
INSERT INTO `userlogin` VALUES ('00031247', '谢晓园', '302320', 2);
INSERT INTO `userlogin` VALUES ('00008955', '谢榕', '160421', 2);
INSERT INTO `userlogin` VALUES ('00007088', '谭小琼', '230062', 2);
INSERT INTO `userlogin` VALUES ('00010917', '谭成予', '164822', 2);
INSERT INTO `userlogin` VALUES ('00010912', '贺莲', '254963', 2);
INSERT INTO `userlogin` VALUES ('00009092', '贾向阳', '101514', 2);
INSERT INTO `userlogin` VALUES ('00008334', '赵俭辉', '024095', 2);
INSERT INTO `userlogin` VALUES ('00009262', '赵小刚', '094816', 2);
INSERT INTO `userlogin` VALUES ('00033822', '辛奇', '213138', 2);
INSERT INTO `userlogin` VALUES ('00009392', '邓娟', '305561', 2);
INSERT INTO `userlogin` VALUES ('00030382', '邹勤', '087359', 2);
INSERT INTO `userlogin` VALUES ('00009309', '邹华', '202853', 2);
INSERT INTO `userlogin` VALUES ('00034043', '郑志高', '208439', 2);
INSERT INTO `userlogin` VALUES ('00010925', '郑鹏', '016913', 2);
INSERT INTO `userlogin` VALUES ('00010950', '郭玮', '244830', 2);
INSERT INTO `userlogin` VALUES ('00009741', '钟鸣', '19081X', 2);
INSERT INTO `userlogin` VALUES ('00008436', '钱铁云', '204088', 2);
INSERT INTO `userlogin` VALUES ('00030867', '陈丹', '112608', 2);
INSERT INTO `userlogin` VALUES ('00201776', '陈军', '265038', 2);
INSERT INTO `userlogin` VALUES ('00009421', '陈旭', '121218', 2);
INSERT INTO `userlogin` VALUES ('00008618', '陈曦', '301832', 2);
INSERT INTO `userlogin` VALUES ('00200243', '陈珉', '101455', 2);
INSERT INTO `userlogin` VALUES ('00034145', '陈翠群', '04472X', 2);
INSERT INTO `userlogin` VALUES ('00010982', '陈萍', '121624', 2);
INSERT INTO `userlogin` VALUES ('00010923', '陈豫', '094025', 2);
INSERT INTO `userlogin` VALUES ('00009707', '韩波', '081217', 2);
INSERT INTO `userlogin` VALUES ('00009239', '韩镇', '080013', 2);
INSERT INTO `userlogin` VALUES ('00006442', '高建华', '270018', 2);
INSERT INTO `userlogin` VALUES ('00007571', '高戈', '114155', 2);
INSERT INTO `userlogin` VALUES ('00002123', '黄传河', '164935', 2);
INSERT INTO `userlogin` VALUES ('00010984', '黄文斌', '312012', 2);
INSERT INTO `userlogin` VALUES ('00030840', '黄浩', '155670', 2);
INSERT INTO `userlogin` VALUES ('00008956', '龚奕利', '172028', 2);

SET FOREIGN_KEY_CHECKS = 1;
