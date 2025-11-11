package com.cb.controller;

import com.cb.common.core.domain.AjaxResult;
import com.cb.enums.FieldTypeEnum;
import com.cb.service.EsService;
import com.cb.vo.EsField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es/test")
public class EsTestController {

    @Autowired
    private EsService esService;
    /*@GetMapping("/selectDataById")
    public AjaxResult selectDataById(String indexName,String id){
        Map map = esService.selectDataById(indexName, id, Map.class);
        return AjaxResult.success(map);
    }*/
/*
    @GetMapping("/selectDataList")
    public AjaxResult selectDataList(String indexName,String search){
        EsField esField1 = new EsField("title",search,FieldTypeEnum.VAGUE_QUERY);
        EsField esField2 = new EsField("labels",search,FieldTypeEnum.VAGUE_QUERY);
        List conditionFileds = new ArrayList<EsField>();
        conditionFileds.add(esField1);
        conditionFileds.add(esField2);
        List list = esService.selectDataList(indexName, conditionFileds, Map.class);
        return AjaxResult.success(list);
    }*/

}
