package com.cb.web.controller.message;

import com.cb.common.core.controller.BaseController;
import com.cb.common.core.domain.AjaxResult;
import com.cb.common.core.page.TableDataInfo;
import com.cb.message.domain.MessageVo;
import com.cb.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/myMessage")
public class SysMessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public TableDataInfo list(MessageVo vo) {
        startPage();
        List<MessageVo> list = messageService.selectList(vo);
        return getDataTable(list);
    }

    @GetMapping("/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(messageService.getInfo(id));
    }


    @GetMapping("/homePageMessage")
    public AjaxResult homePageList() {
        MessageVo vo = new MessageVo();
        List<MessageVo> list = messageService.selectUnReadList(vo);
        int total = messageService.countUnRead(vo);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("msgList", list);
        return AjaxResult.success(map);
    }

    @PutMapping("/readMessage/{id}")
    public AjaxResult readMessage(@PathVariable("id") String id) {
        return toAjax(messageService.readMessage(id));
    }
}
