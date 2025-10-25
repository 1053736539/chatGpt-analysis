package com.cb.web.controller.cadretrain;

import com.cb.cadretrain.domain.BizTrainRecords;
import com.cb.cadretrain.domain.BizTrainRecordsResult;
import com.cb.cadretrain.domain.TrainDurationResult;
import com.cb.cadretrain.service.IBizTrainRecordsService;
import com.cb.common.annotation.Log;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 干部教育培训,培训记录Controller
 *
 * @author yangcd
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/cadretrain/records")
public class BizTrainRecordsController extends BaseController {
    @Autowired
    private IBizTrainRecordsService bizTrainRecordsService;

    /**
     * 查询培训记录列表
     */
    //@PreAuthorize("@ss.hasPermi('cadretrain:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsList(bizTrainRecords);
        return getDataTable(list);
    }

    @GetMapping("/selectListByUserInfo")
    public TableDataInfo userInfoList(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.selectUserInfoBizTrainRecordsList(bizTrainRecords);
        return getDataTable(list);
    }

    /**
     *
     * @param bizTrainRecords
     * @return
     */
    @GetMapping("/selectListByUserId")
    public AjaxResult selectListByUserId(BizTrainRecords bizTrainRecords) {
//        startPage();
        BizTrainRecords query = new BizTrainRecords();
        query.setUserId(bizTrainRecords.getUserId());
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsList(query);
        return AjaxResult.success(list);
    }

    /**
     * 导出培训记录列表
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:records:export')")
    @Log(title = "培训记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BizTrainRecords bizTrainRecords) {
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsList(bizTrainRecords);
        ExcelUtil<BizTrainRecords> util = new ExcelUtil<BizTrainRecords>(BizTrainRecords.class);
        return util.exportExcel(list, "records");
    }

    /**
     * 获取培训记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(bizTrainRecordsService.selectBizTrainRecordsById(id));
    }

    /**
     * 新增培训记录
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:records:add')")
    @Log(title = "培训记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTrainRecords bizTrainRecords) {
        return toAjax(bizTrainRecordsService.insertBizTrainRecords(bizTrainRecords));
    }

    /**
     * 修改培训记录
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:records:edit')")
    @Log(title = "培训记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTrainRecords bizTrainRecords) {
        return toAjax(bizTrainRecordsService.updateBizTrainRecords(bizTrainRecords));
    }

    /**
     * 删除培训记录
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:records:remove')")
    @Log(title = "培训记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizTrainRecordsService.deleteBizTrainRecordsByIds(ids));
    }

    /**
     * @param bizTrainRecords
     * @Auther: yangcd
     * @Date: 2023/10/31 17:46
     * @Description: 人事处审核培训记录 cadretrain:index:edit
     */
    @PreAuthorize("@ss.hasPermi('cadretrain:index:edit')")
    @Log(title = "人事处审核培训记录", businessType = BusinessType.UPDATE)
    @PutMapping("/recordsAudit")
    public AjaxResult recordsAudit(@RequestBody BizTrainRecords bizTrainRecords) {
        return toAjax(bizTrainRecordsService.updateBizTrainRecordsByAudit(bizTrainRecords));
    }

    /**
     * @param bizTrainRecords
     * @Auther: yangcd
     * @Date: 2023/10/31 17:50
     * @Description: 人事处查询所有
     */
    @GetMapping("/listAll")
    public TableDataInfo listAll(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsAllList(bizTrainRecords);
        return getDataTable(list);
    }

    /**
     * 获取人事处审核的列表
     * @param bizTrainRecords
     * @return
     */
    @GetMapping("/listByPersonnelDept")
    public TableDataInfo listByPersonnelDept(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsByPersonnelDept(bizTrainRecords);
        return getDataTable(list);
    }

    /**
     * 获取部门审核的列表
     * @param bizTrainRecords
     * @return
     */
    @GetMapping("/listByDept")
    public TableDataInfo listByDept(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.selectBizTrainRecordsByDept(bizTrainRecords);
        return getDataTable(list);
    }

    /**
     * 获取部门的所有培训记录
     * @param bizTrainRecords
     * @return
     */
    @GetMapping("/listByDeptMgr")
    public TableDataInfo listByDeptMgr(BizTrainRecords bizTrainRecords) {
        startPage();
        List<BizTrainRecords> list = bizTrainRecordsService.listByDeptMgr(bizTrainRecords);
        return getDataTable(list);
    }

    @GetMapping("/getTrainDuration1")
    public AjaxResult  getTrainDuration1(
            @RequestParam("trainYear") String trainYear,
            @RequestParam(value = "quarter", required = false) Integer quarter,
            @RequestParam(value = "traineeName",required = false) String traineeName ,
            @RequestParam("approvalStatus") String approvalStatus
    ) {
        // 处理客户端请求，调用Service层的方法
        List<TrainDurationResult> result = bizTrainRecordsService.getTrainDurationByYearAndQuarter1(trainYear, quarter, traineeName,approvalStatus);

        return AjaxResult.success(result);
    }

   /**
    * @Auther: yangcd
    * @Date: 2023/11/6 14:46
    * @param trainYear
    * @Description: 按年时间查询学时
    */
   @GetMapping("/getTrainDuration")
   public TableDataInfo  getTrainDuration(
           @RequestParam(value = "trainYear", required = false) String trainYear,
           BizTrainRecords bizTrainRecords,
           @RequestParam(value = "traineeName",required = false) String traineeName
   ) {
       startPage();
       // 处理客户端请求，调用Service层的方法
       List<BizTrainRecordsResult> list = bizTrainRecordsService.getTrainDurationByYearAndQuarter(trainYear, bizTrainRecords, traineeName);

//       return AjaxResult.success(result);
       return getDataTable(list);
   }

}
