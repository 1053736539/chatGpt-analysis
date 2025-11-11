package com.cb.oa.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.utils.DateUtils;
import com.cb.oa.domain.SysDeptOut;
import com.cb.oa.mapper.SysDeptOutMapper;
import com.cb.oa.service.ISysDeptOutService;
import com.cb.oa.service.OAService;
import com.cb.oa.vo.DeptItem;
import com.cb.oa.vo.ListResp;
import com.cb.system.service.ISysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * oa系统的部门列Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-01
 */
@Service
public class SysDeptOutServiceImpl implements ISysDeptOutService
{
    @Autowired
    private SysDeptOutMapper sysDeptOutMapper;

    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private OAService oaService;
    /**
     * 查询oa系统的部门列
     * 
     * @param id oa系统的部门列ID
     * @return oa系统的部门列
     */
    @Override
    public SysDeptOut selectSysDeptOutById(String id)
    {
        return sysDeptOutMapper.selectSysDeptOutById(id);
    }

    /**
     * 根据我们系统内的部门id查询oa的部门id
     * @param
     * @return
     */
    @Override
    public SysDeptOut selectOaDeptIdByDeptId(String deptId)
    {
        return  sysDeptOutMapper.selectSysDeptOutByParam(deptId,null);
    }
    /**
     * 根据oa的部门id查询我们的id
     * @param
     * @return
     */
    @Override
    public SysDept selectDeptIdByOaDeptId(String oaDeptId)
    {
        SysDeptOut sysDeptOut = sysDeptOutMapper.selectSysDeptOutByParam(null,oaDeptId);
        return sysDeptOut==null?null:sysDeptService.selectDeptById(sysDeptOut.getDeptId());
    }

    /**
     * 查询oa系统的部门列列表
     * 
     * @param sysDeptOut oa系统的部门列
     * @return oa系统的部门列
     */
    @Override
    public List<SysDeptOut> selectSysDeptOutList(SysDeptOut sysDeptOut)
    {
        return sysDeptOutMapper.selectSysDeptOutList(sysDeptOut);
    }

    @Override
    public Integer syncSysDeptOutList()
    {
        ListResp<DeptItem> deptItemListResp = oaService.listDept();
        List<DeptItem> data = deptItemListResp.getData();
        List<SysDeptOut> sysDeptOuts = selectSysDeptOutList(null);
        //
        SysDept sysDept1 = new SysDept();
        List<SysDept> sysDepts = sysDeptService.selectDeptListNoScope(sysDept1);
        Map<String, SysDept> deptNameMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getDeptName, o -> o));
        Integer count=0;
        //本地已有的部门列表
        Map<String, SysDeptOut> collect = sysDeptOuts.stream().collect(Collectors.toMap(SysDeptOut::getId, o -> o));
        if (null!=data && !data.isEmpty()){
            for (DeptItem datum : data) {
                if (null!=datum){
                    SysDeptOut sysDeptOut1 = new SysDeptOut();
                    BeanUtils.copyProperties(datum,sysDeptOut1);
                    SysDept sysDept = deptNameMap.get(datum.getName());
                    if (null!=sysDept) sysDeptOut1.setDeptId(sysDept.getDeptId());
                    //已有就更新，没有就创建
                    if (null!=collect.get(datum.getId())){
                        sysDeptOut1.setUpdateTime(new Date());
                        count+=updateSysDeptOut(sysDeptOut1);
                    }else {
                        sysDeptOut1.setCreateTime(new Date());
                        count+=insertSysDeptOut(sysDeptOut1);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 新增oa系统的部门列
     * 
     * @param sysDeptOut oa系统的部门列
     * @return 结果
     */
    @Override
    public int insertSysDeptOut(SysDeptOut sysDeptOut)
    {
        sysDeptOut.setCreateTime(DateUtils.getNowDate());
        return sysDeptOutMapper.insertSysDeptOut(sysDeptOut);
    }

    /**
     * 修改oa系统的部门列
     * 
     * @param sysDeptOut oa系统的部门列
     * @return 结果
     */
    @Override
    public int updateSysDeptOut(SysDeptOut sysDeptOut)
    {
        sysDeptOut.setUpdateTime(DateUtils.getNowDate());
        return sysDeptOutMapper.updateSysDeptOut(sysDeptOut);
    }

    /**
     * 批量删除oa系统的部门列
     * 
     * @param ids 需要删除的oa系统的部门列ID
     * @return 结果
     */
    @Override
    public int deleteSysDeptOutByIds(String[] ids)
    {
        return sysDeptOutMapper.deleteSysDeptOutByIds(ids);
    }

    /**
     * 删除oa系统的部门列信息
     * 
     * @param id oa系统的部门列ID
     * @return 结果
     */
    @Override
    public int deleteSysDeptOutById(String id)
    {
        return sysDeptOutMapper.deleteSysDeptOutById(id);
    }
}
