package com.cb.task.scheduler;

import com.cb.message.service.MessageService;
import com.cb.probity.domain.BizProbity;
import com.cb.probity.service.IBizProbityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 提醒廉政档案确认信息定时任务
 *
 * @author Administrator
 */
@Component
public class ProbityScheduler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private IBizProbityService probService;

    public void noticeProbity() {
        String title = "廉政档案确认";

        List<BizProbity> bizProbities = probService.selectBizProbityList(new BizProbity() {{
            setConfirmStatus("1");
        }});
        bizProbities.forEach(bizProbity -> {
            String message = "您有【" + bizProbity.getYear() + "年】的廉政档案需要确认，请及时确认！";
            messageService.sendMessage2User(title, message, null, bizProbity.getUserId().toString());
        });
    }

}
