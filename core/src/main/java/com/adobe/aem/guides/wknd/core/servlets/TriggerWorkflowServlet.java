package com.adobe.aem.guides.wknd.core.servlets;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;


/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.servlets.
 * @Description: todo.
 * @date 2020/4/20.
 */
@Component(
    service = Servlet.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Trigger Worklow Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/triggerWorkflow"
    }
)
public class TriggerWorkflowServlet extends SlingSafeMethodsServlet {

  private static final long serialVersionUID = 4235730140092282985L;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

    try {
      final String payloadPath = "/content/we-retail/language-masters/en/equipment";

      // 获取资源解析器
      final ResourceResolver resolver = request.getResourceResolver();

      // 从资源解析器的工作流程
      final WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

      //工作流程模型路径-这是已经创建的工作流程
      final String model = "/var/workflow/models/metadata_map_example";

      // 获取工作流模型对象
      final WorkflowModel workflowModel = workflowSession.getModel(model);

      // 经由JCR创建工作流数据（或有效载荷）对象指向一个资源
      // 路径（或者，一个JCR_UUID可以使用）
      final WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payloadPath);

      // （可选）通过Map
      final Map<String, Object> workflowMetadata = new HashMap<>();
      workflowMetadata.put("anyData", "You Want");
      workflowMetadata.put("evenThingsLike", new Date());

      // 开始工作流程！
      workflowSession.startWorkflow(workflowModel, workflowData, workflowMetadata);

      log.info("Workflow: {} 开始", model);

      response.getWriter().println("Workflow 已执行");

    } catch (WorkflowException | IOException e) {
      log.error(e.getMessage(), e);
    }
  }

}
