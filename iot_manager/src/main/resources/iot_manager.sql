create database iot;

use iot;

create table iot_system_user (
  id int not null AUTO_INCREMENT,
  account varchar (64) unique ,
  password varchar (128),
  type int default 0,
  name varchar (64),
  mobile varchar (64),
  email varchar (64),
--   lastTime datetime,
--   lastIp varchar (32),
  status int default 0,
  addTime datetime,
  constraint pkiot_system_user primary key (id)
);

insert into iot_system_user values (1, 'admin', 'admin', 0, null, null, null,0, null );

create table iot_system_user_log (
 id int not null auto_increment primary key,
 account varchar (64),-- 账号
 operationType varchar (64),-- 操作类型
 operationIp varchar (32),-- 作类型
 operationTime datetime,-- 操作时间
 instruction varchar (128) -- 说明
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--代理商表
create table iot_agents(
  id int not null auto_increment primary key,
  agentAccount varchar (64) unique , -- 登陆账户
  password varchar (64),-- 密码
  name varchar (64),-- 名字
  mobile varchar(64),-- 电话
  email varchar (64),-- 邮箱
  address varchar (256),-- 地址
  serviceHotLine varchar (64),-- 服务热线
  preSaleHotLine varchar (64),-- 售前热线
  billingMode int default 0,-- 计费模式
  rebateMode int default 0,-- 返佣模式
  rebateValue double(8,2), -- 返佣比例
  balance decimal (16,4), -- 余额
  addTime dateTime, -- 添加事件
  status int default 0,-- 状态 0启用,1停用--
  companyName varchar (128),-- 公司名称
  bankName varchar (128), -- 开户银行
  bankUserName varchar (128), -- 开户名称
  bankAccount varchar (128), -- 银行账户
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--客户表
create table iot_client(
  id int not null auto_increment primary key,
  clientAccount varchar (64) unique, -- 登陆账户
  agentAccount varchar (64), -- 所属代理商
  password varchar (64),-- 密码
  balance decimal (16,4),-- 余额
  addTime dateTime,-- 添加时间
  status int default 0,-- 状态
  foreign key(clientAccount) references iot_agents(agentAccount)
)ENGINE=InnoDB default CHARSET=utf8;

-- 通道表
create table iot_channel(
  id int not null auto_increment primary key,-- 主键ID
  channelName varchar (64) unique, -- 通道名称
  channelDiscount double (8,2), -- 通道折扣
  showName varchar (64), -- 展示名称
  channelAgentDiscount double (8,2), --  代理基础折扣（如果代理折扣没有配置 就用这个）
  operator int default 0, -- 运行商类型（0全网，1移动，2联通，3电信）
  submissionInterval int default 0, -- 提交时间间隔
  taskScanInterval int default 0, -- 任务扫描间隔
  settlementDate int default 1, -- 结算日 默认1号
  note varchar (256), -- 备注
  status int default 0, --  通道状态 默认0 正常， 1 不正常
  rechargeMode int default 0, -- 充值模式 0 自动， 1 人工审核
  sms int default 0 -- 是否启动短信 默认不启动 暂时没用
)ENGINE=InnoDB default CHARSET=utf8;

-- 通道资费计划表
create table iot_channel_tariff(
  id int not null auto_increment primary key, -- 主键ID
  packageId varchar (32), --  套餐编码
  channelName varchar (64), -- 归属通道
  operator int default 0, -- 运营商
  originalName varchar (64), -- 原始名称
  showName varchar (64), -- 显示名称
  originalPrice double (8,2), -- 原始售价
  tariffPrice double (8,2), -- 售价
  tariffCost double (8,2), -- 成本
  trafficType int default 0, -- 流量类型（1 普通套餐， 2 包月套餐）
  packageSize int default 0, -- 包含多少的流量
  feeMode int default 0, -- 计费模式（1 整月， 2 剩余天数）
  superpositionMode int default 0, -- 叠加模式 （1 不可叠加，2 可叠加）
  remark varchar (256), --  备注
  status int default 0, -- 状态 （0 启动， 1 停用）
  foreign key(channelName) references iot_channel(channelName)
)ENGINE=InnoDB default CHARSET=utf8;

-- 通道加油包
create table iot_channel_addPackage(
  id int not null auto_increment primary key, --  主键ID
  packageId varchar (32), --  套餐编码
  channelName varchar (64), -- 归属通道
  operator int default 0, -- 运营商
  originalName varchar (64), -- 原始名称
  showName varchar (64), -- 显示名称
  originalPrice double (8,2), -- 原始售价
  packagePrice double (8,2), -- 售价
  packageCost double (8,2), -- 成本
  packageSize int default 0, -- 包含多少的流量
  feeMode int default 0, -- 计费模式（0 整月， 1 剩余天数）
  remark varchar (256), --  备注
  status int default 0 -- 状态 （0 启动， 1 停用）
)ENGINE=InnoDB default CHARSET=utf8;

-- 代理商资费计划
create table iot_agent_tariff(
  id int not null auto_increment primary key, --  主键
  agentName  varchar (64), -- 关联到代理商资费计划表的名称
  channelName varchar (32), -- 关联到供应商资费计划表的名称
  packageId varchar(32), -- 关联到供应商资费计划的套餐编码
  showName varchar (32), -- 关联到供应商资费计划的展示名称
  packageSize int default 0, -- 包含流量大小 关联到供应商资费计划的流量包大小
  operator int default 0, -- 运营商
  feeMode int default 0, -- 计费模式（1 整月， 2 剩余天数）  关联到供应商资费计划的计费模式
  superpositionMode int default 0, -- 叠加模式 （1 不可叠加，2 可叠加）关联到供应商资费计划的叠加模式
  rebateMode int default 0,-- 返佣模式 关联到代理商的返佣模式
  totalStatus varchar (32), -- 总状态
  status int default 0, -- 状态
  originalPrice double (8,2), -- 原始售价 供应商资费计划的原始售价
  tariffPrice double (8,2), -- 售价 关联到供应商资费计划的售价
  tariffCost double (8,2)-- 成本
)ENGINE=InnoDB default CHARSET=utf8;

-- 代理商加油包
create table iot_agent_addPackage(
  id int not null auto_increment primary key, --  主键
  agentName  varchar (64), -- 关联到代理商资费计划表的名称
  channelName varchar (32), -- 关联到供应商资费计划表的名称
  packageId varchar(32), -- 关联到供应商资费计划的套餐编码
  showName varchar (32), -- 关联到供应商资费计划的展示名称
  packageSize int default 0, -- 包含流量大小 关联到供应商资费计划的流量包大小
  operator int default 0, -- 运营商
  feeMode int default 0, -- 计费模式（1 整月， 2 剩余天数）  关联到供应商资费计划的计费模式
  rebateMode int default 0,-- 返佣模式 关联到代理商的返佣模式
  totalStatus varchar (32), -- 总状态
  status int default 0, -- 状态
  originalPrice double (8,2), -- 原始售价 供应商资费计划的原始售价
  packagePrice double (8,2), -- 售价 关联到供应商资费计划的售价
  packageCost double (8,2)-- 成本
)ENGINE=InnoDB default CHARSET=utf8;

-- 物联网卡表
create table iot_card(
id int not null auto_increment primary  key, -- 主键
cardId varchar (32) unique , -- 物联网卡号
status int default 0, -- 0 未分配， 1 已分配
networkStatus int default 0, -- 0正常， 1 不正常
useSize int default 0, -- 使用量
totalSize int default 0, -- 总量
agentName varchar (32), -- 代理商
channelName varchar (64), -- 供应商
cardType int default 0, -- 物联卡类型， 0 普通，1 未知
cardDetail varchar (256), -- 套餐详情
settlementDate int default 1, -- 结算日 默认1号
addTime dateTime,-- 导入时间
renew int default 0, -- 0未续费， 1 续费过了
renewDetail varchar (128), -- 续费情况
trafficPool int default 0, -- 流量池， 0 不是流量池，1 流量池
operator int default 0 --运营商
)ENGINE=InnoDB default CHARSET=utf8;