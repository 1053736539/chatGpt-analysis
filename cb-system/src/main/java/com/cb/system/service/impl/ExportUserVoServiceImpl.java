package com.cb.system.service.impl;

import com.cb.common.annotation.DataScope;
import com.cb.common.core.domain.vo.ExportUserAppointInfo;
import com.cb.common.core.domain.vo.ExportUserVo;
import com.cb.system.mapper.ExportUserVoMapper;
import com.cb.system.service.IExportUserVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportUserVoServiceImpl implements IExportUserVoService {
    @Autowired
    private ExportUserVoMapper  exportUserVoMapper;

    /**
     * 导出用户名册
     * @param userRoster
     * @return
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ExportUserVo> selectUserExportList(ExportUserVo userRoster) {
        String str = "";
        if(userRoster.getAbilityLabelIds()!=null && userRoster.getAbilityLabelIds().length>0){
            for (String AbilityLabel1 : userRoster.getAbilityLabelIds()) {
                str = str + AbilityLabel1 + ',';
            }
            userRoster.setAbilityLabel(str.substring(0, str.length()-1));
        }
        return exportUserVoMapper.selectUserExportList(userRoster);
    }

    /**
     * 选拔任用资格信息担任正处级领导职务人员导出
     * @param userAppointInfo
     * @return
     */
    @Override
    public List<ExportUserAppointInfo> selectUserAppointZcjInfoList(ExportUserAppointInfo userAppointInfo) {
        return exportUserVoMapper.selectUserAppointZcjInfoList(userAppointInfo);
    }

    /**
     * 导出担任副处级领导职务
     * @param userAppointInfo
     * @return
     */
    @Override
    public List<ExportUserAppointInfo> selectUserAppointFcjInfoList(ExportUserAppointInfo userAppointInfo) {
        return exportUserVoMapper.selectUserAppointFcjInfoList(userAppointInfo);
    }

    /**
     * 导出担任事业单位正处级领导职务（管理五级岗位）
     * @param userAppointInfo
     * @return
     */
    @Override
    public List<ExportUserAppointInfo> selectUserAppointSyZcjInfoList(ExportUserAppointInfo userAppointInfo) {
        return exportUserVoMapper.selectUserAppointSyZcjInfoList(userAppointInfo);
    }


}
