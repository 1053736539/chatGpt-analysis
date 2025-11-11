package com.cb.service;

import com.cb.domain.TransferringStudentHealth;

import java.util.List;

/**
 * 监督管理-身心健康情况Service接口
 *
 * @author yangxin
 * @date 2024-08-27
 */
public interface ITransferringStudentHealthService
{
    /**
     * 查询监督管理-身心健康情况
     *
     * @param id 监督管理-身心健康情况ID
     * @return 监督管理-身心健康情况
     */
    public TransferringStudentHealth selectTransferringStudentHealthById(String id);

    /**
     * 查询监督管理-身心健康情况列表
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 监督管理-身心健康情况集合
     */
    public List<TransferringStudentHealth> selectTransferringStudentHealthList(TransferringStudentHealth transferringStudentHealth);

    /**
     * 新增监督管理-身心健康情况
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 结果
     */
    public int insertTransferringStudentHealth(TransferringStudentHealth transferringStudentHealth);

    /**
     * 修改监督管理-身心健康情况
     *
     * @param transferringStudentHealth 监督管理-身心健康情况
     * @return 结果
     */
    public int updateTransferringStudentHealth(TransferringStudentHealth transferringStudentHealth);

    /**
     * 批量删除监督管理-身心健康情况
     *
     * @param ids 需要删除的监督管理-身心健康情况ID
     * @return 结果
     */
    public int deleteTransferringStudentHealthByIds(String[] ids);

    /**
     * 删除监督管理-身心健康情况信息
     *
     * @param id 监督管理-身心健康情况ID
     * @return 结果
     */
    public int deleteTransferringStudentHealthById(String id);
}