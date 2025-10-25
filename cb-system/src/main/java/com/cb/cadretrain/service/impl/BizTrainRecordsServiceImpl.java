package com.cb.cadretrain.service.impl;

import com.cb.cadretrain.domain.BizTrainRecords;
import com.cb.cadretrain.domain.BizTrainRecordsResult;
import com.cb.cadretrain.domain.TrainDurationResult;
import com.cb.cadretrain.mapper.BizTrainRecordsMapper;
import com.cb.cadretrain.service.IBizTrainRecordsService;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 培训记录Service业务层处理
 *
 * @author yangcd
 * @date 2023-10-30
 */
@Service
public class BizTrainRecordsServiceImpl implements IBizTrainRecordsService {
    @Autowired
    private BizTrainRecordsMapper bizTrainRecordsMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询培训记录
     *
     * @param id 培训记录ID
     * @return 培训记录
     */
    @Override
    public BizTrainRecords selectBizTrainRecordsById(String id) {
        return bizTrainRecordsMapper.selectBizTrainRecordsById(id);
    }

    /**
     * 查询培训记录列表
     *
     * @param bizTrainRecords 培训记录
     * @return 培训记录
     */
    @Override
    public List<BizTrainRecords> selectBizTrainRecordsList(BizTrainRecords bizTrainRecords) {
//        bizTrainRecords.setCreateBy(SecurityUtils.getUsername());

        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        bizTrainRecords.setUserId(user.getUserId());
        return bizTrainRecordsMapper.selectBizTrainRecordsList(bizTrainRecords);
    }
    /*根据用户ID 查询个人培训记录 用户档案信息使用*/
    @Override
    public List<BizTrainRecords> selectUserInfoBizTrainRecordsList(BizTrainRecords bizTrainRecords) {
        return bizTrainRecordsMapper.selectBizTrainRecordsList(bizTrainRecords);
    }

    /**
     * 这个应该是全部参数都给，不受任何参数影响，
     * @param bizTrainRecords
     * @return
     */
    @Override
    public List<BizTrainRecords> selectAllList(BizTrainRecords bizTrainRecords) {
        return bizTrainRecordsMapper.selectBizTrainRecordsList(bizTrainRecords);
    }

    @Override
    public List<BizTrainRecords> selectBizTrainRecordsAllList(BizTrainRecords bizTrainRecords) {
//        bizTrainRecords.setApproverDept(String.valueOf(124));
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        Long deptId = user.getDeptId();
        if (deptId==124){
            return bizTrainRecordsMapper.selectBizTrainRecordsAllList1(bizTrainRecords);
        }else {
            bizTrainRecords.setApproverDept(String.valueOf(deptId));
            return bizTrainRecordsMapper.selectBizTrainRecordsAllList(bizTrainRecords);
        }


    }

    @Override
    public List<BizTrainRecords> selectBizTrainRecordsByPersonnelDept(BizTrainRecords bizTrainRecords) {
        return bizTrainRecordsMapper.selectBizTrainRecordsAllList1(bizTrainRecords);
    }

    @Override
    public List<BizTrainRecords> selectBizTrainRecordsByDept(BizTrainRecords bizTrainRecords) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        Long deptId = user.getDeptId();
        bizTrainRecords.setApproverDept(String.valueOf(deptId));
        return bizTrainRecordsMapper.selectBizTrainRecordsAllList(bizTrainRecords);
    }

    @Override
    public List<BizTrainRecords> listByDeptMgr(BizTrainRecords bizTrainRecords) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        Long deptId = user.getDeptId();
        bizTrainRecords.setApproverDept(String.valueOf(deptId));
        return bizTrainRecordsMapper.listByDeptMgr(bizTrainRecords);
    }

    /**
     * 新增培训记录
     *
     * @param bizTrainRecords 培训记录
     * @return 结果
     */
    @Override
    public int insertBizTrainRecords(BizTrainRecords bizTrainRecords) {

        if (bizTrainRecords.getUserId() == null || bizTrainRecords.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid userId");
        }

        //检查用户是否存在
        SysUser sysUser = userMapper.selectUserById(bizTrainRecords.getUserId());
        if (sysUser == null) {
            throw new IllegalArgumentException("用户信息没有找到 for userId: " + bizTrainRecords.getUserId());
        }

        bizTrainRecords.setId(IdUtils.randomUUID());
        bizTrainRecords.setTraineeName(sysUser.getNickName());
        bizTrainRecords.setApproverDept(String.valueOf(sysUser.getDeptId()));
        //默认待审核
        bizTrainRecords.setApprovalStatus("0");
        bizTrainRecords.setCreateBy(SecurityUtils.getUsername());
        bizTrainRecords.setCreateTime(DateUtils.getNowDate());

        return bizTrainRecordsMapper.insertBizTrainRecords(bizTrainRecords);
    }

    /**
     * 修改培训记录
     *
     * @param bizTrainRecords 培训记录
     * @return 结果
     */
    @Override
    public int updateBizTrainRecords(BizTrainRecords bizTrainRecords) {
        bizTrainRecords.setUpdateTime(DateUtils.getNowDate());
        return bizTrainRecordsMapper.updateBizTrainRecords(bizTrainRecords);
    }

    @Override
    public int updateBizTrainRecordsByAudit(BizTrainRecords bizTrainRecords) {
        bizTrainRecords.setUpdateTime(DateUtils.getNowDate());
        bizTrainRecords.setApproverName(SecurityUtils.getUsername());
        return bizTrainRecordsMapper.updateBizTrainRecords(bizTrainRecords);
    }

    /**
     * 批量删除培训记录
     *
     * @param ids 需要删除的培训记录ID
     * @return 结果
     */
    @Override
    public int deleteBizTrainRecordsByIds(String[] ids) {
        return bizTrainRecordsMapper.deleteBizTrainRecordsByIds(ids);
    }

    /**
     * 删除培训记录信息
     *
     * @param id 培训记录ID
     * @return 结果
     */
    @Override
    public int deleteBizTrainRecordsById(String id) {
        return bizTrainRecordsMapper.deleteBizTrainRecordsById(id);
    }

    @Override
    public List<TrainDurationResult> getTrainDurationByYearAndQuarter1(String trainYear,
                                                                       Integer quarter, // 季度是可选参数
                                                                       String traineeName,
                                                                       String approvalStatus) {
        if (traineeName.isEmpty()) {
            traineeName = SecurityUtils.getUsername();

        }

        // 在这里可以编写业务逻辑，例如数据处理、验证等
        // 调用Mapper接口的方法执行数据库操作
        return bizTrainRecordsMapper.sumTrainDurationByYearAndQuarter1(trainYear, quarter, traineeName, approvalStatus);
    }

    @Override
    public List<BizTrainRecordsResult> getTrainDurationByYearAndQuarter(String trainYear,
                                                                        BizTrainRecords bizTrainRecords, // 季度是可选参数
                                                                        String traineeName
    ) {
      /*  if (traineeName.isEmpty()) {
            traineeName = SecurityUtils.getUsername();

        }*/

        // 在这里可以编写业务逻辑，例如数据处理、验证等
        // 调用Mapper接口的方法执行数据库操作
        return bizTrainRecordsMapper.sumTrainDurationByYearAndQuarter(trainYear, bizTrainRecords, traineeName);
    }
}
