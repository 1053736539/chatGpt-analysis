package com.cb.system.service;


import com.cb.common.core.domain.vo.ExportUserAppointInfo;
import com.cb.common.core.domain.vo.ExportUserVo;

import java.util.List;

public interface IExportUserVoService {

    /**
     * 导出用户名册
     * @param userRoster
     * @return
     */
    public List<ExportUserVo> selectUserExportList(ExportUserVo userRoster);

    /**
     * 选拔任用资格信息担任正处级领导职务人员导出
     * @param userAppointInfo
     * @return
     */
    public List<ExportUserAppointInfo> selectUserAppointZcjInfoList(ExportUserAppointInfo userAppointInfo);

    /**
     * 导出担任副处级领导职务
     * @param userAppointInfo
     * @return
     */
    public List<ExportUserAppointInfo> selectUserAppointFcjInfoList(ExportUserAppointInfo userAppointInfo);

    /**
     * 导出担任事业单位正处级领导职务（管理五级岗位）
     * @param userAppointInfo
     * @return
     */
    public List<ExportUserAppointInfo> selectUserAppointSyZcjInfoList(ExportUserAppointInfo userAppointInfo);
}
