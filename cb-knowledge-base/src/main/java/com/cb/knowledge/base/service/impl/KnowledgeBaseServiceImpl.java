package com.cb.knowledge.base.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.cb.common.config.RuoYiConfig;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.knowledge.base.domain.DataCostant;
import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.domain.vo.KnowledgeBaseVo;
import com.cb.knowledge.base.essearch.ChineseTokenizer;
import com.cb.knowledge.base.essearch.DocumentParser;
import com.cb.knowledge.base.essearch.QueryEngine;
import com.cb.knowledge.base.mapper.KnowledgeBaseMapper;
import com.cb.knowledge.base.service.IKnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/15 20:50
 * @Copyright (c) 2025
 * @Description 知识库服务层实现类
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KnowledgeBaseServiceImpl implements IKnowledgeBaseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KnowledgeBaseMapper knowledgeBaseMapper;

    private final RedisCache redisCache;

    @Override
    public List<KnowledgeBase> selectDataList(KnowledgeBase knowledgeBase) {
        if (StrUtil.isNotBlank(knowledgeBase.getFileName())) {
            // 此处的fileName可能是文件名， 也可能是文件简述， 还有可能是文件内容
            // 判断是否有文件内容
            List<Integer> ids = new ArrayList<>(16);
            // 两种查询结果合并
            QueryEngine engine = QueryEngine.getInstance();
            List<String> resultIds = engine.search(knowledgeBase.getFileName(), true);
            resultIds.addAll(engine.search(knowledgeBase.getFileName(), false));
            if (!resultIds.isEmpty()) {
                // 获取对应的ID 并 去重
                ids.addAll(resultIds.stream().map(DataCostant.FILE_PATH_ID::get).filter(Objects::nonNull).collect(Collectors.toSet()));
            }
            knowledgeBase.setIds(ids);
        }
        return knowledgeBaseMapper.selectKnowledgeList(knowledgeBase);
    }

    @Override
    public Set<String> getFileTags(String fileType) {
        String cacheKey = "knowledgeBase:tags:" + fileType;
        if (StrUtil.isNotBlank(fileType)) {
            Set<String> tags = redisCache.getCacheSet(cacheKey);
            if (tags == null || tags.isEmpty()) {
                List<String> fileTags = knowledgeBaseMapper.selectTags(fileType);
                if (fileTags != null && !fileTags.isEmpty()) {
                    tags = new HashSet<>(64);
                    for (String fileTag : fileTags) {
                        tags.addAll(Convert.toList(String.class, fileTag));
                    }
                    redisCache.setCacheSet(cacheKey, tags);
                    redisCache.expire(cacheKey, 24 * 60 * 60);
                }
            }
            return tags;
        }
        return null;
    }

    @Override
    public KnowledgeBase selectData(Integer id) {
        return knowledgeBaseMapper.selectById(id);
    }

    @Override
    public boolean saveData(List<KnowledgeBaseVo> knowledgeBases) {
        List<KnowledgeBase> collect = knowledgeBases.stream().map(knowledgeBase -> {
            KnowledgeBase base = Convert.convert(KnowledgeBase.class, knowledgeBase);
            analyseFile(base);
            base.setFileTags(String.join(",", knowledgeBase.getFileTags()));
            base.setCreateBy(SecurityUtils.getLoginUser().getUsername());
            base.setCreateTime(DateUtils.getNowDate());
            return base;
        }).collect(Collectors.toList());
        int result = knowledgeBaseMapper.batchSave(collect);
        if (result != 0) {
            updatePathMap(collect.toArray(new KnowledgeBase[0]));
        }
        return true;
    }

    @Override
    public boolean updateData(KnowledgeBaseVo knowledgeBase) {
        KnowledgeBase base = Convert.convert(KnowledgeBase.class, knowledgeBase);
        String username = SecurityUtils.getLoginUser().getUsername();
        Date nowDate = DateUtils.getNowDate();
        int result;
        base.setFileTags(String.join(",", knowledgeBase.getFileTags()));
        analyseFile(base);
        if (base.getId() != null) {
            base.setUpdateBy(username);
            base.setUpdateTime(nowDate);
            result = knowledgeBaseMapper.updateData(base);
        } else {
            base.setCreateBy(username);
            base.setCreateTime(nowDate);
            result = knowledgeBaseMapper.saveData(base);
        }
        if (result != 0) {
            updatePathMap(base);
        }
        return true;
    }

    @Override
    public boolean deleteData(List<Integer> ids) {
        if (ids== null || ids.isEmpty()) {
            return false;
        }
        for (Integer id : ids) {
            KnowledgeBase knowledgeBase = new KnowledgeBase();
            knowledgeBase.setId(id);
            knowledgeBase.setDelFlag("1");
            knowledgeBaseMapper.updateData(knowledgeBase);
        }
        return true;
    }

    @Override
    public List<File> exportKnowLedgeBases() {
        List<File> result;
        List<String> paths = knowledgeBaseMapper.selectFilePaths();
        if (paths != null && !paths.isEmpty()) {
            // 去重
            Set<String> setPaths = new HashSet<>(paths);
            result = new ArrayList<>(paths.size());
            for (String path : setPaths) {
                if (StrUtil.isNotBlank(path)) {
                    File avatarFile = new File(buildFiePath(path));
                    if (avatarFile.exists() && avatarFile.isFile()) {
                        result.add(avatarFile);
                    }
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    private void updatePathMap(KnowledgeBase... knowledgeBases) {
        for (KnowledgeBase knowledgeBase : knowledgeBases) {
            Integer id = knowledgeBase.getId();
            String filePath = knowledgeBase.getFilePath();
            if (id == null) {
                id = knowledgeBaseMapper.selectIdByPath(filePath);
            }
            DataCostant.FILE_PATH_ID.put(buildFiePath(filePath), id);
        }
    }

    private String buildFiePath(String rowFilePath) {
        if (rowFilePath.startsWith("/profile/upload")) {
            rowFilePath = RuoYiConfig.getUploadPath() + rowFilePath.replaceFirst("/profile/upload", "");
        } else if (rowFilePath.startsWith("/profile/avatar")) {
            rowFilePath = RuoYiConfig.getAvatarPath() + rowFilePath.replaceFirst("/profile/avatar", "");
        } else {
            rowFilePath = RuoYiConfig.getOtherAvatarPath() + rowFilePath.replaceFirst("/profile/otherAvatar", "");
        }
        return rowFilePath;
    }

    private void analyseFile(KnowledgeBase base) {
        // 获取原文
        File file = new File(buildFiePath(base.getFilePath()));
        try {
            String content = DocumentParser.parse(file);
            base.setFileContent(content);
            // 获取分词
            List<String> terms = ChineseTokenizer.tokenize(content);
            base.setFileTokenizer(String.join(" ", terms));
        } catch (Exception e) {
            logger.error("文件解析失败，{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
