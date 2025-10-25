alter table KMJJW."biz_probity_modify_approval" add column(apply_user VARCHAR(200));
comment on column KMJJW."biz_probity_modify_approval".apply_user is '申请用户';
alter table KMJJW."biz_probity_modify_approval" add column(year INT);
comment on column KMJJW."biz_probity_modify_approval".year is '年度';

CREATE TABLE "KMJJW"."biz_probity_modify_record"
(
    "id" VARCHAR(50) NOT NULL,
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" DATETIME(0) DEFAULT NULL,
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" DATETIME(0) DEFAULT NULL,
    "remark" VARCHAR(255) DEFAULT NULL,
    "probity_id" VARCHAR(50) DEFAULT NULL,
    "record_time" DATETIME(0) DEFAULT NULL,
    "modify_record" VARCHAR(500) DEFAULT NULL,
    "user_name" VARCHAR(200) DEFAULT NULL,
    "year" INT DEFAULT NULL,
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_modify_record" IS '修改廉政档案记录表';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."record_time" IS '记录时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."modify_record" IS '修改记录';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."user_name" IS '用户';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."year" IS '年度';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."update_time" IS '更新时间';

alter table KMJJW."biz_probity_modify_record" add column(modify_filed VARCHAR2(1000));
comment on column KMJJW."biz_probity_modify_record".modify_filed is '修改字段';

alter table KMJJW."biz_probity_house" add column(house_target_dest VARCHAR(200));
comment on column KMJJW."biz_probity_house".house_target_dest is '房产去向';