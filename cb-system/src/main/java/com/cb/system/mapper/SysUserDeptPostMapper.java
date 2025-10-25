package com.cb.system.mapper;

import java.util.List;

import com.cb.common.core.domain.entity.UserDeptPost;
import com.cb.system.domain.SysUserDeptPost;
import com.cb.system.domain.vo.DeptPostVo;

/**
 * userDeptMapper接口
 *
 * @author ruoyi
 * @date 2023-10-23
 */
public interface SysUserDeptPostMapper {

    public List<UserDeptPost> selectByUserId(Long userId);

    public int insertDeptPost(SysUserDeptPost userDeptPost);

    public int batchDeptPost(List<SysUserDeptPost> userDeptList);

    public int deleteByUserId(Long userId);

    public int deleteByUserIds(Long[] ids);

    public List<DeptPostVo> selectAssociationInfo(Long userId);

}
