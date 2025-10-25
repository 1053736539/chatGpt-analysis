
CREATE OR REPLACE VIEW "ASSESSMENT"."v_annual_assessment_task"
AS
    -- 优秀互评
select distinct
    assessment_year as belong_year,
    concat(assessment_year,'年优秀互评') as task_name,
    discussant_user_id as voter_id,
    status as handle_status,
    1 as biz_type -- 1 优秀互评
FROM
    biz_assess_dept_excellent_evaluation

union ALL
-- A类评议表
SELECT DISTINCT
    assessment_year as belong_year,
    concat(assessment_year,'年A类评议表') as task_name,
    discussant_user_id as voter_id,
    status as handle_status,
    2 as biz_type -- 2 A类评议表
FROM
    biz_assess_annual_review_type_a

union ALL
-- B类评议表
SELECT DISTINCT
    assessment_year as belong_year,
    concat(assessment_year,'年B类评议表') as task_name,
    discussant_user_id as voter_id,
    status as handle_status,
    3 as biz_type -- 3 B类评议表
FROM
    biz_assess_annual_review_type_b

union ALL
-- 专项测评
SELECT DISTINCT
    assessment_year as belong_year,
    concat(assessment_year,'年专项测评') as task_name,
    reviewer_user_id as voter_id,
    status as handle_status,
    4 as biz_type -- 4  专项测评
FROM
    biz_assess_cadre_political_quality

union ALL
-- 民主测评
SELECT DISTINCT
    dt.year as belong_year,
    concat(dt.year,'年民主测评') as task_name,
    r.vote_user_id as voter_id,
    r.status as handle_status,
    5 as biz_type -- 4  民主测评
FROM
    biz_assess_cadre_democratic_record r
        LEFT JOIN biz_assess_cadre_democratic_task dt ON dt.id = r.task_id

;

