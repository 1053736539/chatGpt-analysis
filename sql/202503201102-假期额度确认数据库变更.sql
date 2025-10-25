1.假期额度表（leave_balances）增加确认状态字段
alter table "KMJJW"."leave_balances" add column("status" INT);
alter table "KMJJW"."leave_balances" add column("leader_id" BIGINT);
alter table "KMJJW"."leave_balances" add column("approval_opinion" VARCHAR2(500));

comment on column "KMJJW"."leave_balances"."status" is '确认状态 1-待确认 2-审核中 3-已确认';
comment on column "KMJJW"."leave_balances"."leader_id" is '用户指定的审批人id';
comment on column "KMJJW"."leave_balances"."approval_opinion" is '审批意见';

2.创建假期额度变更日志表
CREATE TABLE "KMJJW"."leave_balances_change_log"
(
    "id" INT NOT NULL,
    "init_data" TEXT,
    "last_data" TEXT,
    "create_by" VARCHAR(64),
    "create_time" TIMESTAMP(6),
    "update_by" VARCHAR(64),
    "update_time" TIMESTAMP(6),
    NOT CLUSTER PRIMARY KEY("id")
)
;

COMMENT ON TABLE "KMJJW"."leave_balances_change_log" IS '假期额度变更日志';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."create_by" IS '创建者';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."id" IS 'leave_balances的id';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."init_data" IS '最初的信息,json对象字符串';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."last_data" IS '最后的信息,json对象字符串';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."update_by" IS '更新者';
COMMENT ON COLUMN "KMJJW"."leave_balances_change_log"."update_time" IS '更新时间';
