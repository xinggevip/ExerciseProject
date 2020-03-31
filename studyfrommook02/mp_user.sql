/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : mp

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-31 12:05:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user` (
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  KEY `manager_fk` (`manager_id`),
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `mp_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` VALUES ('1087982257332887553', '大boss', '40', 'boss@baomidou.com', null, '2019-01-11 14:20:20');
INSERT INTO `mp_user` VALUES ('1088248166370832385', '王天风', '25', 'wtf@baomidou.com', '1087982257332887553', '2019-02-05 11:12:22');
INSERT INTO `mp_user` VALUES ('1088250446457389058', '李艺伟', '28', 'lyw@baomidou.com', '1088248166370832385', '2019-02-14 08:31:16');
INSERT INTO `mp_user` VALUES ('1094590409767661570', '张雨琪', '31', 'zjq@baomidou.com', '1088248166370832385', '2019-01-14 09:15:15');
INSERT INTO `mp_user` VALUES ('1094592041087729666', '刘红雨', '32', 'lhm@baomidou.com', '1088248166370832385', '2019-01-14 09:48:16');
INSERT INTO `mp_user` VALUES ('1244808674497593345', '张三', '19', null, '1088248166370832385', '2020-03-31 02:08:10');
INSERT INTO `mp_user` VALUES ('1244832954518114305', '李四', '20', null, '1088248166370832385', '2020-03-31 03:44:38');
INSERT INTO `mp_user` VALUES ('1244835526943498241', '王五', '20', null, '1088248166370832385', '2020-03-31 03:54:52');
INSERT INTO `mp_user` VALUES ('1244837521221775362', '赵六', '20', null, '1088248166370832385', '2020-03-31 04:02:47');
