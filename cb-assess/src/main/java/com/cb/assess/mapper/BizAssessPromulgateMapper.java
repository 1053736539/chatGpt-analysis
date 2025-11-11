package com.cb.assess.mapper;

import java.util.List;

import com.cb.assess.domain.BizAssessPromulgate;
import org.apache.ibatis.annotations.Param;

/**
 * 考核公示Mapper接口
 *
 * @author ouyang
 * @date 2023-12-29
 */
public interface BizAssessPromulgateMapper {
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
     * 删除考核公示
     *
     * @param id 考核公示ID
     * @return 结果
     */
    public int deleteBizAssessPromulgateById(String id);

    /**
     * 批量删除考核公示
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessPromulgateByIds(String[] ids);


    public boolean checkExist(BizAssessPromulgate bizAssessPromulgate);


    public List<BizAssessPromulgate> selectPromulgateList();

    public int changePromulgateStatus(String id);

    public int batchChangePromulgateStatus(@Param("list") List<String> list);
}
