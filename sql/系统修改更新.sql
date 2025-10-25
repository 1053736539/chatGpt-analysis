-- 新增表
CREATE TABLE "ASSESSMENT"."biz_assess_task_assess_user_confirm"
(
    "task_id" VARCHAR(50) NOT NULL,
    "dept_id" BIGINT,
    "users_json" CLOB,
    "status" VARCHAR(10),
    "create_by" VARCHAR(50),
    "create_time" TIMESTAMP(0),
    "update_by" VARCHAR(50),
    "update_time" TIMESTAMP(0),
    "remark" VARCHAR(255),
    NOT CLUSTER PRIMARY KEY("task_id")) STORAGE(ON "MAIN", CLUSTERBTR) ;

COMMENT ON TABLE "ASSESSMENT"."biz_assess_task_assess_user_confirm" IS '考核任务下发确认表';
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."dept_id" IS '部门Id';
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."remark" IS '备注';
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."status" IS '状态';
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."task_id" IS '任务ID';
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."users_json" IS '用户JSON';



-- 新增视图
CREATE or REPLACE view v_ordinary_assess_task AS
SELECT
    a.task_id,
    a.task_name,
    a.assess_cycle as "cycle",
    a.belong_year,
    p.special,
    p.personnel_type "type",
    CASE a.assess_cycle
        WHEN '1' THEN a.task_moth
        WHEN '2' THEN a.task_quarter
        WHEN '3' THEN a.task_year
        ELSE '专项考核' END as cycle_desc,
    a.status as task_status,
    a.start_time,
    a.end_time,
    a.del_flag,
    a.create_time,
    p.pro_id,
    p.pro_name,
    p.score
from biz_assess_task_manage a left join biz_assess_indicator_pro p on p.pro_id = a.pro_id;


-- 查询考核任务总结视图
create or REPLACE view v_regular_fill_info AS
SELECT
    ut.id,
    ut.task_id,
    ut.user_id,
    CASE WHEN ri.id is null THEN '0' ELSE '1' END as status,
    ri.id as info_id,
    ri.dept_and_post,
    ri.responsibilities,
    ri.personal_summary,
    ri.absenteeism_num,
    ri.leave_num,
    ri.late_arrival_early_departure_num,
    ri.rewards_punish_misconduct,
    ri.evaluation_level_suggest,
    ri.organ_opinions,
    ri.others_remark,
    ri.auditer,
    ri.audit_time,
    ri.audit_status,
    ifnull(ri.step,-1) as step,
    ri.establish_type,
    ri.auditer_seal,
    ri.organ_seal,
    ri.sign_img
FROM
    biz_assess_task_user_tag ut
        left JOIN biz_assess_regular_info ri ON ri.user_tag_id = ut.id;
COMMENT ON VIEW "ASSESSMENT"."v_regular_fill_info" IS '个人总结视图';


-- 考核用户视图
CREATE OR REPLACE VIEW v_task_assess_user_confirm AS
SELECT
    cf.task_id,
    '0' as special,
    TO_CHAR ( cf.users_json ) AS users_json,
    cf.status as confirm_status,
    u.user_id,
    u.user_name,
    u.name,
    u.avatar,
    u.phonenumber,
    u.sex,
    u.identity_type,
    u.education,
    u.work_post,
    u.del_flag,
    u.status,
    u.user_type,
    cf.dept_id,
    d.dept_name
FROM
    biz_assess_task_assess_user_confirm AS cf
        JOIN sys_user AS u ON find_in_set( u.user_id, cf.users_json )
        LEFT JOIN sys_dept AS d ON d.dept_id = cf.dept_id
UNION ALL
SELECT
    task_id,
    special,
    null AS users_json,
    '1' as confirm_status,
    u.user_id,
    u.user_name,
    u.name,
    u.avatar,
    u.phonenumber,
    u.sex,
    u.identity_type,
    u.education,
    u.work_post,
    u.del_flag,
    u.status,
    u.user_type,
    u.dept_id,
    d.dept_name
