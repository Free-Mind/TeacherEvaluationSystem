# Host: localhost  (Version 5.6.17)
# Date: 2016-05-19 22:16:19
# Generator: MySQL-Front 5.3  (Build 5.39)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "competition_grade"
#

DROP TABLE IF EXISTS `competition_grade`;
CREATE TABLE `competition_grade` (
  `Id` varchar(255) NOT NULL DEFAULT '' COMMENT '教师编号',
  `grade` int(11) DEFAULT NULL COMMENT '竞赛成绩',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师竞赛成绩表';

#
# Data for table "competition_grade"
#

INSERT INTO `competition_grade` VALUES ('t00001',3),('t00003',3),('t00004',3);

#
# Structure for table "course"
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL DEFAULT '' COMMENT '课程名称',
  `level` varchar(255) NOT NULL DEFAULT '' COMMENT '培训层次',
  `course_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '课程类型 选修 必修',
  `course_hour` int(11) NOT NULL DEFAULT '0' COMMENT '课时',
  `start_time` date DEFAULT NULL COMMENT '课程开始时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='课程表';

#
# Data for table "course"
#

INSERT INTO `course` VALUES (6,'计算机组成原理','本科',0,84,'2016-03-16'),(12,'JAVA入门','外训',1,20,'2016-03-31'),(13,'高等数学1','轮训',0,84,'2015-07-15'),(14,'马克思原理','研究生',0,56,'2016-04-21');

#
# Structure for table "course_weight"
#

DROP TABLE IF EXISTS `course_weight`;
CREATE TABLE `course_weight` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NOT NULL DEFAULT '0' COMMENT '课程id',
  `course_weight` int(11) NOT NULL DEFAULT '0' COMMENT '课程权重 类似于学分',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='课程权重';

#
# Data for table "course_weight"
#

INSERT INTO `course_weight` VALUES (1,6,2),(2,12,1),(3,13,1),(4,14,1);

#
# Structure for table "effect_score"
#

DROP TABLE IF EXISTS `effect_score`;
CREATE TABLE `effect_score` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_course_id` int(11) DEFAULT NULL COMMENT 'teacher_course表的主键，代表以为老师选择的课程',
  `score` int(11) DEFAULT NULL COMMENT '教学效果分数',
  `admin_id` varchar(255) DEFAULT NULL COMMENT '评分员ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='教学效果评分（由管理员打分）';

#
# Data for table "effect_score"
#

INSERT INTO `effect_score` VALUES (1,12,3,'a00001'),(2,18,3,'a00001'),(3,19,3,'a00001'),(4,21,4,'a00001'),(5,22,30,'a00001');

#
# Structure for table "score"
#

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '用户id',
  `tc_id` int(11) NOT NULL DEFAULT '0' COMMENT 'teacher_course ID',
  `p1` int(11) NOT NULL DEFAULT '0' COMMENT '教学准备得分',
  `p2` int(11) NOT NULL DEFAULT '0' COMMENT '教学实施得分',
  `p3` int(11) NOT NULL DEFAULT '0' COMMENT '教学效果得分',
  `pl` int(11) NOT NULL DEFAULT '0' COMMENT '其他加减分',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='评分表';

#
# Data for table "score"
#

INSERT INTO `score` VALUES (1,'s00003',12,60,50,70,0),(2,'s00003',18,70,80,90,0),(3,'s00003',19,80,90,70,0),(16,'s00001',12,2,2,0,0),(17,'s00001',18,2,2,0,0),(18,'s00001',19,2,2,0,0);

#
# Structure for table "score_weight"
#

DROP TABLE IF EXISTS `score_weight`;
CREATE TABLE `score_weight` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `p1_i` double NOT NULL DEFAULT '0' COMMENT 'p1权重',
  `p2_i` double NOT NULL DEFAULT '0' COMMENT 'p2权重',
  `p3_i` double NOT NULL DEFAULT '0' COMMENT 'p3权重',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='评分权重';

#
# Data for table "score_weight"
#

INSERT INTO `score_weight` VALUES (1,15,50,35);

#
# Structure for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Id` varchar(16) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `organization` varchar(255) NOT NULL DEFAULT '' COMMENT '单位',
  `tech_title` varchar(255) NOT NULL DEFAULT '' COMMENT '教学职称',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别',
  `birthday` date NOT NULL DEFAULT '0000-00-00' COMMENT '出生日期',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '用户类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "teacher"
#

INSERT INTO `teacher` VALUES ('a00001','7c4a8d09ca3762af61e59520943dc26494f8941b','王明','清华大学','教授',1,'1986-01-29','admin_uncheck'),('a00002','7c4a8d09ca3762af61e59520943dc26494f8941b','刘明峰','清华大学','教授',1,'1986-01-29','admin'),('s00001','7c4a8d09ca3762af61e59520943dc26494f8941b','评分一号','清华大学','教授',1,'1974-07-18','scorer'),('s00002','7c4a8d09ca3762af61e59520943dc26494f8941b','李四四','清华大学','教务主任',0,'1969-07-16','dean'),('s00003','7c4a8d09ca3762af61e59520943dc26494f8941b','王一','清华大学','教授',1,'1968-06-04','scorer'),('s00004','7c4a8d09ca3762af61e59520943dc26494f8941b','赵一','北京大学','教务主任',0,'1969-06-18','admin'),('t00001','7c4a8d09ca3762af61e59520943dc26494f8941b','张三','清华大学','研究员',1,'1985-06-12','teacher'),('t00002','7c4a8d09ca3762af61e59520943dc26494f8941b','李四','北京大学','助教',0,'1992-06-09','teacher'),('t00003','7c4a8d09ca3762af61e59520943dc26494f8941b','张三三','清华大学','助教',1,'1991-06-11','teacher'),('t00004','7c4a8d09ca3762af61e59520943dc26494f8941b','匡啊','清华大学','助教',1,'2016-03-14','teacher');

#
# Structure for table "teacher_course"
#

DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` varchar(255) NOT NULL DEFAULT '' COMMENT '老师id',
  `u_id` varchar(255) NOT NULL DEFAULT '' COMMENT '课程id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='授课表';

#
# Data for table "teacher_course"
#

INSERT INTO `teacher_course` VALUES (12,'t00001','6'),(18,'t00001','12'),(19,'t00001','13'),(20,'t00002','6'),(21,'t00003','14'),(22,'t00004','6');

#
# Structure for table "teacher_other_grade"
#

DROP TABLE IF EXISTS `teacher_other_grade`;
CREATE TABLE `teacher_other_grade` (
  `Id` varchar(255) NOT NULL DEFAULT '' COMMENT '教师编号',
  `other_grade` int(11) DEFAULT NULL COMMENT '其他项加减分',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "teacher_other_grade"
#

INSERT INTO `teacher_other_grade` VALUES ('t00001',5),('t00003',5),('t00004',5);
