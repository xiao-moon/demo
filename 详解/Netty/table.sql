####朋友圈表结构设计####
#消息表
#还需要添加可见标志
CREATE TABLE `t_friend_circle_message` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(15) DEFAULT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '图片',
  `location` varbinary(100) DEFAULT '' COMMENT '位置',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
)

#时间轴表
#还需要添加删除标志
CREATE TABLE `t_friend_circle_timeline` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `uid` bigint(15) DEFAULT NULL COMMENT '用户id',
  `fcmid` bigint(15) DEFAULT NULL COMMENT '朋友圈信息id',
  `is_own` int(1) DEFAULT '0' COMMENT '是否是自己的',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
)


#评论表
#需要改造,不完整
CREATE TABLE `t_friend_circle_comment` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `fcmid` bigint(15) DEFAULT NULL COMMENT '朋友圈信息id',
  `uid` bigint(15) DEFAULT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `like_count` int(10) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
)













