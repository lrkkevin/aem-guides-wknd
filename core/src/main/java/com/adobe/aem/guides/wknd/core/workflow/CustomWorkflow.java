package com.adobe.aem.guides.wknd.core.workflow;


import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.workflow.
 * @Description: 自定义工作流步骤.
 * @date 2020/4/14.
 */
@Component(service = WorkflowProcess.class, property = {"process.label = Custom Workflow"})
public class CustomWorkflow implements WorkflowProcess {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(CustomWorkflow.class);

  /**
   * 执行工作流.
   *
   * @param workItem 流程节点.
   * @param workflowSession 流程会话.
   * @param metaDataMap 数据.
   */
  @Override
  public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
      throws WorkflowException {

    log.info("------------< 执行workflow>--------------------");
    try {
      String textValue = metaDataMap.get("textValue", "empty");
      String dateValue = metaDataMap.get("dateValue", "empty");
      log.info("--------------<Text: {}>--------------", textValue);
      log.info("--------------<Date: {}>--------------", dateValue);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
