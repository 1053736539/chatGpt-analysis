CREATE TABLE "KMJJW"."biz_adopt_dept"
(
"id" INT IDENTITY(1, 1) NOT NULL,
"name" VARCHAR(500),
"type" INT,
"order_no" INT,
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_adopt_dept" IS '报送单位信息表';
COMMENT ON COLUMN KMJJW."biz_adopt_dept"."name" IS '单位名称';
COMMENT ON COLUMN KMJJW."biz_adopt_dept"."order_no" IS '排序号';
COMMENT ON COLUMN KMJJW."biz_adopt_dept"."type" IS '单位类型 1-各县(区)市纪委监委 2-市纪委监委机关各部门及市委巡查机构 3-市纪委监委各派驻(出)机构';


CREATE UNIQUE  INDEX "INDEX6687991374100" ON "KMJJW"."biz_adopt_dept"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;

CREATE TABLE "KMJJW"."biz_adopt_record"
(
"id" VARCHAR(64) NOT NULL,
"issue_no" VARCHAR(255),
"title" VARCHAR(500),
"level" VARCHAR(500),
"carrier" VARCHAR(500),
"adopt_date" TIMESTAMP(0),
"create_by" VARCHAR(64) DEFAULT '',
"create_time" TIMESTAMP(0),
"update_by" VARCHAR(64) DEFAULT '',
"update_time" TIMESTAMP(0),
"remark" VARCHAR(500) DEFAULT '',
"destination" VARCHAR(5000),
"ATTACH" VARCHAR(100),
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_adopt_record" IS '信息采用记录表';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."adopt_date" IS '采用时间';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."ATTACH" IS '附件';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."carrier" IS '采用载体';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."destination" IS '内容';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."issue_no" IS '期号';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."level" IS '采用级别';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."title" IS '标题';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_adopt_record"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX6687714376700" ON "KMJJW"."biz_adopt_record"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;

CREATE TABLE "KMJJW"."biz_adopt_record_score"
(
"id" VARCHAR(64) NOT NULL,
"record_id" VARCHAR(64),
"dept" VARCHAR(500),
"score" INT,
"main_flag" INT,
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_adopt_record_score" IS '信息采用记录的相关单位及分值记录';
COMMENT ON COLUMN KMJJW."biz_adopt_record_score"."dept" IS '单位名称';
COMMENT ON COLUMN KMJJW."biz_adopt_record_score"."main_flag" IS '是否主报单位 1-是 0-否';
COMMENT ON COLUMN KMJJW."biz_adopt_record_score"."record_id" IS '记录id';
COMMENT ON COLUMN KMJJW."biz_adopt_record_score"."score" IS '分值';


CREATE UNIQUE  INDEX "INDEX6687418841500" ON "KMJJW"."biz_adopt_record_score"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;

CREATE TABLE "KMJJW"."biz_adopt_score_rule"
(
"id" INT IDENTITY(1, 1) NOT NULL,
"level" VARCHAR(500),
"carrier" VARCHAR(500),
"main_score" INT,
"other_score" INT,
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_adopt_score_rule" IS '得分规则表';
COMMENT ON COLUMN KMJJW."biz_adopt_score_rule"."carrier" IS '采用载体';
COMMENT ON COLUMN KMJJW."biz_adopt_score_rule"."level" IS '采用级别';
COMMENT ON COLUMN KMJJW."biz_adopt_score_rule"."main_score" IS '主报单位分值';
COMMENT ON COLUMN KMJJW."biz_adopt_score_rule"."other_score" IS '其它单位分值';

CREATE UNIQUE  INDEX "INDEX6687051032600" ON "KMJJW"."biz_adopt_score_rule"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;
