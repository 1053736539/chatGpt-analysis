package com.cb.leave.service.impl;

import java.util.List;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.leave.domain.vo.UserLeaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.leave.mapper.LeaveTypesMapper;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.service.ILeaveTypesService;

/**
 * 请休假类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@Service
public class LeaveTypesServiceImpl implements ILeaveTypesService 
{
    @Autowired
    private LeaveTypesMapper leaveTypesMapper;

    /**
     * 查询请休假类型
     * 
     * @param id 请休假类型ID
     * @return 请休假类型
     */
    @Override
    public LeaveTypes selectLeaveTypesById(Long id)
    {
        return leaveTypesMapper.selectLeaveTypesById(id);
    }

    /**
     * 查询请休假类型列表
     * 
     * @param leaveTypes 请休假类型
     * @return 请休假类型
     */
    @Override
    public List<LeaveTypes> selectLeaveTypesList(LeaveTypes leaveTypes)
    {
        return leaveTypesMapper.selectLeaveTypesList(leaveTypes);
    }

    /**
     * 新增请休假类型
     * 
     * @param leaveTypes 请休假类型
     * @return 结果
     */
    @Override
    public int insertLeaveTypes(LeaveTypes leaveTypes)
    {
        String username = SecurityUtils.getUsername();
        leaveTypes.setCreateBy(username);
        leaveTypes.setCreateTime(DateUtils.getNowDate());
        return leaveTypesMapper.insertLeaveTypes(leaveTypes);
    }

    /**
     * 修改请休假类型
     * 
     * @param leaveTypes 请休假类型
     * @return 结果
     */
    @Override
    public int updateLeaveTypes(LeaveTypes leaveTypes)
    {
        String username = SecurityUtils.getUsername();
        leaveTypes.setUpdateBy(username);
        leaveTypes.setUpdateTime(DateUtils.getNowDate());
        return leaveTypesMapper.updateLeaveTypes(leaveTypes);
    }

    /**
     * 批量删除请休假类型
     * 
     * @param ids 需要删除的请休假类型ID
     * @return 结果
     */
    @Override
    public int deleteLeaveTypesByIds(Integer[] ids)
    {
        return leaveTypesMapper.deleteLeaveTypesByIds(ids);
    }

    /**
     * 删除请休假类型信息
     * 
     * @param id 请休假类型ID
     * @return 结果
     */
    @Override
    public int deleteLeaveTypesById(Integer id)
    {
        return leaveTypesMapper.deleteLeaveTypesById(id);
    }

    @Override
    public List<UserLeaveVo> selectUserLeaveVoList(UserLeaveVo vo) {
        return leaveTypesMapper.selectUserLeaveVoList(vo);
    }

    @Override
    public LeaveTypes selectLeaveIdByLeavName(String leaveName) {
        return leaveTypesMapper.selectLeaveIdByLeavName(leaveName);
    }
}
