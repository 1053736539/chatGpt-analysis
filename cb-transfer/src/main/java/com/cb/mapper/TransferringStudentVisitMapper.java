package com.cb.mapper;

import java.util.List;
import com.cb.domain.TransferringStudentVisit;

/**
 * 监督管理-走访基层Mapper接口
 * 
 * @author yangxin
 * @date 2024-08-27
 */
public interface TransferringStudentVisitMapper 
{
    /**
     * 查询监督管理-走访基层
     * 
     * @param id 监督管理-走访基层ID
     * @return 监督管理-走访基层
     */
    public TransferringStudentVisit selectTransferringStudentVisitById(String id);

    /**
     * 查询监督管理-走访基层列表
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 监督管理-走访基层集合
     */
    public List<TransferringStudentVisit> selectTransferringStudentVisitList(TransferringStudentVisit transferringStudentVisit);

    /**
     * 新增监督管理-走访基层
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 结果
     */
    public int insertTransferringStudentVisit(TransferringStudentVisit transferringStudentVisit);

    /**
     * 修改监督管理-走访基层
     * 
     * @param transferringStudentVisit 监督管理-走访基层
     * @return 结果
     */
    public int updateTransferringStudentVisit(TransferringStudentVisit transferringStudentVisit);

    /**
     * 删除监督管理-走访基层
     * 
     * @param id 监督管理-走访基层ID
     * @return 结果
     */
    public int deleteTransferringStudentVisitById(String id);

    /**
     * 批量删除监督管理-走访基层
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferringStudentVisitByIds(String[] ids);
}
