package com.adobe.aem.guides.wknd.core.workflow;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.workflow.
 * @Description: todo.
 * @date 2020/4/20.
 */
@Component(service = ParticipantStepChooser.class, property = {"chooser.label=" + "Dynamic Participant Step Example"})
public class DynamicParticipantStepExample implements ParticipantStepChooser {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
      throws WorkflowException {
    log.info("----------< [{}] >----------", this.getClass().getName());

    String participant = "admin";

    Workflow workflow = workItem.getWorkflow();

    // 获取工作流历史记录
    List<HistoryItem> workflowHistory = workflowSession.getHistory(workflow);

    if (!workflowHistory.isEmpty()) {
      // 将administrators组设置为参与者.
      participant = "administrators";
    } else {
      participant = "admin";
    }

    log.info("----------< Participant: {} >----------", participant);

    return participant;
  }

}
