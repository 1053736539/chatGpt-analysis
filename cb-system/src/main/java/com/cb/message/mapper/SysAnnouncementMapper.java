package com.cb.message.mapper;

import com.cb.message.domain.SysAnnouncement;

import java.util.List;

/**
 * 系统消息Mapper接口
 *
 * @author ouyang
 * @date 2023-07-12
 */
public interface SysAnnouncementMapper {
    /**
     * 查询系统消息
     *
     * @param id 系统消息ID
     * @return 系统消息
     */
    public SysAnnouncement selectSysAnnouncementById(String id);

    /**
     * 查询系统消息列表
     *
     * @param sysAnnouncement 系统消息
     * @return 系统消息集合
     */
    public List<SysAnnouncement> selectSysAnnouncementList(SysAnnouncement sysAnnouncement);

    /**
     * 新增系统消息
     *
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    public int insertSysAnnouncement(SysAnnouncement sysAnnouncement);

    /**
     * 修改系统消息
     *
     * @param sysAnnouncement 系统消息
     * @return 结果
     */
    public int updateSysAnnouncement(SysAnnouncement sysAnnouncement);

    /**
     * 删除系统消息
     *
     * @param id 系统消息ID
     * @return 结果
     */
    public int deleteSysAnnouncementById(String id);

    /**
     * 批量删除系统消息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAnnouncementByIds(String[] ids);


}
