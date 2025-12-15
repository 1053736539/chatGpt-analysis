package com.cb.web.controller.system;

import com.cb.activiti.domain.BizLeave;
import com.cb.activiti.service.IBizLeaveService;
import com.cb.common.annotation.Log;
import com.cb.common.annotation.RepeatSubmit;
import com.cb.common.constant.HttpStatus;
import com.cb.common.constant.UserConstants;
import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.entity.SysUser;
import com.cb.common.core.page.PageDomain;
import com.cb.common.core.page.TableDataInfo;
import com.cb.common.core.page.TableSupport;
import com.cb.common.enums.BusinessType;
import com.cb.common.utils.SecurityUtils;
import com.cb.common.utils.StringUtils;
import com.cb.common.utils.poi.ExcelUtil;
import com.cb.knowledge.base.domain.entity.KnowledgeBase;
import com.cb.knowledge.base.service.IKnowledgeBaseService;
import com.cb.system.domain.SysConfig;
import com.cb.system.service.ISysConfigService;
import com.cb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 参数配置 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    @Autowired
    private ISysConfigService configService;


    @Autowired
    private ISysUserService userService;

    @Autowired
    private IKnowledgeBaseService knowledgeBaseService;

    @Autowired
    private IBizLeaveService bizLeaveService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConfig config)
    {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:config:export')")
    @GetMapping("/export")
    public AjaxResult export(SysConfig config)
    {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable Long configId)
    {
        return AjaxResult.success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public AjaxResult getConfigKey(@PathVariable String configKey)
    {
        return AjaxResult.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@Validated @RequestBody SysConfig config)
    {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(SecurityUtils.getUsername());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysConfig config)
    {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config)))
        {
            return AjaxResult.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 根据key修改配置参数
     * @param config
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @PutMapping("editByConfigKey")
    public AjaxResult editByConfigKey(@RequestBody SysConfig config)
    {
        String key = config.getConfigKey();
        String value = config.getConfigValue();
        if(StringUtils.isBlank(key)){
            return AjaxResult.error("修改参数失败，参数key不能为空");
        }
        if(StringUtils.isBlank(value)){
            return AjaxResult.error("修改参数失败，参数value不能为空");
        }
        SysConfig oldConfig = configService.selectByKeyWithoutCache(key);
        if(null == oldConfig){
            return AjaxResult.error("修改参数失败，参数不存在!");
        }
        oldConfig.setConfigValue(value);
        oldConfig.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(configService.updateConfig(oldConfig));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds)
    {
        return toAjax(configService.deleteConfigByIds(configIds));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clearCache")
    public AjaxResult clearCache()
    {
        configService.clearCache();
        return AjaxResult.success();
    }


    /**
     * 综合关键词搜索用户、知识库与请假信息
     */
    @GetMapping("/searchAll")
    public TableDataInfo searchAll(@RequestParam(required = false) String searchKeywords)
    {
        if (StringUtils.isBlank(searchKeywords))
        {
            return getDataTable(Collections.emptyList());
        }

        List<Object> results = new ArrayList<>();

        SysUser user = new SysUser();
        user.setQueryKeywords(searchKeywords);
        List<SysUser> users = userService.selectUserList(user);
        users.forEach(item -> item.setPageType(1));
        results.addAll(users);

        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.setSearchKeywords(searchKeywords);
        knowledgeBase.setDelFlag("0");
        List<KnowledgeBase> knowledgeBases = knowledgeBaseService.searchDataByKeywords(knowledgeBase);
        knowledgeBases.forEach(item -> item.setPageType(2));
        results.addAll(knowledgeBases);

        BizLeave bizLeave = new BizLeave();
        bizLeave.setSearchKeywords(searchKeywords);
        bizLeave.setDelFlag("0");
        List<BizLeave> leaves = bizLeaveService.searchBizLeaveList(bizLeave);
        leaves.forEach(item -> item.setPageType(3));
        results.addAll(leaves);

        PageDomain pageDomain = TableSupport.buildPageRequest();
        int pageNum = pageDomain.getPageNum() == null ? 1 : pageDomain.getPageNum();
        int pageSize = pageDomain.getPageSize() == null ? results.size() : pageDomain.getPageSize();
        int fromIndex = Math.max(0, (pageNum - 1) * pageSize);
        int toIndex = Math.min(fromIndex + pageSize, results.size());
        List<Object> pageRows = fromIndex >= results.size() ? Collections.emptyList() : results.subList(fromIndex, toIndex);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(pageRows);
        rspData.setTotal(results.size());
        return rspData;
    }

}
