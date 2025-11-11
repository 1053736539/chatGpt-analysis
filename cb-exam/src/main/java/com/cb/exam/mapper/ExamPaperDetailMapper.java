package com.cb.exam.mapper;

import com.cb.exam.domain.ExamPaperDetail;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author hu
 * @date 2021-12-13
 */
public interface ExamPaperDetailMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ExamPaperDetail selectExamPaperDetailById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param examPaperDetail 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ExamPaperDetail> selectExamPaperDetailList(ExamPaperDetail examPaperDetail);

    /**
     * 新增【请填写功能名称】
     *
     * @param examPaperDetail 【请填写功能名称】
     * @return 结果
     */
    public int insertExamPaperDetail(ExamPaperDetail examPaperDetail);


    /**
     * 新增【批量新增】
     *
     * @param examPaperDetailList 【请填写功能名称】
     * @return 结果
     */
    public int insertBatchExamPaperDetail(List<ExamPaperDetail> examPaperDetailList);

    /**
     * 修改【请填写功能名称】
     *
     * @param examPaperDetail 【请填写功能名称】
     * @return 结果
     */
    public int updateExamPaperDetail(ExamPaperDetail examPaperDetail);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteExamPaperDetailById(Long id);

    public int deleteExamPaperDetailByPaperIds(Long[] ids);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamPaperDetailByIds(Long[] ids);
}
