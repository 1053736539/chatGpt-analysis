package com.cb.assess.service.impl;

import java.util.List;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.assess.mapper.BizAssessPromulgateMapper;
import com.cb.assess.domain.BizAssessPromulgate;
import com.cb.assess.service.IBizAssessPromulgateService;

/**
 * 考核公示Service业务层处理
 *
 * @author ouyang
 * @date 2023-12-29
 */
@Service
public class BizAssessPromulgateServiceImpl implements IBizAssessPromulgateService {
    @Autowired
    private BizAssessPromulgateMapper bizAssessPromulgateMapper;

    /**
     * 查询考核公示
     *
     * @param id 考核公示ID
     * @return 考核公示
     */
    @Override
    public BizAssessPromulgate selectBizAssessPromulgateById(String id) {
        return bizAssessPromulgateMapper.selectBizAssessPromulgateById(id);
    }

    /**
     * 查询考核公示列表
     *
     * @param bizAssessPromulgate 考核公示
     * @return 考核公示
     */
    @Override
    public List<BizAssessPromulgate> selectBizAssessPromulgateList(BizAssessPromulgate bizAssessPromulgate) {
        return bizAssessPromulgateMapper.selectBizAssessPromulgateList(bizAssessPromulgate);
    }

    /**
     * 新增考核公示
     *
     * @param bizAssessPromulgate 考核公示
     * @return 结果
     */
    @Override
    public int insertBizAssessPromulgate(BizAssessPromulgate bizAssessPromulgate) {
        bizAssessPromulgate.setId(IdUtils.randomUUID());
        bizAssessPromulgate.setCreateBy(SecurityUtils.getUsername());
        bizAssessPromulgate.setCreateTime(DateUtils.getNowDate());
        return bizAssessPromulgateMapper.insertBizAssessPromulgate(bizAssessPromulgate);
    }

    /**
     * 修改考核公示
     *
     * @param bizAssessPromulgate 考核公示
     * @return 结果
     */
    @Override
    public int updateBizAssessPromulgate(BizAssessPromulgate bizAssessPromulgate) {
        bizAssessPromulgate.setUpdateBy(SecurityUtils.getUsername());
        bizAssessPromulgate.setUpdateTime(DateUtils.getNowDate());
        return bizAssessPromulgateMapper.updateBizAssessPromulgate(bizAssessPromulgate);
    }

    /**
     * 批量删除考核公示
     *
     * @param ids 需要删除的考核公示ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessPromulgateByIds(String[] ids) {
        return bizAssessPromulgateMapper.deleteBizAssessPromulgateByIds(ids);
    }

    /**
     * 删除考核公示信息
     *
     * @param id 考核公示ID
     * @return 结果
     */
    @Override
    public int deleteBizAssessPromulgateById(String id) {
        return bizAssessPromulgateMapper.deleteBizAssessPromulgateById(id);
    }

    @Override
    public Boolean checkExist(BizAssessPromulgate bizAssessPromulgate) {
        return bizAssessPromulgateMapper.checkExist(bizAssessPromulgate);
    }

    @Override
    public List<BizAssessPromulgate> selectPromulgateList() {
        return bizAssessPromulgateMapper.selectPromulgateList();
    }

    @Override
    public int changePromulgateStatus(String id) {
        return bizAssessPromulgateMapper.changePromulgateStatus(id);
    }

    @Override
    public int batchChangePromulgateStatus(List<String> ids) {
        return bizAssessPromulgateMapper.batchChangePromulgateStatus(ids);
    }
}
