/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100411
 Source Host           : localhost:3306
 Source Schema         : familydoctor

 Target Server Type    : MySQL
 Target Server Version : 100411
 File Encoding         : 65001

 Date: 27/12/2022 22:56:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `d_phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_qua` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_local` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `d_cover` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_image1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_image2` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_price` float NOT NULL,
  `dt_id` int(11) NOT NULL,
  `d_stock` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `d_mark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`d_id`) USING BTREE,
  UNIQUE INDEX `d_phone`(`d_phone`) USING BTREE,
  INDEX `dt_id`(`dt_id`) USING BTREE,
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`dt_id`) REFERENCES `doctortype` (`dt_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 100141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (100136, '13225465658', '张三', '医生', '南方医科医院', 'images/1672115621168.jpg', 'images/1672115621170.jpg', 'images/1672115621171.jpg', 300, 1, '4', '你好');
INSERT INTO `doctor` VALUES (100140, '11111111111', '王五', '医生', '人民医院', 'images/1672127682403.jpg', 'images/1672127682405.jpg', 'images/1672127682407.jpg', 200, 6, '1', '喜欢运动');

-- ----------------------------
-- Table structure for doctortype
-- ----------------------------
DROP TABLE IF EXISTS `doctortype`;
CREATE TABLE `doctortype`  (
  `dt_id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`dt_id`) USING BTREE,
  UNIQUE INDEX `dt_name`(`dt_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctortype
-- ----------------------------
INSERT INTO `doctortype` VALUES (6, '中内科');
INSERT INTO `doctortype` VALUES (1, '儿童哮喘专科');
INSERT INTO `doctortype` VALUES (2, '儿童康复专科');
INSERT INTO `doctortype` VALUES (10, '内分泌科');
INSERT INTO `doctortype` VALUES (33, '内科');
INSERT INTO `doctortype` VALUES (3, '呼吸内科');
INSERT INTO `doctortype` VALUES (7, '外科');
INSERT INTO `doctortype` VALUES (9, '心血管内科');
INSERT INTO `doctortype` VALUES (4, '新生儿科');
INSERT INTO `doctortype` VALUES (5, '神经内科');
INSERT INTO `doctortype` VALUES (8, '耳鼻喉科');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `o_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `o_total` float NOT NULL,
  `o_amount` int(11) NOT NULL,
  `o_status` int(1) NOT NULL,
  `o_paytype` int(1) NOT NULL,
  `u_id` int(11) NOT NULL,
  `o_datetime` timestamp(0) NOT NULL DEFAULT current_timestamp(0),
  `o_realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `o_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `o_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `oi_id` int(11) NOT NULL AUTO_INCREMENT,
  `oi_price` float NOT NULL,
  `oi_amount` int(11) NOT NULL,
  `d_id` int(11) NOT NULL,
  `o_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`oi_id`) USING BTREE,
  INDEX `o_id`(`o_id`) USING BTREE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`o_id`) REFERENCES `order` (`o_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_type` int(1) NOT NULL,
  `d_id` int(11) NOT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES (81, 1, 100104);
INSERT INTO `recommend` VALUES (82, 2, 100104);
INSERT INTO `recommend` VALUES (83, 3, 100104);
INSERT INTO `recommend` VALUES (100, 2, 100136);
INSERT INTO `recommend` VALUES (101, 3, 100136);
INSERT INTO `recommend` VALUES (107, 1, 100140);
INSERT INTO `recommend` VALUES (108, 2, 100140);
INSERT INTO `recommend` VALUES (109, 3, 100140);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_redgt` timestamp(0) NOT NULL DEFAULT current_timestamp(0),
  `u_role` int(1) NOT NULL,
  `u_mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  UNIQUE INDEX `u_name`(`u_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (5, 'admin', '8vudy7x0eQc=', '管理员', '2022-12-24 11:36:58', 0, '超级管理员', '12312311245', '和谐公寓');
INSERT INTO `user` VALUES (30, '111', 'ZTcVIUvuILE=', '龚俊雄', '2022-12-27 15:23:13', 1, '普通用户', '11111111111', '广东技术师范大学');

SET FOREIGN_KEY_CHECKS = 1;
