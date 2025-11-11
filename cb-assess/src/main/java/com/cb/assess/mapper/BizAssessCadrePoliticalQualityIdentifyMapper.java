package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessCadrePoliticalQualityIdentify;
import com.cb.assess.domain.vo.BizAssessCadrePoliticalQualityIdentifyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 政治素质鉴定Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-22
 */
public interface BizAssessCadrePoliticalQualityIdentifyMapper 
{
    /**
     * 查询政治素质鉴定
     * 
     * @param id 政治素质鉴定ID
     * @return 政治素质鉴定
     */
    public BizAssessCadrePoliticalQualityIdentifyVo selectBizAssessCadrePoliticalQualityIdentifyById(String id);

    /**
     * 根据用户和年份统计他的政治素质考察表
     * @param userId
     * @param assessmentYear
     * @return
     */
    public BizAssessCadrePoliticalQualityIdentify selectStaticsByUser(@Param("userId") String userId,@Param("assessmentYear")  String assessmentYear);

    /**
     * 查询政治素质鉴定列表
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 政治素质鉴定集合
     */
    public List<BizAssessCadrePoliticalQualityIdentifyVo> selectBizAssessCadrePoliticalQualityIdentifyList(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify);
    public List<BizAssessCadrePoliticalQualityIdentifyVo> listByManagerUser(BizAssessCadrePoliticalQualityIdentifyVo bizAssessCadrePoliticalQualityIdentify);

    /**
     * 查询数据中的所有年，用来在前端区分年
     * @return
     */
    public List<BizAssessCadrePoliticalQualityIdentifyVo> selectYears();

    /**
     * 新增政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    public int insertBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    /**
     * 修改政治素质鉴定
     * 
     * @param bizAssessCadrePoliticalQualityIdentify 政治素质鉴定
     * @return 结果
     */
    public int updateBizAssessCadrePoliticalQualityIdentify(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    /**
     * 对象里面必须包含三个参数，就是部门id，上报状态，年份
     * @param bizAssessCadrePoliticalQualityIdentify
     * @return
     */
    public int updateSetIsReport(BizAssessCadrePoliticalQualityIdentify bizAssessCadrePoliticalQualityIdentify);

    /**
     * 删除政治素质鉴定
     * 
     * @param id 政治素质鉴定ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityIdentifyById(String id);

    /**
     * 批量删除政治素质鉴定
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAssessCadrePoliticalQualityIdentifyByIds(String[] ids);
}
