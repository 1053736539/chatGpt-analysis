package com.cb.probity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cb.probity.domain.BizProbityFamilyMember;

/**
 * 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）Mapper接口
 *
 * @author ruoyi
 * @date 2025-03-18
 */
public interface BizProbityFamilyMemberMapper {
    /**
     * 查询廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param id 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）ID
     * @return 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     */
    public BizProbityFamilyMember selectBizProbityFamilyMemberById(String id);

    /**
     * 查询廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）列表
     *
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）集合
     */
    public List<BizProbityFamilyMember> selectBizProbityFamilyMemberList(BizProbityFamilyMember bizProbityFamilyMember);

    /**
     * 新增廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 结果
     */
    public int insertBizProbityFamilyMember(BizProbityFamilyMember bizProbityFamilyMember);


    /**
     * 批量新增廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @return 结果
     */
    public int insertBatch(@Param("entities") List<BizProbityFamilyMember> entities);

    /**
     * 修改廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param bizProbityFamilyMember 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     * @return 结果
     */
    public int updateBizProbityFamilyMember(BizProbityFamilyMember bizProbityFamilyMember);

    /**
     * 删除廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param id 廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）ID
     * @return 结果
     */
    public int deleteBizProbityFamilyMemberById(String id);

    /**
     * 批量删除廉政档案-5.家庭成员基本信息（填写本人的父母、配偶、子女及其配偶）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizProbityFamilyMemberByIds(String[] ids);
}
