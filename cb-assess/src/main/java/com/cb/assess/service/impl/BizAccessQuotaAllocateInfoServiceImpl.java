package com.cb.assess.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cb.assess.domain.BizAccessQuotaAllocateInfo;
import com.cb.assess.mapper.BizAccessQuotaAllocateInfoMapper;
import com.cb.assess.mapper.BizAssessUserMapper;
import com.cb.assess.service.IBizAccessQuotaAllocateInfoService;
import com.cb.assess.vo.QuotaAllocateInfoVo;
import com.cb.common.constant.NoticeBizUrlTpl;
import com.cb.common.core.domain.entity.SysDept;
import com.cb.common.core.redis.RedisCache;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.uuid.IdUtils;
import com.cb.system.domain.SysNotice;
import com.cb.system.service.ISysDeptService;
import com.cb.system.service.ISysNoticeService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 年度机关事业单位考核人数及优秀等次名额分配信息Service业务层处理
 * 
 * @author yangxin
 * @date 2023-12-09
 */
@Service
public class BizAccessQuotaAllocateInfoServiceImpl implements IBizAccessQuotaAllocateInfoService 
{
    @Autowired
    private BizAccessQuotaAllocateInfoMapper bizAccessQuotaAllocateInfoMapper;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private BizAssessUserMapper assessUserMapper;

    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private RedisCache redisCache;

    public static final String DEFAULT_TABLE_INFO_KEY = "QuotaAllocateInfo_DEFAULT_TABLE_INFO";

    /**
     * 查询年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param id 年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 年度机关事业单位考核人数及优秀等次名额分配信息
     */
    @Override
    public BizAccessQuotaAllocateInfo selectBizAccessQuotaAllocateInfoById(String id)
    {
        return bizAccessQuotaAllocateInfoMapper.selectBizAccessQuotaAllocateInfoById(id);
    }

    /**
     * 查询年度机关事业单位考核人数及优秀等次名额分配信息列表
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 年度机关事业单位考核人数及优秀等次名额分配信息
     */
    @Override
    public List<BizAccessQuotaAllocateInfo> selectBizAccessQuotaAllocateInfoList(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        return bizAccessQuotaAllocateInfoMapper.selectBizAccessQuotaAllocateInfoList(bizAccessQuotaAllocateInfo);
    }

    /**
     * 新增年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 结果
     */
    @Override
    public int insertBizAccessQuotaAllocateInfo(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        BizAccessQuotaAllocateInfo query = new BizAccessQuotaAllocateInfo();
        query.setYear(bizAccessQuotaAllocateInfo.getYear());
        List<BizAccessQuotaAllocateInfo> existList = selectBizAccessQuotaAllocateInfoList(query);
        if(null != existList && !existList.isEmpty()){
            throw new IllegalArgumentException("该年度已存在记录，不允许重复添加");
        }

        if(StringUtils.isBlank(bizAccessQuotaAllocateInfo.getId())){
            bizAccessQuotaAllocateInfo.setId(IdUtils.randomUUID());
        }
        if(StringUtils.isBlank(bizAccessQuotaAllocateInfo.getNoticeFlag())){
            bizAccessQuotaAllocateInfo.setNoticeFlag("1");//默认值 1-未发布
        }
        try{
            if(StringUtils.isBlank(bizAccessQuotaAllocateInfo.getCreateBy())){
                bizAccessQuotaAllocateInfo.setCreateBy(SecurityUtils.getUsername());
            }
        } catch (Exception e){}
        bizAccessQuotaAllocateInfo.setCreateTime(DateUtils.getNowDate());
        return bizAccessQuotaAllocateInfoMapper.insertBizAccessQuotaAllocateInfo(bizAccessQuotaAllocateInfo);
    }

