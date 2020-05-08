package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.utils.Network;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.PrintWriter;

import static com.adobe.aem.guides.wknd.core.constants.AppConstants.URL;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.servlets.
 * @Description: 这个servlet使用HTTP GET方法从RESTful web服务读取数据..
 * @date 2020/3/312:22 下午.
 */
@Component(service = Servlet.class, property = {
        Constants.SERVICE_DESCRIPTION + "=从外部web服务读取数据的JSON Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/readjson"})
public class JsonObjectServlet extends SlingSafeMethodsServlet {
    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(JsonObjectServlet.class);
    /**
     * serialVersionUID.
     **/
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        final Resource resource = request.getResource();
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            log.info("Reading the data from the webservice");
            //1.获取数据.
            String responseString = Network.readJson(URL);
            //2.返回数据.
            out.write("web服务读取数据结果：\n"+responseString);
            log.info(String.format("======web服务读取数据结果\n %s=======",responseString));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}
