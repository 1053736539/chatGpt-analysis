package com.cb.dept.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.cb.common.annotation.DataScope;
import com.cb.common.constant.UserConstants;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.exception.CustomException;
import com.cb.common.exception.ServiceException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.StringUtils;
import com.cb.dept.domain.CensusTreeSelect;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.dept.mapper.CensusDeptMapper;
import com.cb.dept.domain.CensusDept;
import com.cb.dept.service.ICensusDeptService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 普查人员部门Service业务层处理
 * 
 * @author yangcd
 * @date 2023-11-11
 */
@Service
public class CensusDeptServiceImpl implements ICensusDeptService 
{
    @Autowired
    private CensusDeptMapper censusDeptMapper;

    //引入log日志
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CensusDeptServiceImpl.class);

    /**
     * 查询普查人员部门
     * 
     * @param deptId 普查人员部门ID
     * @return 普查人员部门
     */
    @Override
    public CensusDept selectCensusDeptById(Long deptId)
    {
        return censusDeptMapper.selectCensusDeptById(deptId);
    }

    @Override
    public int selectNormalChildrenDeptById(Long deptId)
    {
        return censusDeptMapper.selectNormalChildrenDeptById(deptId);
    }

    /**
     * 查询普查人员部门列表
     * 
     * @param censusDept 普查人员部门
     * @return 普查人员部门
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<CensusDept> selectCensusDeptList(CensusDept censusDept)
    {
        return censusDeptMapper.selectCensusDeptList(censusDept);
    }

    /**
     * 新增普查人员部门
     * 
     * @param censusDept 普查人员部门
     * @return 结果
     */
    @Override
    public int insertCensusDept(CensusDept censusDept)
    {


        CensusDept info = censusDeptMapper.selectCensusDeptById(censusDept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new CustomException("部门停用，不允许新增");
        }
        censusDept.setAncestors(info.getAncestors() + "," + censusDept.getParentId());
        censusDept.setCreateTime(DateUtils.getNowDate());
        return censusDeptMapper.insertCensusDept(censusDept);
    }

    /**
     * 修改普查人员部门
     * 
     * @param censusDept 普查人员部门
     * @return 结果
     */
    @Override
    public int updateCensusDept(CensusDept dept)
    {
        /*censusDept.setUpdateTime(DateUtils.getNowDate());
        return censusDeptMapper.updateCensusDept(censusDept);*/
        CensusDept newParentDept = censusDeptMapper.selectCensusDeptById(dept.getParentId());
        CensusDept oldDept = censusDeptMapper.selectCensusDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = censusDeptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }
    private void updateParentDeptStatus(CensusDept dept)
    {
        String updateBy = dept.getUpdateBy();
        dept = censusDeptMapper.selectCensusDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        censusDeptMapper.updateDeptStatus(dept);
    }

    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<CensusDept> children = censusDeptMapper.selectChildrenDeptById(deptId);
        for (CensusDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            censusDeptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 批量删除普查人员部门
     * 
     * @param deptIds 需要删除的普查人员部门ID
     * @return 结果
     */
    @Override
    public int deleteCensusDeptByIds(Integer[] deptIds)
    {
        return censusDeptMapper.deleteCensusDeptByIds(deptIds);
    }


    @Override
    public List<CensusTreeSelect> buildDeptTreeSelect(List<CensusDept> depts)
    {
        List<CensusDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(CensusTreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<CensusDept> buildDeptTree(List<CensusDept> depts)
    {
        List<CensusDept> returnList = new ArrayList<CensusDept>();
        List<Long> tempList = new ArrayList<Long>();
        for (CensusDept dept : depts)
        {
            tempList.add(dept.getDeptId());
        }
        for (Iterator<CensusDept> iterator = depts.iterator(); iterator.hasNext();)
        {
            CensusDept dept = (CensusDept) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<CensusDept> list, CensusDept t)
    {
        // 得到子节点列表
        List<CensusDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CensusDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<CensusDept> getChildList(List<CensusDept> list, CensusDept t)
    {
        List<CensusDept> tlist = new ArrayList<CensusDept>();
        Iterator<CensusDept> it = list.iterator();
        while (it.hasNext())
        {
            CensusDept n = (CensusDept) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    private boolean hasChild(List<CensusDept> list, CensusDept t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    @Override
    public String checkDeptNameUnique(CensusDept dept)
    {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        CensusDept info = censusDeptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean hasChildByDeptId(Long deptId)
    {
        int result = censusDeptMapper.hasChildByDeptId(deptId);
        return result > 0 ? true : false;
    }

    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = censusDeptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    @Override
    public int deleteCensusDeptById(Long deptId)
    {
        return censusDeptMapper.deleteCensusDeptById(deptId);
    }


    @Override
    @Transactional
    public String importCensusDepts(List<CensusDept> censusDeptList) {
        if (CollectionUtils.isEmpty(censusDeptList)) {
            throw new ServiceException("导入数据不能为空！");
        }

        // 清空现有部门数据
        try {
            censusDeptMapper.deleteAllCensusDepts();
            log.info("已清空现有部门数据");
        } catch (Exception e) {
            log.error("清空部门数据失败", e);
            throw new ServiceException("清空现有部门数据失败，导入操作已取消");
        }

        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        Map<Long, CensusDept> codeMap = new HashMap<>();

        // 预处理：移除无效数据并记录
        List<CensusDept> validDeptList = new ArrayList<>();
        for (CensusDept dept : censusDeptList) {
            if (dept == null) {
                log.warn("发现空的部门对象，已跳过");
                failureNum++;
                failureMsg.append("<br/>").append(failureNum).append("、发现空的部门对象，已跳过");
                continue;
            }
            if (dept.getDeptCode() == null) {
                log.warn("部门编码为null，部门名称: {}", dept.getDeptName());
                failureNum++;
                failureMsg.append("<br/>").append(failureNum).append("、部门编码为null，部门名称: ").append(dept.getDeptName());
                continue;
            }
            validDeptList.add(dept);
        }

        // 按编码长度和数值大小排序，确保父级部门先处理
        validDeptList.sort(Comparator.<CensusDept, Integer>comparing(dept -> dept.getDeptCode().toString().length())
                .thenComparing(CensusDept::getDeptCode));

        for (CensusDept censusDept : validDeptList) {
            try {
                // 验证必要的字段
                if (StringUtils.isBlank(censusDept.getDeptName())) {
                    throw new ServiceException("机构名称不能为空");
                }

                // 设置默认值
                censusDept.setDelFlag("0");
                censusDept.setStatus("0");

                // 处理层级关系
                processHierarchy(censusDept, codeMap);

                // 插入数据库并获取自增的deptId
                censusDeptMapper.insertCensusDept(censusDept);

                // 将新插入的部门添加到codeMap中
                codeMap.put(censusDept.getDeptCode(), censusDept);

                successNum++;
                successMsg.append("<br/>").append(successNum).append("、机构 ")
                        .append(censusDept.getDeptName()).append(" 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、机构 " + censusDept.getDeptName() + " 导入失败：";
                failureMsg.append(msg).append(e.getMessage());
                log.error(msg, e);
            }
        }

        return buildResultMessage(successNum, failureNum, successMsg, failureMsg);
    }

    private void processHierarchy(CensusDept censusDept, Map<Long, CensusDept> codeMap) {
        String codeStr = censusDept.getDeptCode().toString();
        int codeLength = codeStr.length();

        if (codeLength == 2) {
            // 顶级机构（省级）
            censusDept.setParentId(0L);
            censusDept.setAncestors("0");
        } else {
            // 查找父级机构
            String parentCodeStr;
            switch (codeLength) {
                case 4:  // 市级
                    parentCodeStr = codeStr.substring(0, 2);
                    break;
                case 6:  // 区级
                    parentCodeStr = codeStr.substring(0, 4);
                    break;
                case 9:  // 街道/镇级
                    parentCodeStr = codeStr.substring(0, 6);
                    break;
                case 12: // 社区/村级
                    parentCodeStr = codeStr.substring(0, 9);
                    break;
                default:
                    throw new ServiceException("无法处理的编码长度: " + codeLength + ", 编码: " + codeStr);
            }

            Long parentCode = Long.parseLong(parentCodeStr);
            CensusDept parentDept = codeMap.get(parentCode);
            if (parentDept == null) {
                throw new ServiceException("父级机构编码 " + parentCode + " 不存在");
            }
            censusDept.setParentId(parentDept.getDeptId());
            censusDept.setAncestors(parentDept.getAncestors() + "," + parentDept.getDeptId());
        }
    }

    private String buildResultMessage(int successNum, int failureNum, StringBuilder successMsg, StringBuilder failureMsg) {
        StringBuilder resultMsg = new StringBuilder();
        resultMsg.append("导入操作完成。现有部门数据已被清空。");

        if (failureNum > 0) {
            resultMsg.append("共 ").append(successNum).append(" 条数据导入成功，")
                    .append(failureNum).append(" 条数据导入失败。具体如下：<br/>")
                    .append(failureMsg);
        } else {
            resultMsg.append("所有数据导入成功，共 ").append(successNum).append(" 条。具体如下：<br/>")
                    .append(successMsg);
        }

        return resultMsg.toString();
    }


}
