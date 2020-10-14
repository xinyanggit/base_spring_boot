    /*
     Navicat Premium Data Transfer

     Source Server         : 10.10.8.205-dev
     Source Server Type    : MySQL
     Source Server Version : 50720
     Source Host           : 10.10.8.205:3306
     Source Schema         : test_db

     Target Server Type    : MySQL
     Target Server Version : 50720
     File Encoding         : 65001

     Date: 21/09/2020 15:00:15
    */
    create database test_db  ;

    SET NAMES utf8mb4;
    SET FOREIGN_KEY_CHECKS = 0;

    -- ----------------------------
    -- Table structure for user
    -- ----------------------------
    DROP TABLE IF EXISTS `user`;
    CREATE TABLE `user`  (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
      `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
      `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '111111' COMMENT '密码',
      `version` smallint(6) NULL DEFAULT 1 COMMENT '版本号',
      `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
      `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
      PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

    -- ----------------------------
    -- Records of user
    -- ----------------------------
    INSERT INTO `user` VALUES (1, 'zhangsan', '张三', '111111', 1, '2020-09-18 15:50:24', '2020-09-18 15:50:24');
    INSERT INTO `user` VALUES (2, 'lisi', '李四', '111111', 1, '2020-09-18 15:50:35', '2020-09-18 15:50:35');
    INSERT INTO `user` VALUES (3, 'wangwu', '王五', '111111', 1, '2020-09-18 15:50:47', '2020-09-18 15:50:47');
    INSERT INTO `user` VALUES (4, 'gouliu', '狗六', '111111', 1, '2020-09-18 15:52:24', '2020-09-18 15:52:24');
    INSERT INTO `user` VALUES (5, 'yx1', '杨新', '111111', 1, '2020-09-18 15:52:35', '2020-09-18 15:52:35');
    INSERT INTO `user` VALUES (6, 'test', '测试', '111111', 1, '2020-09-18 15:52:45', '2020-09-18 15:52:45');

    SET FOREIGN_KEY_CHECKS = 1;
