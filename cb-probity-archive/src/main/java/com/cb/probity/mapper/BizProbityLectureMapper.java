package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityLecture;

/**
 * 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityLectureMapper {
    /**
     * 查询廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param id 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得ID
     * @return 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     */
    public BizProbityLecture selectBizProbityLectureById(String id);

    /**
     * 查询廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得列表
     *
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得集合
     */
    public List<BizProbityLecture> selectBizProbityLectureList(BizProbityLecture bizProbityLecture);

    /**
     * 新增廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 结果
     */
    public int insertBizProbityLecture(BizProbityLecture bizProbityLecture);


    /**
     * 批量新增廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityLecture> entities);

    /**
     * 修改廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param bizProbityLecture 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     * @return 结果
     */
    public int updateBizProbityLecture(BizProbityLecture bizProbityLecture);

    /**
     * 删除廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param id 廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得ID
     * @return 结果
     */
    public int deleteBizProbityLectureById(String id);

    /**
     * 批量删除廉政档案-11.本人从事讲学、写作、咨询、审稿、书画等劳务所得
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityLectureByIds(String[] ids);
}
