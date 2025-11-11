package com.cb.ai.mapper;

import java.util.List;

import com.cb.ai.domain.AiSkillModel;
import org.apache.ibatis.annotations.Param;

/**
 * AI 技能模型Mapper接口
 *
 * @author ouyang
 * @date 2024-11-02
 */
public interface AiSkillModelMapper {
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
     * 删除AI 技能模型
     *
     * @param id AI 技能模型ID
     * @return 结果
     */
    public int deleteAiSkillModelById(String id);

    /**
     * 批量删除AI 技能模型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAiSkillModelByIds(String[] ids);

    public boolean checkModelUniqueByCode(@Param("modelCode") String modelCode, @Param("id") String id);
}
