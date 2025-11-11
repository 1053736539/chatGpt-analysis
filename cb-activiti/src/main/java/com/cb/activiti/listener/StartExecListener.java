package com.cb.activiti.listener;

import com.cb.common.utils.spring.SpringUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 流程开始前执行监听器 demo
 * @author 一只闲鹿
 */
@Component
public class StartExecListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
//        String processDefinitionId = execution.getProcessDefinitionId();
//        System.out.println("[StartExecListener]当前流程定义id：" + processDefinitionId);
//
//        IBizExampleDemoService bizExampleDemoService = SpringUtils.getBean("bizExampleDemoServiceImpl");
//        System.out.println("bizExampleDemoService=" + bizExampleDemoService);
    }
}
