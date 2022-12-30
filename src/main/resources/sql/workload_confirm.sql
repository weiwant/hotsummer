/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : hot_summer

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2022-12-30 11:42:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for workload_confirm
-- ----------------------------
DROP TABLE IF EXISTS `workload_confirm`;
CREATE TABLE `workload_confirm` (
  `natural_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2022',
  `teacher_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '11',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '11',
  `confirm` mediumint(1) NOT NULL DEFAULT '0',
  KEY `teacher` (`teacher_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of workload_confirm
-- ----------------------------
INSERT INTO `workload_confirm` VALUES ('2022', '00002123', '黄传河', '1');
INSERT INTO `workload_confirm` VALUES ('2022', '00003683', '康卓', '1');
INSERT INTO `workload_confirm` VALUES ('2022', '00003722', '杨运伟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00005589', '应时', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00005608', '吴志健', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00005906', '彭敏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00005933', '胡继承', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006309', '李艳', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006442', '高建华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006444', '蒋华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006459', '刘娟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006549', '熊素萍', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006672', '丁建利', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006892', '姚昱', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00006990', '吕慧', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007047', '张健', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007088', '谭小琼', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007107', '彭智勇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007127', '丁立新', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007452', '涂卫平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007477', '董文永', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007531', '梁鹏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007543', '彭蓉', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007553', '何发智', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007571', '高戈', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007584', '余纯武', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007603', '杜卓敏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007728', '王中元', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007732', '夏启明', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007748', '刘进', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007851', '何璐璐', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007855', '刘婧', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00007964', '曾承', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008005', '李兵', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008253', '周国富', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008334', '赵俭辉', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008433', '胡文斌', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008434', '肖春霞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008435', '林馥', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008436', '钱铁云', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008442', '桂浩', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008444', '王玲玲', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008618', '陈曦', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008626', '王玉林', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008629', '田纲', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008661', '蔡恒进', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008672', '文卫东', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008721', '何国良', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008723', '何扬帆', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008749', '李文海', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008760', '刘峰', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008761', '王黎维', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008830', '怀念', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008842', '孔若杉', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008924', '李雪飞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008925', '冯晶', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008955', '谢榕', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008956', '龚奕利', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00008975', '孙世磊', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009059', '宋伟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009060', '刘丹丹', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009061', '刘浩文', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009062', '彭煜玮', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009087', '汪鼎文', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009088', '李晓剑', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009089', '王峰', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009090', '王翀', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009091', '王健', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009092', '贾向阳', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009154', '傅佑铭', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009206', '罗飞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009210', '何政', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009214', '张锡宁', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009239', '韩镇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009240', '周浩', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009262', '赵小刚', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009263', '张昀', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009308', '牛晓光', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009309', '邹华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009313', '叶刚', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009329', '李飞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009390', '王晓晨', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009392', '邓娟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009417', '苏科华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009418', '蔡贤涛', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009421', '陈旭', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009707', '韩波', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009741', '钟鸣', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00009742', '杜博', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010892', '常军', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010893', '唐学兵', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010903', '李晶', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010905', '李宗福', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010906', '徐佑军', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010910', '蔡朝晖', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010912', '贺莲', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010915', '刘树波', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010917', '谭成予', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010918', '余一清', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010920', '王汉飞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010923', '陈豫', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010925', '郑鹏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010927', '刘敏忠', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010928', '李蓉蓉', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010929', '李石君', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010930', '李小红', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010944', '刘纪平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010946', '袁梦霆', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010949', '李晖', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010950', '郭玮', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010967', '孟德鸿', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010968', '张华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010969', '周雅洁', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010978', '关焕梅', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010982', '陈萍', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00010984', '黄文斌', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00011001', '刘珺', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030044', '吴小莹', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030213', '刘华俊', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030214', '余伟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030382', '邹勤', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030426', '梁超', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030432', '夏桂松', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030618', '张乐飞', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030619', '肖晶', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030679', '祝园园', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030790', '朱卫平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030832', '李清安', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030840', '黄浩', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00030867', '陈丹', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00031247', '谢晓园', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00031248', '玄跻峰', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00031629', '瞿涛', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00031676', '甘庭', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00032946', '刘威威', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033000', '王增茂', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033324', '许永超', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033355', '刘舟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033385', '薛楠', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033394', '林雨恬', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033402', '罗勇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033404', '程大钊', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033502', '叶茫', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033606', '王正', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033743', '万国佳', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033807', '王胜', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033822', '辛奇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033905', '张丽华', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033919', '江佳伟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00033939', 'Muhammad Waseem', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034041', '胡创', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034043', '郑志高', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034145', '陈翠群', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034184', '王皓', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034291', '武宇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00034292', '李祖超', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200186', '熊小兵', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200192', '章登义', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200208', '曾慧', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200209', '袁志勇', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200211', '杭小庆', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200213', '喻丹丹', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200214', '胡启平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200218', '安杨', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200222', '曾平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200225', '伍春香', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200243', '陈珉', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200244', '李春葆', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200270', '刘斌', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200272', '张沪寅', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200296', '肖忠付', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200310', '胡瑞敏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200312', '徐武平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200313', '蒋晶珏', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200324', '武小平', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200511', '李明', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00200581', '胡志雄', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00201571', '罗云芳', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00201761', '朱荣', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00201776', '陈军', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00201795', '杨玉红', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00300463', '宋麟', '0');
INSERT INTO `workload_confirm` VALUES ('2022', '00300577', '杨先娣', '0');
