package com.cb.rpa.es.controller;

import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.domain.EsPageResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.enums.FieldTypeEnum;
import com.cb.rpa.domain.vo.WebsiteReq;
import com.cb.rpa.domain.vo.WebsiteVo;
import com.cb.service.EsService;
import com.cb.vo.EsField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rpa/es/website")
public class EsWebsiteController extends BaseController {
    @Autowired
    private EsService esService;

    @GetMapping("/list")
    public TableDataInfo list(WebsiteReq req) {
        EsField esField1 = new EsField("title", req.getSearch(), FieldTypeEnum.PRECISE_QUERY);
        EsField esField2 = new EsField("labels", req.getSearch(), FieldTypeEnum.PRECISE_QUERY);
        EsField esField3 = new EsField("content", req.getSearch(), FieldTypeEnum.PRECISE_QUERY);
        List conditionFileds = new ArrayList<EsField>();
        conditionFileds.add(esField1);
        conditionFileds.add(esField2);
        conditionFileds.add(esField3);
        EsPageResult result = esService.selectDataPageList(req.getIndexName(), conditionFileds, WebsiteVo.class);
        return getEsDataTable(result);
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        WebsiteVo website = esService.selectDataById("rpa_ccdi_website", id, WebsiteVo.class);
        return AjaxResult.success(website);
    }

}
