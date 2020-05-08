package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.config.HttpConfiguration;
import com.adobe.aem.guides.wknd.core.services.HttpService;
import com.adobe.aem.guides.wknd.core.utils.Network;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.services.impl.
 * @Description: HttpService接口的实现类，这个类也从OSGi配置中读取值.
 * @date 2020/4/9.
 */
@Component(service = HttpService.class, immediate = true)
@Designate(ocd = HttpConfiguration.class)
public class HttpServiceImpl implements HttpService {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(HttpServiceImpl.class);

    /**
     * OSGi配置类的实例.
     */
    private HttpConfiguration configuration;

    @Activate
    protected void activate(HttpConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * 调用.
     */
    @Override
    public String makeHttpCall() {

        log.info("----------< 读取配置值 >----------");

        try {
            //从配置中读取值.
            boolean enable = configuration.enableConfig();
            String protocol = configuration.getProtocol();
            String server = configuration.getServer();
            String endpoint = configuration.getEndpoint();
            log.info(String.format("----------< 读取配置值结果：\n >----------enable = %s , protocol = %s , server = %s , " +
                    "endpoint = %s ,", enable, protocol, server, endpoint));

            //构建URL.
            String url = String.format("%s://%s/%s", protocol, server, endpoint);
            log.info(String.format("----------<请求URL >-----url = %s-----", url));
            //只有在“enable”为真时才调用HTTP.
            if (enable) {
                //进行实际的HTTP调用.
                String response = Network.readJson(url);

                //在日志中打印响应.
                log.info("----------< 来自web服务的JSON响应是 >----------");
                log.info(response);
                return response;
            } else {
                log.info("----------< 未启用配置 >----------");
                return "未启用配置";
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return String.format("发生异常：%s", e.getMessage());
        }
    }

}
