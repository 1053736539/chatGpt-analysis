package com.cb.system.service;

import java.util.List;

import com.cb.common.core.domain.entity.UserDeptPost;
import com.cb.system.domain.vo.DeptPostVo;

/**
 * userDeptService接口
 *
 * @author ruoyi
 * @date 2023-10-23
 */
public interface ISysUserDeptPostService {
    public List<UserDeptPost> selectByUserId(Long userId);

    public List<DeptPostVo> selectAssociationInfo(Long userId,Long deptId);
}
