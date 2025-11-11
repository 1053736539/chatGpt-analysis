package com.cb.assess.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.cb.assess.domain.vo.OrdinaryAssessParamVo;
import com.cb.assess.domain.vo.PersonalAssessResult;
import com.cb.assess.enums.Special;
import com.cb.assess.utils.CycleUtil;
import com.cb.common.core.domain.model.LoginUser;
import com.cb.common.utils.ServletUtils;
import com.cb.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.enums.BusinessType;
import com.cb.assess.domain.BizAssessOverallScoreLevelRecord;
import com.cb.assess.service.IBizAssessOverallScoreLevelRecordService;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 平时考核最终综合得分及建议评定等次记录Controller
 *
 * @author ouyang
 * @date 2023-12-12
 */
@RestController
@RequestMapping("/assess/finalLevelRecord")
public class BizAssessOverallScoreLevelRecordController extends BaseController {
    @Autowired
    private IBizAssessOverallScoreLevelRecordService bizAssessOverallScoreLevelRecordService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询平时考核最终综合得分及建议评定等次记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        startPage();
        List<BizAssessOverallScoreLevelRecord> list = bizAssessOverallScoreLevelRecordService.selectBizAssessOverallScoreLevelRecordList(bizAssessOverallScoreLevelRecord);
        return getDataTable(list);
    }

    @GetMapping("/listQuarter")
    public AjaxResult listQuarter(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        List<BizAssessOverallScoreLevelRecord> list = bizAssessOverallScoreLevelRecordService.selectBizAssessOverallScoreLevelRecordList(bizAssessOverallScoreLevelRecord);
        return AjaxResult.success(list);
    }



    /**
     * 导出平时考核最终综合得分及建议评定等次记录列表
     */
    @Log(title = "平时考核最终综合得分及建议评定等次记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizAssessOverallScoreLevelRecord bizAssessOverallScoreLevelRecord) {
        List<BizAssessOverallScoreLevelRecord> list = bizAssessOverallScoreLevelRecordService.selectBizAssessOverallScoreLevelRecordList(bizAssessOverallScoreLevelRecord);
        List<BizAssessOverallScoreLevelRecord> collect = list.stream().map(item -> {
            String special = item.getSpecial();
            if(Special.NORMAL.getCode().equals(special)){
                String str = CycleUtil.parseCycle(item.getCycle(), item.getCycleDesc());
                item.setSpecial("否");
                item.setQuarter(str);
            }else {
                item.setSpecial("是");
                item.setQuarter("专项考核");
            }
            return item;
        }).collect(Collectors.toList());
        ExcelUtil<BizAssessOverallScoreLevelRecord> util = new ExcelUtil<BizAssessOverallScoreLevelRecord>(BizAssessOverallScoreLevelRecord.class);
        return util.exportExcel(collect, "finalLevelRecord");
    }

    /**
     * 获取平时考核最终综合得分及建议评定等次记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizAssessOverallScoreLevelRecordService.selectBizAssessOverallScoreLevelRecordById(id));
    }

    /**
     * 新增平时考核最终综合得分及建议评定等次记录
     */
    @Log(title = "平时考核最终综合得分及建议评定等次记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAssessOverallScoreLevelRecord record) {
        Boolean aBoolean = bizAssessOverallScoreLevelRecordService.checkExist(record.getId(), record.getAssessedUserId(), record.getTaskId());
        if (aBoolean) return AjaxResult.error("存在记录");
        return toAjax(bizAssessOverallScoreLevelRecordService.insertBizAssessOverallScoreLevelRecord(record));
    }

    /**
     * 修改平时考核最终综合得分及建议评定等次记录
     */
    @Log(title = "平时考核最终综合得分及建议评定等次记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAssessOverallScoreLevelRecord record) {
        Boolean aBoolean = bizAssessOverallScoreLevelRecordService.checkExist(record.getId(), record.getAssessedUserId(), record.getTaskId());
        if (aBoolean) return AjaxResult.error("存在记录");
        return toAjax(bizAssessOverallScoreLevelRecordService.updateBizAssessOverallScoreLevelRecord(record));
    }

    /**
     * 删除平时考核最终综合得分及建议评定等次记录
     */
    @Log(title = "平时考核最终综合得分及建议评定等次记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizAssessOverallScoreLevelRecordService.deleteBizAssessOverallScoreLevelRecordByIds(ids));
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<BizAssessOverallScoreLevelRecord> util = new ExcelUtil<>(BizAssessOverallScoreLevelRecord.class);
        return util.importTemplateExcel("考核数据");
    }

    @Log(title = "导入考核数据", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<BizAssessOverallScoreLevelRecord> util = new ExcelUtil<>(BizAssessOverallScoreLevelRecord.class);
        List<BizAssessOverallScoreLevelRecord> list = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operateName = loginUser.getUsername();
        String message = bizAssessOverallScoreLevelRecordService.importData(list, updateSupport, operateName);
        return AjaxResult.success(message);
    }

    /**
     * 删除平时考核最终综合得分及建议评定等次记录
     */
    @Log(title = "平时考核结果公示", businessType = BusinessType.INSERT)
    @PostMapping("/promulgate")
    public AjaxResult promulgate(@RequestBody OrdinaryAssessParamVo param) {
        bizAssessOverallScoreLevelRecordService.publish2Notice(param);
        return AjaxResult.success();
    }

    /**
     *  获取人员的平时考核信息
     * @param result
     * @return
     */
    @GetMapping("/personalAssessResult")
    public TableDataInfo personalAssessResult(PersonalAssessResult result) {
        startPage();
        List<PersonalAssessResult> resultList = bizAssessOverallScoreLevelRecordService.selectPersonalAssessResult(result);
        return getDataTable(resultList);
    }
}
