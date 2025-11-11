package com.cb.knowledge.base.service;

import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.domain.vo.KnowledgeBaseVo;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/16 20:49
 * @Copyright (c) 2025
 * @Description 知识库服务层
 */
public interface IKnowledgeBaseService {
    /**
     * @description 查询知识库列表
     * @param knowledgeBase
     * @return
     */
    List<KnowledgeBase> selectDataList(KnowledgeBase knowledgeBase);

    /**
     * @description 查询标签列表
     * @param fileType
     * @return List<String>
     */
    Set<String> getFileTags(String fileType);

    /**
     * @description 查询知识库文件
     * @param knowledgeBase
     * @return
     */
    KnowledgeBase selectData(Integer id);

    /**
     * @description 保存知识库
     * @param knowledgeBase
     * @return
     */
    boolean saveData(List<KnowledgeBaseVo> knowledgeBases);

    /**
     * @description 修改知识库
     * @param knowledgeBase
     * @return
     */
    boolean updateData(KnowledgeBaseVo knowledgeBase);

    /**
     * @description 删除知识库
     * @param knowledgeBase
     * @return
     */
    boolean deleteData(List<Integer> ids);

    /**
     * @description 导出 知识库文件
     * @return List<File>
     * @createtime 2025/5/10 下午5:48
     * @author 李融业
     * @version 1.0
     */
    List<File> exportKnowLedgeBases();
}
