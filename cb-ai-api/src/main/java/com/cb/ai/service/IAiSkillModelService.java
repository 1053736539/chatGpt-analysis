package com.cb.ai.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.domain.AiSkillModel;
import com.cb.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * AI 技能模型Service接口
 *
 * @author ouyang
 * @date 2024-11-02
 */
public interface IAiSkillModelService {
    /**
     * 查询AI 技能模型
     *
     * @param id AI 技能模型ID
     * @return AI 技能模型
     */
    public AiSkillModel selectAiSkillModelById(String id);

    public AiSkillModel selectAiSkillModelByModelCode(String modelCode);

    /**
     * 查询AI 技能模型列表
     *
     * @param aiSkillModel AI 技能模型
     * @return AI 技能模型集合
     */
    public List<AiSkillModel> selectAiSkillModelList(AiSkillModel aiSkillModel);

    public List<AiSkillModel> selectAllAiSkillModelList();

    /**
     * 新增AI 技能模型
     *
     * @param aiSkillModel AI 技能模型
     * @return 结果
     */
    public int insertAiSkillModel(AiSkillModel aiSkillModel);

    /**
     * 修改AI 技能模型
     *
     * @param aiSkillModel AI 技能模型
     * @return 结果
     */
    public int updateAiSkillModel(AiSkillModel aiSkillModel);

    /**
     * 批量删除AI 技能模型
     *
     * @param ids 需要删除的AI 技能模型ID
     * @return 结果
     */
    public int deleteAiSkillModelByIds(String[] ids);

    /**
     * 删除AI 技能模型信息
     *
     * @param id AI 技能模型ID
     * @return 结果
     */
    public int deleteAiSkillModelById(String id);

    /**
     * 测试模型
     * @param aiSkillModel
     * @return
     */
    public AjaxResult textTest(AiSkillModel aiSkillModel);

    public AjaxResult fileTest(MultipartFile file,AiSkillModel model);

    public AjaxResult runFileModel(MultipartFile file,String modelId);
}
