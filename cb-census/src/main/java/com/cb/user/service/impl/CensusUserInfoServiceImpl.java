package com.cb.user.service.impl;

import com.cb.common.annotation.EncryptMethod;
import com.cb.common.enums.EODType;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.dept.domain.CensusDept;
import com.cb.dept.mapper.CensusDeptMapper;
import com.cb.user.domain.CensusUserInfo;
import com.cb.user.service.ICensusUserInfoService;
import com.cb.user.mapper.CensusUserInfoMapper;
import org.apache.avalon.framework.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 普查人员Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class CensusUserInfoServiceImpl implements ICensusUserInfoService {
    private static final Logger log = LoggerFactory.getLogger(CensusUserInfoServiceImpl.class);
    @Autowired
    private CensusUserInfoMapper censusUserInfoMapper;

    @Autowired
    private CensusDeptMapper censusDeptMapper;

    /**
     * 查询普查人员
     *
     * @param userId 普查人员ID
     * @return 普查人员
     */
    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public CensusUserInfo selectCensusUserInfoById(String userId) {
        return censusUserInfoMapper.selectCensusUserInfoById(userId);
    }

    /**
     * 查询普查人员列表
     *
     * @param censusUserInfo 普查人员
     * @return 普查人员
     */
    @Override
    @EncryptMethod(businessType = EODType.DECRYPT)
    public List<CensusUserInfo> selectCensusUserInfoList(CensusUserInfo censusUserInfo) {
        return censusUserInfoMapper.selectCensusUserInfoList(censusUserInfo);
    }

    /**
     * 新增普查人员
     *
     * @param censusUserInfo 普查人员
     * @return 结果
     */
    @Override
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int insertCensusUserInfo(CensusUserInfo censusUserInfo) {
        CensusDept censusDept = censusDeptMapper.selectCensusDeptById(Long.valueOf(censusUserInfo.getCensusDeptId()));
        if (censusDept != null) {
            censusUserInfo.setUnitInfo(censusDept.getDeptName());
        }
        censusUserInfo.setCreateTime(DateUtils.getNowDate());
        return censusUserInfoMapper.insertCensusUserInfo(censusUserInfo);
    }

    /**
     * 修改普查人员
     *
     * @param censusUserInfo 普查人员
     * @return 结果
     */
    @Override
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int updateCensusUserInfo(CensusUserInfo censusUserInfo) {
        if (censusUserInfo.getCensusDeptId() != null) {
            CensusDept censusDept = censusDeptMapper.selectCensusDeptById(Long.valueOf(censusUserInfo.getCensusDeptId()));
            if (censusDept != null) {
                censusUserInfo.setUnitInfo(censusDept.getDeptName());
            }
        }
        censusUserInfo.setUpdateTime(DateUtils.getNowDate());
        return censusUserInfoMapper.updateCensusUserInfo(censusUserInfo);
    }

    /**
     * 批量删除普查人员
     *
     * @param userIds 需要删除的普查人员ID
     * @return 结果
     */
    @Override
    public int deleteCensusUserInfoByIds(String[] userIds) {
        return censusUserInfoMapper.deleteCensusUserInfoByIds(userIds);
    }

    /**
     * 删除普查人员信息
     *
     * @param userId 普查人员ID
     * @return 结果
     */
    @Override
    public int deleteCensusUserInfoById(String userId) {
        return censusUserInfoMapper.deleteCensusUserInfoById(userId);
    }

    /**
     * 导入普查人员信息数据
     *
     * @param censusUserList  用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importCensusUserInfo(List<CensusUserInfo> censusUserList, Boolean isUpdateSupport) throws ServiceException {
        if (StringUtils.isNull(censusUserList) || censusUserList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        if (StringUtils.isNull(censusUserList) || censusUserList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CensusUserInfo user : censusUserList) {
            try {
                // 验证是否存在这个用户
                CensusUserInfo u = censusUserInfoMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u)) {
                    //BeanValidators.validateWithException(validator, user);
                    // censusUserInfoMapper.insertCensusUserInfo(user);
                    this.importCensusUserInfo(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    //BeanValidators.validateWithException(validator, user);
                    //checkUserDataScope(u.getUserId());
                    user.setUserId(u.getUserId());
//                    censusUserInfoMapper.updateCensusUserInfo(user);
                    this.updateCensusUserInfo(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + user.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Transactional
    @EncryptMethod(businessType = EODType.ENCRYPT)
    public int importCensusUserInfo(CensusUserInfo censusUserInfo) {
        // 新增普查人员信息
        censusUserInfo.setUserId(IdUtils.randomUUID());
        censusUserInfo.setCreateTime(new Date());
        censusUserInfo.setUpdateTime(new Date());
        censusUserInfo.setDelFlag("0");
        int rows = censusUserInfoMapper.insertCensusUserInfo(censusUserInfo);
        return rows;
    }

    /**
     * 用户数据导入
     *
     * @param censusUserList
     * @return
     */
    @Override
    public String importCensusUserInfo1(List<CensusUserInfo> censusUserList, Integer censusNumber) {
        List<CensusUserInfo> validUsers = preprocessAndValidateData(censusUserList, censusNumber);
        int successCount = 0;
        int failCount = 0;
        StringBuilder failureMsg = new StringBuilder();

        try {
            int deletedCount = censusUserInfoMapper.deleteUsersByCensusNumber(censusNumber);
            log.info("Deleted {} existing records for census number {}", deletedCount, censusNumber);

            for (CensusUserInfo user : validUsers) {
                try {
                    censusUserInfoMapper.insertCensusUserInfo(user);
                    successCount++;
                } catch (Exception e) {
                    failCount++;
                    failureMsg.append(String.format("导入用户 %s (身份证: %s) 失败：%s\n",
                            user.getUserName(), user.getIdNumber(), e.getMessage()));
                }
            }

            return buildResultMessage(successCount, failCount, deletedCount, failureMsg);
        } catch (Exception e) {
            log.error("Import failed", e);
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }

    private List<CensusUserInfo> preprocessAndValidateData(List<CensusUserInfo> censusUserList, int censusNumber) {
        return censusUserList.stream()
                .map(user -> preprocessUser(user, censusNumber))
                .filter(this::isValidUser)
                .collect(Collectors.toList());
    }

    private CensusUserInfo preprocessUser(CensusUserInfo user, int censusNumber) {
        user.setUserId(IdUtils.randomUUID());
        user.setCensusNumber(censusNumber);
        user.setCensusDeptId(findMatchingDepartment(user.getDeptCode()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDelFlag("0");
        return user;
    }

    /*private Long findMatchingDepartment(Long deptCode) {
        String deptCodeStr = String.valueOf(deptCode);
        while (!deptCodeStr.isEmpty()) {
            Long deptId = censusDeptMapper.getCensusDeptIdByCode(Long.parseLong(deptCodeStr));
            if (deptId != null) {
                return deptId;
            }
            deptCodeStr = shortenDeptCode(deptCodeStr);
        }
        log.warn("无法找到匹配的部门 for deptCode: {}", deptCode);
        return null;
    }*/
    private Long findMatchingDepartment(Long deptCode) {
        if (deptCode == null) {
            log.warn("DeptCode is null, cannot find matching department.");
            return null;
        }

        String deptCodeStr = String.valueOf(deptCode);
        while (!deptCodeStr.isEmpty() && !deptCodeStr.equals("null")) { // 防止传入 "null"
            try {
                Long deptId = censusDeptMapper.getCensusDeptIdByCode(Long.parseLong(deptCodeStr));
                if (deptId != null) {
                    return deptId;
                }
            } catch (NumberFormatException e) {
                log.warn("Invalid deptCode format during parsing: {}", deptCodeStr, e);
            }
            deptCodeStr = shortenDeptCode(deptCodeStr);
        }
        log.warn("无法找到匹配的部门 for deptCode: {}", deptCode);
        return null;
    }


    private String shortenDeptCode(String deptCodeStr) {
        if (deptCodeStr.length() >= 9) {
            return deptCodeStr.substring(0, deptCodeStr.length() - 3);
        } else if (deptCodeStr.length() > 2) {
            return deptCodeStr.substring(0, deptCodeStr.length() - 2);
        }
        return "";
    }

    private boolean isValidUser(CensusUserInfo user) {
        if (StringUtils.isBlank(user.getUserName())) {
            log.warn("Invalid user data: {}", user);
            return false;
        }
        if (user.getCensusDeptId() == null) {
            log.warn("No matching department found for user: {}", user);
            return false;
        }
        // 添加更多验证规则...
        return true;
    }

    private String buildResultMessage(int successCount, int failCount, int deletedCount, StringBuilder failureMsg) {
        if (failCount > 0) {
            failureMsg.insert(0, String.format("导入结果：成功 %d 条，失败 %d 条\n", successCount, failCount));
            return failureMsg.toString();
        } else {
            return String.format("导入成功：共导入 %d 条数据，删除了 %d 条旧数据", successCount, deletedCount);
        }
    }

    // 辅助方法：Long 转 String
    private String longToString(Long value) {
        return value != null ? String.valueOf(value) : "";
    }

    // 辅助方法：String 转 Long
    private Long stringToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            log.warn("Invalid deptCode format: {}", value);
            return null;
        }
    }

    // 辅助方法
    private String convertEducation(String education) {
        Map<String, String> educationMap = new HashMap<>();
        educationMap.put("小学", "1");
        educationMap.put("初中", "2");
        educationMap.put("高中", "3");
        educationMap.put("专科", "4");
        educationMap.put("本科", "5");
        educationMap.put("研究生", "6");
        return educationMap.getOrDefault(education, education);
    }

    private String convertGender(String gender) {
        return "男".equals(gender) ? "1" : ("女".equals(gender) ? "2" : gender);
    }

    /*private boolean isValidUser(CensusUserInfo user) {
        // 实现用户数据验证逻辑
        // 例如，检查必填字段是否为空，数据格式是否正确等
        return true; // 根据实际验证结果返回 true 或 false
    }*/
}
