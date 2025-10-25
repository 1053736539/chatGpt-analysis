CREATE TABLE "KMJJW"."biz_probity"
(
    "id" VARCHAR(50) NOT NULL,
    "year" INT,
    "user_id" BIGINT,
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "confirm_status" VARCHAR(10),
    "confirm_time" TIMESTAMP(0),
    "dept_name" VARCHAR(200),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity" IS '廉政档案记录';
COMMENT ON COLUMN KMJJW."biz_probity"."confirm_status" IS '状态 1-未确认 2-已确认 3-允许修改';
COMMENT ON COLUMN KMJJW."biz_probity"."confirm_time" IS '确认时间';
COMMENT ON COLUMN KMJJW."biz_probity"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity"."dept_name" IS '所属部门';
COMMENT ON COLUMN KMJJW."biz_probity"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity"."user_id" IS 'userId';
COMMENT ON COLUMN KMJJW."biz_probity"."year" IS '年度';


CREATE UNIQUE  INDEX "INDEX24859866028700" ON "KMJJW"."biz_probity"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_basic"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "name" VARCHAR(50),
    "gender" VARCHAR(10),
    "birth" VARCHAR(20),
    "nation" VARCHAR(50),
    "native_place" VARCHAR(255),
    "birth_place" VARCHAR(255),
    "political_status" VARCHAR(255),
    "party_join_time" VARCHAR(20),
    "start_work_time" VARCHAR(20),
    "full_time_edu_level" VARCHAR(255),
    "full_time_edu_school_major" VARCHAR(255),
    "on_job_edu_level" VARCHAR(255),
    "on_job_edu_school_major" VARCHAR(255),
    "work_dept" VARCHAR(255),
    "current_position" VARCHAR(255),
    "position_level" VARCHAR(255),
    "delegate_flag" VARCHAR(255),
    "id_no" VARCHAR(50),
    "home_addr" VARCHAR(255),
    "work_resume" TEXT,
    "reward_punishment" TEXT,
    "annual_assessment" TEXT,
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_basic" IS '廉政档案-1.报告人基本情况';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."annual_assessment" IS '年度考核结果';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."birth" IS '出生年月';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."birth_place" IS '出生地';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."current_position" IS '现任职务';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."delegate_flag" IS '是否党代表、人大代表、政协委员';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."full_time_edu_level" IS '全日制教育';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."full_time_edu_school_major" IS '全日制教育-毕业院校系及专业';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."gender" IS '性别';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."home_addr" IS '家庭住址';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."id_no" IS '身份证号码';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."name" IS '姓名';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."nation" IS '民族';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."native_place" IS '籍贯';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."on_job_edu_level" IS '在职教育';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."on_job_edu_school_major" IS '在职教育-毕业院校系及专业';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."party_join_time" IS '入党时间';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."political_status" IS '政治面貌';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."position_level" IS '职级';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."reward_punishment" IS '奖惩情况';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."start_work_time" IS '参加工作时间';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."work_dept" IS '工作单位';
COMMENT ON COLUMN KMJJW."biz_probity_basic"."work_resume" IS '工作简历';


CREATE UNIQUE  INDEX "INDEX24859653604200" ON "KMJJW"."biz_probity_basic"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_certificate"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "certificate_name_no" VARCHAR(500),
    "expire_date" VARCHAR(100),
    "depository_dept" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_certificate" IS '廉政档案-6.1本人持有护照、往来港澳台证件情况';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."certificate_name_no" IS '持有证件名称及编号';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."depository_dept" IS '保管机构';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."expire_date" IS '有效期';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_certificate"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24859570820400" ON "KMJJW"."biz_probity_certificate"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_child_spouse"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "name" VARCHAR(255),
    "spouse_name" VARCHAR(255),
    "spouse_contry_region" VARCHAR(255),
    "spouse_work_study_dept" VARCHAR(500),
    "spouse_position" VARCHAR(255),
    "spouse_marriage_time" VARCHAR(20),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_child_spouse" IS '廉政档案-7.本人子女与外国人、无国籍人及港澳、台湾居民通婚情况';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."name" IS '子女姓名';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."spouse_contry_region" IS '子女配偶基本情况-国家、地区';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."spouse_marriage_time" IS '子女配偶基本情况-结婚时间';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."spouse_name" IS '子女配偶基本情况-姓名';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."spouse_position" IS '子女配偶基本情况-职务';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."spouse_work_study_dept" IS '子女配偶基本情况-工作（学习）单位';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_child_spouse"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24859460610800" ON "KMJJW"."biz_probity_child_spouse"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_enterprise"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "name" VARCHAR(50),
    "uscc" VARCHAR(255),
    "enterprise_name" VARCHAR(255),
    "establishment_time" VARCHAR(20),
    "business_scope" TEXT,
    "registered_place" VARCHAR(500),
    "business_place" VARCHAR(500),
    "enterprise_type" VARCHAR(255),
    "registered_capital" DECIMAL(12,2),
    "enterprise_status" VARCHAR(255),
    "subscribed_capital" DECIMAL(12,2),
    "subscribed_percent" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_enterprise" IS '廉政档案-16.本人、配偶、子女及其配偶投资的企业情况或者担任高级职务的企业情况';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."business_place" IS '经营地';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."business_scope" IS '经营范围';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."enterprise_name" IS '企业或其他市场主体名称';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."enterprise_status" IS '企业状态';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."enterprise_type" IS '企业或其他市场主体类型';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."establishment_time" IS '成立日期';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."name" IS '姓名';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."registered_capital" IS '注册资本（金）或资金数额（出资额）（万元）';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."registered_place" IS '注册地';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."subscribed_capital" IS '个人认缴出资额或个人出资额';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."subscribed_percent" IS '个人认缴出资比例或个人出资比例';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_enterprise"."uscc" IS '统一社会信用代码/注册号';


CREATE UNIQUE  INDEX "INDEX24859311854400" ON "KMJJW"."biz_probity_enterprise"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_family_member"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "relation" VARCHAR(50),
    "name" VARCHAR(255),
    "birth" VARCHAR(20),
    "political_status" VARCHAR(50),
    "work_dept_position" VARCHAR(255),
    "id_no" VARCHAR(50),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_family_member" IS '廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."birth" IS '出生年月';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."id_no" IS '身份证号码';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."name" IS '姓名';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."political_status" IS '政治面貌';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."relation" IS '关系';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_family_member"."work_dept_position" IS '工作单位及职务';


CREATE UNIQUE  INDEX "INDEX24859208331100" ON "KMJJW"."biz_probity_family_member"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_foreign_deposit"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "depositor" VARCHAR(255),
    "country_region" VARCHAR(255),
    "bank" VARCHAR(255),
    "currency" VARCHAR(50),
    "amount" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_foreign_deposit" IS '廉政档案-17.本人、配偶、共同生活的子女在国（境）外及港澳台地区的存款情况';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."amount" IS '金额（万）';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."bank" IS '开户银行或金融机构全称';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."country_region" IS '存款的国家（地区）及城市';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."currency" IS '币种';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."depositor" IS '存款人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_deposit"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24859113810700" ON "KMJJW"."biz_probity_foreign_deposit"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_foreign_investment"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "investor" VARCHAR(50),
    "country_region" VARCHAR(255),
    "investment_situation" TEXT,
    "currency" VARCHAR(50),
    "amount" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_foreign_investment" IS '廉政档案-18.本人、配偶、子女及其配偶在国（境）外及港澳台地区的投资情况';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."amount" IS '投资金额（万）';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."country_region" IS '投资的国家（地区）及城市';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."currency" IS '币种';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."investment_situation" IS '投资情况';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."investor" IS '投资人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_foreign_investment"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24859007595300" ON "KMJJW"."biz_probity_foreign_investment"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_fund"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "holder" VARCHAR(255),
    "fund_name_code" VARCHAR(255),
    "purchase_amount" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_fund" IS '廉政档案-14.本人、配偶、子女投资或者以其他方式持有基金、理财产品的情况';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."fund_name_code" IS '基金、理财产品代码';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."holder" IS '持有人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."purchase_amount" IS '基金或理财产品购买金额';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_fund"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858923732300" ON "KMJJW"."biz_probity_fund"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_gift"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "rejection_submit_amount" VARCHAR(255),
    "gift_name" VARCHAR(255),
    "gift_num" VARCHAR(50),
    "gift_equivalent_value" VARCHAR(255),
    "gift_time" VARCHAR(20),
    "submit_dept" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_gift" IS '廉政档案-3.本人拒收或上交礼金、礼品情况登记表';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."gift_equivalent_value" IS '拒收或上交礼品情况-折合价值';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."gift_name" IS '拒收或上交礼品情况-名称';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."gift_num" IS '拒收或上交礼品情况-数量';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."gift_time" IS '时间';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."rejection_submit_amount" IS '拒收或上交礼金、有价证券或支付凭证金额';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."submit_dept" IS '上交部门';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_gift"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858820592900" ON "KMJJW"."biz_probity_gift"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_go_abroad"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "round_trip_time" VARCHAR(255),
    "country_region" VARCHAR(255),
    "content" VARCHAR(500),
    "approval_dept" VARCHAR(255),
    "certificate_no" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_go_abroad" IS '廉政档案-6.2本人因私出国（境）及往来港澳台情况';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."approval_dept" IS '审批机构';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."certificate_no" IS '所用护照号或港澳台通行证号';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."content" IS '事由';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."country_region" IS '所到国家、地区';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."round_trip_time" IS '往返时间';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_go_abroad"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858721828600" ON "KMJJW"."biz_probity_go_abroad"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_house"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "owner" VARCHAR(50),
    "house_source_dest" VARCHAR(255),
    "addr" VARCHAR(255),
    "build_area" VARCHAR(50),
    "house_nature" VARCHAR(255),
    "trade_date" VARCHAR(20),
    "trade_price" VARCHAR(50),
    "house_target_dest" VARCHAR(200),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_house" IS '廉政档案-12.本人、配偶、共同生活的子女为所有权人或者共有人的房产、车位、车库情况';
COMMENT ON COLUMN KMJJW."biz_probity_house"."addr" IS '具体地址';
COMMENT ON COLUMN KMJJW."biz_probity_house"."build_area" IS '建筑面积（㎡）';
COMMENT ON COLUMN KMJJW."biz_probity_house"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_house"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_house"."house_nature" IS '房产性质和功能类型';
COMMENT ON COLUMN KMJJW."biz_probity_house"."house_source_dest" IS '房产来源（去向）';
COMMENT ON COLUMN KMJJW."biz_probity_house"."house_target_dest" IS '房产去向';
COMMENT ON COLUMN KMJJW."biz_probity_house"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_house"."owner" IS '产权人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_house"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_house"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_house"."trade_date" IS '交易时间（年月）';
COMMENT ON COLUMN KMJJW."biz_probity_house"."trade_price" IS '交易价格（万元）';
COMMENT ON COLUMN KMJJW."biz_probity_house"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_house"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858610929700" ON "KMJJW"."biz_probity_house"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_insurance"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "policyholder" VARCHAR(255),
    "insurance_product" VARCHAR(255),
    "policy_no" VARCHAR(255),
    "insurance_company" VARCHAR(255),
    "accumulated_amount" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_insurance" IS '廉政档案-15.本人、配偶、共同生活的子女投资或者以其他方式持有投资型保险的情况';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."accumulated_amount" IS '累积缴纳保费、投资金（万元）';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."insurance_company" IS '保险公司名称';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."insurance_product" IS '保险产品全称';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."policy_no" IS '保单号';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."policyholder" IS '投保人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_insurance"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858496016500" ON "KMJJW"."biz_probity_insurance"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_investigate"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "investigate_time" VARCHAR(20),
    "investigate_reason" VARCHAR(1000),
    "investigate_dept" VARCHAR(1000),
    "handle_stage" VARCHAR(1000),
    "handle_result" VARCHAR(1000),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_investigate" IS '廉政档案-2.本人、配偶、子女及其配偶被追究责任的情况记录';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."handle_result" IS '处理结果';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."handle_stage" IS '处理阶段';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."investigate_dept" IS '调查处罚机关';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."investigate_reason" IS '被追究原因';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."investigate_time" IS '被追究时间';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_investigate"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858384939600" ON "KMJJW"."biz_probity_investigate"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_lecture"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "lecture" VARCHAR(255),
    "writing" VARCHAR(255),
    "consult" VARCHAR(255),
    "review" VARCHAR(255),
    "calligraphy_painting" VARCHAR(255),
    "other" VARCHAR(255),
    "total" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_lecture" IS '廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."calligraphy_painting" IS '书画';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."consult" IS '咨询';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."lecture" IS '讲学';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."other" IS '其他';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."review" IS '审稿';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."total" IS '合计';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_lecture"."writing" IS '写作';


CREATE UNIQUE  INDEX "INDEX24858279882300" ON "KMJJW"."biz_probity_lecture"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_live_abroad"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "name" VARCHAR(255),
    "country_region" VARCHAR(255),
    "live_city" VARCHAR(255),
    "begin_time" VARCHAR(20),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_live_abroad" IS '廉政档案-9.配偶、子女虽未移居、居住国（境）外及港澳台，但连续在国（境）外及港澳台工作、生活一年以上的情况';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."begin_time" IS '起始时间';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."country_region" IS '所在国家（地区）';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."live_city" IS '现工作、居住城市';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."name" IS '姓名';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_live_abroad"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858173606800" ON "KMJJW"."biz_probity_live_abroad"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_marital"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "marital_status" VARCHAR(50),
    "link_user_phone" VARCHAR(500),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_marital" IS '廉政档案-4.本人婚姻状况及紧急联系人情况';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."link_user_phone" IS '紧急联系人及联系方式';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."marital_status" IS '婚姻现状';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_marital"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24858091857300" ON "KMJJW"."biz_probity_marital"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_migrate"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "name" VARCHAR(255),
    "migrate_country_region" VARCHAR(255),
    "live_city" VARCHAR(255),
    "migrate_certificate_no" VARCHAR(255),
    "migrate_type" VARCHAR(50),
    "migrate_time" VARCHAR(20),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_migrate" IS '廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."live_city" IS '现居住城市';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."migrate_certificate_no" IS '移居证件号码';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."migrate_country_region" IS '移居国家、地区';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."migrate_time" IS '移居时间';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."migrate_type" IS '移居类别';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."name" IS '姓名';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_migrate"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857990195500" ON "KMJJW"."biz_probity_migrate"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_modify_approval"
(
    "id" VARCHAR(50) NOT NULL,
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "probity_id" VARCHAR(50),
    "status" INT,
    "approval_time" TIMESTAMP(0),
    "reject_reason" VARCHAR(500),
    "apply_user" VARCHAR(200),
    "YEAR" INT,
    "dept_name" VARCHAR(200),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_modify_approval" IS '申请修改廉政档案的记录';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."apply_user" IS '申请用户';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."approval_time" IS '审批时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."dept_name" IS '所属部门';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."reject_reason" IS '驳回原因';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."status" IS '状态 1-待审批 2-审批通过 3-审批驳回';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_approval"."YEAR" IS '年度';


CREATE UNIQUE  INDEX "INDEX24857888299000" ON "KMJJW"."biz_probity_modify_approval"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


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
    "modify_record" VARCHAR2(4000) DEFAULT NULL,
    "user_name" VARCHAR(200) DEFAULT NULL,
    "year" INT DEFAULT NULL,
    "user_id" VARCHAR(64),
    "dept_name" VARCHAR(200),
    "modify_filed" VARCHAR2(1000),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_modify_record" IS '修改廉政档案记录表';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."dept_name" IS '所属部门';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."modify_filed" IS '修改字段';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."modify_record" IS '修改记录';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."record_time" IS '记录时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."update_time" IS '更新时间';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."user_id" IS '用户ID';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."user_name" IS '用户';
COMMENT ON COLUMN KMJJW."biz_probity_modify_record"."year" IS '年度';



CREATE TABLE "KMJJW"."biz_probity_operate"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "operate_item" VARCHAR(255),
    "operate_time" VARCHAR(20),
    "operate_addr" VARCHAR(255),
    "people_num" VARCHAR(50),
    "filing_flag" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_operate" IS '廉政档案-19.本人或本人子女操办婚丧嫁娶情况';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."filing_flag" IS '是否进行报告备案';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."operate_addr" IS '操办地点';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."operate_item" IS '操办事项';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."operate_time" IS '操办时间';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."people_num" IS '人数';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_operate"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857786522300" ON "KMJJW"."biz_probity_operate"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_other"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "context" TEXT,
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_other" IS '廉政档案-20.个人认为需要报告的其他事项';
COMMENT ON COLUMN KMJJW."biz_probity_other"."context" IS '内容';
COMMENT ON COLUMN KMJJW."biz_probity_other"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_other"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_other"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_other"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_other"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_other"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_other"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857706067300" ON "KMJJW"."biz_probity_other"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_salary"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "salary" VARCHAR(255),
    "bonus" VARCHAR(255),
    "other" VARCHAR(255),
    "total" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_salary" IS '廉政档案-10.本人工资及各类奖金、津贴、补贴情况';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."bonus" IS '奖金';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."other" IS '其他';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."salary" IS '工资（含津贴、补贴）';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."total" IS '合计';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_salary"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857608422500" ON "KMJJW"."biz_probity_salary"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_stock"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "total" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_stock" IS '廉政档案-13.1本人、配偶、共同生活的子女投资或者以其他方式持有股票的情况';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."total" IS '填报前一交易日所有股票的总市值';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_stock"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857514305400" ON "KMJJW"."biz_probity_stock"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;


CREATE TABLE "KMJJW"."biz_probity_stock_item"
(
    "id" VARCHAR(50) NOT NULL,
    "probity_id" VARCHAR(50),
    "create_by" VARCHAR(64) DEFAULT '',
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(64) DEFAULT '',
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    "holder" VARCHAR(255),
    "stock_name_code" VARCHAR(255),
    "hold_num" VARCHAR(255),
    "market_value" DECIMAL(12,2),
    NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE KMJJW."biz_probity_stock_item" IS '廉政档案-13.2持有股票记录信息项';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."create_by" IS '创建者';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."create_time" IS '创建时间';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."hold_num" IS '持股数量';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."holder" IS '持有人姓名';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."id" IS 'id';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."market_value" IS '填报前一交易日市值（万元）';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."probity_id" IS 'probity_id';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."remark" IS '备注';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."stock_name_code" IS '股票名称或代码';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."update_by" IS '更新者';
COMMENT ON COLUMN KMJJW."biz_probity_stock_item"."update_time" IS '更新时间';


CREATE UNIQUE  INDEX "INDEX24857417812300" ON "KMJJW"."biz_probity_stock_item"("id" ASC) STORAGE(ON "MAIN", CLUSTERBTR) ;
