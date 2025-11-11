package com.cb.activiti.service;

import com.cb.activiti.domain.ProcessDefinition;
import com.github.pagehelper.Page;

/**
 * @author 一只闲鹿
 */
public interface IProcessDefinitionService {

    Page<ProcessDefinition> listProcessDefinition(ProcessDefinition processDefinition);
    void deployProcessDefinition(String filePath);
    int deleteProcessDeploymentByIds(String deploymentIds) throws Exception;
    void suspendOrActiveDefinition(String id, String suspendState);

}