FROM
    biz_assess_task_manage AS m
        JOIN biz_assess_indicator_pro_duty AS du ON du.pro_id = m.pro_id AND du.type = '1'
        join sys_user u on u.user_id = du.user_id
        left join sys_dept d on u.dept_id = d.dept_id;
COMMENT ON VIEW "ASSESSMENT"."v_task_assess_user_confirm" IS '考核用户视图';


-- 平时考核用户办理情况判定视图
create OR REPLACE VIEW v_assess_task_handle as
AS
select distinct
    T.task_id,
    T.voter,
    T.user_id,
    T.handle_status,
    T.biz_type,
    T.enable_edit
from (
         SELECT
             t.task_id,
             t.vote_user_id as voter,
             t.assessed_user_id as user_id,
             t.status as handle_status,
             '0' as biz_type, -- 互评
             case when g.id IS NULL and t.status='1'  then true else false end as enable_edit
         FROM biz_assess_task_evaluate_tag t
                  left join biz_assess_evaluation_grade g on g.task_id= t.task_id and g.user_id= t.assessed_user_id
         UNION ALL
         SELECT

             task_id,
             user_id AS voter,
             user_id ,
             case when status = '0' then 0
                  when status = '1' and audit_status ='2' then 0
                  else 1 end
                     as handle_status,
             '1' as biz_type, --  总结填写
             case when audit_status ='1' then false else true end as enable_edit
         FROM
             v_regular_fill_info
     ) as T;

COMMENT ON VIEW "ASSESSMENT"."v_assess_task_handle" IS '平时考核用户办理情况判定视图';


-- 视图修改
/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_evaluation_grade"
AS

SELECT
    g.id,
    m.task_id,
    m.task_name,
    m.pro_id,
    m.assess_cycle as "cycle",
    CASE
        m.assess_cycle
        WHEN '1' THEN m.task_moth
        WHEN '2' THEN m.task_quarter
        WHEN '3' THEN  m.task_year
        ELSE 'special' END
                   AS cycle_desc,
    m.belong_year ,
    m.special,
    cf.user_id,
    cf.name,
    cf.dept_id,
    cf.dept_name,
    ifnull(g.dept_score,0) as dept_score,
    ifnull(g.leader_score,0) as leader_score,
    ifnull(g.unusual_score,0) as unusual_score,
    ifnull(g.evaluation_score,0) AS score,
    g.modified,
    ifnull(g.discipline_score,0)  as discipline_score,
    g.grade,
    g.rsc_opinion,
    g.dzh_opinion,
    g.final_option,
    g.escalation_id,
    p.personnel_type,
    ifnull(g.step,'-1') as step,
    g.scoring_mirror
FROM
    biz_assess_task_manage m
        join biz_assess_indicator_pro p on p.pro_id = m.pro_id
        join v_task_assess_user_confirm cf on cf.task_id = m.task_id
        left JOIN biz_assess_evaluation_grade g ON g.task_id = cf.task_id and g.user_id = cf.user_id
    where m.status ='1';



-- 抽象办理视图
/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_assessment_process"
AS
-- 0.考核用户确认
SELECT
    a.task_id,
    a.task_name,
    a.cycle,
    a.belong_year,
    a.special,
    a.type as personnel_type,
    a.cycle_desc,
    a.task_status,
    a.start_time,
    a.end_time,
    a.del_flag,
    a.create_time,
    null as voter,
    null as user_id,
    pd.dept_id,
    0 as process,
    (select count(0) from biz_assess_task_assess_user_confirm where task_id = a.task_id and dept_id = pd.dept_id) as handle_status
FROM
    v_ordinary_assess_task a
        LEFT JOIN biz_assess_indicator_pro_duty pd ON pd.pro_id = a.pro_id
where a.special = '0'

-- 1.总结审核
UNION ALL
SELECT DISTINCT
    a.task_id,
    a.task_name,
    a.cycle,
    a.belong_year,
    a.special,
    a.type as personnel_type,
    a.cycle_desc,
    a.task_status,
    a.start_time,
    a.end_time,
    a.del_flag,
    a.create_time,
    fi.user_id as voter,
    fi.user_id as user_id,
    null dept_id,
    1 as process,
    case when fi.step=0 THEN 0 ELSE 1 END as handle_status
