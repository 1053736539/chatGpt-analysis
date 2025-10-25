package com.cb.system.service.impl;

import com.cb.common.utils.DateUtils;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.domain.SysNotice;
import com.cb.system.mapper.SysNoticeMapper;
import com.cb.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        if(StringUtils.isBlank(notice.getCreateBy())){
            try{
                notice.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        if(StringUtils.isBlank(notice.getUpdateBy())){
            try{
                notice.setUpdateBy(SecurityUtils.getUsername());
            } catch (Exception e){}
        }
        notice.setUpdateTime(DateUtils.getNowDate());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    @Override
    public void changeTopFlag(Long id) {
        SysNotice notice = selectNoticeById(id);
        if(null == notice){
            throw new IllegalArgumentException("未获取到公告");
        }
        String flag = "0".equals(notice.getTopFlag()) ? "1" : "0";
        notice.setTopFlag(flag);
        updateNotice(notice);
    }

    @Override
    public void changeStatus(Long id) {
        SysNotice notice = selectNoticeById(id);
        if(null == notice){
            throw new IllegalArgumentException("未获取到公告");
        }
        String flag = "0".equals(notice.getStatus()) ? "1" : "0";
        notice.setStatus(flag);
        updateNotice(notice);
    }

    @Override
    public void deleteByBizUrl(String bizUrl) {
        if(StringUtils.isBlank(bizUrl)){
            return;
        }
        noticeMapper.deleteByBizUrl(bizUrl);
    }
}
