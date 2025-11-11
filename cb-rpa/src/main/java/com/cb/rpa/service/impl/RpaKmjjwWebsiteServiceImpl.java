package com.cb.rpa.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.rpa.es.repository.RpaKmjjwWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.cb.rpa.mapper.RpaKmjjwWebsiteMapper;
import com.cb.rpa.domain.RpaKmjjwWebsite;
import com.cb.rpa.service.IRpaKmjjwWebsiteService;

/**
 * 昆明市纪监委网站Service业务层处理
 *
 * @author ouyang
 * @date 2024-10-22
 */
@Service
public class RpaKmjjwWebsiteServiceImpl implements IRpaKmjjwWebsiteService {
    @Autowired
    private RpaKmjjwWebsiteMapper rpaKmjjwWebsiteMapper;

    @Autowired
    private AIClient aiClient;

    @Value("${rpa.ai.knowledge-id}")
    private Integer knowledgeId;
    @Autowired
    private RpaKmjjwWebsiteRepository esRepository;

    /**
     * 查询昆明市纪监委网站
     *
     * @param id 昆明市纪监委网站ID
     * @return 昆明市纪监委网站
     */
    @Override
    public RpaKmjjwWebsite selectRpaKmjjwWebsiteById(String id) {
        return rpaKmjjwWebsiteMapper.selectRpaKmjjwWebsiteById(id);
    }

    /**
     * 查询昆明市纪监委网站列表
     *
     * @param rpaKmjjwWebsite 昆明市纪监委网站
     * @return 昆明市纪监委网站
     */
    @Override
    public List<RpaKmjjwWebsite> selectRpaKmjjwWebsiteList(RpaKmjjwWebsite rpaKmjjwWebsite) {
        return rpaKmjjwWebsiteMapper.selectRpaKmjjwWebsiteList(rpaKmjjwWebsite);
    }

    /**
     * 新增昆明市纪监委网站
     *
     * @param website 昆明市纪监委网站
     * @return 结果
     */
    @Override
    public int insertRpaKmjjwWebsite(RpaKmjjwWebsite website) {
        website.setId(IdUtils.randomUUID());
        website.setCreateTime(DateUtils.getNowDate());
        int result = rpaKmjjwWebsiteMapper.insertRpaKmjjwWebsite(website);
        if (result > 0) {
            esRepository.save(website);
        }
        return result;
    }

    /**
     * 修改昆明市纪监委网站
     *
     * @param website 昆明市纪监委网站
     * @return 结果
     */
    @Override
    public int updateRpaKmjjwWebsite(RpaKmjjwWebsite website) {
        website.setUpdateTime(DateUtils.getNowDate());
        int result = rpaKmjjwWebsiteMapper.updateRpaKmjjwWebsite(website);
        String id = website.getId();
        if (result > 0) {
            esRepository.save(website);
        }
        return result;
    }

    /**
     * 批量删除昆明市纪监委网站
     *
     * @param ids 需要删除的昆明市纪监委网站ID
     * @return 结果
     */
    @Override
    public int deleteRpaKmjjwWebsiteByIds(String[] ids) {
        int result = rpaKmjjwWebsiteMapper.deleteRpaKmjjwWebsiteByIds(ids);
        if(result > 0){
            esRepository.deleteAllById(Arrays.asList(ids));
        }
        return result;
    }

    /**
     * 删除昆明市纪监委网站信息
     *
     * @param id 昆明市纪监委网站ID
     * @return 结果
     */
    @Override
    public int deleteRpaKmjjwWebsiteById(String id) {
        int result = rpaKmjjwWebsiteMapper.deleteRpaKmjjwWebsiteById(id);
        if(result > 0){
            esRepository.deleteById(id);
        }
        return result;
    }

    @Override
    public int collectWebsiteInfo(RpaKmjjwWebsite website) {
        int result;
        website.setPushAiKnowledge("1");
        RpaKmjjwWebsite existence = rpaKmjjwWebsiteMapper.existence(website.getTitle());
        if (existence != null) {
            website.setId(existence.getId());
            result = this.updateRpaKmjjwWebsite(website);
        } else {
            result = this.insertRpaKmjjwWebsite(website);
        }
        if (result > 0) {
            esRepository.save(website);
            aiClient.uploadContentToKnowledge(website.getTitle(), HtmlUtil.cleanHtmlTag(website.getContent()), knowledgeId);
        }
        return result;
    }

    @Override
    public int push2AiKnowledge(RpaKmjjwWebsite website) {
        JSONObject json = aiClient.uploadContentToKnowledge(website.getTitle(), HtmlUtil.cleanHtmlTag(website.getContent()), knowledgeId);
        long statusCode = json.getLongValue("status_code");
        if (statusCode != 200) {
            throw new RuntimeException("推送至AI知识库失败： " + json.getString("status_message"));
        }
        website.setPushAiKnowledge("1");
        return rpaKmjjwWebsiteMapper.updateRpaKmjjwWebsite(website);
    }
}
