package com.cb.assess.mapper;

import com.cb.assess.domain.BizAssessIndicatorIsmConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 指标配置Mapper接口
 *
 * @author ouyang
 * @date 2023-11-03
 */
public interface BizAssessIndicatorIsmConfigMapper {
    public List<BizAssessIndicatorIsmConfig> selectListByIsmId(String ismId);

    public int batchInsertIndicatorIsmConfig(List<BizAssessIndicatorIsmConfig> list);

    public int deleteIndicatorIsmConfigById(String ismId);

    public int deleteIndicatorIsmConfigByIds(String[] ismIds);

    public int updateIndicatorIsmConfig(@Param("configId") String configId, @Param("indicatorSnapshot") String indicatorSnapshot);

}
