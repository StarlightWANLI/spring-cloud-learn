DROP TABLE IF EXISTS t_order_test;

CREATE TABLE  `jpa`.`t_order_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `product_code` varchar(100) DEFAULT NULL COMMENT '产品编号',
  `price` int(10) DEFAULT NULL COMMENT '价格',
  `num` int(10) DEFAULT NULL COMMENT '数量',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `user_id` int(10) DEFAULT NULL COMMENT '用户idwwwwwwwww',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



