create table csi_vcard (
  `name` VARCHAR(64) NOT NULL comment '名称',
  `mobile` VARCHAR(64) NOT NULL comment '手机号',
  `city` VARCHAR(64) DEFAULT NULL COMMENT '城市',
  `company` VARCHAR(128) DEFAULT NULL COMMENT '所在公司',
  `pos` VARCHAR(128) DEFAULT NULL COMMENT '职位',
  `graduation` VARCHAR(32) DEFAULT NULL COMMENT '毕业时间',
  `createtime` bigint(20) not null comment '创建时间',
  PRIMARY KEY (name),
  KEY idx_name (`mobile`),
  KEY idx_city (`city`),
  KEY idx_company (`company`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment 'csi实验室名单';
