package com.cb.assess.service;

import java.util.List;

import com.cb.assess.domain.BizAssessPromulgate;

/**
 * 考核公示Service接口
 *
 * @author ouyang
 * @date 2023-12-29
 */
public interface IBizAssessPromulgateService {
    /**
     * 查询考核公示
     *
     * @param id 考核公示ID
     * @return 考核公示
     */
    public BizAssessPromulgate selectBizAssessPromulgateById(String id);

    /**
     * 查询考核公示列表
     *
     * @param bizAssessPromulgate 考核公示
     * @return 考核公示集合
     */
    public List<BizAssessPromulgate> selectBizAssessPromulgateList(BizAssessPromulgate bizAssessPromulgate);

    /**
     * 新增考核公示
     *
     * @param bizAssessPromulgate 考核公示
     * @return 结果
     */
    public int insertBizAssessPromulgate(BizAssessPromulgate bizAssessPromulgate);

    /**
     * 修改考核公示
     *
     * @param bizAssessPromulgate 考核公示
     * @return 结果
     */
    public int updateBizAssessPromulgate(BizAssessPromulgate bizAssessPromulgate);

    /**
     * 批量删除考核公示
     *
     * @param ids 需要删除的考核公示ID
     * @return 结果
     */
    public int deleteBizAssessPromulgateByIds(String[] ids);

    /**
     * 删除考核公示信息
     *
     * @param id 考核公示ID
     * @return 结果
     */
    public int deleteBizAssessPromulgateById(String id);


    public Boolean checkExist(BizAssessPromulgate bizAssessPromulgate);

    public List<BizAssessPromulgate> selectPromulgateList();

    public int changePromulgateStatus(String id);

    public int batchChangePromulgateStatus(List<String> ids);
}