FROM v_ordinary_assess_task AS a
         join v_regular_fill_info fi on fi.task_id = a.task_id and fi.audit_status in (0,1)
where (select count(0) from  v_assess_task_handle h where h.task_id = a.task_id and h.user_id = fi.user_id and h.handle_status = '0') = 0

-- 2.总结审定
UNION ALL
SELECT DISTINCT
    a.task_id,
    a.task_name,
    a.cycle,
    a.belong_year,
    a.special,
    a.type as personnel_type,
    a.cycle_desc,
    a.task_status,
    a.start_time,
    a.end_time,
    a.del_flag,
    a.create_time,
    fi.user_id as voter,
    fi.user_id as user_id,
    null dept_id,
    2 as process,
    case when fi.step=2 THEN 1 ELSE 0 END as handle_status
FROM v_ordinary_assess_task AS a
         join v_regular_fill_info fi on fi.task_id = a.task_id and fi.audit_status = 1

-- 3.待办考评
UNION ALL
SELECT DISTINCT
    a.task_id,
    a.task_name,
    a.cycle,
    a.belong_year,
    a.special,
    a.type as personnel_type,
    a.cycle_desc,
    a.task_status,
    a.start_time,
    a.end_time,
    a.del_flag,
    a.create_time,
    h.voter,
    h.user_id,
    null dept_id,
    3 as process,
    h.handle_status
FROM v_ordinary_assess_task AS a
         join v_assess_task_handle h on h.task_id = a.task_id
where a.task_status = '1'

-- 4.数据上报
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    4 AS process,
    case
        when sm.audit_status is NULL then 0
        when sm.audit_status ='0' then 0
        when sm.audit_status ='3' then 0
        ELSE 1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        LEFT JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id

-- 5.数据审核
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    5 AS process,
    case
        when sm.audit_status ='1' then 0
        when sm.audit_status ='2' then 1
        when sm.audit_status ='3' then 1
        ELSE -1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id

-- 6.纪委评分
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    6 AS process,
    case
        when CAST(sm.step AS INT) =0 then 0
        when CAST(sm.step AS INT) >=1 then 1
        ELSE -1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id and sm.audit_status ='2'
where  m.type in ('civil_servant_special','institution_special')

  and (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
        -- -1 为未上报
        and g.step='-1' and  g.user_id = cf.user_id
        and g.personnel_type in('civil_servant_special','institution_special')) = 0

-- 7.主要负责人评定等次
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    7 AS process,
    case
        when CAST(sm.step AS INT) =1 then 0
        when CAST(sm.step AS INT) >=2 then 1
        ELSE -1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id and sm.audit_status ='2'
where  m.type in ('civil_servant_special','institution_special')
  and  (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
             -- -1 为未上报
             and g.step in ('-1','0') and  g.user_id = cf.user_id
             and g.personnel_type in('civil_servant_special','institution_special')
       ) = 0

-- 8.机关党委审定意见
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    8 AS process,
    case
        when CAST(sm.step AS INT) =2 then 0
        when CAST(sm.step AS INT) >=3 then 1
        ELSE -1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id and sm.audit_status ='2'
where (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
        -- -1 为未上报
        and g.step in ('-1','0','1') and  g.user_id = cf.user_id
      ) = 0

-- 9.党组会确定等次回填
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    cf.user_id,
    cf.dept_id,
    9 AS process,
    case
        when CAST(sm.step AS INT) =3 then 0
        when CAST(sm.step AS INT) >=4 then 1
        ELSE -1 END
           as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        JOIN v_assess_report sm ON  sm.dept_id = cf.dept_id and sm.task_id = m.task_id and sm.audit_status ='2'
where (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
        -- -1 为未上报
        and g.step in ('-1','0','1','2') and  g.user_id = cf.user_id
      ) = 0

-- 10.公示
UNION ALL
SELECT DISTINCT
    m.task_id,
    m.task_name,
    m.cycle,
    m.belong_year,
    m.special,
    m.type AS personnel_type,
    m.cycle_desc,
    m.task_status,
    m.start_time,
    m.end_time,
    m.del_flag,
    m.create_time,
    NULL voter,
    null as user_id,
    null as dept_id,
    10 AS process,
    case
        when pr.id is null  then 0
        ELSE 1 END as handle_status
