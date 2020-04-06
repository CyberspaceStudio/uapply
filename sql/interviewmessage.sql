/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : uapply

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/02/2020 10:40:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interviewmessage
-- ----------------------------
DROP TABLE IF EXISTS `interviewmessage`;
CREATE TABLE `interviewmessage`  (
  `user_id` int(11) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  `user_character` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `param_score1` int(11) DEFAULT NULL,
  `param_score2` int(11) DEFAULT NULL,
  `param_score3` int(11) DEFAULT NULL,
  `param_score4` int(11) DEFAULT NULL,
  `param_score5` int(11) DEFAULT NULL,
  `param_score6` int(11) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `overview` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `interview_pass` int(11) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
