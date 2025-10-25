/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 19/10/2023 15:43:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_attach
-- ----------------------------
DROP TABLE IF EXISTS `biz_attach`;
CREATE TABLE `biz_attach`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attach_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件ID',
  `folder_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件夹ID',
  `old_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原文件名',
  `new_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新文件名',
  `ext_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `secret` int(1) NULL DEFAULT NULL COMMENT '密级 1 普通 2秘密 3机密 4绝密',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `modules` int(10) NULL DEFAULT NULL COMMENT '所属模块',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件版本',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除 1 正常 2 删除',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`, `attach_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_attach
-- ----------------------------
INSERT INTO `biz_attach` VALUES (9, 'e523f45f-cc61-4a09-9889-f4d9dd64ed8b', 'e3d69d62-92ea-4568-a15d-f76c3299294d', '123456.pdf', '1532f71c-7b86-4dcc-b7f4-4716e830d1c6.pdf', 'pdf', 1, '/profile/upload/2023/05/25/1532f71c-7b86-4dcc-b7f4-4716e830d1c6.pdf', NULL, 29025, '1', 1, 'admin', '2023-05-25 15:58:22', NULL, NULL);
INSERT INTO `biz_attach` VALUES (10, '74b4eb18-d053-4477-8ce4-bd8da5c7e6df', '53f644b8-22e0-4a0b-a00a-11f6e3bba1c2', '完整性校验.docx', '8e14c24b-0240-4488-84a1-715502e31e3e.docx', 'docx', 1, '/profile/upload/2023/05/25/8e14c24b-0240-4488-84a1-715502e31e3e.docx', NULL, 11186, '1', 1, 'admin', '2023-05-25 15:58:45', NULL, NULL);
INSERT INTO `biz_attach` VALUES (12, '54b62806-0e44-4559-a5dd-50c288c2732b', '53f644b8-22e0-4a0b-a00a-11f6e3bba1c2', '123456 - 副本.pdf', 'e3253ffe-1663-4e78-a361-1c2ce3efba57.pdf', 'pdf', 1, '/profile/upload/2023/05/25/e3253ffe-1663-4e78-a361-1c2ce3efba57.pdf', NULL, 29025, '1', 1, 'admin', '2023-05-25 16:01:39', NULL, NULL);
INSERT INTO `biz_attach` VALUES (14, '254d6e3e-984e-4955-9f96-c46e3fda715d', '987f6208-ab4a-413d-8a89-fcca2bbd9581', '新建文本文档.txt', '34ceef97-efb8-4b0f-9485-be800b9ed05b.txt', 'txt', 1, '/profile/upload/2023/05/25/34ceef97-efb8-4b0f-9485-be800b9ed05b.txt', NULL, 5572, '1', 1, 'admin', '2023-05-25 22:09:50', NULL, NULL);
INSERT INTO `biz_attach` VALUES (16, '1f483e52-2432-486a-83e4-4151710a4817', '0', 'mock.ofd', '11b3dced-b13d-480c-a6ac-08eaeb637558.ofd', 'ofd', 1, '/profile/upload/2023/05/26/11b3dced-b13d-480c-a6ac-08eaeb637558.ofd', NULL, 1269758, '1', 1, 'admin', '2023-05-26 11:13:53', NULL, NULL);
INSERT INTO `biz_attach` VALUES (19, '84b5c1ad-9cbd-4f4b-a491-c4d41dcb2f9d', '0', '123456.pdf', 'cf236adb-3300-4c80-8423-b2120c5dd3d9.pdf', 'pdf', 2, '/profile/upload/2023/05/26/cf236adb-3300-4c80-8423-b2120c5dd3d9.pdf', NULL, 29025, '1', 1, 'ry', '2023-05-26 11:43:16', NULL, NULL);

-- ----------------------------
-- Table structure for biz_file_folder
-- ----------------------------
DROP TABLE IF EXISTS `biz_file_folder`;
CREATE TABLE `biz_file_folder`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `folder_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件夹ID',
  `folder_parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上级文件夹ID',
  `folder_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件夹名称',
  `del_flag` int(1) NOT NULL DEFAULT 1 COMMENT '删除标记 1 正常 2 删除',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `folder_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务文件夹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_file_folder
