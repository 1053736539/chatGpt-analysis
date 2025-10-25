package com.cb.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.cb.common.core.domain.entity.UserDeptPost;
import com.cb.common.utils.StringUtils;
import com.cb.system.domain.vo.DeptPostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.system.mapper.SysUserDeptPostMapper;
import com.cb.system.service.ISysUserDeptPostService;

/**
 * userDeptService业务层处理
 *
 * @author ruoyi
 * @date 2023-10-23
 */
@Service
public class SysUserDeptPostServiceImpl implements ISysUserDeptPostService {
    @Autowired
    private SysUserDeptPostMapper userDeptMapper;

    @Override
    public List<UserDeptPost> selectByUserId(Long userId) {
        return userDeptMapper.selectByUserId(userId);
    }

    @Override
    public List<DeptPostVo> selectAssociationInfo(Long userId,Long deptId) {
        List<DeptPostVo> deptPostVos = userDeptMapper.selectAssociationInfo(userId);
        return deptPostVos.stream().map(item -> {
            if(item.getDeptId().equals(deptId)) item.setOnline(true);
            String deptName = item.getDeptName();
            String workPost = item.getWorkPost();
            String workTitle = item.getWorkTitle();
            StringBuilder builder = new StringBuilder();
            builder.append(deptName);
            if(StringUtils.isNotBlank(workPost)) {
                builder.append(" " +workPost);
            }
            if(StringUtils.isNotBlank(workTitle)){
                builder.append("(" +workTitle +")");
            }
            item.setJobDeions(builder.toString());
            return item;
        }).collect(Collectors.toList());
    }
}
