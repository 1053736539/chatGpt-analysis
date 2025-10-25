package com.cb.system.service.impl;

import java.util.Date;
import java.util.List;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.entity.SysUserEmploy;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.UUID;
import com.cb.system.service.ISysDictDataService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.SysUserEmployMapper;
import com.cb.system.service.ISysUserEmployService;

/**
 * 任免和聘用信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-09
 */
@Service
public class SysUserEmployServiceImpl implements ISysUserEmployService
{
    @Autowired
    private SysUserEmployMapper sysUserEmployMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询任免和聘用信息
     *
     * @param id 任免和聘用信息ID
     * @return 任免和聘用信息
     */
    @Override
    public SysUserEmploy selectSysUserEmployById(String id)
    {
        return sysUserEmployMapper.selectSysUserEmployById(id);
    }

    /**
     * 查询任免和聘用信息列表
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 任免和聘用信息
     */
    @Override
    public List<SysUserEmploy> selectSysUserEmployList(SysUserEmploy sysUserEmploy)
    {
        return sysUserEmployMapper.selectSysUserEmployList(sysUserEmploy);
    }

    /**
     * 新增任免和聘用信息
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 结果
     */
    @Override
    public int insertSysUserEmploy(SysUserEmploy sysUserEmploy)
    {
        sysUserEmploy.setId(UUID.randomUUID().toString());
        sysUserEmploy.setCreateTime(new Date());
        sysUserEmploy.setDelFlag("0");
        sysUserEmploy.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        sysUserEmploy.setCreateTime(DateUtils.getNowDate());
        int rst = sysUserEmployMapper.insertSysUserEmploy(sysUserEmploy);
        if(rst > 0) {
            updateUserPostInfo(sysUserEmploy);
        }
        return rst;
    }

    /**
     * 修改任免和聘用信息
     *
     * @param sysUserEmploy 任免和聘用信息
     * @return 结果
     */
    @Override
    public int updateSysUserEmploy(SysUserEmploy sysUserEmploy)
    {
        sysUserEmploy.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        sysUserEmploy.setUpdateTime(DateUtils.getNowDate());
        int rst = sysUserEmployMapper.updateSysUserEmploy(sysUserEmploy);
        if(rst > 0) {
            updateUserPostInfo(sysUserEmploy);
        }
        return rst;
    }

    private void updateUserPostInfo(SysUserEmploy sysUserEmploy) {
        if(sysUserEmploy.getStatus().equals("2")) {
            SysUser sysUser = sysUserService.selectUserById(Long.parseLong(sysUserEmploy.getUserId()));
            if(sysUser != null) {
                sysUser.setWorkPost(sysUserEmploy.getDutiesAfter());
                sysUser.setWorkPostTime(sysUserEmploy.getChangeTime());
                sysUser.setSameWorkPostTime(sysUserEmploy.getEqualChangeTime());
                String code = sysDictDataService.selectDictLabel("duties_code", sysUserEmploy.getDutiesAfter());
                sysUser.setWorkPostCode(code);
                sysUserService.updateUser(sysUser);
            }
            SysUserEmploy temp = new SysUserEmploy();
            temp.setUserId(sysUserEmploy.getUserId());
            temp.setStatus("2");
            temp.setType("1");
            List<SysUserEmploy> employList = selectSysUserEmployList(temp);
            for(SysUserEmploy employ : employList) {
                if(!employ.getId().equals(sysUserEmploy.getId())) {
                    employ.setType("2");
                    employ.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
                    employ.setUpdateTime(DateUtils.getNowDate());
                    sysUserEmployMapper.updateSysUserEmploy(employ);
                }
            }
        }
    }

    /**
     * 批量删除任免和聘用信息
     *
     * @param ids 需要删除的任免和聘用信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserEmployByIds(String[] ids)
    {
        return sysUserEmployMapper.deleteSysUserEmployByIds(ids);
    }

    /**
     * 删除任免和聘用信息信息
     *
     * @param id 任免和聘用信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserEmployById(String id)
    {
        return sysUserEmployMapper.deleteSysUserEmployById(id);
    }

    @Override
    public int disableEmployById(String id) {
        int rst = 0;
        SysUserEmploy sysUserEmploy = selectSysUserEmployById(id);
        if(sysUserEmploy != null) {
            sysUserEmploy.setType("2");
            sysUserEmploy.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
            sysUserEmploy.setUpdateTime(DateUtils.getNowDate());
            rst = sysUserEmployMapper.updateSysUserEmploy(sysUserEmploy);
            if(rst > 0) {
                SysUser sysUser = sysUserService.selectUserById(Long.parseLong(sysUserEmploy.getUserId()));
                if(sysUser != null && sysUser.getWorkPost().equals(sysUserEmploy.getDutiesAfter())) {
                    sysUser.setWorkPost("");
                    sysUser.setWorkPostTime("");
                    sysUser.setSameWorkPostTime("");
                    sysUser.setWorkPostCode("");
                    sysUserService.updateUser(sysUser);
                }
            }
        }
        return rst;
    }
}
