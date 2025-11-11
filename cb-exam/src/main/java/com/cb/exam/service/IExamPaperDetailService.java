package com.cb.exam.service;

import com.cb.exam.domain.ExamPaperDetail;
import com.cb.exam.dto.ExamPaperDto;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author hu
 * @date 2023-11-08
 */
public interface IExamPaperDetailService {
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
     * 新增【请填写功能名称】
     *
     * @param examPaperlList 【请填写功能名称】
     * @return 结果
     */
    public int addExamPaperDetail(ExamPaperDto.Add.Input examPaperlList);

    /**
     * 修改【请填写功能名称】
     *
     * @param examPaperDetail 【请填写功能名称】
     * @return 结果
     */
    public int updateExamPaperDetail(ExamPaperDetail examPaperDetail);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteExamPaperDetailByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteExamPaperDetailById(Long id);
}
