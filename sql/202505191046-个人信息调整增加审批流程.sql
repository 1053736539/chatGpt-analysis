1.增加表sys_user_info_change_apply（用户信息修改申请）
CREATE TABLE "KMJJW"."sys_user_info_change_apply"
(
"id" VARCHAR(50) NOT NULL,
"user_id" BIGINT NOT NULL,
"before_data" TEXT,
"after_data" TEXT,
"create_by" VARCHAR(64),
"create_time" TIMESTAMP(6),
"update_by" VARCHAR(64),
"update_time" TIMESTAMP(6),
"status" VARCHAR(10),
"approval_opinion" TEXT,
"approval_by" VARCHAR(64),
NOT CLUSTER PRIMARY KEY("id")
) ;

COMMENT ON TABLE "KMJJW"."sys_user_info_change_apply" IS '用户信息修改申请';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."after_data" IS '修改后的数据';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."approval_by" IS '审批人';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."approval_opinion" IS '审批意见';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."before_data" IS '之前的数据';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."create_by" IS '创建人';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."create_time" IS '创建时间';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."status" IS '审批状态 1-待审批 2-审批通过 3-审批驳回';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."update_by" IS '更新人';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."update_time" IS '更新时间';
COMMENT ON COLUMN "KMJJW"."sys_user_info_change_apply"."user_id" IS '用户id';


