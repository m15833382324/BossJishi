/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2020-08-27 14:03:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `boss`
-- ----------------------------
DROP TABLE IF EXISTS `boss`;
CREATE TABLE `boss` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `flush_time` varchar(255) DEFAULT NULL,
  `map_name` varchar(255) DEFAULT NULL,
  `minute` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `hour` varchar(255) DEFAULT NULL,
  `second` varchar(255) DEFAULT NULL,
  `daojishi` varchar(255) DEFAULT NULL,
  `dead_time` varchar(255) DEFAULT NULL,
  `y` varchar(255) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `tic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boss
-- ----------------------------
INSERT INTO `boss` VALUES ('5', '3600', '', null, '测试虫子1', null, null, null, '13:08:56', '', '', '1598504936892');
INSERT INTO `boss` VALUES ('6', '7200', '', null, '测试虫子2', null, null, null, '13:08:34', '', '', '1598504914255');
