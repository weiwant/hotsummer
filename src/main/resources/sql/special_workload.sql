/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : ice_project

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2022-06-30 18:58:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for special_workload
-- ----------------------------
DROP TABLE IF EXISTS `special_workload`;
CREATE TABLE `special_workload` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `report_time` date DEFAULT NULL COMMENT '申报时间',
  `declarant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申报人',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师姓名',
  `author_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序情况/位次',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'BB类型',
  `teaching_scores` double DEFAULT NULL COMMENT '教分',
  `achievement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '成果/作品/参赛项目/参评项目/论文/专著名称',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '级别',
  `project_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程/项目/奖项/竞赛/论文指导类别',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程/项目/奖项/竞赛详细名称',
  `award_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '获奖等级',
  `award_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '获奖/获评/出版日期',
  `project_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目进展',
  `award_apartment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授奖单位',
  `publication_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '刊物/出版社名称',
  `publications_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '刊物期数/出版版次',
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ISBN号',
  `brief_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容简介',
  `receiving_honor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所获荣誉',
  `guiding_student_team` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生团队名',
  `guiding_student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生姓名',
  `guiding_student_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '指导学生学号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核状态',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段1',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of special_workload
-- ----------------------------
INSERT INTO `special_workload` VALUES ('1', '2022-06-30', '丁建利', '丁建利', '第一负责人/第二负责人', '课程建设（BB1）', null, '数据结构', '国家级/省级/校级', '一流本科课程', '', '', '', '结题', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('2', '2022-06-30', '丁建利', '蔡朝晖', '第二负责人', '课程建设（BB1）', null, '数据结构', '国家级/省级/校级', '一流本科课程', '', '', '', '结题', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('3', '2022-06-30', '丁建利', '丁建利', '第一负责人', '教研项目（BB2）', null, '计算机网络', '国家级/省级/校级', '教育部“产学研”协同创新项目', '', '', '', '建设中', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('4', '2022-06-30', '丁建利', '丁建利', '排名三', '教学成果奖（BB3）', null, '计算机网络', '国家级/省级/校级', '教学成果奖', '', '一等奖', 'XX年XX月', '', 'XXXXX学会', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('5', '2022-06-30', '丁建利', '丁建利', '排名一/二', '教学竞赛获奖（BB4）', null, '计算机网络', '国家级/省级/校级', '教学竞赛', 'XXX竞赛', '一等奖', 'XX年XX月', '', 'XXXXX学会', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('6', '2022-06-30', '丁建利', '丁建利', '第一作者/第二作者', '发表教学研究论文（BB5）', null, '基于XXX的XXX研究', '权威/核心/非核心', '', '', '', '', '', '', 'SCI', 'XX年XX期', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('7', '2022-06-30', '丁建利', '丁建利', '主编/参编', '出版教材（BB6）', null, '数据结构教程', '', '', '', '', '44348', '', '', '北京大学出版社', '第一版', '23278329', '这是一本适用于计算机专业学生的教材XXXXX', '“十四五”规划教材', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('8', '2022-06-30', '丁建利', '丁建利', '', '教学名师（BB7）', null, '教学名师', '国家级/省级/校级', '', '', '', '44348', '', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('9', '2022-06-30', '丁建利', '丁建利', '', '指导优秀毕业论文（BB8）', null, '对XXXX的研究', '', '独立指导/与院外合作指导', '', '', '', '', '', '', '', '', '', '', '', '张三同学', '', '加个学号', null, null);
INSERT INTO `special_workload` VALUES ('10', '2022-06-30', '丁建利', '丁建利', '', '指导学生竞赛获奖（BB9）', '150', '机器识别的XXXX应用', '国际/全国/省级/校级', 'A/B/C类', '微软创新杯大赛中国区决赛', '一等奖/二等奖/三等奖——加个特等', '2021年6月', '', 'XXX学会', '', '', '', '', '', '', '张三、李四、王五同学', '', '', null, null);
INSERT INTO `special_workload` VALUES ('11', '2022-06-30', '丁建利', '丁建利', '', '指导学生科研立项并顺利完成项目（BB10）', null, 'XXXX大创项目', '国际/全国/省级/校级', '大创/其他', '湖北省优秀大学生科研成果奖/其他', '一等奖/二等奖/三等奖', '44348', '', 'XXX学会', '', '', '', '', '', 'XXXX大创队伍', '张三、李四、王五同学', '', '加个学号', null, null);
INSERT INTO `special_workload` VALUES ('12', '2022-06-30', '丁建利', '丁建利', '', '教学兼职（BB11）', null, '教学单位主任', '院级', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('13', '2022-06-30', '丁建利', '丁建利', '', 'BB12', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('14', '2022-06-30', '丁建利', '丁建利', '', '本科生培养活动（BB13）', null, '', '', '卓越班选拔面试', 'XX年XX月XXX届计科卓越班面试', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('15', '2022-06-30', '丁建利', '丁建利', '', '本科生主考之外的监考（BB14）', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null);
INSERT INTO `special_workload` VALUES ('16', '2022-06-30', '丁建利', '丁建利', '', '其他（BB15）', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null);
