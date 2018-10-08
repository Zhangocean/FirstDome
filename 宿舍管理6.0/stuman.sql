/*
MySQL Data Transfer
Source Host: localhost
Source Database: stuman
Target Host: localhost
Target Database: stuman
Date: 2017/12/15 9:12:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_dorm
-- ----------------------------
CREATE TABLE `t_dorm` (
  `dormName` char(20) NOT NULL default '',
  `dormDes` char(20) NOT NULL default '',
  `dormId` char(20) NOT NULL default '0',
  PRIMARY KEY  (`dormDes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_stu
-- ----------------------------
CREATE TABLE `t_stu` (
  `stuName` char(20) NOT NULL default '',
  `stuNum` char(20) NOT NULL default '',
  `sex` char(10) default NULL,
  `birthday` char(30) default NULL,
  `dormid` char(10) default '0',
  `email` char(30) default NULL,
  `stuDes` char(20) default NULL,
  `stuId` int(10) default NULL,
  PRIMARY KEY  (`stuNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `userName` char(10) NOT NULL,
  `password` char(20) NOT NULL,
  `classname` int(5) default NULL,
  PRIMARY KEY  (`userName`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_dorm` VALUES ('111', '111sftsdjafkhdasf', '111');
INSERT INTO `t_dorm` VALUES ('777', '1313', '777');
INSERT INTO `t_dorm` VALUES ('222', '2222222222222222', '222');
INSERT INTO `t_dorm` VALUES ('555', 'asdasd', '555');
INSERT INTO `t_dorm` VALUES ('444', 'fdsfsdf', '444');
INSERT INTO `t_dorm` VALUES ('666', 'qqqqq', '666');
INSERT INTO `t_dorm` VALUES ('999', '违法范德萨发斯蒂芬', '999');
INSERT INTO `t_dorm` VALUES ('888', '驱蚊器翁111qq11', '888');
INSERT INTO `t_stu` VALUES ('阿萨', '12312', '男', '2017-12-03', '444', '12324@qq.com', '做线程做线程做线程', '12312');
INSERT INTO `t_stu` VALUES ('哈4', '12423', '男', '2017-12-04', '555', '12324@qq.com', '32423', '12423');
INSERT INTO `t_stu` VALUES ('张海洋', '144601042', '男', '2011-02-09', '666', '12324@qq.com', '我你速度', '144601042');
INSERT INTO `t_stu` VALUES ('占xiao', '2', '女', '2017-12-04', '222', '32323@qq.com', '答案动次打次', '2');
INSERT INTO `t_stu` VALUES ('册', '2323423', '女', '2017-12-05', '555', '98989@qq.com', '234234', '2323423');
INSERT INTO `t_stu` VALUES ('张哈啊啊啊啊', '324234', '女', '2017-12-03', '222', '12324@qq.com', '2342', '324234');
INSERT INTO `t_stu` VALUES ('急急急', '438774837', '女', '2017-11-28', '666', '11111@qq.com', 'sdyufysudyfudsuyf', '438774837');
INSERT INTO `t_stu` VALUES ('44', '444', '女', '2017-12-13', '777', '12324@qq.com', '444', '444');
INSERT INTO `t_stu` VALUES ('海洋', '4565', '男', '2017-12-05', '555', '12324@qq.com', '45645654', '4565');
INSERT INTO `t_stu` VALUES ('测试', '5545', '女', '2017-12-04', '444', '12324@qq.com', '54454', '5545');
INSERT INTO `t_stu` VALUES ('得到', '657', '女', '2017-12-04', '999', '33333@qq.com', '56756756', '657');
INSERT INTO `t_user` VALUES ('000', '000', '1');
INSERT INTO `t_user` VALUES ('0000000', '00000000', '1');
INSERT INTO `t_user` VALUES ('000000000', '000000000000', '0');
INSERT INTO `t_user` VALUES ('222', '222', '0');
INSERT INTO `t_user` VALUES ('333', '333', '0');
INSERT INTO `t_user` VALUES ('33333', '333333', '1');
INSERT INTO `t_user` VALUES ('444', '444', '0');
INSERT INTO `t_user` VALUES ('555', '555', '1');
INSERT INTO `t_user` VALUES ('666', '666', '1');
INSERT INTO `t_user` VALUES ('6666', '66666', '0');
INSERT INTO `t_user` VALUES ('999', '999', '1');
INSERT INTO `t_user` VALUES ('qwe', '111', '1');
INSERT INTO `t_user` VALUES ('张海洋', '111', '0');
INSERT INTO `t_user` VALUES ('张海洋', '222', '0');
