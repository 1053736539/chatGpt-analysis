package com.cb.ai.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.ai.mapper.AiSkillModelMapper;
import com.cb.ai.domain.AiSkillModel;
import com.cb.ai.service.IAiSkillModelService;
import org.springframework.web.multipart.MultipartFile;

/**
 * AI 技能模型Service业务层处理
 *
 * @author ouyang
 * @date 2024-11-02
 */
@Service
public class AiSkillModelServiceImpl implements IAiSkillModelService {
    @Autowired
    private AiSkillModelMapper aiSkillModelMapper;

    @Autowired
    private AIClient aiClient;


    /**
     * 查询AI 技能模型
     *
     * @param id AI 技能模型ID
     * @return AI 技能模型
     */
    @Override
    public AiSkillModel selectAiSkillModelById(String id) {
        return aiSkillModelMapper.selectAiSkillModelById(id);
    }

    @Override
    public AiSkillModel selectAiSkillModelByModelCode(String modelCode) {
        return aiSkillModelMapper.selectAiSkillModelByModelCode(modelCode);
    }

    /**
     * 查询AI 技能模型列表
     *
     * @param aiSkillModel AI 技能模型
     * @return AI 技能模型
     */
    @Override
    public List<AiSkillModel> selectAiSkillModelList(AiSkillModel aiSkillModel) {
        return aiSkillModelMapper.selectAiSkillModelList(aiSkillModel);
    }

    @Override
    public List<AiSkillModel> selectAllAiSkillModelList() {
        return aiSkillModelMapper.selectAllAiSkillModelList();
    }

    /**
     * 新增AI 技能模型
     *
     * @param aiSkillModel AI 技能模型
     * @return 结果
     */
    @Override
    public int insertAiSkillModel(AiSkillModel aiSkillModel) {
        boolean b = aiSkillModelMapper.checkModelUniqueByCode(aiSkillModel.getModelCode(), null);
        if(b){
            throw new CustomException("模型编码已存在！");
        }
        aiSkillModel.setId(IdUtils.randomUUID());
        aiSkillModel.setCreateBy(SecurityUtils.getUsername());
        aiSkillModel.setCreateTime(DateUtils.getNowDate());
        return aiSkillModelMapper.insertAiSkillModel(aiSkillModel);
    }

    /**
     * 修改AI 技能模型
     *
     * @param aiSkillModel AI 技能模型
     * @return 结果
     */
    @Override
    public int updateAiSkillModel(AiSkillModel aiSkillModel) {
        boolean b = aiSkillModelMapper.checkModelUniqueByCode(aiSkillModel.getModelCode(), aiSkillModel.getId());
        if(b){
            throw new CustomException("模型编码已存在！");
        }
        aiSkillModel.setUpdateBy(SecurityUtils.getUsername());
        aiSkillModel.setUpdateTime(DateUtils.getNowDate());
        return aiSkillModelMapper.updateAiSkillModel(aiSkillModel);
    }

    /**
     * 批量删除AI 技能模型
     *
     * @param ids 需要删除的AI 技能模型ID
     * @return 结果
     */
    @Override
    public int deleteAiSkillModelByIds(String[] ids) {
        return aiSkillModelMapper.deleteAiSkillModelByIds(ids);
    }

    /**
     * 删除AI 技能模型信息
     *
     * @param id AI 技能模型ID
     * @return 结果
     */
    @Override
    public int deleteAiSkillModelById(String id) {
        return aiSkillModelMapper.deleteAiSkillModelById(id);
    }

    @Override
    public AjaxResult textTest(AiSkillModel model) {
        String inputType = model.getInputType();
        if ("1".equals(inputType)) {
            throw new CustomException("文件类型模型暂不支持测试！！！");
        }
        String tipWords = model.getTipWords();
        String example = model.getExample();
        String outValueScheme = model.getOutValueScheme();
        tipWords = tipWords.replace("{out_value_scheme}", outValueScheme).replace("{input}", example);
        JSONObject jsonObject = aiClient.generalDataCleanByText(tipWords, outValueScheme);
        String resultType = model.getResultType();
        if ("0".equals(resultType)) {
            return AjaxResult.success(jsonObject);
        } else {
            return AjaxResult.success(jsonObject.toJSONString());
        }
    }

    @Override
    public AjaxResult fileTest(MultipartFile file, AiSkillModel model) {
        String text = aiClient.uploadFileAndPrompt(file, model.getTipWords());
        return formatOut(model,text);
    }

    @Override
    public AjaxResult runFileModel(MultipartFile file,String modelId) {
        AiSkillModel skillModel = selectAiSkillModelById(modelId);
        if(null == skillModel || !"1".equals(skillModel.getInputType())){
            throw new CustomException("模型不存在或模型输入类型不是文件类型！");
        }
        String text = aiClient.uploadFileAndPrompt(file, skillModel.getTipWords());
        return formatOut(skillModel,text);
    }

    private AjaxResult formatOut(AiSkillModel skillModel,String text){
        String resultType = skillModel.getResultType();
        if ("0".equals(resultType)) {
            try{
                JSONObject jsonObject = JSONObject.parseObject(text);
                return AjaxResult.success(jsonObject);
            } catch (Exception e){
                throw new RuntimeException("AI调用返回数据解析失败！",e);
            }
        } else {
            return AjaxResult.success(text);
        }
    }

}
