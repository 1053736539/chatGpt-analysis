package com.cb.task.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.task.domain.BizTaskDocType;
import com.cb.task.mapper.BizTaskDocTypeMapper;
import com.cb.task.service.IBizTaskDocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务公文类型Service业务层处理
 * 
 * @author yangxin
 * @date 2023-11-11
 */
@Service
public class BizTaskDocTypeServiceImpl implements IBizTaskDocTypeService 
{
    @Autowired
    private BizTaskDocTypeMapper bizTaskDocTypeMapper;

    /**
     * 查询任务公文类型
     * 
     * @param id 任务公文类型ID
     * @return 任务公文类型
     */
    @Override
    public BizTaskDocType selectBizTaskDocTypeById(Long id)
    {
        return bizTaskDocTypeMapper.selectBizTaskDocTypeById(id);
    }

    @Override
    public BizTaskDocType selectBizTaskDocTypeByCode(String code) {
        return bizTaskDocTypeMapper.selectBizTaskDocTypeByCode(code);
    }

    /**
     * 查询任务公文类型列表
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 任务公文类型
     */
    @Override
    public List<BizTaskDocType> selectBizTaskDocTypeList(BizTaskDocType bizTaskDocType)
    {
        return bizTaskDocTypeMapper.selectBizTaskDocTypeList(bizTaskDocType);
    }

    @Override
    public List<BizTaskDocType> listMy(BizTaskDocType bizTaskDocType,Long deptId) {
        bizTaskDocType.setDeptId(deptId);
        return bizTaskDocTypeMapper.listMy(bizTaskDocType);
    }

    /**
     * 新增任务公文类型
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 结果
     */
    @Override
    public int insertBizTaskDocType(BizTaskDocType bizTaskDocType)
    {
        bizTaskDocType.setCreateTime(DateUtils.getNowDate());
        try{
            bizTaskDocType.setCreateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        return bizTaskDocTypeMapper.insertBizTaskDocType(bizTaskDocType);
    }

    /**
     * 修改任务公文类型
     * 
     * @param bizTaskDocType 任务公文类型
     * @return 结果
     */
    @Override
    public int updateBizTaskDocType(BizTaskDocType bizTaskDocType)
    {
        bizTaskDocType.setUpdateTime(DateUtils.getNowDate());
        try{
            bizTaskDocType.setUpdateBy(SecurityUtils.getUsername());
        } catch (Exception e){}
        return bizTaskDocTypeMapper.updateBizTaskDocType(bizTaskDocType);
    }

    /**
     * 批量删除任务公文类型
     * 
     * @param ids 需要删除的任务公文类型ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskDocTypeByIds(Long[] ids)
    {
//        return bizTaskDocTypeMapper.deleteBizTaskDocTypeByIds(ids);
        return bizTaskDocTypeMapper.vDeleteByIds(ids);
    }

    /**
     * 删除任务公文类型信息
     * 
     * @param id 任务公文类型ID
     * @return 结果
     */
    @Override
    public int deleteBizTaskDocTypeById(Long id)
    {
//        return bizTaskDocTypeMapper.deleteBizTaskDocTypeById(id);
        return bizTaskDocTypeMapper.vDeleteByIds(new Long[]{ id });
    }
}
