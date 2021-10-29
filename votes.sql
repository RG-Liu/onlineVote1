/*
 Navicat Premium Data Transfer

 Source Server         : votes
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : votes

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 14/10/2021 19:25:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `createtime` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  `endtime` timestamp(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (14, '今晚吃什么？', 0, '2021-10-09 19:17:45', '2021-10-17 00:00:00');
INSERT INTO `article` VALUES (17, '测试多选', 1, '2021-10-10 17:16:04', '2021-10-11 00:00:00');
INSERT INTO `article` VALUES (19, '你的爱好是什么？', 0, '2021-10-10 17:17:11', '2021-10-11 00:00:00');
INSERT INTO `article` VALUES (20, '明天出去玩吗？', 0, '2021-10-10 22:15:16', '2021-10-11 00:00:00');
INSERT INTO `article` VALUES (21, '今天吃什么？', 0, '2021-10-11 10:56:07', '2021-10-11 00:00:00');
INSERT INTO `article` VALUES (22, '王维', 1, '2021-10-11 10:57:31', '2021-10-16 00:00:00');
INSERT INTO `article` VALUES (23, '你喜欢听的音乐是什么？', 0, '2021-10-11 10:59:13', '2021-10-14 00:00:00');

-- ----------------------------
-- Table structure for optionss
-- ----------------------------
DROP TABLE IF EXISTS `optionss`;
CREATE TABLE `optionss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optionvalue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `articleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk1`(`articleid`) USING BTREE,
  CONSTRAINT `pk1` FOREIGN KEY (`articleid`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of optionss
-- ----------------------------
INSERT INTO `optionss` VALUES (42, '吃螺蛳粉', 14);
INSERT INTO `optionss` VALUES (43, '吃面', 14);
INSERT INTO `optionss` VALUES (44, '吃饭', 14);
INSERT INTO `optionss` VALUES (51, '1', 17);
INSERT INTO `optionss` VALUES (52, '2', 17);
INSERT INTO `optionss` VALUES (53, '3', 17);
INSERT INTO `optionss` VALUES (54, '4', 17);
INSERT INTO `optionss` VALUES (58, '1', 19);
INSERT INTO `optionss` VALUES (59, '2', 19);
INSERT INTO `optionss` VALUES (60, '3', 19);
INSERT INTO `optionss` VALUES (61, '1', 20);
INSERT INTO `optionss` VALUES (62, '2', 20);
INSERT INTO `optionss` VALUES (63, '3', 20);
INSERT INTO `optionss` VALUES (64, '4', 20);
INSERT INTO `optionss` VALUES (65, '1', 21);
INSERT INTO `optionss` VALUES (66, '2', 21);
INSERT INTO `optionss` VALUES (67, '3', 21);
INSERT INTO `optionss` VALUES (68, '1', 22);
INSERT INTO `optionss` VALUES (69, '2', 22);
INSERT INTO `optionss` VALUES (70, '3', 22);
INSERT INTO `optionss` VALUES (71, '4', 22);
INSERT INTO `optionss` VALUES (72, '1', 23);
INSERT INTO `optionss` VALUES (73, '2', 23);
INSERT INTO `optionss` VALUES (74, '3', 23);
INSERT INTO `optionss` VALUES (75, '4', 23);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(1) DEFAULT 0 COMMENT '类型',
  `status` int(1) DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xiaoliu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (2, 'xiaoli', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (3, 'xiaobao', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (4, 'admin', '202cb962ac59075b964b07152d234b70', 1, 1);
INSERT INTO `user` VALUES (5, 'xiaoyu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (6, 'yu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (7, 'xiaobu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (8, 'xiaotu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (9, 'yu', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (10, 'xiaoqi', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (11, 'qq', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (12, 'rr', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (13, 'aa', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (14, 'ee', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (15, 'qqq', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (16, 'll', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (17, 'oo', '202cb962ac59075b964b07152d234b70', 0, 0);
INSERT INTO `user` VALUES (18, 'www', '202cb962ac59075b964b07152d234b70', 0, 0);

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `optionid` int(11) NOT NULL,
  `voterid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk3`(`articleid`) USING BTREE,
  INDEX `pk4`(`optionid`) USING BTREE,
  INDEX `pk5`(`voterid`) USING BTREE,
  CONSTRAINT `pk3` FOREIGN KEY (`articleid`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pk4` FOREIGN KEY (`optionid`) REFERENCES `optionss` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pk5` FOREIGN KEY (`voterid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES (1, 14, 42, 4);
INSERT INTO `vote` VALUES (4, 14, 42, 3);
INSERT INTO `vote` VALUES (8, 20, 54, 3);
INSERT INTO `vote` VALUES (14, 22, 54, 3);
INSERT INTO `vote` VALUES (15, 23, 53, 4);

SET FOREIGN_KEY_CHECKS = 1;
