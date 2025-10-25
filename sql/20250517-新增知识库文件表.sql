CREATE TABLE "KMJJW"."knowledge_base"
(
"id" INT IDENTITY(1, 1) NOT NULL,
"file_name" VARCHAR2(128) NULL,
"file_type" VARCHAR2(50) NULL,
"file_level" VARCHAR2(50) NULL,
"file_desc" VARCHAR2(1024) NULL,
"file_path" VARCHAR2(256) NULL,
"file_tags" VARCHAR2(256) NULL,
"file_size" INT NULL,
"file_suffix" VARCHAR2(8) NULL,
"file_content" LONGVARCHAR NULL,
"file_tokenizer" LONGVARCHAR NULL,
"del_flag" VARCHAR(1) NULL,
"remark" VARCHAR2(50) NULL,
"create_by" VARCHAR2(50) NULL,
"create_time" DATETIME(6) NULL,
"update_by" VARCHAR2(50) NULL,
"update_time" DATETIME(6) NULL,
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON TABLE "KMJJW"."knowledge_base" IS '知识库';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."id" IS 'id';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_name" IS '文件名';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_type" IS '文件类型';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_level" IS '文件级别';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_desc" IS '文件简述';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_path" IS '文件路径';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_tags" IS '文件标签';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_size" IS '文件大小';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_suffix" IS '文件后缀';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_content" IS '文件原文';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."file_tokenizer" IS '文件分词内容';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."del_flag" IS '是否删除';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."remark" IS '备注';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."create_by" IS '创建人';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."create_time" IS '创建时间';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."update_by" IS '更新人';
COMMENT ON COLUMN "KMJJW"."knowledge_base"."update_time" IS '更新时间';

