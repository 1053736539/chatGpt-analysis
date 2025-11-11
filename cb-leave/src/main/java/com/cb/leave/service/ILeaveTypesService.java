package com.cb.leave.service;

import java.util.List;
import com.cb.leave.domain.LeaveTypes;
import com.cb.leave.domain.vo.UserLeaveVo;

/**
 * 请休假类型Service接口
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
public interface ILeaveTypesService 
{
    /**
     * 查询请休假类型
     * 
     * @param id 请休假类型ID
     * @return 请休假类型
     */
    public LeaveTypes selectLeaveTypesById(Long id);

    /**
     * 查询请休假类型列表
     * 
     * @param leaveTypes 请休假类型
     * @return 请休假类型集合
     */
    public List<LeaveTypes> selectLeaveTypesList(LeaveTypes leaveTypes);

    /**
     * 新增请休假类型
     * 
     * @param leaveTypes 请休假类型
     * @return 结果
     */
    public int insertLeaveTypes(LeaveTypes leaveTypes);

    /**
     * 修改请休假类型
     * 
     * @param leaveTypes 请休假类型
     * @return 结果
     */
    public int updateLeaveTypes(LeaveTypes leaveTypes);

    /**
     * 批量删除请休假类型
     * 
     * @param ids 需要删除的请休假类型ID
     * @return 结果
     */
    public int deleteLeaveTypesByIds(Integer[] ids);

    /**
     * 删除请休假类型信息
     * 
     * @param id 请休假类型ID
     * @return 结果
     */
    public int deleteLeaveTypesById(Integer id);

    /**
     * 获取用户请休假类型以及剩余休假时间
     * @param vo
     * @return
     */
    public List<UserLeaveVo> selectUserLeaveVoList(UserLeaveVo vo);

    /**
     * 查询请休假类型id
     *
     * @param  leaveName 请休假类型
     * @return 请休假类型
     */
    public LeaveTypes selectLeaveIdByLeavName(String leaveName);
}