FROM
    v_ordinary_assess_task m
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id
        LEFT JOIN biz_assess_promulgate pr ON pr.task_id = m.task_id OR pr.quarter = m.cycle_desc
where  (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
         -- -1 为未上报
         and g.step in ('-1','0','1','2','3') and  g.user_id = cf.user_id
       ) = 0
;
COMMENT ON VIEW "ASSESSMENT"."v_assessment_process" IS '平时考核待办抽象视图';



-- 用户角色视图
CREATE OR REPLACE VIEW v_sys_user_role as
SELECT
    u.user_id,
    u.user_name,
    u.nick_name,
    r.role_key
FROM
    sys_user u
        join sys_user_role ur on ur.user_id = u.user_id
        JOIN sys_role r ON r.role_id = ur.role_id and r.status ='0' and r.del_flag = '0';
COMMENT ON VIEW "ASSESSMENT"."v_sys_user_role" IS '用户角色视图';

-- 平时考核任务上报视图
/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_assess_report"
AS
SELECT
    g.id AS repor_id,
    g.task_id,
    g.escalation_id AS save_mark_id,
    g.step,
    g.user_id,
    sm.dept_id,
    sm.YEAR AS belong_year,
    sm.cycle_desc,
    sm.escalation_flag as audit_status,
    sm.options,
    sm.create_by,
    sm.create_time
FROM
    biz_assess_evaluation_grade g
        JOIN biz_assess_evaluation_grade_save_mark sm ON sm.id = g.escalation_id;
COMMENT ON VIEW "ASSESSMENT"."v_assess_report" IS '平时考核任务上报视图';



-- biz_assess_evaluation_grade 新增字段
ALTER TABLE "ASSESSMENT"."biz_assess_evaluation_grade" ADD COLUMN attendance_score DECIMAL(10, 2) DEFAULT 0;
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_evaluation_grade"."attendance_score" IS '考勤扣分（二级巡视员、总师、督查员、主要负责人、主持工作人员）';


alter table "ASSESSMENT"."biz_assess_evaluation_grade" modify "grade" VARCHAR(50);
alter table "ASSESSMENT"."biz_assess_evaluation_grade" modify "rsc_opinion" VARCHAR(50);
alter table "ASSESSMENT"."biz_assess_evaluation_grade" modify "dzh_opinion" VARCHAR(50);
alter table "ASSESSMENT"."biz_assess_evaluation_grade" modify "final_option" VARCHAR(50);
--biz_assess_evaluation_grade
alter table "ASSESSMENT"."biz_assess_overall_score_level_record" modify "final_option" VARCHAR(50);


/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_evaluation_grade"
AS
SELECT
    g.id,
    m.task_id,
    m.task_name,
    m.pro_id,
    m.assess_cycle as "cycle",
    CASE
        m.assess_cycle
        WHEN '1' THEN m.task_moth
        WHEN '2' THEN m.task_quarter
        WHEN '3' THEN  m.task_year
        ELSE 'special' END
                   AS cycle_desc,
    m.belong_year ,
    m.special,
    cf.user_id,
    cf.name,
    cf.dept_id,
    cf.dept_name,
    ifnull(g.dept_score,0) as dept_score,
    ifnull(g.leader_score,0) as leader_score,
    ifnull(g.unusual_score,0) as unusual_score,
    ifnull(g.evaluation_score,0) AS score,
    g.modified,
    ifnull(g.discipline_score,0)  as discipline_score,
    ifnull(g.attendance_score,0) as attendance_score,
    g.grade,
    g.rsc_opinion,
    g.dzh_opinion,
    g.final_option,
    g.escalation_id,
    p.personnel_type,
    ifnull(g.step,'-1') as step,
    g.scoring_mirror
FROM
    biz_assess_task_manage m
        join biz_assess_indicator_pro p on p.pro_id = m.pro_id
        join v_task_assess_user_confirm cf on cf.task_id = m.task_id
        left JOIN biz_assess_evaluation_grade g ON g.task_id = cf.task_id and g.user_id = cf.user_id
