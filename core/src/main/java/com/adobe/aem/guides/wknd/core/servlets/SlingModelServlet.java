package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.models.UserModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.servlets.
 * @Description: 这个servlet使用HTTP GET方法从RESTful web服务读取数据..
 * @date 2020/04/07 9:22 上午.
 */
@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=Sling Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/slingmodel/user"})
public class SlingModelServlet extends SlingSafeMethodsServlet {

    /**
     * uuid.
     */
    private static final long serialVersionUID = 7558680464517017317L;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(SlingModelServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try (ResourceResolver resourceResolver = request.getResourceResolver()) {
            log.info("----------< Processing starts >----------");
            //获取资源.
            Resource resource = resourceResolver
                    .getResource("/content/forms/af/wknd/test-dome/jcr:content/guideContainer/rootPanel/items/user");
            //将资源调整为UserModel类.
            UserModel model = resource.adaptTo(UserModel.class);
            log.info("===========存储在资源中的数据是=============="+model.toString()+"=========================");
            log.info("=============存储在资源中的数据是============"+model.toString()+"=========================");
            //
            response.getWriter()
                    .print("存储在资源中的数据是:\nFirst Name: " + model.getFirstName() + "\nLast Name: "
                            + model.getLastName() + "\nGender: " + model.getGender() + "\nCountry: "
                            + model.getCountry());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}
