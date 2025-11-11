package com.cb.knowledge.base.mapper;

import com.cb.knowledge.base.domain.entity.KnowledgeBase;

import java.util.List;
import java.util.Map;

/**
 * @author 李融业
 * @version 1.0
 * @CreateTime 2025/5/15 20:52
 * @Copyright (c) 2025
 * @Description 知识库mapper
 */
public interface KnowledgeBaseMapper {
    /**
     * @description 查询知识库
     * @param data
     * @return List<KnowledgeBase>
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    List<KnowledgeBase> selectKnowledgeList(KnowledgeBase data);

    /**
     * @description 查询标签列表
     * @param fileType
     * @return List<String>
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    List<String> selectTags(String fileType);

    /**
     * @description 查询知识库
     * @param id
     * @return KnowledgeBase
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    KnowledgeBase selectById(Integer id);

    /**
     * @description 根据路径获取Id
     * @param path
     * @return Integer
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    Integer selectIdByPath(String path);

    /**
     * @description 批量保存
     * @param datas
     * @return int
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    int batchSave(List<KnowledgeBase> lists);

    /**
     * @description 单个保存
     * @param data
     * @return int
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    int saveData(KnowledgeBase data);

    /**
     * @description 单个更新
     * @param data
     * @return int
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    int updateData(KnowledgeBase data);

    /**
     * @description 批量删除
     * @param ids
     * @return int
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    int batchDelete(List<Integer> ids);

    /**
     * @description 删除
     * @param id
     * @return int
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    int delete(Integer id);

    /**
     * @description 导出知识库数据
     * @param params
     * @return List<Map<String, Object>>
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    List<KnowledgeBase> exportKnowledgeData(Map<String, Object> params);

    /**
     * @description 获取文件路径
     * @return List<String>
     * @createtime 2025/5/16 下午2:15
     * @author 李融业
     * @version 1.0
     */
    List<String> selectFilePaths();
}