-- ----------------------------
INSERT INTO `biz_file_folder` VALUES (11, NULL, '0', '-1', '根目录', 1, 'admin', '2023-05-22 17:04:57', NULL, NULL);
INSERT INTO `biz_file_folder` VALUES (21, NULL, '53f644b8-22e0-4a0b-a00a-11f6e3bba1c2', '0', '电力模块', 1, 'admin', '2023-05-25 15:54:44', NULL, NULL);
INSERT INTO `biz_file_folder` VALUES (23, NULL, '26f47fa7-0b05-4d15-a6ec-42fa4b3734fb', '0', '饮水工程', 1, 'admin', '2023-05-25 15:55:59', 'admin', NULL);
INSERT INTO `biz_file_folder` VALUES (26, NULL, 'e3d69d62-92ea-4568-a15d-f76c3299294d', '53f644b8-22e0-4a0b-a00a-11f6e3bba1c2', '金福小区', 1, 'admin', '2023-05-25 15:57:18', NULL, NULL);
INSERT INTO `biz_file_folder` VALUES (27, NULL, '851cd617-f26e-490a-82a1-219d854c0089', '26f47fa7-0b05-4d15-a6ec-42fa4b3734fb', '丰宁小区', 1, 'admin', '2023-05-25 15:57:55', NULL, NULL);
INSERT INTO `biz_file_folder` VALUES (29, NULL, '987f6208-ab4a-413d-8a89-fcca2bbd9581', '26f47fa7-0b05-4d15-a6ec-42fa4b3734fb', '金福地南区', 1, 'admin', '2023-05-25 22:09:30', 'admin', NULL);
INSERT INTO `biz_file_folder` VALUES (30, NULL, 'df2ec7af-a630-41a9-a24e-c1339d85ab38', '53f644b8-22e0-4a0b-a00a-11f6e3bba1c2', '馨予园', 1, 'admin', '2023-05-26 10:55:53', 'admin', NULL);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (3, 'biz_file_folder', '业务文件夹表', NULL, NULL, 'BizFileFolder', 'crud', 'com.cb.system', 'system', 'folder', '业务文件夹', 'ruoyi', '0', '/', '{}', 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10', NULL);
INSERT INTO `gen_table` VALUES (4, 'v_folder_or_file', '文件夹和文件', NULL, NULL, 'VFolderOrFile', 'crud', 'com.cb.system', 'filemanage', 'file', '文件夹和视图', 'ruoyi', '0', '/', '{}', 'admin', '2023-05-19 23:10:07', '', '2023-05-19 23:13:01', NULL);
INSERT INTO `gen_table` VALUES (5, 'biz_attach', '业务文件表', NULL, NULL, 'BizAttach', 'crud', 'com.cb.system', 'system', 'attach', '业务文件', 'ruoyi', '0', '/', '{}', 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32', NULL);
INSERT INTO `gen_table` VALUES (6, 'sys_common_config', '通用配置', NULL, NULL, 'SysCommonConfig', 'crud', 'com.cb.system', 'system', 'config', '系统通用配置', 'ouyang', '0', '/', '{\"parentMenuId\":1}', 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (29, '3', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (30, '3', 'sort', '排序', 'int(10)', 'Integer', 'sort', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 2, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (31, '3', 'folder_id', '文件夹ID', 'varchar(50)', 'String', 'folderId', '1', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (32, '3', 'folder_prent_id', '上级文件夹ID', 'varchar(50)', 'String', 'folderPrentId', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 4, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (33, '3', 'folder_name', '文件夹名称', 'varchar(255)', 'String', 'folderName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (34, '3', 'del_flag', '删除标记 1 正常 2 删除', 'int(1)', 'Integer', 'delFlag', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', 'del_flag', 6, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (35, '3', 'create_by', NULL, 'varchar(255)', 'String', 'createBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (36, '3', 'create_time', NULL, 'datetime', 'Date', 'createTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (37, '3', 'update_by', NULL, 'varchar(255)', 'String', 'updateBy', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (38, '3', 'update_time', NULL, 'datetime', 'Date', 'updateTime', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-05-15 14:57:57', '', '2023-05-19 16:07:10');
INSERT INTO `gen_table_column` VALUES (39, '4', 'id', NULL, 'varchar(50)', 'String', 'id', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-19 23:10:07', '', '2023-05-19 23:13:01');
INSERT INTO `gen_table_column` VALUES (40, '4', 'parent_id', NULL, 'varchar(50)', 'String', 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-05-19 23:10:07', '', '2023-05-19 23:13:01');
INSERT INTO `gen_table_column` VALUES (41, '4', 'name', NULL, 'varchar(255)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-05-19 23:10:07', '', '2023-05-19 23:13:01');
INSERT INTO `gen_table_column` VALUES (42, '4', 'type', NULL, 'bigint(20)', 'Long', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-05-19 23:10:07', '', '2023-05-19 23:13:01');
INSERT INTO `gen_table_column` VALUES (43, '5', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (44, '5', 'attach_id', '附件ID', 'varchar(50)', 'String', 'attachId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (45, '5', 'folder_id', '文件夹ID', 'varchar(50)', 'String', 'folderId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (46, '5', 'old_name', '原文件名', 'varchar(255)', 'String', 'oldName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (47, '5', 'new_name', '新文件名', 'varchar(255)', 'String', 'newName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (48, '5', 'ext_name', '文件后缀', 'varchar(255)', 'String', 'extName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (49, '5', 'secret', '密级 1 普通 2秘密 3机密 4绝密', 'int(1)', 'Integer', 'secret', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'secret', 7, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (50, '5', 'path', '存储路径', 'varchar(255)', 'String', 'path', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'fileUpload', '', 8, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (51, '5', 'modules', '所属模块', 'int(10)', 'Integer', 'modules', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (52, '5', 'file_size', '文件大小', 'double', 'Long', 'fileSize', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (53, '5', 'version', '文件版本', 'varchar(255)', 'String', 'version', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (54, '5', 'del_flag', '是否删除 1 正常 2 删除', 'int(1)', 'Integer', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (55, '5', 'create_by', '创建人', 'varchar(255)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (56, '5', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (57, '5', 'update_by', '更新人', 'varchar(255)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (58, '5', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2023-05-22 17:38:01', '', '2023-05-23 12:33:32');
INSERT INTO `gen_table_column` VALUES (59, '6', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (60, '6', 'enable_three_member', '三员配置 0 正常 1 停用', 'int(1)', 'Integer', 'enableThreeMember', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (61, '6', 'enable_password_policy', '是否开启密码策略', 'int(1)', 'Integer', 'enablePasswordPolicy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (62, '6', 'password_policy', '密码策略', 'int(1)', 'Integer', 'passwordPolicy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (63, '6', 'password_length', '密码长度', 'int(2)', 'Integer', 'passwordLength', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (64, '6', 'password_expiration', '密码过期时间', 'int(5)', 'Integer', 'passwordExpiration', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (65, '6', 'password_errors_no', '密码错误次数', 'int(2)', 'Integer', 'passwordErrorsNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (66, '6', 'password_lock_time', '错误锁定时间', 'int(2)', 'Integer', 'passwordLockTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (67, '6', 'password_reminder_time', '密码到期提醒', 'int(1)', 'Integer', 'passwordReminderTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (68, '6', 'enable_auto_message', '是否自动消息 0 正常 1 停用', 'int(1)', 'Integer', 'enableAutoMessage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (69, '6', 'create_by', '创建人', 'varchar(255)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (70, '6', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (71, '6', 'update_by', '更新人', 'varchar(255)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');
INSERT INTO `gen_table_column` VALUES (72, '6', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-10-19 10:00:28', '', '2023-10-19 10:02:19');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(20) NOT NULL,
  `sched_time` bigint(20) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.cb.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001B636F6D2E63622E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720024636F6D2E63622E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001786CE13DA078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'com.cb.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001B636F6D2E63622E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720024636F6D2E63622E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001786CE13DA078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'com.cb.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001B636F6D2E63622E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720024636F6D2E63622E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001786CE13DA078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(20) NOT NULL,
  `checkin_interval` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'LAPTOP-0O9RQ5Q71697698608066', 1697701402485, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(20) NOT NULL,
  `repeat_interval` bigint(20) NOT NULL,
  `times_triggered` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(20) NULL DEFAULT NULL,
  `prev_fire_time` bigint(20) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `end_time` bigint(20) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(6) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1697698610000, -1, 5, 'PAUSED', 'CRON', 1697698608000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1697698620000, -1, 5, 'PAUSED', 'CRON', 1697698608000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1697698620000, -1, 5, 'PAUSED', 'CRON', 1697698608000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_common_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_config`;
CREATE TABLE `sys_common_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enable_three_member` int(1) NULL DEFAULT 0 COMMENT '三员配置 0 正常 1 停用',
  `enable_password_policy` int(1) NULL DEFAULT 0 COMMENT '是否开启密码策略',
  `password_policy` int(1) NULL DEFAULT 1 COMMENT '密码策略',
  `password_length` int(2) NULL DEFAULT 8 COMMENT '密码长度',
  `password_expiration` int(5) NULL DEFAULT 90 COMMENT '密码过期时间',
  `password_errors_no` int(2) NULL DEFAULT 5 COMMENT '密码错误次数',
  `password_lock_time` int(2) NULL DEFAULT 10 COMMENT '错误锁定时间',
  `password_reminder_time` int(1) NULL DEFAULT 3 COMMENT '密码到期提醒',
  `enable_auto_message` int(1) NULL DEFAULT 0 COMMENT '是否自动消息 0 正常 1 停用',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统通用配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_common_config
-- ----------------------------
INSERT INTO `sys_common_config` VALUES (1, 1, 0, 1, 8, 90, 5, 10, 3, 1, NULL, '2023-06-01 10:35:37', NULL, '2023-10-19 15:23:47');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-03-26 12:53:24', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-03-26 12:53:24', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-03-26 12:53:24', '', NULL, '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2021-03-26 12:53:24', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(11) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 0, '正常', '1', 'del_flag', NULL, NULL, 'N', '0', 'admin', '2023-05-19 16:05:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (30, 0, '删除', '2', 'del_flag', NULL, NULL, 'N', '0', 'admin', '2023-05-19 16:05:43', 'admin', '2023-05-25 22:55:40', NULL);
INSERT INTO `sys_dict_data` VALUES (31, 0, '普通', '1', 'secret', NULL, NULL, 'N', '0', 'admin', '2023-05-22 17:38:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (32, 1, '秘密', '2', 'secret', NULL, NULL, 'N', '0', 'admin', '2023-05-22 17:39:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (33, 3, '机密', '3', 'secret', NULL, NULL, 'N', '0', 'admin', '2023-05-22 17:39:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (34, 4, '绝密', '4', 'secret', NULL, NULL, 'N', '0', 'admin', '2023-05-22 17:39:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (35, 1, '普通用户', '00', 'user_type', NULL, NULL, 'N', '0', 'admin', '2023-05-26 14:02:33', '', NULL, '普通用户');
INSERT INTO `sys_dict_data` VALUES (36, 2, '系统管理员', '01', 'user_type', NULL, NULL, 'N', '0', 'admin', '2023-05-26 14:02:51', '', NULL, '系统管理员');
INSERT INTO `sys_dict_data` VALUES (37, 3, '安全管理员', '02', 'user_type', NULL, NULL, 'N', '0', 'admin', '2023-05-26 14:03:09', '', NULL, '安全管理员');
INSERT INTO `sys_dict_data` VALUES (38, 4, '安全审计员', '03', 'user_type', NULL, NULL, 'N', '0', 'admin', '2023-05-26 14:03:28', '', NULL, '安全审计员');
INSERT INTO `sys_dict_data` VALUES (39, 0, '字母数字任意 ', '1', 'password_policy', NULL, NULL, 'N', '0', 'admin', '2023-10-19 10:39:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (40, 1, '数字与字母混合', '2', 'password_policy', NULL, NULL, 'N', '0', 'admin', '2023-10-19 10:39:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (41, 2, '数字、特殊字符与字母混合', '3', 'password_policy', NULL, NULL, 'N', '0', 'admin', '2023-10-19 10:40:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (42, 3, '数字、特殊字符与大小写字母混合', '4', 'password_policy', NULL, NULL, 'N', '0', 'admin', '2023-10-19 10:40:19', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '删除标记', 'del_flag', '0', 'admin', '2023-05-19 16:04:28', 'admin', '2023-05-26 14:36:56', '删除标记，禁止删除');
INSERT INTO `sys_dict_type` VALUES (12, '密级', 'secret', '0', 'admin', '2023-05-22 17:38:38', 'admin', '2023-05-26 14:37:02', '密级，禁止删除');
INSERT INTO `sys_dict_type` VALUES (13, '用户类型', 'user_type', '0', 'admin', '2023-05-26 14:02:02', 'admin', '2023-05-26 14:37:06', '用户类型，禁止删除');
INSERT INTO `sys_dict_type` VALUES (14, '密码强度策略', 'password_policy', '0', 'admin', '2023-10-19 10:39:11', '', NULL, '禁止删除');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2021-03-26 12:53:24', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 255 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-03-26 13:15:46');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2021-03-26 13:41:20');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-03-26 13:41:25');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:26:54');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:27:02');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:27:32');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:28:06');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:29:20');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:30:49');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:32:38');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:32:59');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 09:33:24');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', NULL, '2021-03-29 10:18:50');
INSERT INTO `sys_logininfor` VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2021-03-29 04:39:07');
INSERT INTO `sys_logininfor` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2023-04-24 15:27:51');
INSERT INTO `sys_logininfor` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码已失效', '2023-05-15 14:09:05');
INSERT INTO `sys_logininfor` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-15 14:09:11');
INSERT INTO `sys_logininfor` VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-15 14:52:43');
INSERT INTO `sys_logininfor` VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-15 17:03:28');
INSERT INTO `sys_logininfor` VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 14:06:32');
INSERT INTO `sys_logininfor` VALUES (120, 'admin', '10.11.1.48', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 14:22:08');
INSERT INTO `sys_logininfor` VALUES (121, 'admin', '10.11.1.48', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 14:56:18');
INSERT INTO `sys_logininfor` VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 15:15:50');
INSERT INTO `sys_logininfor` VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 15:54:57');
INSERT INTO `sys_logininfor` VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 22:10:52');
INSERT INTO `sys_logininfor` VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 23:09:20');
INSERT INTO `sys_logininfor` VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-19 23:46:25');
INSERT INTO `sys_logininfor` VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-20 21:17:14');
INSERT INTO `sys_logininfor` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-21 12:04:14');
INSERT INTO `sys_logininfor` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-22 14:00:32');
INSERT INTO `sys_logininfor` VALUES (130, 'admin', '10.11.1.96', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-22 14:26:59');
INSERT INTO `sys_logininfor` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码已失效', '2023-05-22 22:54:48');
INSERT INTO `sys_logininfor` VALUES (132, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-22 22:54:53');
INSERT INTO `sys_logininfor` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-23 09:17:04');
INSERT INTO `sys_logininfor` VALUES (134, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-23 09:48:41');
INSERT INTO `sys_logininfor` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-23 12:32:01');
INSERT INTO `sys_logininfor` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-24 20:47:21');
INSERT INTO `sys_logininfor` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码已失效', '2023-05-25 11:01:52');
INSERT INTO `sys_logininfor` VALUES (138, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 11:01:56');
INSERT INTO `sys_logininfor` VALUES (139, 'admin', '10.11.1.96', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 14:10:17');
INSERT INTO `sys_logininfor` VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 15:54:11');
INSERT INTO `sys_logininfor` VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 16:59:03');
INSERT INTO `sys_logininfor` VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 20:22:10');
INSERT INTO `sys_logininfor` VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-25 22:32:41');
INSERT INTO `sys_logininfor` VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 09:10:31');
INSERT INTO `sys_logininfor` VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 11:34:37');
INSERT INTO `sys_logininfor` VALUES (146, 'ry', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 11:34:45');
INSERT INTO `sys_logininfor` VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 11:35:08');
INSERT INTO `sys_logininfor` VALUES (148, 'ry', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 11:38:19');
INSERT INTO `sys_logininfor` VALUES (149, 'ry', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 11:38:25');
INSERT INTO `sys_logininfor` VALUES (150, 'ry', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 11:38:52');
INSERT INTO `sys_logininfor` VALUES (151, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 11:38:56');
INSERT INTO `sys_logininfor` VALUES (152, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 11:39:23');
INSERT INTO `sys_logininfor` VALUES (153, 'ry', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 11:39:28');
INSERT INTO `sys_logininfor` VALUES (154, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 13:34:47');
INSERT INTO `sys_logininfor` VALUES (155, 'ouyang', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '1', '验证码错误', '2023-05-26 14:42:38');
INSERT INTO `sys_logininfor` VALUES (156, 'ouyang', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 14:42:42');
INSERT INTO `sys_logininfor` VALUES (157, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:30:12');
INSERT INTO `sys_logininfor` VALUES (158, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:30:16');
INSERT INTO `sys_logininfor` VALUES (159, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:31:05');
INSERT INTO `sys_logininfor` VALUES (160, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:31:08');
INSERT INTO `sys_logininfor` VALUES (161, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:32:11');
INSERT INTO `sys_logininfor` VALUES (162, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:32:15');
INSERT INTO `sys_logininfor` VALUES (163, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:32:33');
INSERT INTO `sys_logininfor` VALUES (164, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:32:58');
INSERT INTO `sys_logininfor` VALUES (165, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:34:50');
INSERT INTO `sys_logininfor` VALUES (166, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:34:56');
INSERT INTO `sys_logininfor` VALUES (167, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:41:19');
INSERT INTO `sys_logininfor` VALUES (168, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:42:23');
INSERT INTO `sys_logininfor` VALUES (169, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:42:51');
INSERT INTO `sys_logininfor` VALUES (170, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:44:28');
INSERT INTO `sys_logininfor` VALUES (171, 'audadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:44:44');
INSERT INTO `sys_logininfor` VALUES (172, 'audadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:45:09');
INSERT INTO `sys_logininfor` VALUES (173, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:51:08');
INSERT INTO `sys_logininfor` VALUES (174, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:51:19');
INSERT INTO `sys_logininfor` VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:51:25');
INSERT INTO `sys_logininfor` VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:51:29');
INSERT INTO `sys_logininfor` VALUES (177, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 15:57:42');
INSERT INTO `sys_logininfor` VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 15:57:46');
INSERT INTO `sys_logininfor` VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:01:30');
INSERT INTO `sys_logininfor` VALUES (180, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:01:37');
INSERT INTO `sys_logininfor` VALUES (181, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:05:12');
INSERT INTO `sys_logininfor` VALUES (182, 'ouyang', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:05:19');
INSERT INTO `sys_logininfor` VALUES (183, 'ouyang', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:06:07');
INSERT INTO `sys_logininfor` VALUES (184, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:06:11');
INSERT INTO `sys_logininfor` VALUES (185, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:06:41');
INSERT INTO `sys_logininfor` VALUES (186, 'ouyang', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:06:49');
INSERT INTO `sys_logininfor` VALUES (187, 'ouyang', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:10:46');
INSERT INTO `sys_logininfor` VALUES (188, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:10:51');
INSERT INTO `sys_logininfor` VALUES (189, 'ouyang', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:16:22');
INSERT INTO `sys_logininfor` VALUES (190, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 16:53:14');
INSERT INTO `sys_logininfor` VALUES (191, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:53:22');
INSERT INTO `sys_logininfor` VALUES (192, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 16:55:26');
INSERT INTO `sys_logininfor` VALUES (193, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 17:02:43');
INSERT INTO `sys_logininfor` VALUES (194, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 17:02:51');
INSERT INTO `sys_logininfor` VALUES (195, 'admin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '退出成功', '2023-05-26 17:06:32');
INSERT INTO `sys_logininfor` VALUES (196, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '1', '用户不存在/密码错误', '2023-05-26 17:06:47');
INSERT INTO `sys_logininfor` VALUES (197, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 17:06:53');
INSERT INTO `sys_logininfor` VALUES (198, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '退出成功', '2023-05-26 17:13:47');
INSERT INTO `sys_logininfor` VALUES (199, 'secadmin', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-26 17:13:56');
INSERT INTO `sys_logininfor` VALUES (200, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-05-26 17:54:54');
INSERT INTO `sys_logininfor` VALUES (201, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 17:54:59');
INSERT INTO `sys_logininfor` VALUES (202, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 22:26:29');
INSERT INTO `sys_logininfor` VALUES (203, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-26 22:28:26');
INSERT INTO `sys_logininfor` VALUES (204, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-27 12:45:10');
INSERT INTO `sys_logininfor` VALUES (205, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码已失效', '2023-05-27 13:39:53');
INSERT INTO `sys_logininfor` VALUES (206, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-05-27 13:39:57');
INSERT INTO `sys_logininfor` VALUES (207, 'ouyang', '127.0.0.1', '内网IP', 'Firefox 11', 'Windows 10', '0', '登录成功', '2023-05-27 13:41:37');
INSERT INTO `sys_logininfor` VALUES (208, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码错误', '2023-10-18 17:29:20');
INSERT INTO `sys_logininfor` VALUES (209, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-18 17:29:25');
INSERT INTO `sys_logininfor` VALUES (210, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 09:24:49');
INSERT INTO `sys_logininfor` VALUES (211, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:00:15');
INSERT INTO `sys_logininfor` VALUES (212, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 10:34:55');
INSERT INTO `sys_logininfor` VALUES (213, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:35:03');
INSERT INTO `sys_logininfor` VALUES (214, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 10:35:14');
INSERT INTO `sys_logininfor` VALUES (215, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '用户不存在/密码错误', '2023-10-19 10:35:34');
INSERT INTO `sys_logininfor` VALUES (216, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:35:53');
INSERT INTO `sys_logininfor` VALUES (217, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 10:36:47');
INSERT INTO `sys_logininfor` VALUES (218, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:36:54');
INSERT INTO `sys_logininfor` VALUES (219, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 10:41:44');
INSERT INTO `sys_logininfor` VALUES (220, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:41:52');
INSERT INTO `sys_logininfor` VALUES (221, 'secadmin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 10:42:22');
INSERT INTO `sys_logininfor` VALUES (222, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 10:42:26');
INSERT INTO `sys_logininfor` VALUES (223, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:18:59');
INSERT INTO `sys_logininfor` VALUES (224, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:19:04');
INSERT INTO `sys_logininfor` VALUES (225, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:32:46');
INSERT INTO `sys_logininfor` VALUES (226, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码错误', '2023-10-19 11:32:49');
INSERT INTO `sys_logininfor` VALUES (227, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:32:53');
INSERT INTO `sys_logininfor` VALUES (228, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:32:53');
INSERT INTO `sys_logininfor` VALUES (229, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:34:25');
INSERT INTO `sys_logininfor` VALUES (230, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:34:25');
INSERT INTO `sys_logininfor` VALUES (231, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码错误', '2023-10-19 11:34:54');
INSERT INTO `sys_logininfor` VALUES (232, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:34:58');
INSERT INTO `sys_logininfor` VALUES (233, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:34:58');
INSERT INTO `sys_logininfor` VALUES (234, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:36:10');
INSERT INTO `sys_logininfor` VALUES (235, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:37:03');
INSERT INTO `sys_logininfor` VALUES (236, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:39:13');
INSERT INTO `sys_logininfor` VALUES (237, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:39:13');
INSERT INTO `sys_logininfor` VALUES (238, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:43:18');
INSERT INTO `sys_logininfor` VALUES (239, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 11:43:19');
INSERT INTO `sys_logininfor` VALUES (240, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码错误', '2023-10-19 11:45:45');
INSERT INTO `sys_logininfor` VALUES (241, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 11:45:56');
INSERT INTO `sys_logininfor` VALUES (242, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 14:18:20');
INSERT INTO `sys_logininfor` VALUES (243, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 14:51:08');
INSERT INTO `sys_logininfor` VALUES (244, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 14:51:17');
INSERT INTO `sys_logininfor` VALUES (245, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 14:58:29');
INSERT INTO `sys_logininfor` VALUES (246, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 15:13:53');
INSERT INTO `sys_logininfor` VALUES (247, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 15:14:06');
INSERT INTO `sys_logininfor` VALUES (248, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 15:14:33');
INSERT INTO `sys_logininfor` VALUES (249, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 15:14:43');
INSERT INTO `sys_logininfor` VALUES (250, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 15:21:39');
INSERT INTO `sys_logininfor` VALUES (251, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '1', '验证码错误', '2023-10-19 15:22:00');
INSERT INTO `sys_logininfor` VALUES (252, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 15:22:10');
INSERT INTO `sys_logininfor` VALUES (253, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '退出成功', '2023-10-19 15:38:37');
INSERT INTO `sys_logininfor` VALUES (254, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-10-19 15:38:53');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(11) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(11) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1089 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', '2021-03-26 12:53:24', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-03-26 12:53:24', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2021-03-26 12:53:24', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-03-26 12:53:24', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-03-26 12:53:24', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-03-26 12:53:24', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-03-26 12:53:24', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-03-26 12:53:24', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2021-03-26 12:53:24', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2021-03-26 12:53:24', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2021-03-26 12:53:24', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2021-03-26 12:53:24', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2021-03-26 12:53:24', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2021-03-26 12:53:24', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2021-03-26 12:53:24', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2021-03-26 12:53:24', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2021-03-26 12:53:24', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2021-03-26 12:53:24', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2021-03-26 12:53:24', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2021-03-26 12:53:24', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-03-26 12:53:24', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-03-26 12:53:24', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '附件管理', 1067, 2, 'attach', 'filemanage/attach/index', 1, 0, 'C', '0', '0', 'filemanage:attach:list', 'zip', 'admin', '2023-05-19 15:09:18', 'admin', '2023-05-19 16:20:23', '业务文件菜单');
INSERT INTO `sys_menu` VALUES (1062, '业务文件查询', 1061, 1, '#', '', 1, 0, 'F', '0', '0', 'filemanage:attach:query', '#', 'admin', '2023-05-19 15:09:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1063, '业务文件新增', 1061, 2, '#', '', 1, 0, 'F', '0', '0', 'filemanage:attach:add', '#', 'admin', '2023-05-19 15:09:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1064, '业务文件修改', 1061, 3, '#', '', 1, 0, 'F', '0', '0', 'filemanage:attach:edit', '#', 'admin', '2023-05-19 15:09:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1065, '业务文件删除', 1061, 4, '#', '', 1, 0, 'F', '0', '0', 'filemanage:attach:remove', '#', 'admin', '2023-05-19 15:09:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1066, '业务文件导出', 1061, 5, '#', '', 1, 0, 'F', '0', '0', 'filemanage:attach:export', '#', 'admin', '2023-05-19 15:09:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1067, '文件管理', 0, 1, 'file', NULL, 1, 0, 'M', '0', '0', '', 'zip', 'admin', '2023-05-19 15:12:17', 'admin', '2023-05-19 15:12:47', '');
INSERT INTO `sys_menu` VALUES (1068, '文件夹设置', 1067, 3, 'folder', 'filemanage/folder/index', 1, 0, 'C', '0', '0', 'filemanage:folder:list', 'zip', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-20 21:28:00', '业务文件夹菜单');
INSERT INTO `sys_menu` VALUES (1069, '业务文件夹查询', 1068, 1, '#', '', 1, 0, 'F', '0', '0', 'filemanage:folder:query', '#', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-19 16:18:00', '');
INSERT INTO `sys_menu` VALUES (1070, '业务文件夹新增', 1068, 2, '#', '', 1, 0, 'F', '0', '0', 'filemanage:folder:add', '#', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-19 16:18:05', '');
INSERT INTO `sys_menu` VALUES (1071, '业务文件夹修改', 1068, 3, '#', '', 1, 0, 'F', '0', '0', 'filemanage:folder:edit', '#', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-19 16:18:09', '');
INSERT INTO `sys_menu` VALUES (1072, '业务文件夹删除', 1068, 4, '#', '', 1, 0, 'F', '0', '0', 'filemanage:folder:remove', '#', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-19 16:18:13', '');
INSERT INTO `sys_menu` VALUES (1073, '业务文件夹导出', 1068, 5, '#', '', 1, 0, 'F', '0', '0', 'filemanage:folder:export', '#', 'admin', '2023-05-19 16:16:32', 'admin', '2023-05-19 16:18:17', '');
INSERT INTO `sys_menu` VALUES (1074, '文件管理', 1067, 1, 'file', 'filemanage/file/index', 1, 0, 'C', '0', '0', 'filemanage:file:list', 'zip', 'admin', '2023-05-20 21:26:11', 'admin', '2023-05-20 21:27:40', '文件夹和视图菜单');
INSERT INTO `sys_menu` VALUES (1075, '文件夹和视图查询', 1074, 1, '#', '', 1, 0, 'F', '0', '0', 'filemanage:file:query', '#', 'admin', '2023-05-20 21:26:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1076, '新增文件夹', 1074, 2, '#', '', 1, 0, 'F', '0', '0', 'filemanage:file:addFolder', '#', 'admin', '2023-05-20 21:26:11', 'admin', '2023-05-23 09:19:12', '');
INSERT INTO `sys_menu` VALUES (1077, '重命名', 1074, 4, '#', '', 1, 0, 'F', '0', '0', 'filemanage:file:rename', '#', 'admin', '2023-05-20 21:26:11', 'admin', '2023-05-26 11:33:20', '');
INSERT INTO `sys_menu` VALUES (1078, '文件夹或文件删除', 1074, 5, '#', '', 1, 0, 'F', '0', '0', 'filemanage:file:delFolderOrFile', '#', 'admin', '2023-05-20 21:26:11', 'admin', '2023-05-26 11:33:15', '');
INSERT INTO `sys_menu` VALUES (1079, '文件夹和视图导出', 1074, 6, '#', '', 1, 0, 'F', '0', '0', 'filemanage:file:export', '#', 'admin', '2023-05-20 21:26:11', 'admin', '2023-05-26 11:33:10', '');
INSERT INTO `sys_menu` VALUES (1080, '上传文件', 1074, 3, '', NULL, 1, 0, 'F', '0', '0', 'filemanage:file:saveAttach', '#', 'admin', '2023-05-26 11:32:59', 'admin', '2023-05-26 11:33:28', '');
INSERT INTO `sys_menu` VALUES (1081, '修改状态', 100, 8, '', NULL, 1, 0, 'F', '0', '0', 'system:user:changeStatus', '#', 'admin', '2023-05-26 16:57:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1082, '权限设置', 100, 9, '', NULL, 1, 0, 'F', '0', '0', 'system:user:permissionSet', '#', 'admin', '2023-05-26 23:52:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1083, '通用配置', 1, 10, 'commonConfig', 'system/commonConfig/index', 1, 0, 'C', '0', '0', 'system:commonConfig:list', 'edit', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:07:39', '系统通用配置菜单');
INSERT INTO `sys_menu` VALUES (1084, '系统通用配置查询', 1083, 1, '#', '', 1, 0, 'F', '0', '0', 'system:commonConfig:query', '#', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:42:53', '');
INSERT INTO `sys_menu` VALUES (1085, '系统通用配置新增', 1083, 2, '#', '', 1, 0, 'F', '0', '0', 'system:commonConfig:add', '#', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:42:56', '');
INSERT INTO `sys_menu` VALUES (1086, '系统通用配置修改', 1083, 3, '#', '', 1, 0, 'F', '0', '0', 'system:commonConfig:edit', '#', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:43:01', '');
INSERT INTO `sys_menu` VALUES (1087, '系统通用配置删除', 1083, 4, '#', '', 1, 0, 'F', '0', '0', 'system:commonConfig:remove', '#', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:43:06', '');
INSERT INTO `sys_menu` VALUES (1088, '系统通用配置导出', 1083, 5, '#', '', 1, 0, 'F', '0', '0', 'system:commonConfig:export', '#', 'admin', '2023-10-19 10:02:48', 'admin', '2023-10-19 10:43:14', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2021-03-26 12:53:24', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(11) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(11) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(11) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 352 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '代码生成', 6, 'com.cb.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_dept', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-03-29 07:05:11');
INSERT INTO `sys_oper_log` VALUES (2, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-24 15:28:20');
INSERT INTO `sys_oper_log` VALUES (3, '菜单管理', 3, 'com.cb.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/4', '127.0.0.1', '内网IP', '{menuId=4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-04-24 15:28:30');
INSERT INTO `sys_oper_log` VALUES (4, '代码生成', 6, 'com.cb.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'biz_file_folder,biz_attach', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-15 14:57:57');
INSERT INTO `sys_oper_log` VALUES (5, '代码生成', 3, 'com.cb.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/1', '127.0.0.1', '内网IP', '{tableIds=1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-15 15:03:20');
INSERT INTO `sys_oper_log` VALUES (6, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":29,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Sort\",\"usableColumn\":false,\"columnId\":30,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"sort\",\"htmlType\":\"input\",\"edit\":true,\"query\":false,\"columnComment\":\"排序\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684133877000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"sort\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":31,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"文件夹ID\",\"sort\":3,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":3,\"pk\":true,\"columnName\":\"folder_id\"},{\"capJavaField\":\"FolderPrentId\",\"usableColumn\":false,\"columnId\":32,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderPrentId\",\"htmlType\":\"input\",\"edit\":true,\"query\":false,\"columnComment\":\"上级文件夹ID\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684133877000,\"isEdit\":\"1\",\"ta', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-15 15:04:32');
INSERT INTO `sys_oper_log` VALUES (7, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-15 17:04:35');
INSERT INTO `sys_oper_log` VALUES (8, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '10.11.1.48', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":15,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":2,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"AttachId\",\"usableColumn\":false,\"columnId\":16,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"attachId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"附件ID\",\"sort\":2,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":2,\"pk\":true,\"columnName\":\"attach_id\"},{\"capJavaField\":\"OldName\",\"usableColumn\":false,\"columnId\":17,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"oldName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"原文件名\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684133877000,\"isEdit\":\"1\",\"tableId\":2,\"pk\":false,\"columnName\":\"old_name\"},{\"capJavaField\":\"NewName\",\"usableColumn\":false,\"columnId\":18,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"newName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"新文件名\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684133877000,\"isEdit\":\"1\",\"tableId\":2,\"pk\":fals', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 14:24:02');
INSERT INTO `sys_oper_log` VALUES (9, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '10.11.1.48', '内网IP', '{}', 'null', 0, NULL, '2023-05-19 14:24:05');
INSERT INTO `sys_oper_log` VALUES (10, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"业务文件\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:10:02');
INSERT INTO `sys_oper_log` VALUES (11, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:10:22');
INSERT INTO `sys_oper_log` VALUES (12, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:10:49');
INSERT INTO `sys_oper_log` VALUES (13, '菜单管理', 1, 'com.cb.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"file\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"新增菜单\'文件管理\'失败，菜单名称已存在\",\"code\":500}', 0, NULL, '2023-05-19 15:12:12');
INSERT INTO `sys_oper_log` VALUES (14, '菜单管理', 1, 'com.cb.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理1\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"file\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"M\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:12:17');
INSERT INTO `sys_oper_log` VALUES (15, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:12:24');
INSERT INTO `sys_oper_log` VALUES (16, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"业务文件\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:12:41');
INSERT INTO `sys_oper_log` VALUES (17, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '10.11.1.48', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"file\",\"children\":[],\"createTime\":1684480337000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1067,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 15:12:47');
INSERT INTO `sys_oper_log` VALUES (18, '字典类型', 1, 'com.cb.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"删除标记\",\"remark\":\"删除标记（1 正常 2 删除）\",\"params\":{},\"dictType\":\"del_flag\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:04:28');
INSERT INTO `sys_oper_log` VALUES (19, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"params\":{},\"dictType\":\"del_flag\",\"dictLabel\":\"正常\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:05:30');
INSERT INTO `sys_oper_log` VALUES (20, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":0,\"params\":{},\"dictType\":\"del_flag\",\"dictLabel\":\"删除\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:05:43');
INSERT INTO `sys_oper_log` VALUES (21, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":29,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1684134272000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":3,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"Sort\",\"usableColumn\":false,\"columnId\":30,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"sort\",\"htmlType\":\"input\",\"edit\":true,\"query\":false,\"columnComment\":\"排序\",\"updateTime\":1684134272000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(10)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684133877000,\"isEdit\":\"1\",\"tableId\":3,\"pk\":false,\"columnName\":\"sort\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":31,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"文件夹ID\",\"updateTime\":1684134272000,\"sort\":3,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684133877000,\"tableId\":3,\"pk\":true,\"columnName\":\"folder_id\"},{\"capJavaField\":\"FolderPrentId\",\"usableColumn\":false,\"columnId\":32,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderPrentId\",\"htmlType\":\"input\",\"edit\":true,\"query\":false,\"columnComment\":\"上级文件夹ID\",\"updateTime\":1684134272000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryTyp', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:07:10');
INSERT INTO `sys_oper_log` VALUES (22, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-19 16:07:13');
INSERT INTO `sys_oper_log` VALUES (23, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"文件夹管理\",\"params\":{},\"parentId\":3,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"system/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"system:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:17:01');
INSERT INTO `sys_oper_log` VALUES (24, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"文件夹管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"system/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"system:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:17:09');
INSERT INTO `sys_oper_log` VALUES (25, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"文件夹管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"filemanage/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"filemanage:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:17:47');
INSERT INTO `sys_oper_log` VALUES (26, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"业务文件夹查询\",\"params\":{},\"parentId\":1068,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1069,\"menuType\":\"F\",\"perms\":\"filemanage:folder:query\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:18:00');
INSERT INTO `sys_oper_log` VALUES (27, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"业务文件夹新增\",\"params\":{},\"parentId\":1068,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1070,\"menuType\":\"F\",\"perms\":\"filemanage:folder:add\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:18:05');
INSERT INTO `sys_oper_log` VALUES (28, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"业务文件夹修改\",\"params\":{},\"parentId\":1068,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1071,\"menuType\":\"F\",\"perms\":\"filemanage:folder:edit\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:18:09');
INSERT INTO `sys_oper_log` VALUES (29, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"业务文件夹删除\",\"params\":{},\"parentId\":1068,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1072,\"menuType\":\"F\",\"perms\":\"filemanage:folder:remove\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:18:13');
INSERT INTO `sys_oper_log` VALUES (30, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"业务文件夹导出\",\"params\":{},\"parentId\":1068,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1073,\"menuType\":\"F\",\"perms\":\"filemanage:folder:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:18:17');
INSERT INTO `sys_oper_log` VALUES (31, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"附件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:20:03');
INSERT INTO `sys_oper_log` VALUES (32, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"filemanage/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"filemanage:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:20:08');
INSERT INTO `sys_oper_log` VALUES (33, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"2\",\"menuName\":\"附件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"attach\",\"component\":\"filemanage/attach/index\",\"children\":[],\"createTime\":1684480158000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1061,\"menuType\":\"C\",\"perms\":\"filemanage:attach:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:20:23');
INSERT INTO `sys_oper_log` VALUES (34, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"filemanage/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"filemanage:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 16:21:25');
INSERT INTO `sys_oper_log` VALUES (35, '代码生成', 6, 'com.cb.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'v_folder_or_file', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 23:10:07');
INSERT INTO `sys_oper_log` VALUES (36, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":39,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"tableId\":4,\"pk\":false,\"columnName\":\"id\"},{\"capJavaField\":\"ParentId\",\"usableColumn\":true,\"columnId\":40,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":true,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"parentId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"parent_id\"},{\"capJavaField\":\"Name\",\"usableColumn\":false,\"columnId\":41,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"name\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":42,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 23:12:03');
INSERT INTO `sys_oper_log` VALUES (37, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":39,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1684509123000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"tableId\":4,\"pk\":false,\"columnName\":\"id\"},{\"capJavaField\":\"ParentId\",\"usableColumn\":true,\"columnId\":40,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":true,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"parentId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"updateTime\":1684509123000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"parent_id\"},{\"capJavaField\":\"Name\",\"usableColumn\":false,\"columnId\":41,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"name\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"updateTime\":1684509123000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684509007000,\"isEdit\":\"1\",\"tableId\":4,\"pk\":false,\"columnName\":\"name\"},{\"capJavaField\":\"Type\",\"usableColumn\":false,\"columnId\":42,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"type\",\"htmlType\":\"select\",\"edit\":true,\"query\":true,\"isQuery\":\"1\",\"updateTime\":1684509123000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnTyp', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-19 23:13:01');
INSERT INTO `sys_oper_log` VALUES (38, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-19 23:13:04');
INSERT INTO `sys_oper_log` VALUES (39, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-19 23:13:09');
INSERT INTO `sys_oper_log` VALUES (40, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-19 23:13:15');
INSERT INTO `sys_oper_log` VALUES (41, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-20 21:24:34');
INSERT INTO `sys_oper_log` VALUES (42, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件夹设置\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"filemanage/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"filemanage:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-20 21:26:46');
INSERT INTO `sys_oper_log` VALUES (43, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":3,\"isCache\":\"0\",\"path\":\"file\",\"component\":\"filemanage/file/index\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1074,\"menuType\":\"C\",\"perms\":\"filemanage:file:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-20 21:27:31');
INSERT INTO `sys_oper_log` VALUES (44, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"1\",\"menuName\":\"文件管理\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"file\",\"component\":\"filemanage/file/index\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1074,\"menuType\":\"C\",\"perms\":\"filemanage:file:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-20 21:27:40');
INSERT INTO `sys_oper_log` VALUES (45, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"zip\",\"orderNum\":\"3\",\"menuName\":\"文件夹设置\",\"params\":{},\"parentId\":1067,\"isCache\":\"0\",\"path\":\"folder\",\"component\":\"filemanage/folder/index\",\"children\":[],\"createTime\":1684484192000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1068,\"menuType\":\"C\",\"perms\":\"filemanage:folder:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-20 21:28:00');
INSERT INTO `sys_oper_log` VALUES (46, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"7ef862e6-af04-40f1-9492-3671e15f3909\",\"createBy\":\"admin\",\"createTime\":1684644049438,\"folderName\":\"测试文件夹2\",\"folderPrentId\":\"0\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'sort\' doesn\'t have a default value\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizFileFolderMapper.xml]\r\n### The error may involve com.cb.filemanage.mapper.BizFileFolderMapper.insertBizFileFolder-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into biz_file_folder          ( folder_id,             folder_prent_id,             folder_name,             del_flag,             create_by,             create_time )           values ( ?,             ?,             ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'sort\' doesn\'t have a default value\n; Field \'sort\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'sort\' doesn\'t have a default value', '2023-05-21 12:40:49');
INSERT INTO `sys_oper_log` VALUES (47, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"createBy\":\"admin\",\"createTime\":1684644090706,\"folderName\":\"测试文件夹2\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 12:41:30');
INSERT INTO `sys_oper_log` VALUES (48, '业务文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.edit()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFolder', '127.0.0.1', '内网IP', '{\"updateTime\":1684644968642,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"updateBy\":\"admin\",\"folderName\":\"测试文件夹3\"}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-21 12:56:08');
INSERT INTO `sys_oper_log` VALUES (49, '业务文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.edit()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFolder', '127.0.0.1', '内网IP', '{\"updateTime\":1684645013503,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"updateBy\":\"admin\",\"folderName\":\"测试文件夹3\"}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-21 12:56:53');
INSERT INTO `sys_oper_log` VALUES (50, '业务文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.edit()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFolder', '127.0.0.1', '内网IP', '{\"updateTime\":1684645046533,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"updateBy\":\"admin\",\"folderName\":\"测试文件夹3\"}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-21 12:57:26');
INSERT INTO `sys_oper_log` VALUES (51, '业务文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.edit()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFolder', '127.0.0.1', '内网IP', '{\"updateTime\":1684645606075,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"updateBy\":\"admin\",\"folderName\":\"测试文件夹3\"}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-21 13:06:46');
INSERT INTO `sys_oper_log` VALUES (52, '业务文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.edit()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFolder', '127.0.0.1', '内网IP', '{\"updateTime\":1684645633793,\"params\":{},\"folderId\":\"78ad6b47-ccf3-4c86-b92d-04faf6a8f65f\",\"updateBy\":\"admin\",\"folderName\":\"测试文件夹3\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:07:13');
INSERT INTO `sys_oper_log` VALUES (53, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/78ad6b47-ccf3-4c86-b92d-04faf6a8f65f', '127.0.0.1', '内网IP', '{folderId=78ad6b47-ccf3-4c86-b92d-04faf6a8f65f}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:20:44');
INSERT INTO `sys_oper_log` VALUES (54, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/1', '127.0.0.1', '内网IP', '{folderId=1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:21:03');
INSERT INTO `sys_oper_log` VALUES (55, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"c2bc012d-037f-43bb-99d2-80f557446774\",\"createBy\":\"admin\",\"createTime\":1684646530156,\"folderName\":\"测试文件夹1\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:22:10');
INSERT INTO `sys_oper_log` VALUES (56, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"d0ab5b98-e4be-4e89-a14e-cd093bd3a7e8\",\"createBy\":\"admin\",\"createTime\":1684646537673,\"folderName\":\"测试文件夹2\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:22:17');
INSERT INTO `sys_oper_log` VALUES (57, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"3f657eac-3090-4f02-a694-c0fe790c85ab\",\"createBy\":\"admin\",\"createTime\":1684646558718,\"folderName\":\"测试文件夹3\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:22:38');
INSERT INTO `sys_oper_log` VALUES (58, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"c78956c6-d055-455f-a53b-8e0ee0357c3e\",\"createBy\":\"admin\",\"createTime\":1684646707233,\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:25:07');
INSERT INTO `sys_oper_log` VALUES (59, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"314e92d8-b026-4d02-84dd-75df42847ff5\",\"createBy\":\"admin\",\"createTime\":1684646777572,\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:26:17');
INSERT INTO `sys_oper_log` VALUES (60, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"b3f04d8f-6ea8-4b5a-9add-5250228826fb\",\"createBy\":\"admin\",\"createTime\":1684646842055,\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:27:22');
INSERT INTO `sys_oper_log` VALUES (61, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"1831f99e-d50d-43d7-b6b3-87ac9e351d51\",\"createBy\":\"admin\",\"createTime\":1684646946711,\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:29:06');
INSERT INTO `sys_oper_log` VALUES (62, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/3f657eac-3090-4f02-a694-c0fe790c85ab', '127.0.0.1', '内网IP', '{folderId=3f657eac-3090-4f02-a694-c0fe790c85ab}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:32:21');
INSERT INTO `sys_oper_log` VALUES (63, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/d0ab5b98-e4be-4e89-a14e-cd093bd3a7e8', '127.0.0.1', '内网IP', '{folderId=d0ab5b98-e4be-4e89-a14e-cd093bd3a7e8}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:32:23');
INSERT INTO `sys_oper_log` VALUES (64, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/c2bc012d-037f-43bb-99d2-80f557446774', '127.0.0.1', '内网IP', '{folderId=c2bc012d-037f-43bb-99d2-80f557446774}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:32:26');
INSERT INTO `sys_oper_log` VALUES (65, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/3f657eac-3090-4f02-a694-c0fe790c85ab', '127.0.0.1', '内网IP', '{folderId=3f657eac-3090-4f02-a694-c0fe790c85ab}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:41:47');
INSERT INTO `sys_oper_log` VALUES (66, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"51395244-64eb-4d09-87c8-e020d39a60d1\",\"createBy\":\"admin\",\"createTime\":1684648758973,\"folderName\":\"测试3\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 13:59:18');
INSERT INTO `sys_oper_log` VALUES (67, '业务文件夹', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolder()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolder/51395244-64eb-4d09-87c8-e020d39a60d1', '127.0.0.1', '内网IP', '{folderId=51395244-64eb-4d09-87c8-e020d39a60d1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-21 14:08:25');
INSERT INTO `sys_oper_log` VALUES (68, '业务文件', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.updateFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/updateFile', '127.0.0.1', '内网IP', '{\"updateTime\":1684736148106,\"params\":{},\"oldName\":\"测试文件1.pdf\",\"attachId\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 14:15:48');
INSERT INTO `sys_oper_log` VALUES (69, '业务文件', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.deleteFile()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/deleteFile/1', '10.11.1.96', '内网IP', '{attachId=1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 14:38:02');
INSERT INTO `sys_oper_log` VALUES (70, '业务文件夹', 1, 'com.cb.web.controller.filemanage.BizFileFolderController.add()', 'POST', 1, 'admin', NULL, '/filemanage/folder', '10.11.1.96', '内网IP', '{\"sort\":0,\"params\":{},\"createTime\":1684746249015,\"folderName\":\"更目录\",\"folderPrentId\":\"-1\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'folder_id\' doesn\'t have a default value\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizFileFolderMapper.xml]\r\n### The error may involve com.cb.filemanage.mapper.BizFileFolderMapper.insertBizFileFolder-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into biz_file_folder          ( sort,                          folder_prent_id,             folder_name,                                       create_time )           values ( ?,                          ?,             ?,                                       ? )\r\n### Cause: java.sql.SQLException: Field \'folder_id\' doesn\'t have a default value\n; Field \'folder_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'folder_id\' doesn\'t have a default value', '2023-05-22 17:04:09');
INSERT INTO `sys_oper_log` VALUES (71, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '10.11.1.96', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"432e18d5-acaf-41c1-9268-9e7af1fff769\",\"createBy\":\"admin\",\"createTime\":1684746297304,\"folderName\":\"根目录\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:04:57');
INSERT INTO `sys_oper_log` VALUES (72, '代码生成', 3, 'com.cb.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', NULL, '/tool/gen/2', '10.11.1.96', '内网IP', '{tableIds=2}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:37:57');
INSERT INTO `sys_oper_log` VALUES (73, '代码生成', 6, 'com.cb.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '10.11.1.96', '内网IP', 'biz_attach', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:38:01');
INSERT INTO `sys_oper_log` VALUES (74, '字典类型', 1, 'com.cb.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '10.11.1.96', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"密级\",\"remark\":\"密级\",\"params\":{},\"dictType\":\"secret\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:38:38');
INSERT INTO `sys_oper_log` VALUES (75, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '10.11.1.96', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"params\":{},\"dictType\":\"secret\",\"dictLabel\":\"普通\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:38:54');
INSERT INTO `sys_oper_log` VALUES (76, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '10.11.1.96', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":1,\"params\":{},\"dictType\":\"secret\",\"dictLabel\":\"秘密\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:39:04');
INSERT INTO `sys_oper_log` VALUES (77, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '10.11.1.96', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":3,\"params\":{},\"dictType\":\"secret\",\"dictLabel\":\"机密\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:39:17');
INSERT INTO `sys_oper_log` VALUES (78, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '10.11.1.96', '内网IP', '{\"dictValue\":\"4\",\"dictSort\":4,\"params\":{},\"dictType\":\"secret\",\"dictLabel\":\"绝密\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:39:30');
INSERT INTO `sys_oper_log` VALUES (79, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '10.11.1.96', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":43,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"AttachId\",\"usableColumn\":false,\"columnId\":44,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"attachId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"附件ID\",\"sort\":2,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"attach_id\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":45,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"文件夹ID\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684748281000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"folder_id\"},{\"capJavaField\":\"OldName\",\"usableColumn\":false,\"columnId\":46,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"oldName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"原文件名\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"LIKE\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684748281000,\"isEdit\":\"1\",\"tab', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:39:56');
INSERT INTO `sys_oper_log` VALUES (80, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '10.11.1.96', '内网IP', '{}', 'null', 0, NULL, '2023-05-22 17:40:53');
INSERT INTO `sys_oper_log` VALUES (81, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '10.11.1.96', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":43,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1684748396000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"AttachId\",\"usableColumn\":false,\"columnId\":44,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"attachId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"附件ID\",\"updateTime\":1684748396000,\"sort\":2,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"attach_id\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":45,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"文件夹ID\",\"isQuery\":\"1\",\"updateTime\":1684748396000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684748281000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"folder_id\"},{\"capJavaField\":\"OldName\",\"usableColumn\":false,\"columnId\":46,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"oldName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"原文件名\",\"isQuery\":\"1\",\"updateTime\":1684748396000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:50:54');
INSERT INTO `sys_oper_log` VALUES (82, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '10.11.1.96', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":43,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1684749054000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"AttachId\",\"usableColumn\":false,\"columnId\":44,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"attachId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"附件ID\",\"updateTime\":1684749054000,\"sort\":2,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"attach_id\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":45,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"文件夹ID\",\"isQuery\":\"1\",\"updateTime\":1684749054000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684748281000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"folder_id\"},{\"capJavaField\":\"OldName\",\"usableColumn\":false,\"columnId\":46,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"oldName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"原文件名\",\"isQuery\":\"1\",\"updateTime\":1684749054000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 17:51:43');
INSERT INTO `sys_oper_log` VALUES (83, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '10.11.1.96', '内网IP', '{}', 'null', 0, NULL, '2023-05-22 17:51:45');
INSERT INTO `sys_oper_log` VALUES (84, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"707098c6-702a-4dbb-9c1c-f1906b00c524\",\"createBy\":\"admin\",\"createTime\":1684767397842,\"folderName\":\"新建文件夹22\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 22:56:37');
INSERT INTO `sys_oper_log` VALUES (85, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"cc38198b-0e43-40a6-84d0-7be997ae3aba\",\"createBy\":\"admin\",\"createTime\":1684767423901,\"folderName\":\"新建文件夹11\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 22:57:03');
INSERT INTO `sys_oper_log` VALUES (86, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"7dc96948-cc0d-4196-9d4e-6ff8934b5063\",\"createBy\":\"admin\",\"createTime\":1684767517776,\"folderName\":\"新建文件夹33\",\"folderPrentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 22:58:37');
INSERT INTO `sys_oper_log` VALUES (87, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"bacd75e9-065b-4083-8b04-b6307f517464\",\"createBy\":\"admin\",\"createTime\":1684767910767,\"folderName\":\"新建文件夹111\",\"folderPrentId\":\"c2bc012d-037f-43bb-99d2-80f557446774\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:10');
INSERT INTO `sys_oper_log` VALUES (88, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/15', '127.0.0.1', '内网IP', '{ids=15}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:33');
INSERT INTO `sys_oper_log` VALUES (89, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/14', '127.0.0.1', '内网IP', '{ids=14}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:35');
INSERT INTO `sys_oper_log` VALUES (90, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/13', '127.0.0.1', '内网IP', '{ids=13}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:38');
INSERT INTO `sys_oper_log` VALUES (91, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/12', '127.0.0.1', '内网IP', '{ids=12}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:40');
INSERT INTO `sys_oper_log` VALUES (92, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/10', '127.0.0.1', '内网IP', '{ids=10}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:51');
INSERT INTO `sys_oper_log` VALUES (93, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/5', '127.0.0.1', '内网IP', '{ids=5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:53');
INSERT INTO `sys_oper_log` VALUES (94, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/4', '127.0.0.1', '内网IP', '{ids=4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:55');
INSERT INTO `sys_oper_log` VALUES (95, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/3', '127.0.0.1', '内网IP', '{ids=3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:05:58');
INSERT INTO `sys_oper_log` VALUES (96, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"cfb20b3a-7b93-4cf4-b2d3-568f6d0bdbdd\",\"createBy\":\"admin\",\"createTime\":1684769450774,\"folderName\":\"新建文件夹1\"}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'folder_parent_id\' doesn\'t have a default value\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizFileFolderMapper.xml]\r\n### The error may involve com.cb.filemanage.mapper.BizFileFolderMapper.insertBizFileFolder-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into biz_file_folder          ( folder_id,                          folder_name,             del_flag,             create_by,             create_time )           values ( ?,                          ?,             ?,             ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'folder_parent_id\' doesn\'t have a default value\n; Field \'folder_parent_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'folder_parent_id\' doesn\'t have a default value', '2023-05-22 23:30:51');
INSERT INTO `sys_oper_log` VALUES (97, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"createBy\":\"admin\",\"createTime\":1684769498332,\"folderName\":\"新建文件夹1\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:31:38');
INSERT INTO `sys_oper_log` VALUES (98, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"e87f80b7-5a0e-4225-950a-a6901c4ecaca\",\"createBy\":\"admin\",\"createTime\":1684769572864,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:32:52');
INSERT INTO `sys_oper_log` VALUES (99, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"40dd936d-f937-4b04-8391-372310b091f6\",\"createBy\":\"admin\",\"createTime\":1684769575785,\"folderName\":\"新建文件夹3\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:32:55');
INSERT INTO `sys_oper_log` VALUES (100, '业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"1f06654b-a393-48ef-87c0-aca944e7342a\",\"createBy\":\"admin\",\"createTime\":1684769590261,\"folderName\":\"新建文件夹3\",\"folderParentId\":\"40dd936d-f937-4b04-8391-372310b091f6\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-22 23:33:10');
INSERT INTO `sys_oper_log` VALUES (101, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"新增文件夹\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1076,\"menuType\":\"F\",\"perms\":\"filemanage:file:addFolder\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:19:12');
INSERT INTO `sys_oper_log` VALUES (102, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"重命名\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1077,\"menuType\":\"F\",\"perms\":\"filemanage:file:rename\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:20:59');
INSERT INTO `sys_oper_log` VALUES (103, '重命名文件或文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.renameFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/renameFolderOrFile', '127.0.0.1', '内网IP', '{\"name\":\"测试文件2.pdf\",\"id\":\"1\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:48:53');
INSERT INTO `sys_oper_log` VALUES (104, '重命名文件或文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.renameFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/renameFolderOrFile', '127.0.0.1', '内网IP', '{\"name\":\"新建文件夹11\",\"id\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:49:26');
INSERT INTO `sys_oper_log` VALUES (105, '删除业务文件夹或者文件', 3, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'DELETE', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-23 09:49:49');
INSERT INTO `sys_oper_log` VALUES (106, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"type\":0}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-23 09:52:16');
INSERT INTO `sys_oper_log` VALUES (107, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"type\":0}', '{\"msg\":\"操作失败\",\"code\":500}', 0, NULL, '2023-05-23 09:52:23');
INSERT INTO `sys_oper_log` VALUES (108, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"1f06654b-a393-48ef-87c0-aca944e7342a\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:52:45');
INSERT INTO `sys_oper_log` VALUES (109, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"1\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 09:52:51');
INSERT INTO `sys_oper_log` VALUES (110, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"11662bc8-e19c-4cde-950d-d05bd9332746\",\"createBy\":\"admin\",\"createTime\":1684808734577,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"40dd936d-f937-4b04-8391-372310b091f6\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 10:25:34');
INSERT INTO `sys_oper_log` VALUES (111, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/23/完整性校验.docx\"}', 0, NULL, '2023-05-23 11:49:46');
INSERT INTO `sys_oper_log` VALUES (112, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ruoyi\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":43,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1684749103000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"AttachId\",\"usableColumn\":false,\"columnId\":44,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"attachId\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"columnComment\":\"附件ID\",\"updateTime\":1684749103000,\"sort\":2,\"list\":false,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1684748281000,\"tableId\":5,\"pk\":true,\"columnName\":\"attach_id\"},{\"capJavaField\":\"FolderId\",\"usableColumn\":false,\"columnId\":45,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":true,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"isRequired\":\"1\",\"javaField\":\"folderId\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"文件夹ID\",\"isQuery\":\"1\",\"updateTime\":1684749103000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"EQ\",\"columnType\":\"varchar(50)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1684748281000,\"isEdit\":\"1\",\"tableId\":5,\"pk\":false,\"columnName\":\"folder_id\"},{\"capJavaField\":\"OldName\",\"usableColumn\":false,\"columnId\":46,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"oldName\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"原文件名\",\"isQuery\":\"1\",\"updateTime\":1684749103000,\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"String\",\"queryType\":\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-23 12:33:33');
INSERT INTO `sys_oper_log` VALUES (113, '代码生成', 8, 'com.cb.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2023-05-23 12:33:36');
INSERT INTO `sys_oper_log` VALUES (114, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"filename : [mock.ofd], extension : [ofd], allowed extension : [[bmp, gif, jpg, jpeg, png, doc, docx, xls, xlsx, ppt, pptx, html, htm, txt, rar, zip, gz, bz2, pdf]]\",\"code\":500}', 0, NULL, '2023-05-24 20:57:51');
INSERT INTO `sys_oper_log` VALUES (115, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 20:57:51');
INSERT INTO `sys_oper_log` VALUES (116, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/完整性校验.docx\"}', 0, NULL, '2023-05-24 20:59:42');
INSERT INTO `sys_oper_log` VALUES (117, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 20:59:42');
INSERT INTO `sys_oper_log` VALUES (118, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 21:05:44');
INSERT INTO `sys_oper_log` VALUES (119, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/完整性校验.docx\"}', 0, NULL, '2023-05-24 21:05:44');
INSERT INTO `sys_oper_log` VALUES (120, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 21:20:32');
INSERT INTO `sys_oper_log` VALUES (121, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"盘龙区OA系统部署文档.doc\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/盘龙区OA系统部署文档.doc\"}', 0, NULL, '2023-05-24 21:20:33');
INSERT INTO `sys_oper_log` VALUES (122, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"1\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-24 21:26:19');
INSERT INTO `sys_oper_log` VALUES (123, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 21:30:48');
INSERT INTO `sys_oper_log` VALUES (124, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/完整性校验.docx\"}', 0, NULL, '2023-05-24 21:30:48');
INSERT INTO `sys_oper_log` VALUES (125, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/完整性校验.docx\"}', 0, NULL, '2023-05-24 21:32:40');
INSERT INTO `sys_oper_log` VALUES (126, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/24/123456.pdf\"}', 0, NULL, '2023-05-24 21:32:40');
INSERT INTO `sys_oper_log` VALUES (127, '业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.add()', 'POST', 1, 'admin', NULL, '/filemanage/attach', '127.0.0.1', '内网IP', '{\"secret\":1,\"params\":{},\"createTime\":1684935516909}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizAttachMapper.xml]\r\n### The error may involve com.cb.filemanage.mapper.BizAttachMapper.insertBizAttach-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into biz_attach          ( secret,                                                                                           create_time )           values ( ?,                                                                                           ? )\r\n### Cause: java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value\n; Field \'attach_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value', '2023-05-24 21:38:37');
INSERT INTO `sys_oper_log` VALUES (128, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"filename : [mock.ofd], extension : [ofd], allowed extension : [[bmp, gif, jpg, jpeg, png, doc, docx, xls, xlsx, ppt, pptx, html, htm, txt, rar, zip, gz, bz2, pdf]]\",\"code\":500}', 0, NULL, '2023-05-25 11:13:01');
INSERT INTO `sys_oper_log` VALUES (129, '业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.add()', 'POST', 1, 'admin', NULL, '/filemanage/attach', '127.0.0.1', '内网IP', '{\"secret\":1,\"params\":{},\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"createTime\":1684984698993}', 'null', 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizAttachMapper.xml]\r\n### The error may involve com.cb.filemanage.mapper.BizAttachMapper.insertBizAttach-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into biz_attach          ( folder_id,                                                    secret,                                                                                           create_time )           values ( ?,                                                    ?,                                                                                           ? )\r\n### Cause: java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value\n; Field \'attach_id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'attach_id\' doesn\'t have a default value', '2023-05-25 11:18:19');
INSERT INTO `sys_oper_log` VALUES (130, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/完整性校验.docx\"}', 0, NULL, '2023-05-25 11:27:25');
INSERT INTO `sys_oper_log` VALUES (131, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"5799a73b-7a03-4499-a0ca-c90fb7ef87e8.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/5799a73b-7a03-4499-a0ca-c90fb7ef87e8.docx\"}', 0, NULL, '2023-05-25 11:30:41');
INSERT INTO `sys_oper_log` VALUES (132, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"095a3c0b-c923-44ab-9e64-1732c44894a8.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/095a3c0b-c923-44ab-9e64-1732c44894a8.pdf\"}', 0, NULL, '2023-05-25 11:34:28');
INSERT INTO `sys_oper_log` VALUES (133, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/7b588f0c-4c5c-42f6-a63e-e5d8f17ab5ea.pdf\"}', 0, NULL, '2023-05-25 14:17:30');
INSERT INTO `sys_oper_log` VALUES (134, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/8d7997ca-de62-4fa3-a81f-15529eba534e.ofd\"}', 0, NULL, '2023-05-25 14:19:18');
INSERT INTO `sys_oper_log` VALUES (135, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/20c264b8-c55c-4da4-91ff-e93be8962044.pdf\"}', 0, NULL, '2023-05-25 14:19:41');
INSERT INTO `sys_oper_log` VALUES (136, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/e445f46c-e96e-4755-944a-c97d429be457.docx\"}', 0, NULL, '2023-05-25 14:22:04');
INSERT INTO `sys_oper_log` VALUES (137, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/0093093a-16a1-4fea-bb20-ce2a1698ffd1.pdf\"}', 0, NULL, '2023-05-25 14:22:47');
INSERT INTO `sys_oper_log` VALUES (138, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/2e477fe9-7ea1-4406-88b4-ee47a3cb4a4e.ofd\"}', 0, NULL, '2023-05-25 14:23:20');
INSERT INTO `sys_oper_log` VALUES (139, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/e9265cd7-2105-4405-b988-1f5ce101bee7.ofd\"}', 0, NULL, '2023-05-25 14:51:05');
INSERT INTO `sys_oper_log` VALUES (140, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/c69a2586-2e39-4126-b06f-c0d295ffd10e.ofd\"}', 0, NULL, '2023-05-25 14:52:14');
INSERT INTO `sys_oper_log` VALUES (141, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/7645a4bf-fe17-4e4c-b2f7-40f9e07331d5.ofd\"}', 0, NULL, '2023-05-25 14:53:08');
INSERT INTO `sys_oper_log` VALUES (142, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"ofd\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/7645a4bf-fe17-4e4c-b2f7-40f9e07331d5.ofd\",\"createBy\":\"admin\",\"newName\":\"7645a4bf-fe17-4e4c-b2f7-40f9e07331d5.ofd\",\"createTime\":1684997593920,\"fileSize\":1269758,\"oldName\":\"mock.ofd\",\"id\":2,\"attachId\":\"f1768169-a6e4-4c01-a010-3a0bef5b4dbe\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 14:53:13');
INSERT INTO `sys_oper_log` VALUES (143, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/bcadb927-1e19-4afe-ae71-30d83e0ca68f.docx\"}', 0, NULL, '2023-05-25 14:57:04');
INSERT INTO `sys_oper_log` VALUES (144, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"docx\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/bcadb927-1e19-4afe-ae71-30d83e0ca68f.docx\",\"createBy\":\"admin\",\"newName\":\"bcadb927-1e19-4afe-ae71-30d83e0ca68f.docx\",\"createTime\":1684997827298,\"fileSize\":11186,\"oldName\":\"完整性校验.docx\",\"id\":3,\"attachId\":\"e1779eb6-ade3-45da-b35d-38a5d34f75a0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 14:57:07');
INSERT INTO `sys_oper_log` VALUES (145, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"新建文本文档.txt\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/9b433ed7-6021-4230-89aa-ce323a37439f.txt\"}', 0, NULL, '2023-05-25 14:57:58');
INSERT INTO `sys_oper_log` VALUES (146, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"txt\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/9b433ed7-6021-4230-89aa-ce323a37439f.txt\",\"createBy\":\"admin\",\"newName\":\"9b433ed7-6021-4230-89aa-ce323a37439f.txt\",\"createTime\":1684997881482,\"fileSize\":5572,\"oldName\":\"新建文本文档.txt\",\"id\":4,\"attachId\":\"41a2bab6-558f-425f-ac1c-499e4f02f0fb\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 14:58:01');
INSERT INTO `sys_oper_log` VALUES (147, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/c1761cd3-7060-439d-aac6-351e7ead82e9.docx\"}', 0, NULL, '2023-05-25 14:58:42');
INSERT INTO `sys_oper_log` VALUES (148, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":2,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"docx\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/c1761cd3-7060-439d-aac6-351e7ead82e9.docx\",\"createBy\":\"admin\",\"newName\":\"c1761cd3-7060-439d-aac6-351e7ead82e9.docx\",\"createTime\":1684997925053,\"fileSize\":11186,\"oldName\":\"完整性校验.docx\",\"id\":5,\"attachId\":\"67f530a8-4cf8-4c15-ba92-2a2b8a19f9d0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 14:58:45');
INSERT INTO `sys_oper_log` VALUES (149, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '10.11.1.96', '内网IP', '{\"id\":\"e1779eb6-ade3-45da-b35d-38a5d34f75a0\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:01:36');
INSERT INTO `sys_oper_log` VALUES (150, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"云南创博检查报告20230525100242.xlsx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/e5f96cad-5dbb-4755-8692-588eccd29822.xlsx\"}', 0, NULL, '2023-05-25 15:03:41');
INSERT INTO `sys_oper_log` VALUES (151, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"xlsx\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/e5f96cad-5dbb-4755-8692-588eccd29822.xlsx\",\"createBy\":\"admin\",\"newName\":\"e5f96cad-5dbb-4755-8692-588eccd29822.xlsx\",\"createTime\":1684998223462,\"fileSize\":31184,\"oldName\":\"云南创博检查报告20230525100242.xlsx\",\"id\":6,\"attachId\":\"f0f40b97-429a-4c2c-bfaa-54b2f4482e54\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:03:43');
INSERT INTO `sys_oper_log` VALUES (152, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/21aa6718-2637-4192-bd68-d88ac34f4084.pdf\"}', 0, NULL, '2023-05-25 15:06:24');
INSERT INTO `sys_oper_log` VALUES (153, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/21aa6718-2637-4192-bd68-d88ac34f4084.pdf\",\"createBy\":\"admin\",\"newName\":\"21aa6718-2637-4192-bd68-d88ac34f4084.pdf\",\"createTime\":1684998385897,\"fileSize\":29025,\"oldName\":\"123456.pdf\",\"id\":7,\"attachId\":\"4ca7433f-e6f8-4c6c-8540-41b150b0e180\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:06:25');
INSERT INTO `sys_oper_log` VALUES (154, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '10.11.1.96', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456 - 副本.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/d9ca28b8-4303-49fe-a709-b6f2325dc6ad.pdf\"}', 0, NULL, '2023-05-25 15:37:33');
INSERT INTO `sys_oper_log` VALUES (155, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '10.11.1.96', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"a104c62f-93e2-40b9-97b0-58d0f444e7e0\",\"path\":\"/profile/upload/2023/05/25/d9ca28b8-4303-49fe-a709-b6f2325dc6ad.pdf\",\"createBy\":\"admin\",\"newName\":\"d9ca28b8-4303-49fe-a709-b6f2325dc6ad.pdf\",\"createTime\":1685000255266,\"fileSize\":29025,\"oldName\":\"123456 - 副本.pdf\",\"id\":8,\"attachId\":\"348127d8-74b0-4699-ac27-e575283f5e07\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:37:35');
INSERT INTO `sys_oper_log` VALUES (156, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\",\"createBy\":\"admin\",\"createTime\":1685001283565,\"folderName\":\"电力模块\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:54:43');
INSERT INTO `sys_oper_log` VALUES (157, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"c7b23798-1e0d-4b55-a8fb-245ed72b5604\",\"createBy\":\"admin\",\"createTime\":1685001294373,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:54:54');
INSERT INTO `sys_oper_log` VALUES (158, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"c7b23798-1e0d-4b55-a8fb-245ed72b5604\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:55:51');
INSERT INTO `sys_oper_log` VALUES (159, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\",\"createBy\":\"admin\",\"createTime\":1685001359365,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:55:59');
INSERT INTO `sys_oper_log` VALUES (160, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"a6f7f9ad-0c01-4fac-b124-d42ef306fa33\",\"createBy\":\"admin\",\"createTime\":1685001362999,\"folderName\":\"新建文件夹3\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:56:03');
INSERT INTO `sys_oper_log` VALUES (161, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"6a78c9d0-d5eb-458b-9d5d-74b42b598c12\",\"createBy\":\"admin\",\"createTime\":1685001408173,\"folderName\":\"新建文件夹4\",\"folderParentId\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:56:48');
INSERT INTO `sys_oper_log` VALUES (162, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"e3d69d62-92ea-4568-a15d-f76c3299294d\",\"createBy\":\"admin\",\"createTime\":1685001438354,\"folderName\":\"金福小区\",\"folderParentId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:57:18');
INSERT INTO `sys_oper_log` VALUES (163, '重命名文件或文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.renameFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/renameFolderOrFile', '127.0.0.1', '内网IP', '{\"name\":\"饮水工程\",\"id\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:57:43');
INSERT INTO `sys_oper_log` VALUES (164, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"851cd617-f26e-490a-82a1-219d854c0089\",\"createBy\":\"admin\",\"createTime\":1685001475049,\"folderName\":\"丰宁小区\",\"folderParentId\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:57:55');
INSERT INTO `sys_oper_log` VALUES (165, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"a6f7f9ad-0c01-4fac-b124-d42ef306fa33\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:58:02');
INSERT INTO `sys_oper_log` VALUES (166, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"6a78c9d0-d5eb-458b-9d5d-74b42b598c12\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:58:05');
INSERT INTO `sys_oper_log` VALUES (167, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/1532f71c-7b86-4dcc-b7f4-4716e830d1c6.pdf\"}', 0, NULL, '2023-05-25 15:58:20');
INSERT INTO `sys_oper_log` VALUES (168, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"e3d69d62-92ea-4568-a15d-f76c3299294d\",\"path\":\"/profile/upload/2023/05/25/1532f71c-7b86-4dcc-b7f4-4716e830d1c6.pdf\",\"createBy\":\"admin\",\"newName\":\"1532f71c-7b86-4dcc-b7f4-4716e830d1c6.pdf\",\"createTime\":1685001501717,\"fileSize\":29025,\"oldName\":\"123456.pdf\",\"id\":9,\"attachId\":\"e523f45f-cc61-4a09-9889-f4d9dd64ed8b\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:58:21');
INSERT INTO `sys_oper_log` VALUES (169, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/8e14c24b-0240-4488-84a1-715502e31e3e.docx\"}', 0, NULL, '2023-05-25 15:58:43');
INSERT INTO `sys_oper_log` VALUES (170, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"docx\",\"folderId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\",\"path\":\"/profile/upload/2023/05/25/8e14c24b-0240-4488-84a1-715502e31e3e.docx\",\"createBy\":\"admin\",\"newName\":\"8e14c24b-0240-4488-84a1-715502e31e3e.docx\",\"createTime\":1685001524692,\"fileSize\":11186,\"oldName\":\"完整性校验.docx\",\"id\":10,\"attachId\":\"74b4eb18-d053-4477-8ce4-bd8da5c7e6df\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:58:44');
INSERT INTO `sys_oper_log` VALUES (171, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456 - 副本 (1).pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/b3fe6b5c-e3a8-4cff-967d-ad23a8005066.pdf\"}', 0, NULL, '2023-05-25 15:59:57');
INSERT INTO `sys_oper_log` VALUES (172, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\",\"path\":\"/profile/upload/2023/05/25/b3fe6b5c-e3a8-4cff-967d-ad23a8005066.pdf\",\"createBy\":\"admin\",\"newName\":\"b3fe6b5c-e3a8-4cff-967d-ad23a8005066.pdf\",\"createTime\":1685001598431,\"fileSize\":29025,\"oldName\":\"123456 - 副本 (1).pdf\",\"id\":11,\"attachId\":\"d5754a3c-b221-4ab3-a3d5-b5cc2f929b78\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 15:59:58');
INSERT INTO `sys_oper_log` VALUES (173, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456 - 副本 (2).pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/2dee2710-34dc-4c54-b4de-7cd32bc0688c.pdf\"}', 0, NULL, '2023-05-25 16:00:38');
INSERT INTO `sys_oper_log` VALUES (174, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456 - 副本 (2).pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/ebb04f2c-c2da-4c61-a0cb-e1fd5a51ab34.pdf\"}', 0, NULL, '2023-05-25 16:00:48');
INSERT INTO `sys_oper_log` VALUES (175, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456 - 副本.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/e3253ffe-1663-4e78-a361-1c2ce3efba57.pdf\"}', 0, NULL, '2023-05-25 16:01:37');
INSERT INTO `sys_oper_log` VALUES (176, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\",\"path\":\"/profile/upload/2023/05/25/e3253ffe-1663-4e78-a361-1c2ce3efba57.pdf\",\"createBy\":\"admin\",\"newName\":\"e3253ffe-1663-4e78-a361-1c2ce3efba57.pdf\",\"createTime\":1685001698645,\"fileSize\":29025,\"oldName\":\"123456 - 副本.pdf\",\"id\":12,\"attachId\":\"54b62806-0e44-4559-a5dd-50c288c2732b\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:01:38');
INSERT INTO `sys_oper_log` VALUES (177, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/6ad438cf-8326-4346-b550-8b9d120e36e7.docx\"}', 0, NULL, '2023-05-25 16:02:07');
INSERT INTO `sys_oper_log` VALUES (178, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":2,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"docx\",\"folderId\":\"851cd617-f26e-490a-82a1-219d854c0089\",\"path\":\"/profile/upload/2023/05/25/6ad438cf-8326-4346-b550-8b9d120e36e7.docx\",\"createBy\":\"admin\",\"newName\":\"6ad438cf-8326-4346-b550-8b9d120e36e7.docx\",\"createTime\":1685001729896,\"fileSize\":11186,\"oldName\":\"完整性校验.docx\",\"id\":13,\"attachId\":\"908a0793-9f62-40cc-82a4-67c25f162ff7\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:02:09');
INSERT INTO `sys_oper_log` VALUES (179, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/22', '127.0.0.1', '内网IP', '{ids=22}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:02:26');
INSERT INTO `sys_oper_log` VALUES (180, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/24', '127.0.0.1', '内网IP', '{ids=24}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:02:29');
INSERT INTO `sys_oper_log` VALUES (181, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/25', '127.0.0.1', '内网IP', '{ids=25}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:02:31');
INSERT INTO `sys_oper_log` VALUES (182, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"d5754a3c-b221-4ab3-a3d5-b5cc2f929b78\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 16:59:18');
INSERT INTO `sys_oper_log` VALUES (183, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"66275de4-fc51-476d-8db1-0e8974bd73c4\",\"createBy\":\"admin\",\"createTime\":1685005239844,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 17:00:39');
INSERT INTO `sys_oper_log` VALUES (184, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"66275de4-fc51-476d-8db1-0e8974bd73c4\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 17:00:43');
INSERT INTO `sys_oper_log` VALUES (185, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/28', '127.0.0.1', '内网IP', '{ids=28}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 21:42:02');
INSERT INTO `sys_oper_log` VALUES (186, '业务文件', 3, 'com.cb.web.controller.filemanage.BizAttachController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/attach/11', '127.0.0.1', '内网IP', '{ids=11}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 21:43:58');
INSERT INTO `sys_oper_log` VALUES (187, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"987f6208-ab4a-413d-8a89-fcca2bbd9581\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1685023770179,\"folderName\":\"新建文件夹2\",\"folderParentId\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 22:09:30');
INSERT INTO `sys_oper_log` VALUES (188, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"新建文本文档.txt\",\"code\":200,\"url\":\"/profile/upload/2023/05/25/34ceef97-efb8-4b0f-9485-be800b9ed05b.txt\"}', 0, NULL, '2023-05-25 22:09:47');
INSERT INTO `sys_oper_log` VALUES (189, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"txt\",\"folderId\":\"987f6208-ab4a-413d-8a89-fcca2bbd9581\",\"path\":\"/profile/upload/2023/05/25/34ceef97-efb8-4b0f-9485-be800b9ed05b.txt\",\"createBy\":\"admin\",\"newName\":\"34ceef97-efb8-4b0f-9485-be800b9ed05b.txt\",\"createTime\":1685023790356,\"fileSize\":5572,\"oldName\":\"新建文本文档.txt\",\"id\":14,\"attachId\":\"254d6e3e-984e-4955-9f96-c46e3fda715d\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 22:09:50');
INSERT INTO `sys_oper_log` VALUES (190, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/987f6208-ab4a-413d-8a89-fcca2bbd9581', '127.0.0.1', '内网IP', '{folderId=987f6208-ab4a-413d-8a89-fcca2bbd9581}', 'null', 1, '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Every derived table must have its own alias\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizFileFolderMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select count(0) from (          select folder_id from biz_file_folder where folder_parent_id = ?          UNION all          select folder_id from biz_attach where folder_id = ?        )\r\n### Cause: java.sql.SQLSyntaxErrorException: Every derived table must have its own alias\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Every derived table must have its own alias', '2023-05-25 22:10:04');
INSERT INTO `sys_oper_log` VALUES (191, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/987f6208-ab4a-413d-8a89-fcca2bbd9581', '127.0.0.1', '内网IP', '{folderId=987f6208-ab4a-413d-8a89-fcca2bbd9581}', 'null', 1, '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Every derived table must have its own alias\r\n### The error may exist in file [E:\\ideaProject\\personal_project\\cb-vue\\cb-vue\\cb-system\\target\\classes\\mapper\\filemanage\\BizFileFolderMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select count(0) from (          select folder_id from biz_file_folder where folder_parent_id = ?          UNION all          select folder_id from biz_attach where folder_id = ?        )\r\n### Cause: java.sql.SQLSyntaxErrorException: Every derived table must have its own alias\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Every derived table must have its own alias', '2023-05-25 22:10:38');
INSERT INTO `sys_oper_log` VALUES (192, '业务文件夹', 3, 'com.cb.web.controller.filemanage.BizFileFolderController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/folder/987f6208-ab4a-413d-8a89-fcca2bbd9581', '127.0.0.1', '内网IP', '{folderId=987f6208-ab4a-413d-8a89-fcca2bbd9581}', '{\"msg\":\"该文件夹存在子集，禁止删除\",\"code\":500}', 0, NULL, '2023-05-25 22:11:40');
INSERT INTO `sys_oper_log` VALUES (193, '字典数据', 2, 'com.cb.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"params\":{},\"dictType\":\"del_flag\",\"dictLabel\":\"删除\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1684483543000,\"dictCode\":30,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 22:55:31');
INSERT INTO `sys_oper_log` VALUES (194, '字典数据', 2, 'com.cb.web.controller.system.SysDictDataController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":0,\"params\":{},\"dictType\":\"del_flag\",\"dictLabel\":\"删除\",\"createBy\":\"admin\",\"default\":false,\"isDefault\":\"N\",\"createTime\":1684483543000,\"dictCode\":30,\"updateBy\":\"admin\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-25 22:55:40');
INSERT INTO `sys_oper_log` VALUES (195, '新增业务文件夹', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.addFolder()', 'POST', 1, 'admin', NULL, '/filemanage/file/addFolder', '127.0.0.1', '内网IP', '{\"delFlag\":1,\"params\":{},\"folderId\":\"df2ec7af-a630-41a9-a24e-c1339d85ab38\",\"createBy\":\"admin\",\"children\":[],\"createTime\":1685069753389,\"folderName\":\"新建文件夹4\",\"folderParentId\":\"53f644b8-22e0-4a0b-a00a-11f6e3bba1c2\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 10:55:53');
INSERT INTO `sys_oper_log` VALUES (196, '重命名文件或文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.renameFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/renameFolderOrFile', '127.0.0.1', '内网IP', '{\"name\":\"馨予园\",\"id\":\"df2ec7af-a630-41a9-a24e-c1339d85ab38\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 10:56:25');
INSERT INTO `sys_oper_log` VALUES (197, '重命名文件或文件夹', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.renameFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/renameFolderOrFile', '127.0.0.1', '内网IP', '{\"name\":\"金福地南区\",\"id\":\"987f6208-ab4a-413d-8a89-fcca2bbd9581\",\"type\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 10:56:48');
INSERT INTO `sys_oper_log` VALUES (198, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"完整性校验.docx\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/93783eda-86a9-41cd-b600-fe2c6c16be7c.docx\"}', 0, NULL, '2023-05-26 10:57:03');
INSERT INTO `sys_oper_log` VALUES (199, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":4,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"docx\",\"folderId\":\"26f47fa7-0b05-4d15-a6ec-42fa4b3734fb\",\"path\":\"/profile/upload/2023/05/26/93783eda-86a9-41cd-b600-fe2c6c16be7c.docx\",\"createBy\":\"admin\",\"newName\":\"93783eda-86a9-41cd-b600-fe2c6c16be7c.docx\",\"createTime\":1685069826497,\"fileSize\":11186,\"oldName\":\"完整性校验.docx\",\"id\":15,\"attachId\":\"85f9b2c5-790b-46cd-935d-a79b981dc40c\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 10:57:06');
INSERT INTO `sys_oper_log` VALUES (200, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/3dc1760d-bc1c-411d-b49a-922f65364277.ofd\"}', 0, NULL, '2023-05-26 11:06:01');
INSERT INTO `sys_oper_log` VALUES (201, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"mock.ofd\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/11b3dced-b13d-480c-a6ac-08eaeb637558.ofd\"}', 0, NULL, '2023-05-26 11:13:51');
INSERT INTO `sys_oper_log` VALUES (202, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"ofd\",\"folderId\":\"0\",\"path\":\"/profile/upload/2023/05/26/11b3dced-b13d-480c-a6ac-08eaeb637558.ofd\",\"createBy\":\"admin\",\"newName\":\"11b3dced-b13d-480c-a6ac-08eaeb637558.ofd\",\"createTime\":1685070833087,\"fileSize\":1269758,\"oldName\":\"mock.ofd\",\"id\":16,\"attachId\":\"1f483e52-2432-486a-83e4-4151710a4817\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:13:53');
INSERT INTO `sys_oper_log` VALUES (203, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/a493f436-391c-41ac-b5b7-96de43ee0fcf.pdf\"}', 0, NULL, '2023-05-26 11:14:09');
INSERT INTO `sys_oper_log` VALUES (204, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":1,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"0\",\"path\":\"/profile/upload/2023/05/26/a493f436-391c-41ac-b5b7-96de43ee0fcf.pdf\",\"createBy\":\"admin\",\"newName\":\"a493f436-391c-41ac-b5b7-96de43ee0fcf.pdf\",\"createTime\":1685070852330,\"fileSize\":29025,\"oldName\":\"123456.pdf\",\"id\":17,\"attachId\":\"30aaf15a-0270-4939-b721-8de9132499f4\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:14:12');
INSERT INTO `sys_oper_log` VALUES (205, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'admin', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/7fcd9ba8-1b4f-4d0c-93d4-97c5b89ee405.pdf\"}', 0, NULL, '2023-05-26 11:14:29');
INSERT INTO `sys_oper_log` VALUES (206, '上传业务文件', 1, 'com.cb.web.controller.filemanage.BizAttachController.saveAttach()', 'POST', 1, 'admin', NULL, '/filemanage/attach/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":2,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"0\",\"path\":\"/profile/upload/2023/05/26/7fcd9ba8-1b4f-4d0c-93d4-97c5b89ee405.pdf\",\"createBy\":\"admin\",\"newName\":\"7fcd9ba8-1b4f-4d0c-93d4-97c5b89ee405.pdf\",\"createTime\":1685070870927,\"fileSize\":29025,\"oldName\":\"123456.pdf\",\"id\":18,\"attachId\":\"9a161773-5818-4d37-9b4e-e800f4da51da\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:14:30');
INSERT INTO `sys_oper_log` VALUES (207, '删除业务文件夹或者文件(logic delete)', 2, 'com.cb.web.controller.filemanage.VFolderOrFileController.delFolderOrFile()', 'PUT', 1, 'admin', NULL, '/filemanage/file/delFolderOrFile', '127.0.0.1', '内网IP', '{\"id\":\"30aaf15a-0270-4939-b721-8de9132499f4\",\"type\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:14:34');
INSERT INTO `sys_oper_log` VALUES (208, '业务文件', 3, 'com.cb.web.controller.filemanage.BizAttachController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/attach/17', '127.0.0.1', '内网IP', '{ids=17}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:14:47');
INSERT INTO `sys_oper_log` VALUES (209, '业务文件', 3, 'com.cb.web.controller.filemanage.BizAttachController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/attach/18', '127.0.0.1', '内网IP', '{ids=18}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:15:02');
INSERT INTO `sys_oper_log` VALUES (210, '业务文件', 3, 'com.cb.web.controller.filemanage.BizAttachController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/attach/13', '127.0.0.1', '内网IP', '{ids=13}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:15:06');
INSERT INTO `sys_oper_log` VALUES (211, '业务文件', 3, 'com.cb.web.controller.filemanage.BizAttachController.remove()', 'DELETE', 1, 'admin', NULL, '/filemanage/attach/15', '127.0.0.1', '内网IP', '{ids=15}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:15:09');
INSERT INTO `sys_oper_log` VALUES (212, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"文件夹或文件删除\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1078,\"menuType\":\"F\",\"perms\":\"filemanage:file:delFolderOrFile\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:30:45');
INSERT INTO `sys_oper_log` VALUES (213, '菜单管理', 1, 'com.cb.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"2\",\"menuName\":\"上传文件\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"filemanage:file:saveAttach\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:32:59');
INSERT INTO `sys_oper_log` VALUES (214, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"6\",\"menuName\":\"文件夹和视图导出\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1079,\"menuType\":\"F\",\"perms\":\"filemanage:file:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:33:10');
INSERT INTO `sys_oper_log` VALUES (215, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"文件夹或文件删除\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1078,\"menuType\":\"F\",\"perms\":\"filemanage:file:delFolderOrFile\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:33:15');
INSERT INTO `sys_oper_log` VALUES (216, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"重命名\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1684589171000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1077,\"menuType\":\"F\",\"perms\":\"filemanage:file:rename\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:33:20');
INSERT INTO `sys_oper_log` VALUES (217, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"上传文件\",\"params\":{},\"parentId\":1074,\"isCache\":\"0\",\"path\":\"\",\"children\":[],\"createTime\":1685071979000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1080,\"menuType\":\"F\",\"perms\":\"filemanage:file:saveAttach\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:33:28');
INSERT INTO `sys_oper_log` VALUES (218, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1076,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:35:36');
INSERT INTO `sys_oper_log` VALUES (219, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1074,1075,1076,1080,1077,1078,1079,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:35:59');
INSERT INTO `sys_oper_log` VALUES (220, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1067,1074,1075,1076,1080,1077,1078,1079,1061,1062,1063,1064,1065,1066,1068,1069,1070,1071,1072,1073,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:37:20');
INSERT INTO `sys_oper_log` VALUES (221, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1076,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:38:46');
INSERT INTO `sys_oper_log` VALUES (222, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:40:05');
INSERT INTO `sys_oper_log` VALUES (223, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1076,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:40:51');
INSERT INTO `sys_oper_log` VALUES (224, '业务文件上传', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.uploadFile()', 'POST', 1, 'ry', NULL, '/filemanage/file/biz/upload', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"fileName\":\"123456.pdf\",\"code\":200,\"url\":\"/profile/upload/2023/05/26/cf236adb-3300-4c80-8423-b2120c5dd3d9.pdf\"}', 0, NULL, '2023-05-26 11:43:14');
INSERT INTO `sys_oper_log` VALUES (225, '上传业务文件', 1, 'com.cb.web.controller.filemanage.VFolderOrFileController.saveAttach()', 'POST', 1, 'ry', NULL, '/filemanage/file/saveAttach', '127.0.0.1', '内网IP', '{\"secret\":2,\"delFlag\":1,\"params\":{},\"version\":\"1\",\"extName\":\"pdf\",\"folderId\":\"0\",\"path\":\"/profile/upload/2023/05/26/cf236adb-3300-4c80-8423-b2120c5dd3d9.pdf\",\"createBy\":\"ry\",\"newName\":\"cf236adb-3300-4c80-8423-b2120c5dd3d9.pdf\",\"createTime\":1685072596053,\"fileSize\":29025,\"oldName\":\"123456.pdf\",\"id\":19,\"attachId\":\"84b5c1ad-9cbd-4f4b-a491-c4d41dcb2f9d\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 11:43:16');
INSERT INTO `sys_oper_log` VALUES (226, '字典类型', 1, 'com.cb.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"用户类型\",\"remark\":\"用户类型\",\"params\":{},\"dictType\":\"user_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:02:02');
INSERT INTO `sys_oper_log` VALUES (227, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"00\",\"dictSort\":1,\"remark\":\"普通用户\",\"params\":{},\"dictType\":\"user_type\",\"dictLabel\":\"普通用户\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:02:33');
INSERT INTO `sys_oper_log` VALUES (228, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"01\",\"dictSort\":2,\"remark\":\"系统管理员\",\"params\":{},\"dictType\":\"user_type\",\"dictLabel\":\"系统管理员\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:02:51');
INSERT INTO `sys_oper_log` VALUES (229, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"02\",\"dictSort\":3,\"remark\":\"安全管理员\",\"params\":{},\"dictType\":\"user_type\",\"dictLabel\":\"安全管理员\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:03:09');
INSERT INTO `sys_oper_log` VALUES (230, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"03\",\"dictSort\":4,\"remark\":\"安全审计员\",\"params\":{},\"dictType\":\"user_type\",\"dictLabel\":\"安全审计员\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:03:28');
INSERT INTO `sys_oper_log` VALUES (231, '字典类型', 2, 'com.cb.web.controller.system.SysDictTypeController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":1684483468000,\"updateBy\":\"admin\",\"dictName\":\"删除标记\",\"remark\":\"删除标记，禁止删除\",\"dictId\":11,\"params\":{},\"dictType\":\"del_flag\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:36:56');
INSERT INTO `sys_oper_log` VALUES (232, '字典类型', 2, 'com.cb.web.controller.system.SysDictTypeController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":1684748318000,\"updateBy\":\"admin\",\"dictName\":\"密级\",\"remark\":\"密级，禁止删除\",\"dictId\":12,\"params\":{},\"dictType\":\"secret\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:37:02');
INSERT INTO `sys_oper_log` VALUES (233, '字典类型', 2, 'com.cb.web.controller.system.SysDictTypeController.edit()', 'PUT', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"createTime\":1685080922000,\"updateBy\":\"admin\",\"dictName\":\"用户类型\",\"remark\":\"用户类型，禁止删除\",\"dictId\":13,\"params\":{},\"dictType\":\"user_type\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:37:06');
INSERT INTO `sys_oper_log` VALUES (234, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"超级管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"password\":\"\",\"postIds\":[1],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"若依\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1616734404000,\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员用户', '2023-05-26 14:41:06');
INSERT INTO `sys_oper_log` VALUES (235, '用户管理', 1, 'com.cb.web.controller.system.SysUserController.add()', 'POST', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"admin\":false,\"password\":\"207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb\",\"postIds\":[4],\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"params\":{},\"userName\":\"ouyang\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:42:05');
INSERT INTO `sys_oper_log` VALUES (236, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"超级管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"password\":\"\",\"postIds\":[1],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"若依\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1616734404000,\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员用户', '2023-05-26 14:43:18');
INSERT INTO `sys_oper_log` VALUES (237, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[4],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ouyang\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1685083325000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:46:13');
INSERT INTO `sys_oper_log` VALUES (238, '用户管理', 1, 'com.cb.web.controller.system.SysUserController.add()', 'POST', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"admin\":false,\"password\":\"207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb\",\"postIds\":[],\"nickName\":\"安全管理员\",\"sex\":\"2\",\"params\":{},\"userName\":\"secadmin\",\"userId\":4,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:46:54');
INSERT INTO `sys_oper_log` VALUES (239, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"2\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"安全管理员\",\"sex\":\"2\",\"deptId\":103,\"avatar\":\"\",\"params\":{},\"userName\":\"secadmin\",\"userId\":4,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1685083614000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:47:07');
INSERT INTO `sys_oper_log` VALUES (240, '用户管理', 1, 'com.cb.web.controller.system.SysUserController.add()', 'POST', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"admin\":false,\"password\":\"207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb\",\"postIds\":[],\"nickName\":\"安全审计员\",\"sex\":\"2\",\"deptId\":103,\"params\":{},\"userName\":\"audadmin\",\"userId\":5,\"createBy\":\"admin\",\"roleIds\":[2],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:48:59');
INSERT INTO `sys_oper_log` VALUES (241, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"超级管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2023-05-26 14:54:27');
INSERT INTO `sys_oper_log` VALUES (242, '角色管理', 1, 'com.cb.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"deptIds\":[],\"menuIds\":[],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:54:57');
INSERT INTO `sys_oper_log` VALUES (243, '角色管理', 1, 'com.cb.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"createBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"deptIds\":[],\"menuIds\":[],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:55:28');
INSERT INTO `sys_oper_log` VALUES (244, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"4\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1076,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 14:55:37');
INSERT INTO `sys_oper_log` VALUES (245, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"超级管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"超级管理员\",\"menuIds\":[],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2023-05-26 14:55:54');
INSERT INTO `sys_oper_log` VALUES (246, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', 'null', 1, '不允许操作超级管理员角色', '2023-05-26 15:00:14');
INSERT INTO `sys_oper_log` VALUES (247, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,108,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:01:44');
INSERT INTO `sys_oper_log` VALUES (248, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1067,1074,1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,1075,1076,1080,1077,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:01:55');
INSERT INTO `sys_oper_log` VALUES (249, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"createTime\":1685084128000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,108,500,1040,1041,1042,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:02:03');
INSERT INTO `sys_oper_log` VALUES (250, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.resetPwd()', 'PUT', 1, 'admin', NULL, '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\"admin\":true,\"password\":\"123456\",\"params\":{},\"userId\":1}', 'null', 1, '不允许系统管理员用户', '2023-05-26 15:02:21');
INSERT INTO `sys_oper_log` VALUES (251, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.resetPwd()', 'PUT', 1, 'admin', NULL, '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\"admin\":true,\"password\":\"123456\",\"params\":{},\"userId\":1}', 'null', 1, '不允许系统管理员用户', '2023-05-26 15:02:34');
INSERT INTO `sys_oper_log` VALUES (252, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,108,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:05:20');
INSERT INTO `sys_oper_log` VALUES (253, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[4],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"安全审计员\",\"sex\":\"2\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"audadmin\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[3],\"createTime\":1685083739000,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:21:47');
INSERT INTO `sys_oper_log` VALUES (254, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,108,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:24:55');
INSERT INTO `sys_oper_log` VALUES (255, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"createTime\":1685084128000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"menuIds\":[1,108,500,1040,1041,1042,501,1043,1044,1045],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:25:37');
INSERT INTO `sys_oper_log` VALUES (256, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,108,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:25:59');
INSERT INTO `sys_oper_log` VALUES (257, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:26:30');
INSERT INTO `sys_oper_log` VALUES (258, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:26:39');
INSERT INTO `sys_oper_log` VALUES (259, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.dataScope()', 'PUT', 1, 'admin', NULL, '/system/role/dataScope', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"roleSort\":\"4\",\"deptCheckStrictly\":true,\"createTime\":1616734404000,\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"deptIds\":[],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:27:34');
INSERT INTO `sys_oper_log` VALUES (260, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:33:24');
INSERT INTO `sys_oper_log` VALUES (261, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:34:10');
INSERT INTO `sys_oper_log` VALUES (262, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:34:19');
INSERT INTO `sys_oper_log` VALUES (263, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:34:32');
INSERT INTO `sys_oper_log` VALUES (264, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,108,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:34:37');
INSERT INTO `sys_oper_log` VALUES (265, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":true,\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1685084128000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"menuIds\":[1,108,500,1040,1041,1042,501,1043,1044,1045],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:34:42');
INSERT INTO `sys_oper_log` VALUES (266, '用户管理', 3, 'com.cb.web.controller.system.SysUserController.remove()', 'DELETE', 1, 'admin', NULL, '/system/user/1', '127.0.0.1', '内网IP', '{userIds=1}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:36:21');
INSERT INTO `sys_oper_log` VALUES (267, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"postIds\":[1],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"系统管理员\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1616734404000,\"userType\":\"01\",\"status\":\"0\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:38:10');
INSERT INTO `sys_oper_log` VALUES (268, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"postIds\":[1],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"系统管理员\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1616734404000,\"userType\":\"01\",\"status\":\"0\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:38:39');
INSERT INTO `sys_oper_log` VALUES (269, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[1],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"系统管理员\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"roleIds\":[1],\"createTime\":1616734404000,\"userType\":\"01\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:38:55');
INSERT INTO `sys_oper_log` VALUES (270, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"params\":{},\"audAdmin\":false,\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"status\":\"1\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 15:39:47');
INSERT INTO `sys_oper_log` VALUES (271, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"params\":{},\"audAdmin\":false,\"deptCheckStrictly\":false,\"secAdmin\":true,\"menuCheckStrictly\":false,\"status\":\"1\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 15:39:50');
INSERT INTO `sys_oper_log` VALUES (272, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"params\":{},\"audAdmin\":true,\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"status\":\"1\"}', 'null', 1, '不允许操作安全审计员用户', '2023-05-26 15:39:52');
INSERT INTO `sys_oper_log` VALUES (273, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"params\":{},\"audAdmin\":false,\"deptCheckStrictly\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:39:55');
INSERT INTO `sys_oper_log` VALUES (274, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"params\":{},\"audAdmin\":false,\"deptCheckStrictly\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:39:57');
INSERT INTO `sys_oper_log` VALUES (275, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,1007,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 15:46:09');
INSERT INTO `sys_oper_log` VALUES (276, '个人信息', 2, 'com.cb.web.controller.system.SysProfileController.updateProfile()', 'PUT', 1, 'admin', NULL, '/system/user/profile', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":1,\"admin\":true,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"status\":\"0\"}],\"phonenumber\":\"15888888888\",\"admin\":true,\"loginDate\":1616734404000,\"remark\":\"管理员\",\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"loginIp\":\"127.0.0.1\",\"email\":\"ry@163.com\",\"nickName\":\"系统管理员\",\"sex\":\"1\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"admin\",\"userId\":1,\"createBy\":\"admin\",\"createTime\":1616734404000,\"userType\":\"01\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:00:28');
INSERT INTO `sys_oper_log` VALUES (277, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"secAdmin\":true,\"menuCheckStrictly\":false,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"createBy\":\"admin\",\"roleIds\":[4],\"createTime\":1685083325000,\"userType\":\"00\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:06:25');
INSERT INTO `sys_oper_log` VALUES (278, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"secAdmin\":true,\"menuCheckStrictly\":false,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"status\":\"0\"}],\"phonenumber\":\"15666666666\",\"admin\":false,\"loginDate\":1616734404000,\"remark\":\"测试员\",\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"127.0.0.1\",\"email\":\"ry@qq.com\",\"nickName\":\"若依\",\"sex\":\"1\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ry\",\"userId\":4,\"createBy\":\"admin\",\"roleIds\":[4],\"createTime\":1616734404000,\"userType\":\"00\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:06:33');
INSERT INTO `sys_oper_log` VALUES (279, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":4,\"admin\":false,\"remark\":\"普通角色\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"4\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"menuIds\":[1067,1074,1075,1076,1080,1077,1078,1079,1061,1062,1063,1064,1065,1066,1068,1069,1070,1071,1072,1073],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:15:59');
INSERT INTO `sys_oper_log` VALUES (280, '角色管理', 1, 'com.cb.web.controller.system.SysRoleController.add()', 'POST', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":5,\"admin\":false,\"params\":{},\"audAdmin\":false,\"roleSort\":\"0\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"test\",\"roleName\":\"测试角色\",\"deptIds\":[],\"menuIds\":[1067,1074,1075,1076,1080,1077,1078,1079,1061,1062,1063,1064,1065,1066,1068,1069,1070,1071,1072,1073,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:18:12');
INSERT INTO `sys_oper_log` VALUES (281, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":4,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"4\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"createBy\":\"admin\",\"roleIds\":[4,5],\"createTime\":1685083325000,\"userType\":\"00\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:18:26');
INSERT INTO `sys_oper_log` VALUES (282, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":4,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"4\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"},{\"flag\":false,\"roleId\":5,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"0\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"test\",\"roleName\":\"测试角色\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"createBy\":\"admin\",\"roleIds\":[4],\"createTime\":1685083325000,\"userType\":\"00\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:19:01');
INSERT INTO `sys_oper_log` VALUES (283, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":true,\"roleSort\":\"3\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":true,\"secAdmin\":false,\"password\":\"\",\"postIds\":[4],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"安全审计员\",\"sex\":\"2\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"audadmin\",\"userId\":3,\"createBy\":\"admin\",\"roleIds\":[],\"createTime\":1685083739000,\"userType\":\"03\",\"status\":\"0\"}', 'null', 1, '不允许操作安全审计员用户', '2023-05-26 16:23:19');
INSERT INTO `sys_oper_log` VALUES (284, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":true,\"params\":{},\"userId\":2,\"status\":\"1\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 16:50:47');
INSERT INTO `sys_oper_log` VALUES (285, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":true,\"audAdmin\":false,\"secAdmin\":false,\"params\":{},\"userId\":1,\"status\":\"1\"}', 'null', 1, '不允许操作系统管理员用户', '2023-05-26 16:50:49');
INSERT INTO `sys_oper_log` VALUES (286, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":true,\"secAdmin\":false,\"params\":{},\"userId\":3,\"status\":\"1\"}', 'null', 1, '不允许操作安全审计员用户', '2023-05-26 16:50:52');
INSERT INTO `sys_oper_log` VALUES (287, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":4,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:50:54');
INSERT INTO `sys_oper_log` VALUES (288, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":4,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:50:56');
INSERT INTO `sys_oper_log` VALUES (289, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:52:42');
INSERT INTO `sys_oper_log` VALUES (290, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,108,1001,1007,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:53:06');
INSERT INTO `sys_oper_log` VALUES (291, '菜单管理', 1, 'com.cb.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"8\",\"menuName\":\"修改状态\",\"params\":{},\"parentId\":100,\"isCache\":\"0\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:user:changeStatus\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 16:57:29');
INSERT INTO `sys_oper_log` VALUES (292, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,108,1001,1007,1081,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 17:09:09');
INSERT INTO `sys_oper_log` VALUES (293, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,108,1001,1007,1081,101,1008,1009,1010,1011,1012,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:09:16');
INSERT INTO `sys_oper_log` VALUES (294, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:09:25');
INSERT INTO `sys_oper_log` VALUES (295, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'secadmin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"secadmin\",\"params\":{},\"userId\":5,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:14:03');
INSERT INTO `sys_oper_log` VALUES (296, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'secadmin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":true,\"secAdmin\":false,\"params\":{},\"userId\":3,\"status\":\"1\"}', 'null', 1, '不允许操作安全审计员用户', '2023-05-26 17:14:05');
INSERT INTO `sys_oper_log` VALUES (297, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'secadmin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"secadmin\",\"params\":{},\"userId\":4,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:14:07');
INSERT INTO `sys_oper_log` VALUES (298, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'secadmin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"secadmin\",\"params\":{},\"userId\":4,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:14:09');
INSERT INTO `sys_oper_log` VALUES (299, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'secadmin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"secadmin\",\"params\":{},\"userId\":5,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:14:11');
INSERT INTO `sys_oper_log` VALUES (300, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,101,104,108,1001,1004,1007,1081,1008,1009,1010,1011,103,1017,1018,1019,1020,1021,1022,1023,1024,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:16:16');
INSERT INTO `sys_oper_log` VALUES (301, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":1,\"admin\":true,\"remark\":\"系统管理员\",\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"1\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1616734404000,\"updateBy\":\"admin\",\"menuCheckStrictly\":false,\"roleKey\":\"admin\",\"roleName\":\"系统管理员\",\"menuIds\":[1,100,1001,1002,1003,1004,1005,1006,101,1008,1009,1010,1011,1012,102,1013,1014,1015,1016,103,1017,1018,1019,1020,104,1021,1022,1023,1024,1025,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,107,1036,1037,1038,1039,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113,3,114,115,1055,1056,1058,1057,1059,1060,116],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:16:39');
INSERT INTO `sys_oper_log` VALUES (302, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":true,\"roleSort\":\"3\",\"deptCheckStrictly\":true,\"secAdmin\":false,\"createTime\":1685084128000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"audadmin\",\"roleName\":\"安全审计员\",\"menuIds\":[1,108,500,1040,1041,1042,501,1043,1044,1045],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:16:42');
INSERT INTO `sys_oper_log` VALUES (303, '参数管理', 1, 'com.cb.web.controller.system.SysConfigController.add()', 'POST', 1, 'admin', NULL, '/system/config', '127.0.0.1', '内网IP', '{\"configName\":\"系统三员管理 - 开启与关闭\",\"configKey\":\"1\",\"createBy\":\"admin\",\"configType\":\"Y\",\"configValue\":\"1\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:21:59');
INSERT INTO `sys_oper_log` VALUES (304, '参数管理', 2, 'com.cb.web.controller.system.SysConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/config', '127.0.0.1', '内网IP', '{\"configName\":\"系统三员管理 - 开启与关闭\",\"configKey\":\"sys.three.member.open.state\",\"createBy\":\"admin\",\"createTime\":1685092919000,\"updateBy\":\"admin\",\"configId\":4,\"remark\":\"系统三员管理 - 开启与关闭, 参数键值为true 开启，false 关闭 \",\"configType\":\"Y\",\"configValue\":\"true\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 17:23:47');
INSERT INTO `sys_oper_log` VALUES (305, '参数管理', 2, 'com.cb.web.controller.system.SysConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/config', '127.0.0.1', '内网IP', '{\"configName\":\"系统三员管理 - 开启与关闭\",\"configKey\":\"sys.three.member.open.state\",\"createBy\":\"admin\",\"createTime\":1685092919000,\"updateBy\":\"admin\",\"configId\":4,\"remark\":\"系统三员管理 - 开启与关闭, 参数键值为true 开启，false 关闭 \",\"updateTime\":1685093027000,\"configType\":\"Y\",\"configValue\":\"false\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 18:01:18');
INSERT INTO `sys_oper_log` VALUES (306, '参数管理', 3, 'com.cb.web.controller.system.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL, '/system/config/4', '127.0.0.1', '内网IP', '{configIds=4}', 'null', 1, '内置参数【sys.three.member.open.state】不能删除 ', '2023-05-26 18:05:44');
INSERT INTO `sys_oper_log` VALUES (307, '参数管理', 2, 'com.cb.web.controller.system.SysConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/config', '127.0.0.1', '内网IP', '{\"configName\":\"系统三员管理 - 开启与关闭\",\"configKey\":\"sys.three.member.open.state\",\"createBy\":\"admin\",\"createTime\":1685092919000,\"updateBy\":\"admin\",\"configId\":4,\"remark\":\"系统三员管理 - 开启与关闭, 参数键值为true 开启，false 关闭 \",\"updateTime\":1685095278000,\"configType\":\"N\",\"configValue\":\"false\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 18:05:53');
INSERT INTO `sys_oper_log` VALUES (308, '参数管理', 2, 'com.cb.web.controller.system.SysConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/config', '127.0.0.1', '内网IP', '{\"configName\":\"系统三员管理 - 开启与关闭\",\"configKey\":\"sys.three.member.open.state\",\"createBy\":\"admin\",\"createTime\":1685092919000,\"updateBy\":\"admin\",\"configId\":4,\"remark\":\"系统三员管理 - 开启与关闭, 参数键值为true 开启，false 关闭 \",\"updateTime\":1685095553000,\"configType\":\"N\",\"configValue\":\"false\",\"params\":{}}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 18:06:36');
INSERT INTO `sys_oper_log` VALUES (309, '参数管理', 3, 'com.cb.web.controller.system.SysConfigController.remove()', 'DELETE', 1, 'admin', NULL, '/system/config/4', '127.0.0.1', '内网IP', '{configIds=4}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 18:06:38');
INSERT INTO `sys_oper_log` VALUES (310, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":false,\"secAdmin\":true,\"menuCheckStrictly\":false,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":true,\"password\":\"\",\"postIds\":[2],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"安全管理员\",\"sex\":\"2\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"secadmin\",\"userId\":2,\"createBy\":\"admin\",\"roleIds\":[2],\"createTime\":1685083614000,\"userType\":\"02\",\"status\":\"0\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 22:32:01');
INSERT INTO `sys_oper_log` VALUES (311, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":true,\"params\":{},\"userId\":2,\"status\":\"1\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 22:32:06');
INSERT INTO `sys_oper_log` VALUES (312, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":true,\"secAdmin\":false,\"params\":{},\"userId\":3,\"status\":\"1\"}', 'null', 1, '不允许操作安全审计员用户', '2023-05-26 22:32:09');
INSERT INTO `sys_oper_log` VALUES (313, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.edit()', 'PUT', 1, 'admin', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"roles\":[{\"flag\":false,\"roleId\":4,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"4\",\"deptCheckStrictly\":false,\"secAdmin\":false,\"menuCheckStrictly\":false,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"status\":\"0\"}],\"phonenumber\":\"\",\"admin\":false,\"delFlag\":\"0\",\"audAdmin\":false,\"secAdmin\":false,\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"欧阳\",\"sex\":\"0\",\"deptId\":103,\"avatar\":\"\",\"dept\":{\"deptName\":\"研发部门\",\"leader\":\"若依\",\"deptId\":103,\"orderNum\":\"1\",\"params\":{},\"parentId\":101,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"createBy\":\"admin\",\"roleIds\":[4],\"createTime\":1685083325000,\"userType\":\"00\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 23:47:38');
INSERT INTO `sys_oper_log` VALUES (314, '用户权限设置', 2, 'com.cb.web.controller.system.SysUserController.permissionSet()', 'PUT', 1, 'admin', NULL, '/system/user/permissionSet', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"roleIds\":[4,5]}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 23:49:15');
INSERT INTO `sys_oper_log` VALUES (315, '菜单管理', 1, 'com.cb.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"orderNum\":\"9\",\"menuName\":\"权限设置\",\"params\":{},\"parentId\":100,\"isCache\":\"0\",\"createBy\":\"admin\",\"children\":[],\"isFrame\":\"1\",\"menuType\":\"F\",\"perms\":\"system:user:permissionSet\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 23:52:34');
INSERT INTO `sys_oper_log` VALUES (316, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,101,104,108,1001,1004,1007,1081,1082,1008,1009,1010,1011,103,1017,1018,1019,1020,1021,1022,1023,1024,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', 'null', 1, '不允许操作安全管理员用户', '2023-05-26 23:52:57');
INSERT INTO `sys_oper_log` VALUES (317, '用户权限设置', 2, 'com.cb.web.controller.system.SysUserController.permissionSet()', 'PUT', 1, 'admin', NULL, '/system/user/permissionSet', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":true,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userName\":\"audadmin\",\"userId\":3,\"roleIds\":[3]}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-26 23:53:09');
INSERT INTO `sys_oper_log` VALUES (318, '用户权限设置', 2, 'com.cb.web.controller.system.SysUserController.permissionSet()', 'PUT', 1, 'admin', NULL, '/system/user/permissionSet', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"roleIds\":[4,5]}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-27 13:16:12');
INSERT INTO `sys_oper_log` VALUES (319, '用户权限设置', 2, 'com.cb.web.controller.system.SysUserController.permissionSet()', 'PUT', 1, 'admin', NULL, '/system/user/permissionSet', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userName\":\"ouyang\",\"userId\":5,\"roleIds\":[4]}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-27 13:16:22');
INSERT INTO `sys_oper_log` VALUES (320, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":5,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-27 13:16:52');
INSERT INTO `sys_oper_log` VALUES (321, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":5,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-27 13:16:54');
INSERT INTO `sys_oper_log` VALUES (322, '在线用户', 7, 'com.cb.web.controller.monitor.SysUserOnlineController.forceLogout()', 'DELETE', 1, 'admin', NULL, '/monitor/online/1709a0bf-0ea3-4d80-beb9-9aeb97f580b2', '127.0.0.1', '内网IP', '{tokenId=1709a0bf-0ea3-4d80-beb9-9aeb97f580b2}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-05-27 13:42:26');
INSERT INTO `sys_oper_log` VALUES (323, '代码生成', 6, 'com.cb.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_common_config', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:00:28');
INSERT INTO `sys_oper_log` VALUES (324, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ouyang\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":59,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1697680828000,\"tableId\":6,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"EnableThreeMember\",\"usableColumn\":false,\"columnId\":60,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"enableThreeMember\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"三员配置 0 正常 1 停用\",\"isQuery\":\"1\",\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1697680828000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"enable_three_member\"},{\"capJavaField\":\"EnablePasswordPolicy\",\"usableColumn\":false,\"columnId\":61,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"enablePasswordPolicy\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"是否开启密码策略\",\"isQuery\":\"1\",\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1697680828000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"enable_password_policy\"},{\"capJavaField\":\"PasswordPolicy\",\"usableColumn\":false,\"columnId\":62,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"passwordPolicy\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"密码策略\",\"isQuery\":\"1\",\"sort\":4,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:00:57');
INSERT INTO `sys_oper_log` VALUES (325, '代码生成', 2, 'com.cb.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"sub\":false,\"functionAuthor\":\"ouyang\",\"columns\":[{\"capJavaField\":\"Id\",\"usableColumn\":false,\"columnId\":59,\"isIncrement\":\"1\",\"increment\":true,\"insert\":true,\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"id\",\"htmlType\":\"input\",\"edit\":false,\"query\":false,\"updateTime\":1697680857000,\"sort\":1,\"list\":false,\"params\":{},\"javaType\":\"Long\",\"queryType\":\"EQ\",\"columnType\":\"bigint(20)\",\"createBy\":\"admin\",\"isPk\":\"1\",\"createTime\":1697680828000,\"tableId\":6,\"pk\":true,\"columnName\":\"id\"},{\"capJavaField\":\"EnableThreeMember\",\"usableColumn\":false,\"columnId\":60,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"enableThreeMember\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"三员配置 0 正常 1 停用\",\"isQuery\":\"1\",\"updateTime\":1697680857000,\"sort\":2,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1697680828000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"enable_three_member\"},{\"capJavaField\":\"EnablePasswordPolicy\",\"usableColumn\":false,\"columnId\":61,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"enablePasswordPolicy\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"是否开启密码策略\",\"isQuery\":\"1\",\"updateTime\":1697680857000,\"sort\":3,\"list\":true,\"params\":{},\"javaType\":\"Integer\",\"queryType\":\"EQ\",\"columnType\":\"int(1)\",\"createBy\":\"admin\",\"isPk\":\"0\",\"createTime\":1697680828000,\"isEdit\":\"1\",\"tableId\":6,\"pk\":false,\"columnName\":\"enable_password_policy\"},{\"capJavaField\":\"PasswordPolicy\",\"usableColumn\":false,\"columnId\":62,\"isIncrement\":\"0\",\"increment\":false,\"insert\":true,\"isList\":\"1\",\"dictType\":\"\",\"required\":false,\"superColumn\":false,\"updateBy\":\"\",\"isInsert\":\"1\",\"javaField\":\"passwordPolicy\",\"htmlType\":\"input\",\"edit\":true,\"query\":true,\"columnComment\":\"密码策略\",', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:02:19');
INSERT INTO `sys_oper_log` VALUES (326, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"10\",\"menuName\":\"通用配置\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"commonConfig\",\"component\":\"system/commonConfig/index\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1083,\"menuType\":\"C\",\"perms\":\"system:commonConfig:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:03:54');
INSERT INTO `sys_oper_log` VALUES (327, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697681141514,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":1,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":1,\"passwordReminderTime\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:05:41');
INSERT INTO `sys_oper_log` VALUES (328, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"dict\",\"orderNum\":\"10\",\"menuName\":\"通用配置\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"commonConfig\",\"component\":\"system/commonConfig/index\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1083,\"menuType\":\"C\",\"perms\":\"system:commonConfig:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:07:17');
INSERT INTO `sys_oper_log` VALUES (329, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"edit\",\"orderNum\":\"10\",\"menuName\":\"通用配置\",\"params\":{},\"parentId\":1,\"isCache\":\"0\",\"path\":\"commonConfig\",\"component\":\"system/commonConfig/index\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1083,\"menuType\":\"C\",\"perms\":\"system:commonConfig:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:07:39');
INSERT INTO `sys_oper_log` VALUES (330, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,101,104,108,1001,1004,1007,1081,1008,1009,1010,1011,103,1017,1018,1019,1020,1021,1022,1023,1024,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,1083,1084,1085,1086,1087,1088,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', 'null', 1, '不允许操作安全管理员用户', '2023-10-19 10:33:07');
INSERT INTO `sys_oper_log` VALUES (331, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,101,104,108,1001,1004,1007,1081,1008,1009,1010,1011,103,1017,1018,1019,1020,1021,1022,1023,1024,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,1083,1084,1085,1086,1087,1088,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:34:03');
INSERT INTO `sys_oper_log` VALUES (332, '角色管理', 2, 'com.cb.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"flag\":false,\"roleId\":2,\"admin\":false,\"dataScope\":\"1\",\"delFlag\":\"0\",\"params\":{},\"audAdmin\":false,\"roleSort\":\"2\",\"deptCheckStrictly\":true,\"secAdmin\":true,\"createTime\":1685084097000,\"updateBy\":\"admin\",\"menuCheckStrictly\":true,\"roleKey\":\"secadmin\",\"roleName\":\"安全管理员\",\"menuIds\":[1,100,101,104,108,1001,1004,1007,1081,1008,1009,1010,1011,103,1017,1018,1019,1020,1021,1022,1023,1024,105,1026,1027,1028,1029,1030,106,1031,1032,1033,1034,1035,501,1043,1044,1045,1083,1084,1085,1086,1087,1088,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,111,112,113],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:34:12');
INSERT INTO `sys_oper_log` VALUES (333, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697682891189,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":0,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":1,\"passwordReminderTime\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:34:51');
INSERT INTO `sys_oper_log` VALUES (334, '字典类型', 1, 'com.cb.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/system/dict/type', '127.0.0.1', '内网IP', '{\"createBy\":\"admin\",\"dictName\":\"密码强度策略\",\"remark\":\"禁止删除\",\"params\":{},\"dictType\":\"password_policy\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:39:11');
INSERT INTO `sys_oper_log` VALUES (335, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"params\":{},\"dictType\":\"password_policy\",\"dictLabel\":\"字母数字任意 \",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:39:34');
INSERT INTO `sys_oper_log` VALUES (336, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"2\",\"dictSort\":1,\"params\":{},\"dictType\":\"password_policy\",\"dictLabel\":\"数字与字母混合\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:39:51');
INSERT INTO `sys_oper_log` VALUES (337, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"3\",\"dictSort\":2,\"params\":{},\"dictType\":\"password_policy\",\"dictLabel\":\"数字、特殊字符与字母混合\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:40:04');
INSERT INTO `sys_oper_log` VALUES (338, '字典数据', 1, 'com.cb.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"4\",\"dictSort\":3,\"params\":{},\"dictType\":\"password_policy\",\"dictLabel\":\"数字、特殊字符与大小写字母混合\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:40:19');
INSERT INTO `sys_oper_log` VALUES (339, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"1\",\"menuName\":\"系统通用配置查询\",\"params\":{},\"parentId\":1083,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1084,\"menuType\":\"F\",\"perms\":\"system:commonConfig:query\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:42:53');
INSERT INTO `sys_oper_log` VALUES (340, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"2\",\"menuName\":\"系统通用配置新增\",\"params\":{},\"parentId\":1083,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1085,\"menuType\":\"F\",\"perms\":\"system:commonConfig:add\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:42:57');
INSERT INTO `sys_oper_log` VALUES (341, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"3\",\"menuName\":\"系统通用配置修改\",\"params\":{},\"parentId\":1083,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1086,\"menuType\":\"F\",\"perms\":\"system:commonConfig:edit\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:43:01');
INSERT INTO `sys_oper_log` VALUES (342, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"4\",\"menuName\":\"系统通用配置删除\",\"params\":{},\"parentId\":1083,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1087,\"menuType\":\"F\",\"perms\":\"system:commonConfig:remove\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:43:06');
INSERT INTO `sys_oper_log` VALUES (343, '菜单管理', 2, 'com.cb.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"#\",\"orderNum\":\"5\",\"menuName\":\"系统通用配置导出\",\"params\":{},\"parentId\":1083,\"isCache\":\"0\",\"path\":\"#\",\"component\":\"\",\"children\":[],\"createTime\":1697680968000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":1088,\"menuType\":\"F\",\"perms\":\"system:commonConfig:export\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 10:43:14');
INSERT INTO `sys_oper_log` VALUES (344, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697699559962,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":1,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":0,\"passwordReminderTime\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:12:40');
INSERT INTO `sys_oper_log` VALUES (345, '个人信息', 2, 'com.cb.web.controller.system.SysProfileController.updatePwd()', 'PUT', 1, 'admin', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', 'admin123 admin@123', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:14:29');
INSERT INTO `sys_oper_log` VALUES (346, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697699692750,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":1,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":0,\"passwordReminderTime\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:14:52');
INSERT INTO `sys_oper_log` VALUES (347, '用户管理', 2, 'com.cb.web.controller.system.SysUserController.resetPwd()', 'PUT', 1, 'admin', NULL, '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\"admin\":false,\"audAdmin\":false,\"secAdmin\":false,\"password\":\"207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb\",\"updateBy\":\"admin\",\"params\":{},\"userId\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:20:48');
INSERT INTO `sys_oper_log` VALUES (348, '个人信息', 2, 'com.cb.web.controller.system.SysProfileController.updatePwd()', 'PUT', 1, 'admin', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', 'admin@123 admin123', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:21:33');
INSERT INTO `sys_oper_log` VALUES (349, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697700141121,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":1,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":0,\"passwordReminderTime\":5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:22:21');
INSERT INTO `sys_oper_log` VALUES (350, '系统配置', 2, 'com.cb.web.controller.system.SysCommonConfigController.edit()', 'PUT', 1, 'admin', NULL, '/system/commonConfig', '127.0.0.1', '内网IP', '{\"passwordExpiration\":90,\"passwordPolicy\":1,\"updateTime\":1697700227239,\"params\":{},\"passwordLength\":8,\"enableThreeMember\":1,\"passwordLockTime\":10,\"createTime\":1685586937000,\"enableAutoMessage\":1,\"passwordErrorsNo\":5,\"id\":1,\"enablePasswordPolicy\":0,\"passwordReminderTime\":3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:23:47');
INSERT INTO `sys_oper_log` VALUES (351, '个人信息', 2, 'com.cb.web.controller.system.SysProfileController.updatePwd()', 'PUT', 1, 'admin', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', 'admin123 admin1234', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2023-10-19 15:38:32');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(11) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-03-26 12:53:24', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-03-26 12:53:24', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', 1, '1', 0, 1, '0', '0', 'admin', '2021-03-26 12:53:24', 'admin', '2023-05-26 17:16:39', '系统管理员');
INSERT INTO `sys_role` VALUES (2, '安全管理员', 'secadmin', 2, '1', 1, 1, '0', '0', 'admin', '2023-05-26 14:54:57', 'admin', '2023-10-19 10:34:12', NULL);
INSERT INTO `sys_role` VALUES (3, '安全审计员', 'audadmin', 3, '1', 1, 1, '0', '0', 'admin', '2023-05-26 14:55:28', 'admin', '2023-05-26 17:16:42', NULL);
INSERT INTO `sys_role` VALUES (4, '普通角色', 'common', 4, '1', 1, 0, '0', '0', 'admin', '2021-03-26 12:53:24', 'admin', '2023-05-26 16:15:59', '普通角色');
INSERT INTO `sys_role` VALUES (5, '测试角色', 'test', 5, '1', 1, 1, '0', '0', 'admin', '2023-05-26 16:18:12', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 110);
INSERT INTO `sys_role_menu` VALUES (1, 111);
INSERT INTO `sys_role_menu` VALUES (1, 112);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 115);
INSERT INTO `sys_role_menu` VALUES (1, 116);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 1008);
INSERT INTO `sys_role_menu` VALUES (1, 1009);
INSERT INTO `sys_role_menu` VALUES (1, 1010);
INSERT INTO `sys_role_menu` VALUES (1, 1011);
INSERT INTO `sys_role_menu` VALUES (1, 1012);
INSERT INTO `sys_role_menu` VALUES (1, 1013);
INSERT INTO `sys_role_menu` VALUES (1, 1014);
INSERT INTO `sys_role_menu` VALUES (1, 1015);
INSERT INTO `sys_role_menu` VALUES (1, 1016);
INSERT INTO `sys_role_menu` VALUES (1, 1017);
INSERT INTO `sys_role_menu` VALUES (1, 1018);
INSERT INTO `sys_role_menu` VALUES (1, 1019);
INSERT INTO `sys_role_menu` VALUES (1, 1020);
INSERT INTO `sys_role_menu` VALUES (1, 1021);
INSERT INTO `sys_role_menu` VALUES (1, 1022);
INSERT INTO `sys_role_menu` VALUES (1, 1023);
INSERT INTO `sys_role_menu` VALUES (1, 1024);
INSERT INTO `sys_role_menu` VALUES (1, 1025);
INSERT INTO `sys_role_menu` VALUES (1, 1026);
INSERT INTO `sys_role_menu` VALUES (1, 1027);
INSERT INTO `sys_role_menu` VALUES (1, 1028);
INSERT INTO `sys_role_menu` VALUES (1, 1029);
INSERT INTO `sys_role_menu` VALUES (1, 1030);
INSERT INTO `sys_role_menu` VALUES (1, 1031);
INSERT INTO `sys_role_menu` VALUES (1, 1032);
INSERT INTO `sys_role_menu` VALUES (1, 1033);
INSERT INTO `sys_role_menu` VALUES (1, 1034);
INSERT INTO `sys_role_menu` VALUES (1, 1035);
INSERT INTO `sys_role_menu` VALUES (1, 1036);
INSERT INTO `sys_role_menu` VALUES (1, 1037);
INSERT INTO `sys_role_menu` VALUES (1, 1038);
INSERT INTO `sys_role_menu` VALUES (1, 1039);
INSERT INTO `sys_role_menu` VALUES (1, 1046);
INSERT INTO `sys_role_menu` VALUES (1, 1047);
INSERT INTO `sys_role_menu` VALUES (1, 1048);
INSERT INTO `sys_role_menu` VALUES (1, 1049);
INSERT INTO `sys_role_menu` VALUES (1, 1050);
INSERT INTO `sys_role_menu` VALUES (1, 1051);
INSERT INTO `sys_role_menu` VALUES (1, 1052);
INSERT INTO `sys_role_menu` VALUES (1, 1053);
INSERT INTO `sys_role_menu` VALUES (1, 1054);
INSERT INTO `sys_role_menu` VALUES (1, 1055);
INSERT INTO `sys_role_menu` VALUES (1, 1056);
INSERT INTO `sys_role_menu` VALUES (1, 1057);
INSERT INTO `sys_role_menu` VALUES (1, 1058);
INSERT INTO `sys_role_menu` VALUES (1, 1059);
INSERT INTO `sys_role_menu` VALUES (1, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1081);
INSERT INTO `sys_role_menu` VALUES (2, 1083);
INSERT INTO `sys_role_menu` VALUES (2, 1084);
INSERT INTO `sys_role_menu` VALUES (2, 1085);
INSERT INTO `sys_role_menu` VALUES (2, 1086);
INSERT INTO `sys_role_menu` VALUES (2, 1087);
INSERT INTO `sys_role_menu` VALUES (2, 1088);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 501);
INSERT INTO `sys_role_menu` VALUES (3, 1040);
INSERT INTO `sys_role_menu` VALUES (3, 1041);
INSERT INTO `sys_role_menu` VALUES (3, 1042);
INSERT INTO `sys_role_menu` VALUES (3, 1043);
INSERT INTO `sys_role_menu` VALUES (3, 1044);
INSERT INTO `sys_role_menu` VALUES (3, 1045);
INSERT INTO `sys_role_menu` VALUES (4, 1061);
INSERT INTO `sys_role_menu` VALUES (4, 1062);
INSERT INTO `sys_role_menu` VALUES (4, 1063);
INSERT INTO `sys_role_menu` VALUES (4, 1064);
INSERT INTO `sys_role_menu` VALUES (4, 1065);
INSERT INTO `sys_role_menu` VALUES (4, 1066);
INSERT INTO `sys_role_menu` VALUES (4, 1067);
INSERT INTO `sys_role_menu` VALUES (4, 1068);
INSERT INTO `sys_role_menu` VALUES (4, 1069);
INSERT INTO `sys_role_menu` VALUES (4, 1070);
INSERT INTO `sys_role_menu` VALUES (4, 1071);
INSERT INTO `sys_role_menu` VALUES (4, 1072);
INSERT INTO `sys_role_menu` VALUES (4, 1073);
INSERT INTO `sys_role_menu` VALUES (4, 1074);
INSERT INTO `sys_role_menu` VALUES (4, 1075);
INSERT INTO `sys_role_menu` VALUES (4, 1076);
INSERT INTO `sys_role_menu` VALUES (4, 1077);
INSERT INTO `sys_role_menu` VALUES (4, 1078);
INSERT INTO `sys_role_menu` VALUES (4, 1079);
INSERT INTO `sys_role_menu` VALUES (4, 1080);
INSERT INTO `sys_role_menu` VALUES (5, 2);
INSERT INTO `sys_role_menu` VALUES (5, 109);
INSERT INTO `sys_role_menu` VALUES (5, 110);
INSERT INTO `sys_role_menu` VALUES (5, 111);
INSERT INTO `sys_role_menu` VALUES (5, 112);
INSERT INTO `sys_role_menu` VALUES (5, 113);
INSERT INTO `sys_role_menu` VALUES (5, 1046);
INSERT INTO `sys_role_menu` VALUES (5, 1047);
INSERT INTO `sys_role_menu` VALUES (5, 1048);
INSERT INTO `sys_role_menu` VALUES (5, 1049);
INSERT INTO `sys_role_menu` VALUES (5, 1050);
INSERT INTO `sys_role_menu` VALUES (5, 1051);
INSERT INTO `sys_role_menu` VALUES (5, 1052);
INSERT INTO `sys_role_menu` VALUES (5, 1053);
INSERT INTO `sys_role_menu` VALUES (5, 1054);
INSERT INTO `sys_role_menu` VALUES (5, 1061);
INSERT INTO `sys_role_menu` VALUES (5, 1062);
INSERT INTO `sys_role_menu` VALUES (5, 1063);
INSERT INTO `sys_role_menu` VALUES (5, 1064);
INSERT INTO `sys_role_menu` VALUES (5, 1065);
INSERT INTO `sys_role_menu` VALUES (5, 1066);
INSERT INTO `sys_role_menu` VALUES (5, 1067);
INSERT INTO `sys_role_menu` VALUES (5, 1068);
INSERT INTO `sys_role_menu` VALUES (5, 1069);
INSERT INTO `sys_role_menu` VALUES (5, 1070);
INSERT INTO `sys_role_menu` VALUES (5, 1071);
INSERT INTO `sys_role_menu` VALUES (5, 1072);
INSERT INTO `sys_role_menu` VALUES (5, 1073);
INSERT INTO `sys_role_menu` VALUES (5, 1074);
INSERT INTO `sys_role_menu` VALUES (5, 1075);
INSERT INTO `sys_role_menu` VALUES (5, 1076);
INSERT INTO `sys_role_menu` VALUES (5, 1077);
INSERT INTO `sys_role_menu` VALUES (5, 1078);
INSERT INTO `sys_role_menu` VALUES (5, 1079);
INSERT INTO `sys_role_menu` VALUES (5, 1080);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '系统管理员', '01', 'ry@163.com', '15888888888', '1', '', 'bcb63d11931c944f605c7aa36cf22a6422642f84b41989c6c0da154fc0ef2489', '0', '0', '127.0.0.1', '2021-03-26 12:53:24', 'admin', '2021-03-26 12:53:24', 'admin', '2023-05-26 16:00:28', '管理员');
INSERT INTO `sys_user` VALUES (2, 103, 'secadmin', '安全管理员', '02', '', '', '2', '', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '0', '0', '', NULL, 'admin', '2023-05-26 14:46:54', 'admin', '2023-05-26 14:47:07', NULL);
INSERT INTO `sys_user` VALUES (3, 103, 'audadmin', '安全审计员', '03', '', '', '2', '', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '0', '0', '', NULL, 'admin', '2023-05-26 14:48:59', 'admin', '2023-05-26 15:21:47', NULL);
INSERT INTO `sys_user` VALUES (4, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '0', '0', '127.0.0.1', '2021-03-26 12:53:24', 'admin', '2021-03-26 12:53:24', 'secadmin', '2023-05-26 17:14:09', '测试员');
INSERT INTO `sys_user` VALUES (5, 103, 'ouyang', '欧阳', '00', '', '', '0', '', '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '0', '0', '', NULL, 'admin', '2023-05-26 14:42:05', 'admin', '2023-10-19 15:20:48', NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);
INSERT INTO `sys_user_post` VALUES (3, 4);

-- ----------------------------
-- Table structure for sys_user_pwd_modify
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_pwd_modify`;
CREATE TABLE `sys_user_pwd_modify`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `new_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '密码修改记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_pwd_modify
-- ----------------------------
INSERT INTO `sys_user_pwd_modify` VALUES (41, 5, '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '2023-10-19 15:20:48', 'admin', '2023-10-19 15:20:48', NULL, NULL);
INSERT INTO `sys_user_pwd_modify` VALUES (42, 1, '667c756cf9334e328a56e44e906245c8e214c655a160f18fdb84d79c209c49cf', '2023-07-01 15:21:34', 'admin', '2023-10-19 15:21:34', NULL, NULL);
INSERT INTO `sys_user_pwd_modify` VALUES (43, 1, 'bcb63d11931c944f605c7aa36cf22a6422642f84b41989c6c0da154fc0ef2489', '2023-10-19 15:38:33', 'admin', '2023-10-19 15:38:33', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (4, 4);
INSERT INTO `sys_user_role` VALUES (5, 4);

-- ----------------------------
-- View structure for v_folder_or_file
-- ----------------------------
DROP VIEW IF EXISTS `v_folder_or_file`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_folder_or_file` AS select `biz_file_folder`.`folder_id` AS `id`,`biz_file_folder`.`folder_parent_id` AS `parent_id`,`biz_file_folder`.`folder_name` AS `name`,`biz_file_folder`.`create_by` AS `create_by`,`biz_file_folder`.`create_time` AS `create_time`,NULL AS `file_url`,0 AS `size`,(select count(0) from `biz_file_folder` `t` where ((`t`.`folder_parent_id` = `biz_file_folder`.`folder_id`) and (`t`.`del_flag` = 1))) AS `folder_num`,(select count(0) from `biz_attach` where ((`biz_attach`.`folder_id` = `biz_file_folder`.`folder_id`) and (`biz_attach`.`del_flag` = '1'))) AS `file_num`,'文件夹' AS `ext_name`,0 AS `type`,NULL AS `secret` from `biz_file_folder` where (`biz_file_folder`.`del_flag` = 1) union all select `biz_attach`.`attach_id` AS `id`,`biz_attach`.`folder_id` AS `parent_id`,`biz_attach`.`old_name` AS `name`,`biz_attach`.`create_by` AS `create_by`,`biz_attach`.`create_time` AS `create_time`,`biz_attach`.`path` AS `file_url`,`biz_attach`.`file_size` AS `size`,0 AS `folder_num`,0 AS `file_num`,`biz_attach`.`ext_name` AS `ext_name`,1 AS `type`,`biz_attach`.`secret` AS `secret` from `biz_attach` where (`biz_attach`.`del_flag` = 1);

SET FOREIGN_KEY_CHECKS = 1;
