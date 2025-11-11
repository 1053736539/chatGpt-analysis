package com.cb.rpa.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.cb.ai.client.AIClient;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.rpa.domain.RpaCcdiWebsite;
import com.cb.rpa.es.repository.RpaCcdiWebsiteRepository;
import com.cb.rpa.mapper.RpaCcdiWebsiteMapper;
import com.cb.rpa.service.IRpaCcdiWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 中纪委Service业务层处理
 *
 * @author ouyang
 * @date 2024-11-13
 */
@Service
public class RpaCcdiWebsiteServiceImpl implements IRpaCcdiWebsiteService {
    @Autowired
    private RpaCcdiWebsiteMapper rpaCcdiWebsiteMapper;

    @Autowired
    private RpaCcdiWebsiteRepository ccdiWebsiteRepository;

    @Autowired
    private AIClient aiClient;

    @Value("${rpa.ai.knowledge-id}")
    private Integer knowledgeId;

    /**
     * 查询中纪委
     *
     * @param id 中纪委ID
     * @return 中纪委
     */
    @Override
    public RpaCcdiWebsite selectRpaCcdiWebsiteById(String id) {
        return rpaCcdiWebsiteMapper.selectRpaCcdiWebsiteById(id);
    }

    /**
     * 查询中纪委列表
     *
     * @param rpaCcdiWebsite 中纪委
     * @return 中纪委
     */
    @Override
    public List<RpaCcdiWebsite> selectRpaCcdiWebsiteList(RpaCcdiWebsite rpaCcdiWebsite) {
        return rpaCcdiWebsiteMapper.selectRpaCcdiWebsiteList(rpaCcdiWebsite);
    }

    /**
     * 新增中纪委
     *
     * @param ccdiWebsite 中纪委
     * @return 结果
     */
    @Override
    public int insertRpaCcdiWebsite(RpaCcdiWebsite ccdiWebsite) {
        ccdiWebsite.setId(IdUtils.randomUUID());
        ccdiWebsite.setCreateTime(DateUtils.getNowDate());
        int result = rpaCcdiWebsiteMapper.insertRpaCcdiWebsite(ccdiWebsite);
        if (result > 0) {
            ccdiWebsiteRepository.save(ccdiWebsite);
        }
        return result;
    }

    /**
     * 修改中纪委
     *
     * @param ccdiWebsite 中纪委
     * @return 结果
     */
    @Override
    public int updateRpaCcdiWebsite(RpaCcdiWebsite ccdiWebsite) {
        ccdiWebsite.setUpdateTime(DateUtils.getNowDate());
        int result = rpaCcdiWebsiteMapper.updateRpaCcdiWebsite(ccdiWebsite);
        if (result > 0) {
            ccdiWebsiteRepository.save(ccdiWebsite);
        }
        return result;
    }

    /**
     * 批量删除中纪委
     *
     * @param ids 需要删除的中纪委ID
     * @return 结果
     */
    @Override
    public int deleteRpaCcdiWebsiteByIds(String[] ids) {
        int result = rpaCcdiWebsiteMapper.deleteRpaCcdiWebsiteByIds(ids);
        if (result > 0) {
            ccdiWebsiteRepository.deleteAllById(Arrays.asList(ids));
        }

        return result;
    }

    /**
     * 删除中纪委信息
     *
     * @param id 中纪委ID
     * @return 结果
     */
    @Override
    public int deleteRpaCcdiWebsiteById(String id) {
        int result = rpaCcdiWebsiteMapper.deleteRpaCcdiWebsiteById(id);
        if (result > 0) {
            ccdiWebsiteRepository.deleteById(id);
        }
        return result;
    }

    @Override
    public int collectWebsiteInfo(RpaCcdiWebsite ccdiWebsite) {
        int result;
        ccdiWebsite.setPushAiKnowledge("1");
        RpaCcdiWebsite existence = rpaCcdiWebsiteMapper.existence(ccdiWebsite.getTitle());
        if (existence != null) {
            ccdiWebsite.setId(existence.getId());
            result = this.updateRpaCcdiWebsite(ccdiWebsite);
        } else {
            result = this.insertRpaCcdiWebsite(ccdiWebsite);
        }
        if (result > 0) {
            ccdiWebsiteRepository.save(ccdiWebsite);
            aiClient.uploadContentToKnowledge(ccdiWebsite.getTitle(), HtmlUtil.cleanHtmlTag(ccdiWebsite.getContent()), knowledgeId);
        }
        return result;
    }

    @Override
    public int push2AiKnowledge(RpaCcdiWebsite website) {
        JSONObject json = aiClient.uploadContentToKnowledge(website.getTitle(), HtmlUtil.cleanHtmlTag(website.getContent()), knowledgeId);
        long statusCode = json.getLongValue("status_code");
        if (statusCode != 200) {
            throw new RuntimeException("推送至AI知识库失败： " + json.getString("status_message"));
        }
        website.setPushAiKnowledge("1");
        return rpaCcdiWebsiteMapper.updateRpaCcdiWebsite(website);
    }
}
