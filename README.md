# NovelServer

### 创建表
```create schema novel collate utf8mb4_0900_ai_ci;

create table cloud_music
(
	id int auto_increment comment '自增主键'
		primary key,
	person_id mediumtext not null comment '人员id',
	music_id mediumtext not null comment '音乐对应唯一Id',
	name varchar(50) not null comment '音乐名称',
	path varchar(255) not null comment '音乐存放路径',
	type varchar(10) not null comment '音乐类型',
	duration int null comment '音乐时长'
)
comment '云音乐库';

create table music_library
(
	id int auto_increment comment '自增主键'
		primary key,
	music_id mediumtext not null comment '音乐对应唯一Id',
	name varchar(50) null comment '音乐名称',
	path varchar(255) null comment '存放路径',
	type varchar(10) null comment '音乐类型',
	duration int null comment '音乐时长'
)
comment '音乐库表';

create table online_status
(
	id int auto_increment comment '自增主键'
		primary key,
	person_id mediumtext not null comment '人员id',
	session_id varchar(50) null comment '会话id',
	login_time mediumtext null comment '登录时间',
	alive_time mediumtext null comment '存活时间',
	status tinyint(1) null comment '在线状态'
)
comment '人员在线状态表';

create table person
(
	id int auto_increment comment '人员表自增id'
		primary key,
	name varchar(20) not null comment '用户名',
	person_id mediumtext not null comment '人员对应唯一Id',
	password varchar(30) not null comment '密码',
	number varchar(20) null comment '手机号',
	sex varchar(5) null comment '性别',
	age int null comment '年龄',
	register_time mediumtext null comment '注册时间'
)
comment '人员表信息';

```