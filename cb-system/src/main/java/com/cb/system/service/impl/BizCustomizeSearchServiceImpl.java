package com.cb.system.service.impl;

import java.util.List;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.BizCustomizeSearchMapper;
import com.cb.system.domain.BizCustomizeSearch;
import com.cb.system.service.IBizCustomizeSearchService;

/**
 * 自定义查询方案Service业务层处理
 *
 * @author hujilie
 * @date 2023-11-27
 */
@Service
public class BizCustomizeSearchServiceImpl implements IBizCustomizeSearchService
{
    @Autowired
    private BizCustomizeSearchMapper bizCustomizeSearchMapper;

    /**
     * 查询自定义查询方案
     *
     * @param id 自定义查询方案ID
     * @return 自定义查询方案
     */
    @Override
    public BizCustomizeSearch selectBizCustomizeSearchById(Integer id)
    {
        return bizCustomizeSearchMapper.selectBizCustomizeSearchById(id);
    }

    /**
     * 查询自定义查询方案列表
     *
     * @param bizCustomizeSearch 自定义查询方案
     * @return 自定义查询方案
     */
    @Override
    public List<BizCustomizeSearch> selectBizCustomizeSearchList(BizCustomizeSearch bizCustomizeSearch)
    {
        if(bizCustomizeSearch.getUserId() == null || bizCustomizeSearch.getUserId() == 0) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            Long userId = user.getUserId();
            bizCustomizeSearch.setUserId(userId);
        }
        return bizCustomizeSearchMapper.selectBizCustomizeSearchList(bizCustomizeSearch);
    }

    /**
     * 新增自定义查询方案
     *
     * @param bizCustomizeSearch 自定义查询方案
     * @return 结果
     */
    @Override
    public int insertBizCustomizeSearch(BizCustomizeSearch bizCustomizeSearch)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long userId = user.getUserId();
        bizCustomizeSearch.setUserId(userId);
        return bizCustomizeSearchMapper.insertBizCustomizeSearch(bizCustomizeSearch);
    }

    /**
     * 修改自定义查询方案
     *
     * @param bizCustomizeSearch 自定义查询方案
     * @return 结果
     */
    @Override
    public int updateBizCustomizeSearch(BizCustomizeSearch bizCustomizeSearch)
    {
        return bizCustomizeSearchMapper.updateBizCustomizeSearch(bizCustomizeSearch);
    }

    /**
     * 批量删除自定义查询方案
     *
     * @param ids 需要删除的自定义查询方案ID
     * @return 结果
     */
    @Override
    public int deleteBizCustomizeSearchByIds(Integer[] ids)
    {
        return bizCustomizeSearchMapper.deleteBizCustomizeSearchByIds(ids);
    }

    /**
     * 删除自定义查询方案信息
     *
     * @param id 自定义查询方案ID
     * @return 结果
     */
    @Override
    public int deleteBizCustomizeSearchById(Integer id)
    {
        return bizCustomizeSearchMapper.deleteBizCustomizeSearchById(id);
    }
}
