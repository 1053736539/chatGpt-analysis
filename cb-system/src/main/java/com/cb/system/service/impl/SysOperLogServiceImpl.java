package com.cb.system.service.impl;

import com.cb.common.annotation.EncryptMethod;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.enums.EODType;
import com.cb.common.exception.CustomException;
import com.cb.system.domain.SysOperLog;
import com.cb.system.mapper.SysOperLogMapper;
import com.cb.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;


    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    @EncryptMethod
    public void insertOperlog(SysOperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    @EncryptMethod(businessType= EODType.DECRYPT)
    public List<SysOperLog> selectOperLogList(SysOperLog operLog, SysUser user,Boolean isOpenThreeMember) {
        if (isOpenThreeMember) {
            if(user.isAdmin()){
                return operLogMapper.selectOperLogList(operLog,"00");
            } else if (user.isSecAdmin()) {
                return operLogMapper.selectOperLogList(operLog,"01");
            } else if (user.isAudAdmin()) {
                return operLogMapper.selectOperLogList(operLog,"01,02");
            } else {
                throw new CustomException("您没有日志查看权限");
            }
        }
        return operLogMapper.selectOperLogList(operLog,null);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return operLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    @EncryptMethod(businessType= EODType.DECRYPT)
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}
