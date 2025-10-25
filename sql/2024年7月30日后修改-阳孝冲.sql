-- biz_assess_evaluation_grade 新增字段
ALTER TABLE "ASSESSMENT"."biz_assess_task_assess_user_confirm" ADD COLUMN rejection VARCHAR(255);
COMMENT ON COLUMN "ASSESSMENT"."biz_assess_task_assess_user_confirm"."rejection" IS '驳回理由';



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
    -- 0未确认 1 已确认 2 驳回 3 不参加
    case when cf.status is null then 0
         when cf.status ='1' then 1
         when cf.status ='2' then 0
         when cf.status ='3' then 1
         else 0 end
           as handle_status
FROM
    v_ordinary_assess_task a
        LEFT JOIN biz_assess_indicator_pro_duty pd ON pd.pro_id = a.pro_id
        left join biz_assess_task_assess_user_confirm cf on cf.task_id = a.task_id and cf.dept_id = pd.dept_id
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
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
        JOIN v_task_assess_user_confirm cf ON cf.task_id = m.task_id and cf.confirm_status = '1'
        LEFT JOIN biz_assess_promulgate pr ON pr.task_id = m.task_id OR pr.quarter = m.cycle_desc
where  (select count(0) from  v_evaluation_grade g where g.task_id = m.task_id
                                                     -- -1 为未上报
                                                     and g.step in ('-1','0','1','2','3') and  g.user_id = cf.user_id
       ) = 0
;



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
             -- (select count(DISTINCT dept_id) from biz_assess_indicator_pro_duty where pro_id =  t.pro_id and type='0')
             (select count(distinct dept_id) from biz_assess_task_assess_user_confirm where task_id = t.task_id and status ='1')
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
        join v_task_assess_user_confirm cf on cf.task_id = m.task_id and cf.confirm_status ='1'
        left JOIN biz_assess_evaluation_grade g ON g.task_id = cf.task_id and g.user_id = cf.user_id
where m.status ='1';






