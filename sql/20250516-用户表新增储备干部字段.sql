--储备干部
ALTER TABLE KMJJW."sys_user" ADD "RESERVE_USER" VARCHAR(100) NULL;
--注释
COMMENT ON COLUMN KMJJW."sys_user"."RESERVE_USER" IS '储备干部 （1-处级  2- 副处级  3 -科级）';