    /**
     * 修改年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param bizAccessQuotaAllocateInfo 年度机关事业单位考核人数及优秀等次名额分配信息
     * @return 结果
     */
    @Override
    public int updateBizAccessQuotaAllocateInfo(BizAccessQuotaAllocateInfo bizAccessQuotaAllocateInfo)
    {
        try{
            if(StringUtils.isBlank(bizAccessQuotaAllocateInfo.getUpdateBy())){
                bizAccessQuotaAllocateInfo.setUpdateBy(SecurityUtils.getUsername());
            }
        } catch (Exception e){}
        bizAccessQuotaAllocateInfo.setUpdateTime(DateUtils.getNowDate());
        return bizAccessQuotaAllocateInfoMapper.updateBizAccessQuotaAllocateInfo(bizAccessQuotaAllocateInfo);
    }

    /**
     * 批量删除年度机关事业单位考核人数及优秀等次名额分配信息
     * 
     * @param ids 需要删除的年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 结果
     */
    @Override
    public int deleteBizAccessQuotaAllocateInfoByIds(String[] ids)
    {
        return bizAccessQuotaAllocateInfoMapper.deleteBizAccessQuotaAllocateInfoByIds(ids);
    }

    /**
     * 删除年度机关事业单位考核人数及优秀等次名额分配信息信息
     * 
     * @param id 年度机关事业单位考核人数及优秀等次名额分配信息ID
     * @return 结果
     */
    @Override
    public int deleteBizAccessQuotaAllocateInfoById(String id)
    {
        return bizAccessQuotaAllocateInfoMapper.deleteBizAccessQuotaAllocateInfoById(id);
    }

