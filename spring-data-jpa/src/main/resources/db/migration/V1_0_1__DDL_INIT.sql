-- -----------------------------------------------------
-- Schema account
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jpa`
  DEFAULT CHARACTER SET utf8;
USE `jpa`;

-- -----------------------------------------------------
-- Table `account`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa`. `t_user` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别,0未知，1男，2女',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1冻结',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `login_pwd` varchar(128) NOT NULL COMMENT '登录密码',
  `pwd_salt` varchar(128) NOT NULL COMMENT '密码盐',
  `balance` bigint(19) NOT NULL DEFAULT '100000000' COMMENT '余额, 单位分',
  `address` varchar(255) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `jpa`.`t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `product_code` varchar(100) DEFAULT NULL COMMENT '产品编号',
  `price` int(10) DEFAULT NULL COMMENT '价格',
  `num` int(10) DEFAULT NULL COMMENT '数量',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

