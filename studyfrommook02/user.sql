/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : mp

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-31 13:24:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `manager_fk` (`manager_id`) USING BTREE,
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1087982257332887553', '大boss', '40', 'boss@baomidou.com', null, '2019-01-11 14:20:20');
INSERT INTO `user` VALUES ('1088248166370832385', '王天风', '25', 'wtf@baomidou.com', '1087982257332887553', '2019-02-05 11:12:22');
INSERT INTO `user` VALUES ('1088250446457389058', '李艺伟', '28', 'lyw@baomidou.com', '1088248166370832385', '2019-02-14 08:31:16');
INSERT INTO `user` VALUES ('1094590409767661570', '张雨琪', '31', 'zjq@baomidou.com', '1088248166370832385', '2019-01-14 09:15:15');
INSERT INTO `user` VALUES ('1094592041087729666', '刘红雨', '32', 'lhm@baomidou.com', '1088248166370832385', '2019-01-14 09:48:16');
INSERT INTO `user` VALUES ('1244808674497593345', '张三', '19', null, '1088248166370832385', '2020-03-31 02:08:10');
INSERT INTO `user` VALUES ('1244832954518114305', '李四', '20', null, '1088248166370832385', '2020-03-31 03:44:38');
INSERT INTO `user` VALUES ('1244835526943498241', '王五', '20', null, '1088248166370832385', '2020-03-31 03:54:52');
INSERT INTO `user` VALUES ('1244837521221775362', '赵六', '20', null, '1088248166370832385', '2020-03-31 04:02:47');
INSERT INTO `user` VALUES ('1244856051883417602', '赵六', '20', 'zs@qq.com', '1088248166370832385', '2020-03-31 05:16:25');