where m.status ='1';



/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_assessment_process_statistics"
as
-- 0.用户确认
SELECT
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '用户确认' as process_name,
    0 as process,
    (select count(0) from biz_assess_indicator_pro_duty where pro_id = t.pro_id) as total_num,
    (select count(DISTINCT dept_id) from v_task_assess_user_confirm where task_id = t.task_id) as completed_num
FROM
    v_ordinary_assess_task as t
-- 1.总结填写
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '总结填写' as process_name,
    1 as process,
    (select count(0) from v_regular_fill_info where task_id = t.task_id) as total_num,
    (select count(0) from v_regular_fill_info where task_id = t.task_id and status = '1') as completed_num
FROM v_ordinary_assess_task as t
-- 2 总结审核
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '总结审核' as process_name,
    2 as process,
    (select count(0) from v_regular_fill_info where task_id = t.task_id) as total_num,
    (select count(0) from v_regular_fill_info where task_id = t.task_id and audit_status != '0') as completed_num
FROM v_ordinary_assess_task as t
-- 3 总结审定
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '总结审定' as process_name,
    3 as process,
    (select count(0) from v_regular_fill_info where task_id = t.task_id) as total_num,
    (select count(0) from v_regular_fill_info where task_id = t.task_id and organ_opinions is not null ) as completed_num
FROM v_ordinary_assess_task as t
-- 4 互评办理
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '互评办理' as process_name,
    4 as process,
    (select count(DISTINCT assessed_user_id) from biz_assess_task_evaluate_tag where task_id = t.task_id) as total_num,
    (select count(DISTINCT assessed_user_id) from biz_assess_task_evaluate_tag e where e.task_id = t.task_id
        and not EXISTS(
            select * from biz_assess_task_evaluate_tag where task_id =e.task_id and  assessed_user_id = e. assessed_user_id and status = '0'
        )
    ) as completed_num
FROM v_ordinary_assess_task as t
-- 5 数据上报
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '数据上报' as process_name,
    5 as process,
    case when t.special=0 then
             (select count(DISTINCT dept_id) from biz_assess_indicator_pro_duty where pro_id =  t.pro_id and type='0')
         else (select count(DISTINCT user_id) from biz_assess_indicator_pro_duty where pro_id =  t.pro_id and type='1')
        end
           as total_num,
    case when t.special=0 then
             (select count(DISTINCT dept_id) from v_assess_report where task_id = t.task_id)
         else (select count(DISTINCT user_id) from v_assess_report where task_id = t.task_id)
        end
           as completed_num
FROM v_ordinary_assess_task as t

-- 6 数据审核
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '数据审核' as process_name,
    6 as process,
    (select count(distinct dept_id) from v_assess_report where task_id = t.task_id) as total_num,
    (select count(distinct dept_id) from v_assess_report where task_id = t.task_id and audit_status = '2') as completed_num
FROM v_ordinary_assess_task as t

-- 7 纪委打分
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '纪委打分' as process_name,
    7 as process,
    (select count(distinct user_id) from v_assess_report where task_id = t.task_id and audit_status = '2'
        -- and t.type in ('civil_servant_special','institution_special')
    ) as total_num,
    (select count(distinct user_id ) from v_evaluation_grade where task_id = t.task_id
       -- and personnel_type in ('civil_servant_special','institution_special')
       and CAST(step AS INT) >=1
    ) as completed_num
FROM v_ordinary_assess_task as t


-- 8 主要负责人评定等次
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '主要负责人评定等次' as process_name,
    8 as process,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
     -- and t.type in ('civil_servant_special','institution_special')
    ) as total_num,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
     -- and t.type in ('civil_servant_special','institution_special')
        and CAST(step AS INT)>=2
    ) as completed_num
FROM v_ordinary_assess_task as t

-- 9.机关党委审定意见
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '机关党委审定意见' as process_name,
    9 as process,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
    ) as total_num,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
                                                        and CAST(step AS INT)>=3
    ) as completed_num
FROM v_ordinary_assess_task as t


