package com.cb.oa.service.impl;

import java.util.List;
import java.util.Map;

import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.oa.domain.SysUserOut;
import com.cb.oa.domain.vo.SysUserOutVo;
import com.cb.oa.mapper.SysUserOutMapper;
import com.cb.oa.service.ISysUserOutService;
import com.cb.oa.service.OAService;
import com.cb.oa.vo.TableResp;
import com.cb.oa.vo.UserItem;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * oa用户Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-01
 */
@Service
public class SysUserOutServiceImpl implements ISysUserOutService {
    @Autowired
    private SysUserOutMapper sysUserOutMapper;
    @Resource
    private OAService oaService;

    @Resource
    private ISysUserService sysUserService;
    /**
     * 查询oa用户
     *
     * @param id oa用户ID
     * @return oa用户
     */
    @Override
    public SysUserOut selectSysUserOutById(String id) {
        return sysUserOutMapper.selectSysUserOutById(id);
    }

    @Override
    public SysUser selectSysUserByOaUserId(String oaUserId) {
        SysUserOut sysUserOut = sysUserOutMapper.selectSysUserOutByParam(oaUserId, null);
        if (null!=sysUserOut){
           return sysUserService.selectUserById(sysUserOut.getUserId());
        }
        return null;
    }

    @Override
    public SysUser selectSysUserByOaUserName(String oaUserName) {
        SysUserOut sysUserOut = sysUserOutMapper.selectSysUserOutByOaUserName(oaUserName);
        if (null!=sysUserOut){
            return sysUserService.selectUserById(sysUserOut.getUserId());
        }
        return null;
    }

    @Override
    public SysUserOut selectSysUserOutById(Long userId) {
        return sysUserOutMapper.selectSysUserOutByParam(null, userId);
    }

    /**
     * 查询oa用户列表
     *
     * @param sysUserOut oa用户
     * @return oa用户
     */
    @Override
    public List<SysUserOutVo> selectSysUserOutVoList(SysUserOutVo sysUserOut) {
        return sysUserOutMapper.selectSysUserOutVoList(sysUserOut);
    }
    @Override
    public List<SysUserOut> selectSysUserOutList(SysUserOut sysUserOut) {
        return sysUserOutMapper.selectSysUserOutList(sysUserOut);
    }
    @Override
    public Integer syncSysUserOutList(Integer count, Integer page, Integer pageSize, Map<String, SysUser> phoneMap, Map<String, SysUser> nameMap, Map<String, SysUserOut> sysUserOutMap) {
        TableResp<UserItem> userItemTableResp = oaService.pageUser(page, pageSize);
        if (null != userItemTableResp) {
            TableResp.TableData<UserItem> data = userItemTableResp.getData();
            if (null != data) {
                List<UserItem> records = data.getRecords();
                //以上拿到数据了，开始处理
                for (UserItem record : records) {
                    SysUserOut sysUserOut = new SysUserOut();
                    BeanUtils.copyProperties(record, sysUserOut);
                    //匹配人
                    Boolean mate = false;
                    //先从电话匹配
                    if (null != record.getPhone() && StringUtils.isNoneBlank(record.getPhone())) {
                        SysUser sysUser = phoneMap.get(record.getPhone());
                        if (null != sysUser) {
                            mate = true;
                            sysUserOut.setUserId(sysUser.getUserId());
                        }
                    }
                    //如果电话匹配不成功，则匹配姓名
                    if (!mate) {
                        if (null != record.getUsername() && StringUtils.isNoneBlank(record.getUsername())) {
                            SysUser sysUser = nameMap.get(record.getUsername());
                            if (null != sysUser) {
                                mate = true;
                                sysUserOut.setUserId(sysUser.getUserId());
                            }
                        }
                    }
                    //匹配完成
                    if (null != sysUserOutMap.get(record.getId())) {
                        //更新
                        count += updateSysUserOut(sysUserOut);
                    } else {
                        count += insertSysUserOut(sysUserOut);
                    }
                }
                //循环走完，如果分页未到尽头，则递归一下
                if (page < data.getPages()) {
                    count = syncSysUserOutList(count, page + 1, pageSize, phoneMap, nameMap, sysUserOutMap);
                }
            }
        }
        return count;
    }

    /**
     * 新增oa用户
     *
     * @param sysUserOut oa用户
     * @return 结果
     */
    @Override
    public int insertSysUserOut(SysUserOut sysUserOut) {
        sysUserOut.setCreateTime(DateUtils.getNowDate());
        return sysUserOutMapper.insertSysUserOut(sysUserOut);
    }

    /**
     * 修改oa用户
     *
     * @param sysUserOut oa用户
     * @return 结果
     */
    @Override
    public int updateSysUserOut(SysUserOut sysUserOut) {
        sysUserOut.setUpdateTime(DateUtils.getNowDate());
        return sysUserOutMapper.updateSysUserOut(sysUserOut);
    }

    /**
     * 批量删除oa用户
     *
     * @param ids 需要删除的oa用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserOutByIds(String[] ids) {
        return sysUserOutMapper.deleteSysUserOutByIds(ids);
    }

    /**
     * 删除oa用户信息
     *
     * @param id oa用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserOutById(String id) {
        return sysUserOutMapper.deleteSysUserOutById(id);
    }
}
