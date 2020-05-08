package com.adobe.aem.guides.wknd.core.servlets;


import com.adobe.aem.guides.wknd.core.services.HttpService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.servlets.
 * @Description: 此方法调用HTTP并通过OSGi配置从JSON web服务读取值.
 * @date 2020/4/9.
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/demo/httpcall" })
public class HttpServlet extends SlingAllMethodsServlet {

    /**
     *  serialVersionUID.
     */
    private static final long serialVersionUID = -2014397651676211439L;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);

    @Reference
    private HttpService httpService;

    /**
     *  doGet().
     */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        try {
            String jsonResponse = httpService.makeHttpCall();
            //在浏览器上打印json响应.
            response.getWriter().println(jsonResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     *  doGet().
     */
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        try {
            String jsonResponse = httpService.makeHttpCall();
            //在浏览器上打印json响应.
            response.getWriter().println(jsonResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
