package com.cb.system.mapper;

import java.util.List;
import com.cb.system.domain.BizCustomizeSearch;

/**
 * 自定义查询方案Mapper接口
 * 
 * @author hujilie
 * @date 2023-11-27
 */
public interface BizCustomizeSearchMapper 
{
    /**
     * 查询自定义查询方案
     * 
     * @param id 自定义查询方案ID
     * @return 自定义查询方案
     */
    public BizCustomizeSearch selectBizCustomizeSearchById(Integer id);

    /**
     * 查询自定义查询方案列表
     * 
     * @param bizCustomizeSearch 自定义查询方案
     * @return 自定义查询方案集合
     */
    public List<BizCustomizeSearch> selectBizCustomizeSearchList(BizCustomizeSearch bizCustomizeSearch);

    /**
     * 新增自定义查询方案
     * 
     * @param bizCustomizeSearch 自定义查询方案
     * @return 结果
     */
    public int insertBizCustomizeSearch(BizCustomizeSearch bizCustomizeSearch);

    /**
     * 修改自定义查询方案
     * 
     * @param bizCustomizeSearch 自定义查询方案
     * @return 结果
     */
    public int updateBizCustomizeSearch(BizCustomizeSearch bizCustomizeSearch);

    /**
     * 删除自定义查询方案
     * 
     * @param id 自定义查询方案ID
     * @return 结果
     */
    public int deleteBizCustomizeSearchById(Integer id);

    /**
     * 批量删除自定义查询方案
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizCustomizeSearchByIds(Integer[] ids);
}
