alter table "KMJJW"."leave_balances" add column("change_flag" INT default (0));

comment on column "KMJJW"."leave_balances"."change_flag" is '确认时是否有修改 1-是 0-否';