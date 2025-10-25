package com.cb.system.service.impl;

import java.util.List;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.RsInfoMapper;
import com.cb.common.core.domain.entity.RsInfo;
import com.cb.system.service.IRsInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 档案系统用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
@Service
public class RsInfoServiceImpl implements IRsInfoService 
{
    private static final Logger log = LoggerFactory.getLogger(RsInfoServiceImpl.class);
    @Autowired
    private RsInfoMapper rsInfoMapper;
    @Autowired
    private SysUserMapper userMapper;
    /**
     * 查询档案系统用户信息
     * 
     * @param id 档案系统用户信息ID
     * @return 档案系统用户信息
     */
    @Override
    public RsInfo selectRsInfoById(Integer id)
    {
        return rsInfoMapper.selectRsInfoById(id);
    }

    /**
     * 查询档案系统用户信息列表
     * 
     * @param rsInfo 档案系统用户信息
     * @return 档案系统用户信息
     */
    @Override
    public List<RsInfo> selectRsInfoList(RsInfo rsInfo)
    {
        return rsInfoMapper.selectRsInfoList(rsInfo);
    }

    /**
     * 新增档案系统用户信息
     * 
     * @param rsInfo 档案系统用户信息
     * @return 结果
     */
    @Override
    public int insertRsInfo(RsInfo rsInfo)
    {
        // 验证是否存在这个用户
        String userName= PinyinUtil.getPinyin(rsInfo.getXm()).replaceAll(" ","");
        SysUser u = userMapper.selectUserByUserName(userName);
        StringBuilder failureMsg = new StringBuilder();
        if (StringUtils.isNull(u)) {
            failureMsg.append("<br/>"+"、用户 " + rsInfo.getXm() + " 不存在");
        } else {
            rsInfo.setCreateBy(SecurityUtils.getUsername());
            rsInfo.setCreateTime(DateUtils.getNowDate());
            rsInfo.setUserId(Integer.valueOf(u.getUserId().toString()));
        }
            return rsInfoMapper.insertRsInfo(rsInfo);
    }

    /**
     * 修改档案系统用户信息
     * 
     * @param rsInfo 档案系统用户信息
     * @return 结果
     */
    @Override
    public int updateRsInfo(RsInfo rsInfo)
    {
        rsInfo.setUpdateBy(SecurityUtils.getUsername());
        rsInfo.setUpdateTime(DateUtils.getNowDate());
        // 验证是否存在这个用户
        String userName= PinyinUtil.getPinyin(rsInfo.getXm()).replaceAll(" ","");
        SysUser u = userMapper.selectUserByUserName(userName);
        StringBuilder failureMsg = new StringBuilder();
        if (StringUtils.isNull(u)) {
            failureMsg.append("<br/>"+"、用户 " + rsInfo.getXm() + " 不存在");
        } else {
            rsInfo.setUserId(Integer.valueOf(u.getUserId().toString()));
        }
        return rsInfoMapper.updateRsInfo(rsInfo);
    }

    /**
     * 批量删除档案系统用户信息
     * 
     * @param ids 需要删除的档案系统用户信息ID
     * @return 结果
     */
    @Override
    public int deleteRsInfoByIds(Integer[] ids)
    {
        return rsInfoMapper.deleteRsInfoByIds(ids);
    }

    /**
     * 删除档案系统用户信息信息
     * 
     * @param id 档案系统用户信息ID
     * @return 结果
     */
    @Override
    public int deleteRsInfoById(Integer id)
    {
        return rsInfoMapper.deleteRsInfoById(id);
    }

    @Override
    public String importRsInfo(List<RsInfo> rsInfoList, String operName) {
        if (StringUtils.isNull(rsInfoList) || rsInfoList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (RsInfo rsInfo : rsInfoList) {
            try {
                // 验证是否存在这个用户
                String userName=rsInfo.getXmpy().replaceAll(" ","");
                SysUser u = userMapper.selectUserByUserName(userName);
                if (StringUtils.isNull(u)) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用户ID " + rsInfo.getXm() + " 不存在");
                } else {
                    rsInfo.setCreateBy(operName);
                    rsInfo.setCreateTime(DateUtils.getNowDate());
                    rsInfo.setUserId(Integer.valueOf(u.getUserId().toString()));
                    this.importRsInfo(rsInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户 " + rsInfo.getXm() + " 导入成功");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、用户 " + rsInfo.getXm() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    @Transactional
    public int importRsInfo(RsInfo rsInfo) {
        // 导入新增档案目录信息
        int rows = rsInfoMapper.insertRsInfo(rsInfo);
        return rows;
    }
}
