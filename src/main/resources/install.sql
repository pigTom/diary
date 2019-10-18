
# 权限表 system_privilege
drop table system_role;
drop table system_privilege;
drop table system_role_privilege;
drop table system_user;



create table if not exists system_privilege (
  id   bigint primary key auto_increment comment '权限主键',
  name varchar(20) not null comment '权限名称',
  description varchar(50) not null comment '权限描述',
  create_time datetime not null default now() comment '创建时间',
  create_id bigint not null comment '创建人ID'
)ENGINE=InnoDB default charset=utf8 comment '系统角色权限表';

# 建角色表 system_role与system_privilege是一对多的关系
# 所以要有一个关系表 system_role_privilege
# system_role 与
create table if not exists system_role (
  id bigint primary key auto_increment comment '主键',
  name varchar(20) not null comment '角色名称',
  description varchar(50) not null comment '角色描述',
  create_time datetime not null default now(),
  update_time datetime not null default now(),
  create_id bigint not null comment '创建人ID'
)ENGINE=InnoDB default charset=utf8 comment '系统角色表';;

# system_role_privilege
create table if not exists system_role_privilege (
  id bigint primary key auto_increment,
  role_id bigint not null comment 'system_role表主键ID',
  privilege_id bigint not null comment 'system_privilege表主键ID',
  create_time datetime not null default now(),
  create_id bigint not null comment '创建人ID'
)ENGINE=InnoDB default charset=utf8 comment '系统角色权限关联表';;


# 建用户表 system_user
create table if not exists system_user (
  id bigint primary key comment '主键' auto_increment,
  name varchar(20) not null comment '用户名称',
  authentication_string text comment '用户密码',
  password_locked bit(1) not null null default (0) comment '密码是否被锁',
  account_locked bit(1) not null null default (0) comment '账号是否被锁',
  description varchar(50) comment '用户描述',
  password_expired bit(1) not null null default (0) comment '密码是否过期',
  password_lifetime smallint(5) unsigned comment '密码生存周期, 单位是天，最长31天',
  create_time datetime not null  default now() comment '创建人ID',
  create_id bigint not null comment '创建者ID',
  update_time datetime not null  default now() comment '最后更新时间',
  update_id bigint not null comment '最后更新人ID'
)ENGINE=InnoDB default charset=utf8 comment '系统用户表';

insert into system_privilege (name, description, create_id) values ('createUser', '添加用户', 0);
insert into system_privilege (name, description, create_id) values ('updateUser', '修改用户', 0);
insert into system_privilege (name, description, create_id) values ('deleteUser', '删除用户', 0);
insert into system_privilege (name, description, create_id) values ('queryUser', '查看用户', 0);


insert into system_role (name, description, create_id) values ('superAdmin', '超级管理员', 0);
insert into system_role (name, description, create_id) values ('admin', '管理员', 0);
insert into system_role (name, description, create_id) values ('normal', '普通用户', 0);

insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (1, 1, 0);
insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (1, 2, 0);
insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (1, 3, 0);
insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (1, 4, 0);

insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (2, 1, 0);
insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (2, 2, 0);
insert into system_role_privilege (role_id, privilege_id, create_id) VALUES (2, 4, 0);

insert into system_user (name, authentication_string, description, create_id, update_id) VALUES
  ('root', md5('123456'), '根用户，具有超级管理员权限',0, 0);