-- 10.党组会确定等次
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '党组会确定等次' as process_name,
    10 as process,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
    ) as total_num,
    (select count(0) from biz_assess_evaluation_grade where task_id = t.task_id
                                                        and CAST(step AS INT)>=4
    ) as completed_num
FROM v_ordinary_assess_task as t

-- 11.公示
union all
select
    t.task_id,
    t.pro_id,
    t.belong_year,
    t.special,
    t.cycle,
    t.cycle_desc,
    t.type,
    t.task_status,
    '公示' as process_name,
    11 as process,
    (select count(0) from v_evaluation_grade where task_id = t.task_id) as total_num,
    (select count(0) from (
                              select g.* from  v_evaluation_grade g join  biz_assess_promulgate p on p.task_id = g.task_id OR p.quarter =g.cycle_desc
                          ) as p where p.task_id = t.task_id
    ) as completed_num
FROM v_ordinary_assess_task as t
;


COMMENT ON VIEW "ASSESSMENT"."v_assessment_process_statistics" IS '任务统计视图';

/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_sys_user"
AS
SELECT
    u.user_id,
    u.dept_id,
    u.user_name,
    u.nick_name,
    u.email,
    u.avatar,
    u.phonenumber,
    u.sex,
    u.status,
    u.del_flag,
    u.user_type,
    u.name,
    u.identity_type,
    u.is_main_leader,
    ifnull(u.is_hosting_work,'0') as is_hosting_work,
    ifnull(case when u.work_post_code ='' then null else u.work_post_code end,u.work_title_code ) as work_title_code,
    u.personnel_status,
    (case when SUBSTR(d.dept_code,1,1) = 'A' then '1' when SUBSTR(d.dept_code,1,1) = 'B' then '1' when SUBSTR(d.dept_code,1,1) = 'C' then '3' else null end) as dept_identity_type,
    d.dept_name
FROM
    sys_user u
        left join sys_dept d on u.dept_id = d.dept_id;

COMMENT ON VIEW "ASSESSMENT"."v_sys_user" IS '用户视图';



ALTER TABLE "ASSESSMENT"."biz_assess_task_assess_user_confirm" ADD COLUMN id VARCHAR(50);
ALTER TABLE "ASSESSMENT"."biz_assess_task_assess_user_confirm" ADD PRIMARY KEY (id);

/***Manager***/CREATE OR REPLACE VIEW "ASSESSMENT"."v_task_assess_user_confirm"
AS
SELECT
    cf.id,
    cf.task_id,
    '0' as special,
    TO_CHAR ( cf.users_json ) AS users_json,
    cf.status as confirm_status,
    u.user_id,
    u.user_name,
    u.name,
    u.avatar,
    u.phonenumber,
    u.sex,
    u.identity_type,
    u.education,
    u.work_post,
    u.del_flag,
    u.status,
    u.user_type,
    cf.dept_id,
    d.dept_name
FROM
    biz_assess_task_assess_user_confirm AS cf
        JOIN sys_user AS u ON find_in_set( u.user_id, cf.users_json )
        LEFT JOIN sys_dept AS d ON d.dept_id = cf.dept_id
UNION ALL
SELECT
    null as id,
    task_id,
    special,
    null AS users_json,
    '1' as confirm_status,
    u.user_id,
    u.user_name,
    u.name,
    u.avatar,
    u.phonenumber,
    u.sex,
    u.identity_type,
    u.education,
    u.work_post,
    u.del_flag,
    u.status,
    u.user_type,
    u.dept_id,
    d.dept_name
FROM
    biz_assess_task_manage AS m
        JOIN biz_assess_indicator_pro_duty AS du ON du.pro_id = m.pro_id AND du.type = '1'
        join sys_user u on u.user_id = du.user_id
        left join sys_dept d on u.dept_id = d.dept_id;


-- biz_assess_indicator_ism_config 新增字段
ALTER TABLE "ASSESSMENT"."biz_assess_indicator_ism_config" add column("indicator_snapshot" TEXT);
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_indicator_ism_config"."indicator_snapshot" IS '考核指标biz_assess_indicator对象数组';








