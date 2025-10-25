
-- biz_assess_task_user_tag
CREATE INDEX idx_user_tag_task_id ON biz_assess_task_user_tag (task_id);
CREATE INDEX idx_user_tag_user_id ON biz_assess_task_user_tag (user_id);


-- biz_assess_task_evaluate_tag
CREATE INDEX idx_evaluate_tag_task_id ON biz_assess_task_evaluate_tag (task_id);
CREATE INDEX idx_evaluate_tag_vote_user_id ON biz_assess_task_evaluate_tag (vote_user_id);
CREATE INDEX idx_evaluate_tag_assessed_user_id ON biz_assess_task_evaluate_tag (assessed_user_id);

-- biz_assess_task_evaluate_record
CREATE INDEX idx_evaluate_record_evaluate_tag_id ON biz_assess_task_evaluate_record (evaluate_tag_id);

-- biz_assess_task_assess_user_confirm
CREATE INDEX idx_user_confirm_task_id ON biz_assess_task_assess_user_confirm (task_id);
CREATE INDEX idx_user_confirm_dept_id ON biz_assess_task_assess_user_confirm (dept_id);

-- biz_assess_evaluation_grade
CREATE INDEX idx_evaluation_grade_task_id ON biz_assess_evaluation_grade (task_id);
CREATE INDEX idx_evaluation_grade_user_id ON biz_assess_evaluation_grade (user_id);
CREATE INDEX idx_evaluation_grade_dept_id ON biz_assess_evaluation_grade (dept_id);

-- biz_assess_evaluation_grade_save_mark
CREATE INDEX idx_grade_save_mark_task_id ON biz_assess_evaluation_grade_save_mark (task_id);
CREATE INDEX idx_grade_save_mark_dept_id ON biz_assess_evaluation_grade_save_mark (dept_id);


-- biz_assess_regular_info
CREATE INDEX idx_regular_info_user_tag_id ON biz_assess_regular_info (user_tag_id);
