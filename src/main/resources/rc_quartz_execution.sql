/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : hue

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-09-21 16:55:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rc_quartz_execution`
-- ----------------------------
DROP TABLE IF EXISTS `rc_quartz_execution`;
CREATE TABLE `rc_quartz_execution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务id',
  `schedule_id` int(11) NOT NULL COMMENT '调度器id',
  `fire_time` timestamp NULL DEFAULT NULL COMMENT '触发时间',
  `pre_fire_time` timestamp NULL DEFAULT NULL COMMENT '前一次触发时间',
  `next_fire_time` timestamp NULL DEFAULT NULL COMMENT '下次触发时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `exec_result` tinyint(4) DEFAULT NULL COMMENT '0失败 1成功 2异常',
  `remark` varchar(512) DEFAULT NULL COMMENT '批注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_quartz_execution
-- ----------------------------

-- ----------------------------
-- Table structure for `rc_quartz_job`
-- ----------------------------
DROP TABLE IF EXISTS `rc_quartz_job`;
CREATE TABLE `rc_quartz_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `name` varchar(64) NOT NULL,
  `task_group` varchar(64) NOT NULL COMMENT '任务所属的module',
  `task_name` varchar(11) NOT NULL COMMENT '操作任务的id或者name，在数据库中的唯一确定某条任务',
  `job_object` varchar(64) NOT NULL COMMENT '每个模块，操作的任务的逻辑类',
  `job_method` varchar(64) DEFAULT NULL COMMENT '逻辑类中操作的方法',
  `is_concurrent` tinyint(4) DEFAULT NULL COMMENT '是否可以并行，并行，同一时间一个任务运行多次',
  `user_id` int(11) NOT NULL COMMENT '所属的用户id',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态0 无效  1有效',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '任务创建时间',
  `extends_json` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_quartz_job
-- ----------------------------

-- ----------------------------
-- Table structure for `rc_quartz_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `rc_quartz_schedule`;
CREATE TABLE `rc_quartz_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '对应的任务id',
  `create_time` timestamp NOT NULL DEFAULT '1991-01-26 00:00:00',
  `state` tinyint(4) DEFAULT NULL COMMENT '0未开始 1执行中  2暂停',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始执行的时间',
  `is_repeat` tinyint(4) DEFAULT NULL COMMENT '是否重复执行 0不重复 1 重复',
  `repeat_times` int(11) DEFAULT NULL COMMENT '重复的次数',
  `repeat_seconds` int(11) DEFAULT NULL COMMENT '重复的秒数',
  `end_time` timestamp NULL DEFAULT NULL COMMENT ' 结束时间 可以定义trigger结束的时间',
  `next_time` timestamp NULL DEFAULT NULL COMMENT '下次执行时间',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态0无效 1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_quartz_schedule
-- ----------------------------