    @Override
    public List<QuotaAllocateInfoVo.TableItemInfo> getDefaultTable() {
        List<QuotaAllocateInfoVo.TableItemInfo> cacheList = redisCache.getCacheList(DEFAULT_TABLE_INFO_KEY);
        if(null != cacheList && cacheList.size() > 0){
            return cacheList;
        }
        List<QuotaAllocateInfoVo.TableItemInfo> list = new LinkedList<>();
        // 部门
        SysDept query = new SysDept();
        query.setParentId(100L);
        query.setStatus("0");
        query.setDelFlag("0");
        List<Long> excludeDeptIds = Arrays.asList(100L,103L,135L,136L,137L);
        List<SysDept> deptList = deptService.selectDeptList(query).stream().filter(item->{
            if(excludeDeptIds.contains(item.getDeptId())){
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        List<CompletableFuture<QuotaAllocateInfoVo.TableItemInfo>> futureList = new LinkedList<>();

        int jgAllowPercent = 20;//机关优秀人数比例 20%
        int syAllowPercent = 20;//事业优秀人数比例 20%

        CompletableFuture<QuotaAllocateInfoVo.TableItemInfo> jgFuture = CompletableFuture.supplyAsync(() -> {
            QuotaAllocateInfoVo.TableItemInfo jgLeaderItem = new QuotaAllocateInfoVo.TableItemInfo();
            jgLeaderItem.setSpecialName("二级巡视员、总师、处（室）负责人（含主持工作）、督查员");
            List<Long> jgLeaderIds = assessUserMapper.getUserList(null, Arrays.asList("1"), Arrays.asList("121", "212"), null,true,false);
            jgLeaderItem.setUserIds(jgLeaderIds);
            jgLeaderItem.setDeptType("机关");
//            jgLeaderItem.setAllocateNum(5);
            int allowNum = Math.round(jgLeaderIds.size() * jgAllowPercent / 100f);
            jgLeaderItem.setAllocateNum(allowNum == 0 ? 1 : allowNum);
            return jgLeaderItem;
        });

        futureList.add(jgFuture);

        CompletableFuture<QuotaAllocateInfoVo.TableItemInfo> syFuture = CompletableFuture.supplyAsync(() -> {
            QuotaAllocateInfoVo.TableItemInfo syLeaderItem = new QuotaAllocateInfoVo.TableItemInfo();
            syLeaderItem.setSpecialName("事业单位主要负责人");
            List<Long> syLeaderIds = assessUserMapper.getUserList(null,Arrays.asList("2","3"), Arrays.asList("121"), null,false,false);
            syLeaderItem.setUserIds(syLeaderIds);
            syLeaderItem.setDeptType("事业");
//            syLeaderItem.setAllocateNum(1);
            int allowNum = Math.round(syLeaderIds.size() * syAllowPercent / 100f);
            syLeaderItem.setAllocateNum(allowNum == 0 ? 1 : allowNum);
            return syLeaderItem;
        });

        futureList.add(syFuture);

        for (int i = 0; i < deptList.size(); i++) {
//            SysDept dept = deptList.get(i);
//            Long deptId = dept.getDeptId();
//            QuotaAllocateInfoVo.TableItemInfo itemInfo = new QuotaAllocateInfoVo.TableItemInfo();
//
//            itemInfo.setDeptId(deptId);
//            itemInfo.setDeptName(dept.getDeptAbbreviation());
//            List<Long> userIds = userMapper.getUserList(deptId,null,null, Arrays.asList("121","212"));
//            itemInfo.setUserIds(userIds);
//            String deptType = dept.getDeptCode().substring(0, 1);
//            if(deptType.equals("A")){
//                itemInfo.setDeptType("机关");
//                int allowNum = userIds.size() * 20 /100;
//                itemInfo.setAllocateNum(allowNum == 0 ? 1 :allowNum);
//            }
//            if(deptType.equals("B") || deptType.equals("C")){
//                itemInfo.setDeptType("事业");
//                int allowNum = userIds.size() * 15 /100;
//                itemInfo.setAllocateNum(allowNum == 0 ? 1 :allowNum);
//            }
//
//            list.add(itemInfo);
            SysDept dept = deptList.get(i);
            CompletableFuture<QuotaAllocateInfoVo.TableItemInfo> future = CompletableFuture.supplyAsync(() -> {

                Long deptId = dept.getDeptId();
                QuotaAllocateInfoVo.TableItemInfo itemInfo = new QuotaAllocateInfoVo.TableItemInfo();

                itemInfo.setDeptId(deptId);
                itemInfo.setDeptName(dept.getDeptAbbreviation());
                List<Long> userIds = assessUserMapper.getUserList(deptId, null, null, Arrays.asList("121", "212"),false,true);
                itemInfo.setUserIds(userIds);
                String deptType = dept.getDeptCode().substring(0, 1);
                if (deptType.equals("A")) {
                    itemInfo.setDeptType("机关");
                    int allowNum = Math.round(userIds.size() * jgAllowPercent / 100f);
                    itemInfo.setAllocateNum(allowNum == 0 ? 1 : allowNum);
                }
                if (deptType.equals("B") || deptType.equals("C")) {
                    itemInfo.setDeptType("事业");
                    int allowNum = Math.round(userIds.size() * syAllowPercent / 100f);
                    itemInfo.setAllocateNum(allowNum == 0 ? 1 : allowNum);
                }
                return itemInfo;
            });
            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        for (int i = 0; i < futureList.size(); i++) {
            try {
                QuotaAllocateInfoVo.TableItemInfo itemInfo = futureList.get(i).get();
                list.add(itemInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        redisCache.setCacheList(DEFAULT_TABLE_INFO_KEY,list);
        redisCache.expire(DEFAULT_TABLE_INFO_KEY,30, TimeUnit.MINUTES);
        return list;
    }

    @Override
    public BizAccessQuotaAllocateInfo getDetailInfo(String id) {
        BizAccessQuotaAllocateInfo info = bizAccessQuotaAllocateInfoMapper.selectBizAccessQuotaAllocateInfoById(id);
        return info;
    }

    @Override
    public void audit(BizAccessQuotaAllocateInfo info) {
        String id = info.getId();
        String status = info.getStatus();
        if(StringUtils.isBlank(id)){
            throw new IllegalArgumentException("请选择要审核的记录");
        }
        if(StringUtils.isBlank(status)){
            throw new IllegalArgumentException("请选择审核状态");
        }
        info.setUpdateBy(SecurityUtils.getUsername());
        info.setUpdateTime(DateUtils.getNowDate());
        bizAccessQuotaAllocateInfoMapper.updateBizAccessQuotaAllocateInfo(info);

    }

    @Override
    public void reSubmit(String id) {
        BizAccessQuotaAllocateInfo info = bizAccessQuotaAllocateInfoMapper.selectBizAccessQuotaAllocateInfoById(id);
        if(!"3".equals(info.getStatus())){
            throw new IllegalArgumentException("该记录当前不允许重新提交审核");
        }
        info.setStatus("1");
        info.setRejectReason("");
        info.setUpdateBy(SecurityUtils.getUsername());
        info.setUpdateTime(DateUtils.getNowDate());
        bizAccessQuotaAllocateInfoMapper.updateBizAccessQuotaAllocateInfo(info);
    }

    @Override
    public QuotaAllocateInfoVo.TableItemInfo getDeptAllocateInfo(Long deptId, String year) {
        BizAccessQuotaAllocateInfo query = new BizAccessQuotaAllocateInfo();
        query.setYear(year);
        query.setStatus("2");
        List<BizAccessQuotaAllocateInfo> infoList = selectBizAccessQuotaAllocateInfoList(query);
        if(null == infoList || infoList.isEmpty()){
            return null;
        }
        String tableInfoStr = infoList.get(0).getTableInfo();
        List<QuotaAllocateInfoVo.TableItemInfo> itemList = JSONObject.parseArray(tableInfoStr, QuotaAllocateInfoVo.TableItemInfo.class);
        QuotaAllocateInfoVo.TableItemInfo itemInfo = itemList.stream().filter(i -> deptId.equals(i.getDeptId())).findFirst().orElse(null);
        return itemInfo;
    }
    //查询没有部门的二级巡视员那些的优秀名额

    /**
     *
     * @param year 年度
     * @param type 类型，机关，事业
     * @return
     */
    @Override
    public QuotaAllocateInfoVo.TableItemInfo getNoDeptAllocateInfo(String year,String type) {
        BizAccessQuotaAllocateInfo query = new BizAccessQuotaAllocateInfo();
        query.setYear(year);
        query.setStatus("2");
        List<BizAccessQuotaAllocateInfo> infoList = selectBizAccessQuotaAllocateInfoList(query);
        if(null == infoList || infoList.isEmpty()){
            return null;
        }
        String tableInfoStr = infoList.get(0).getTableInfo();
        List<QuotaAllocateInfoVo.TableItemInfo> itemList = JSONObject.parseArray(tableInfoStr, QuotaAllocateInfoVo.TableItemInfo.class);
        QuotaAllocateInfoVo.TableItemInfo itemInfo = itemList.stream().filter(i -> i.getDeptId()==null&&type.equals(i.getDeptType())).findFirst().orElse(null);
        return itemInfo;
    }


    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void publish2Notice(String id) {
        BizAccessQuotaAllocateInfo info = selectBizAccessQuotaAllocateInfoById(id);
        if(null == info){
            throw new CustomException("信息不存在，请刷新重试");
        }
        if(!"2".equals(info.getStatus())){
            throw new CustomException("信息尚未审核通过，不允许发布公告");
        }

        SysNotice notice = new SysNotice();
        String title = StringUtils.format("{}年度机关事业单位考核人数及优秀等次名额分配表",info.getYear());
        notice.setNoticeTitle(title);
        notice.setNoticeType("2");
        notice.setStatus("0");
        String bizUrl = NoticeBizUrlTpl.ALLOCATE_INFO.build(info.getId());
        notice.setBizUrl(bizUrl);

        noticeService.insertNotice(notice);

        info.setNoticeFlag("2");
        updateBizAccessQuotaAllocateInfo(info);

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void revokeNotice(String id) {
        BizAccessQuotaAllocateInfo info = selectBizAccessQuotaAllocateInfoById(id);
        if(null == info){
            throw new CustomException("信息不存在，请刷新重试");
        }

        String bizUrl = NoticeBizUrlTpl.ALLOCATE_INFO.build(info.getId());
        noticeService.deleteByBizUrl(bizUrl);

        info.setNoticeFlag("1");
        updateBizAccessQuotaAllocateInfo(info);

    }
}
