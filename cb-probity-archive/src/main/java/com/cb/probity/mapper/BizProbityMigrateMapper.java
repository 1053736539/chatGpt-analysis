package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityMigrate;

/**
 * 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityMigrateMapper {
    /**
     * 查询廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param id 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况ID
     * @return 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     */
    public BizProbityMigrate selectBizProbityMigrateById(String id);

    /**
     * 查询廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况列表
     *
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况集合
     */
    public List<BizProbityMigrate> selectBizProbityMigrateList(BizProbityMigrate bizProbityMigrate);

    /**
     * 新增廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 结果
     */
    public int insertBizProbityMigrate(BizProbityMigrate bizProbityMigrate);


    /**
     * 批量新增廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityMigrate> entities);

    /**
     * 修改廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param bizProbityMigrate 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     * @return 结果
     */
    public int updateBizProbityMigrate(BizProbityMigrate bizProbityMigrate);

    /**
     * 删除廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param id 廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况ID
     * @return 结果
     */
    public int deleteBizProbityMigrateById(String id);

    /**
     * 批量删除廉政档案-8.配偶、子女及其配偶移居、居住国（境）外及港澳台的情况
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityMigrateByIds(String[] ids);
}
