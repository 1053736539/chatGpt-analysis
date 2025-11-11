package com.cb.activiti.service;

import java.util.List;

import com.cb.activiti.domain.BizLeave;
import com.cb.common.core.domain.vo.ImportLeaveVo;

/**
 * 请假Service接口
 * 
 * @author 一只闲鹿
 * @date 2020-11-29
 */
public interface IBizLeaveService 
{
    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    public BizLeave selectBizLeaveById(Long id);

    /**
     * 查询请假列表
     * 
     * @param bizLeave 请假
     * @return 请假集合
     */
    public List<BizLeave> selectBizLeaveList(BizLeave bizLeave);

    /**
     * 新增请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    public int insertBizLeave(BizLeave bizLeave);

    /**
     * 修改请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    public int updateBizLeave(BizLeave bizLeave);

    /**
     * 批量删除请假
     * 
     * @param ids 需要删除的请假ID
     * @return 结果
     */
    public int deleteBizLeaveByIds(Long[] ids);

    /**
     * 删除请假信息
     * 
     * @param id 请假ID
     * @return 结果
     */
    public int deleteBizLeaveById(Long id);

    /**
     * 查看查询病假列表
     * @param bizLeave
     * @return
     */
    List<BizLeave> listByTypeOrHospital(BizLeave bizLeave);
    /**
     * 查询请假列表 区分个人和组织部管理员
     *
     * @param bizLeave 请假
     * @return 请假集合
     */
    List<BizLeave> selectAllBizLeaveList(BizLeave bizLeave);

    /**
     * 查询请假数据 区分个人和组织部管理员
     *
     * @param bizLeave 请假
     * @return 请假集合
     */
    List<BizLeave> selectHistoryBizLeaveList(BizLeave bizLeave);

    /**
     * 导入请假数据
     * @param leaveList
     * @param operName
     * @return
     */
    public String importLeaveList(List<ImportLeaveVo> leaveList, String operName);

    /**
     * 导入请假数据新增到数据库
     * @param bizLeave
     * @return
     */
    public int  insertImportBizLeave(BizLeave bizLeave);

    /**
     * 查询已经审核通过的请假记录
     * @param bizLeave
     * @return
     */
    List<BizLeave> selectPassedBizLeaveList(BizLeave bizLeave);
}
