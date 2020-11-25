DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `buyer_id` varchar(40)  NOT NULL DEFAULT '' COMMENT '用户ID',
    `name`     varchar(80)  NOT NULL DEFAULT '' COMMENT '收货人',
    `city`     varchar(255) NOT NULL DEFAULT '' COMMENT '城市',
    `addr`     varchar(400) NOT NULL DEFAULT '' COMMENT '收货地址',
    `phone`    varchar(60)  NOT NULL DEFAULT '' COMMENT '手机号或是固定电话号',
    `is_def`   int(1)       NOT NULL DEFAULT 1 COMMENT '是否默认 0否 1是',
    PRIMARY KEY (`id`),
    KEY `buyer_id` (`buyer_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='收货地址';

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(40) NOT NULL DEFAULT '' COMMENT '名称',
    `description` varchar(80) NOT NULL DEFAULT '' COMMENT '描述',
    `img_url`     varchar(80) NOT NULL DEFAULT '' COMMENT '图片Url',
    `web_site`    varchar(80) NOT NULL DEFAULT '' COMMENT '品牌网址',
    `sort`        int(11)     NOT NULL DEFAULT 0 COMMENT '排序:最大最排前',
    `is_display`  tinyint(1)  NOT NULL DEFAULT '1' COMMENT '是否可见 1:可见 0:不可见',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='品牌';

DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`      varchar(18)  NOT NULL DEFAULT '' COMMENT '用户名',
    `password`      varchar(32)  NOT NULL DEFAULT '' COMMENT '密码',
    `gender`        int(2)       NOT NULL DEFAULT 1 COMMENT '性别(1:男 2:女)',
    `email`         varchar(50)  NOT NULL DEFAULT '' COMMENT '邮箱',
    `real_name`     varchar(8)   NOT NULL DEFAULT '' COMMENT '真实名字',
    `register_time` datetime     NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '注册时间',
    `province`      varchar(11)  NOT NULL DEFAULT '' COMMENT '省ID',
    `city`          varchar(11)  NOT NULL DEFAULT '' COMMENT '市ID',
    `town`          varchar(11)  NOT NULL DEFAULT '' COMMENT '县ID',
    `addr`          varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
    `is_del`        tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否已删除:1:未,0:删除了',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='购买者';

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`
(
    `id`           bigint         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id`     bigint         NOT NULL DEFAULT 0 COMMENT '订单ID',
    `product_no`   varchar(255)   NOT NULL DEFAULT '' COMMENT '商品编号',
    `product_name` varchar(255)   NOT NULL DEFAULT '' COMMENT '商品名称',
    `color`        varchar(11)    NOT NULL DEFAULT '' COMMENT ' 颜色名称',
    `size`         varchar(11)    NOT NULL DEFAULT '' COMMENT '尺码',
    `sku_price`    decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品销售价',
    `amount`       int(11)        NOT NULL DEFAULT 0 COMMENT '购买数量',
    PRIMARY KEY (`id`),
    KEY `fk_order_id` (`order_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='订单详情';

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `id`           bigint         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `oid`          varchar(36)    NOT NULL DEFAULT '' COMMENT '订单号',
    `deliver_fee`  decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
    `payable_fee`  decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '应付金额',
    `total_price`  decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单金额',
    `payment_way`  tinyint(1)     NOT NULL DEFAULT 0 COMMENT '支付方式 0:到付 1:在线 2:邮局 3:公司转帐',
    `payment_cash` tinyint(1)     NOT NULL DEFAULT 1 COMMENT '货到付款方式.1现金,2POS刷卡',
    `delivery`     datetime       NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '送货时间',
    `isConfirm`    tinyint(1)     NOT NULL DEFAULT 0 COMMENT '是否电话确认 1:是  0: 否',
    `is_paiy`      tinyint(1)     NOT NULL DEFAULT 0 COMMENT '支付状态 :0到付1,待付款,2已付款,3待退款,4退款成功,5退款失败',
    `state`        tinyint(1)     NOT NULL DEFAULT 0 COMMENT '订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货',
    `create_date`  datetime       NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '订单生成时间',
    `note`         varchar(100)   NOT NULL DEFAULT '' COMMENT '附言',
    `buyer_id`     bigint         NOT NULL DEFAULT 0 COMMENT '用户Id',
    PRIMARY KEY (`id`),
    KEY `buyer_id` (`buyer_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='订单';

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`             bigint         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `no`             varchar(30)    NOT NULL DEFAULT '' COMMENT '商品编号',
    `name`           varchar(255)   NOT NULL DEFAULT '' COMMENT '商品名称',
    `weight`         decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '重量 单位:克',
    `is_new`         tinyint(1)     NOT NULL DEFAULT 1 COMMENT '是否新品:0:旧品,1:新品',
    `is_hot`         tinyint(1)     NOT NULL DEFAULT 0 COMMENT '是否热销:0,否 1:是',
    `is_commend`     tinyint(1)     NOT NULL DEFAULT 1 COMMENT '推荐 1推荐 0 不推荐',
    `create_time`    datetime       NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '添加时间',
    `create_user_id` varchar(255)   NOT NULL DEFAULT '' COMMENT '添加人ID',
    `check_time`     datetime       NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '审核时间',
    `check_user_id`  varchar(255)   NOT NULL DEFAULT '' COMMENT '审核人ID',
    `is_show`        tinyint(1)     NOT NULL DEFAULT 1 COMMENT '上下架:0否 1是',
    `is_del`         tinyint(1)     NOT NULL DEFAULT 1 COMMENT '是否删除:0删除,1,没删除',
    `type_id`        int(11)        NOT NULL DEFAULT 0 COMMENT '类型ID',
    `brand_id`       int(11)        NOT NULL DEFAULT 0 COMMENT '品牌ID',
    `keywords`       varchar(255)   NOT NULL DEFAULT '' COMMENT '检索关键词',
    `sales`          int(11)        NOT NULL DEFAULT 0 COMMENT '销量',
    `description`    longtext COMMENT '商品描述',
    `package_list`   longtext COMMENT '包装清单',
    `feature`        varchar(255)   NOT NULL DEFAULT '' COMMENT '商品属性集',
    `color`          varchar(255)   NOT NULL DEFAULT '' COMMENT '颜色集',
    `size`           varchar(255)   NOT NULL DEFAULT '' COMMENT '尺寸集',
    PRIMARY KEY (`id`),
    KEY `type_id` (`type_id`),
    KEY `brand_id` (`brand_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='商品';