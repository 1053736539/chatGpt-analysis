package com.cb.leave.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.PageDomain;
import com.cb.common.core.page.TableSupport;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.sql.SqlUtil;
import com.cb.leave.domain.vo.LeaveCensusVo;
import com.cb.leave.mapper.LeaveCensusMapper;
import com.cb.leave.service.LeaveCensusService;
import com.cb.leave.util.UserRoleUtil;
import com.cb.leave.vo.LeaveChartVo;
import com.cb.system.mapper.SysDeptMapper;
import com.cb.system.mapper.SysUserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LeaveCensusServiceImpl implements LeaveCensusService {
    @Autowired
    private LeaveCensusMapper leaveCensusMapper;

    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private SysUserMapper userMapper;


    @Override
    public List<LeaveCensusVo> selectLeaveSituationList(LeaveCensusVo leaveCensusVo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        SysDept dept = user.getDept();

        String leaveYear = leaveCensusVo.getLeaveYear();
        if (StringUtils.isBlank(leaveYear)) {
            String year = DateUtil.format(new Date(), "yyyy");
            leaveCensusVo.setLeaveYear(year);
        }
        // admin/组织部管理员/书记角色 可以查看所有人的数据
        if (user.isAdmin() || UserRoleUtil.hasRole_shuji(user) || UserRoleUtil.hasRole_organization_admin(user)){

        } else if(UserRoleUtil.hasRole_fushuji(user) || UserRoleUtil.hasRole_changwei(user)){  //副书记 或 常委 查分管的部门
            List<String> fgRoleKeys = UserRoleUtil.getFgRoleKeys(user);
            if(ObjectUtil.isEmpty(fgRoleKeys)){
                return Collections.emptyList();
            }
            List<SysDept> deptList = new LinkedList<>();
            List<SysDept> chargeDeptList = deptMapper.getDeptListByRoleKeys(fgRoleKeys);
            //分管部门及其子部门查询
            for (int i = 0; i < chargeDeptList.size(); i++) {
                SysDept o = chargeDeptList.get(i);
                String ancestors = o.getAncestors() + ',' + o.getDeptId();
                List<SysDept> oList = deptMapper.selectDeptListLeftLikeAncestorsAndDeptId(ancestors, o.getDeptId());
                if(!oList.isEmpty()){
                    deptList.addAll(oList);
                }
            }
            deptList = distinctSordExculdeDept(deptList, !UserRoleUtil.nameIsAdmin(user));
            Set<Long> deptIdSet = deptList.stream().map(SysDept::getDeptId).collect(Collectors.toSet());
            List<Long> deptIds = new ArrayList<>(deptIdSet);
            //没有分管部门的话，返回空
            if(ObjectUtil.isEmpty(deptIds)){
                return Collections.emptyList();
            }
            startPage();
            List<LeaveCensusVo> list = leaveCensusMapper.selectLeaveCensusByDeptIds(leaveCensusVo,deptIds);
            return list;
        } else if (UserRoleUtil.isMainLeader(user) || UserRoleUtil.isHostWork(user) || UserRoleUtil.hasRole_dept_master_leader(user)) {//部门领导可以查看本部门
            leaveCensusVo.setDeptId(dept.getDeptId());
        } else {//普通人看自己
            leaveCensusVo.setUserId(user.getUserId());
        }
        startPage();
        List<LeaveCensusVo> list = leaveCensusMapper.selectLeaveCensus(leaveCensusVo, !UserRoleUtil.nameIsAdmin(user));
        return list;
    }

    private void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    @Override
    public LeaveChartVo.HomeChartResp listHomeChartData(LeaveChartVo.HomeChartReq req) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        SysDept dept = user.getDept();
        LeaveChartVo.HomeChartResp rst = new LeaveChartVo.HomeChartResp();
        LeaveCensusVo query = new LeaveCensusVo();
        query.setLeaveYear(req.getYear());
        query.setIdentityType(req.getIdentityType());
        // admin/组织部管理员/书记角色 可以查看所有人的数据
        if (user.isAdmin() || UserRoleUtil.hasRole_shuji(user) || UserRoleUtil.hasRole_organization_admin(user)){
            //领导查看某个部门的公休率情况
            boolean leaderDeptIdFlag = ObjectUtil.isNotEmpty(req.getDeptId()) && req.getDeptId() != 0;
            List<LeaveCensusVo> list;
            if(leaderDeptIdFlag){
                query.setDeptId(req.getDeptId());
            }
            if(req.getDeptId()==147){
                list = leaveCensusMapper.selectLeaveCensusListByDeptIds(query, !UserRoleUtil.nameIsAdmin(user));
            }else{
                list = leaveCensusMapper.selectLeaveCensus(query, !UserRoleUtil.nameIsAdmin(user));
            }
            Map<Long, List<LeaveCensusVo>> deptIdListMap = list.stream().collect(Collectors.groupingBy(LeaveCensusVo::getDeptId));
            rst.setTotalUsedDays(list.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
            rst.setTotalRemainingDays(list.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
            String ancestors = "0,100";
            // 获取指定ancestors 值的部门列表
            List<SysDept> deptList =new ArrayList<>();
            SysDept sysDept=new SysDept();
            if(leaderDeptIdFlag){
                //领导查看某个部门的公休率情况,x轴只显示一个部门的公休率情况
                sysDept.setDeptId(req.getDeptId());
                deptList = deptMapper.selectDeptListLeftBydeptId(sysDept);
            }else {
                deptList = deptMapper.selectDeptListLeftLikeAncestors(ancestors);
            }
            deptList = distinctSordExculdeDept(deptList, !UserRoleUtil.nameIsAdmin(user));
            //办公室：办公室秘书处、办公室信息督查处、办公室行政处不显示，合并到办公室
            AtomicReference<Float> usedDays0= new AtomicReference<>(0.0f);
            AtomicReference<Float> remainingDays0= new AtomicReference<>(0.0f);
            List<LeaveChartVo.HomeChartItem> itemList = deptList.stream().map(o -> {
                Long deptId = o.getDeptId();
                String deptName = o.getDeptAbbreviation();
                LeaveChartVo.HomeChartItem item = new LeaveChartVo.HomeChartItem();
                item.setLabel(deptName);
                item.setDeptId(deptId);
                List<LeaveCensusVo> censusVoList = deptIdListMap.get(deptId);
             if(deptId==147) {
                 Float sum1=0.0f;
                 Float sum2=0.0f;
                 for(long i=147;i<=150;i++){
                     List<LeaveCensusVo> censusVoList0 = deptIdListMap.get(i);
                     if (!ObjectUtil.isEmpty(censusVoList0)) {
                         usedDays0.set(censusVoList0.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
                         remainingDays0.set(censusVoList0.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
                         sum1+=usedDays0.get();
                         sum2+=remainingDays0.get();
                         item.setUsedDays(sum1);
                         item.setRemainingDays(sum2);
                     }
                 }
                }else{
                        if (ObjectUtil.isEmpty(censusVoList)) {
                            item.setUsedDays(0F);
                            item.setRemainingDays(0F);
                        } else {
                            item.setUsedDays(censusVoList.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
                            item.setRemainingDays(censusVoList.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
                        }
                    }
                return item;
            })
            //都为0的部门不显示了 办公室秘书处、办公室信息督查处、办公室行政处不显示，合并到办公室
            .filter(item->{
                return (item.getUsedDays() > 0 || item.getRemainingDays() > 0 )&&(item.getDeptId() !=148&&item.getDeptId() !=149 &&item.getDeptId() !=150);
            }).collect(Collectors.toList());
            rst.setItemList(itemList);
        }  else if(UserRoleUtil.hasRole_fushuji(user) || UserRoleUtil.hasRole_changwei(user)){  //副书记 或 常委 查分管的部门
            //领导查看某个部门的公休率情况
            boolean leaderDeptIdFlag = ObjectUtil.isNotEmpty(req.getDeptId()) && req.getDeptId() != 0;
            if(leaderDeptIdFlag){
                query.setDeptId(req.getDeptId());
            }
            List<LeaveCensusVo> list;
            if(req.getDeptId()==147){
                //办公室
                list = leaveCensusMapper.selectLeaveCensusListByDeptIds(query, !UserRoleUtil.nameIsAdmin(user));
            }else{
                list = leaveCensusMapper.selectLeaveCensus(query, !UserRoleUtil.nameIsAdmin(user));
            }
            Map<Long, List<LeaveCensusVo>> deptIdListMap = list.stream().collect(Collectors.groupingBy(LeaveCensusVo::getDeptId));

            // 获取指定ancestors 值的部门列表
            List<SysDept> deptList = new ArrayList<>();
            SysDept sysDept=new SysDept();
            if(leaderDeptIdFlag){
                //领导查看某个部门的公休率情况,x轴只显示一个部门的公休率情况
                sysDept.setDeptId(req.getDeptId());
                deptList = deptMapper.selectDeptListLeftBydeptId(sysDept);
            }else {
                List<String> fgRoleKeys = UserRoleUtil.getFgRoleKeys(user);
                if(fgRoleKeys.size()>0){
                    List<SysDept> chargeDeptList = deptMapper.getDeptListByRoleKeys(fgRoleKeys);
                    //分管部门及其子部门查询
                    for (int i = 0; i < chargeDeptList.size(); i++) {
                        SysDept o = chargeDeptList.get(i);
                        String ancestors = o.getAncestors() + ',' + o.getDeptId();
                        List<SysDept> oList = deptMapper.selectDeptListLeftLikeAncestorsAndDeptId(ancestors, o.getDeptId());
                        if(!oList.isEmpty()){
                            deptList.addAll(oList);
                        }
                    }
                }
            }
            deptList = distinctSordExculdeDept(deptList, !UserRoleUtil.nameIsAdmin(user));
            //办公室：办公室秘书处、办公室信息督查处、办公室行政处不显示，合并到办公室
            AtomicReference<Float> usedDays0= new AtomicReference<>(0.0f);
            AtomicReference<Float> remainingDays0= new AtomicReference<>(0.0f);
            List<LeaveChartVo.HomeChartItem> itemList = deptList.stream().map(o -> {
                Long deptId = o.getDeptId();
                String deptName = o.getDeptAbbreviation();
                LeaveChartVo.HomeChartItem item = new LeaveChartVo.HomeChartItem();
                item.setLabel(deptName);
                item.setDeptId(deptId);
                List<LeaveCensusVo> censusVoList = deptIdListMap.get(deptId);
               if(deptId==147) {
                   Float sum1=0.0f;
                   Float sum2=0.0f;
                   for(long i=147;i<=150;i++){
                       List<LeaveCensusVo> censusVoList0 = deptIdListMap.get(i);
                       if (!ObjectUtil.isEmpty(censusVoList0)) {
                           usedDays0.set(censusVoList0.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
                           remainingDays0.set(censusVoList0.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
                           sum1+=usedDays0.get();
                           sum2+=remainingDays0.get();
                           item.setUsedDays(sum1);
                           item.setRemainingDays(sum2);
                       }
                   }
                }else {
                    if (ObjectUtil.isEmpty(censusVoList)) {
                        item.setUsedDays(0F);
                        item.setRemainingDays(0F);
                    } else {
                        item.setUsedDays(censusVoList.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
                        item.setRemainingDays(censusVoList.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
                    }
                }
                return item;
            })
            //都为0的部门不显示了
            .filter(item->{
                return (item.getUsedDays() > 0 || item.getRemainingDays() > 0) &&(item.getDeptId() !=148 &&item.getDeptId() !=149 &&item.getDeptId() !=150);
            }).collect(Collectors.toList());
            rst.setTotalRemainingDays(itemList.stream().map(LeaveChartVo.HomeChartItem::getRemainingDays).reduce(0F, Float::sum));
            rst.setTotalUsedDays(itemList.stream().map(LeaveChartVo.HomeChartItem::getUsedDays).reduce(0F, Float::sum));
            rst.setItemList(itemList);
        } else if (UserRoleUtil.isMainLeader(user) || UserRoleUtil.isHostWork(user) || UserRoleUtil.hasRole_dept_master_leader(user)) {//部门领导可以查看本部门
            query.setDeptId(dept.getDeptId());
            List<LeaveCensusVo> list;
            if(dept.getDeptId()==147){
                list = leaveCensusMapper.selectLeaveCensusListByDeptIds(query, !UserRoleUtil.nameIsAdmin(user));
            }else {
               list = leaveCensusMapper.selectLeaveCensus(query, !UserRoleUtil.nameIsAdmin(user));
            }
            Map<Long, List<LeaveCensusVo>> userIdListMap = list.stream().collect(Collectors.groupingBy(LeaveCensusVo::getUserId));
            rst.setTotalUsedDays(list.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
            rst.setTotalRemainingDays(list.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
            List<SysUser> userList = userMapper.selectUserListByDeptId(user.getDeptId());
            List<LeaveChartVo.HomeChartItem> itemList = userList.stream().map(o -> {
                Long userId = o.getUserId();
                String name = o.getName();
                LeaveChartVo.HomeChartItem item = new LeaveChartVo.HomeChartItem();
                item.setLabel(name);
                List<LeaveCensusVo> censusVoList = userIdListMap.get(userId);
                if (ObjectUtil.isEmpty(censusVoList)) {
                    item.setUsedDays(0L);
                    item.setRemainingDays(0L);
                } else {
                    item.setUsedDays(censusVoList.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
                    item.setRemainingDays(censusVoList.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
                }
                return item;
            })
             //都为0的部门不显示了
            .filter(item->{
                return item.getUsedDays() > 0 || item.getRemainingDays() > 0;
            }).collect(Collectors.toList());
            rst.setItemList(itemList);
        } else {//普通人看自己
            query.setUserId(user.getUserId());
            List<LeaveCensusVo> list = leaveCensusMapper.selectLeaveCensus(query, !UserRoleUtil.nameIsAdmin(user));
            rst.setTotalUsedDays(list.stream().map(LeaveCensusVo::getUsedDays).reduce(0F, Float::sum));
            rst.setTotalRemainingDays(list.stream().map(LeaveCensusVo::getRemainingDays).reduce(0F, Float::sum));
            rst.setItemList(Collections.emptyList());
        }
        return rst;
    }

    /**
     * 去重并排序
     * @param orginList
     * @param exculde 是否排除 县（市）区纪委、监委这个及以下的部门
     * @return
     */
    private List<SysDept> distinctSordExculdeDept(List<SysDept> orginList, boolean exculde){
        Stream<SysDept> deptStream  = orginList.stream().filter(o -> {
            Long deptId = o.getDeptId();
            if(new Long(100L).equals(deptId)){
                return false;
            }
            String status = o.getStatus();
            String delFlag = o.getDelFlag();
            return "0".equals(status) && "0".equals(delFlag);
        });
        if(exculde){
            deptStream = deptStream.filter(o -> {
                Long deptId = o.getDeptId();
                String ancestors = o.getAncestors();
                return !(new Long(138L).equals(deptId) || ancestors.startsWith("0,100,138"));
            });
        }
        Map<Long, SysDept> deptIdMap = deptStream.collect(Collectors.toMap(SysDept::getDeptId, o -> o, (o1, o2) -> o1));
        return deptIdMap.values().stream()
                .sorted(Comparator.comparing(o->{
                    String str = o.getOrderNum();
                    return StringUtils.isNotBlank(str) ? Integer.parseInt(str) : Integer.MAX_VALUE;
                }))
                .collect(Collectors.toList());
    }

}
