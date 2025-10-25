1.增加配置项sys.user.selfInfoModify-是否开启普通用户干免表修改权限
insert into "KMJJW"."sys_config" ("config_id","config_name","config_key","config_value","config_type","create_by","create_time","update_by","update_time","remark") values (5, '是否开启普通用户干免表修改权限', 'sys.user.selfInfoModify', 'false', 'N', 'admin', '2025-03-11 14:16:57', '', null, '勿删，用于控制用户干免表的修改权限是否开启');

2.创建表sys_user_info_change_log-干部任免表信息修改日志
create table "KMJJW"."sys_user_info_change_log"
(
	"user_id" BIGINT not null ,
	"init_data" TEXT,
	"last_data" TEXT,
	"create_by" VARCHAR(64),
	"create_time" TIMESTAMP(6),
	"update_by" VARCHAR(64),
	"update_time" TIMESTAMP(6),
	primary key("user_id")
)
;
comment on table "KMJJW"."sys_user_info_change_log" is '干部任免表信息修改日志';
comment on column "KMJJW"."sys_user_info_change_log"."user_id" is 'user_id';
comment on column "KMJJW"."sys_user_info_change_log"."init_data" is '最初的信息,json对象字符串';
comment on column "KMJJW"."sys_user_info_change_log"."last_data" is '最后的信息,json对象字符串';
comment on column "KMJJW"."sys_user_info_change_log"."create_by" is '创建者';
comment on column "KMJJW"."sys_user_info_change_log"."create_time" is '创建时间';
comment on column "KMJJW"."sys_user_info_change_log"."update_by" is '更新者';
comment on column "KMJJW"."sys_user_info_change_log"."update_time" is '更新时间';