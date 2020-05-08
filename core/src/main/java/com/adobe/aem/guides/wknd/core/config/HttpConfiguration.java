package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.servlets.
 * @Description: 这个接口表示一个OSGi配置，可以在./system/console/configMgr 找到它.
 * @date 2020/04/09 12:22 下午.
 */
@ObjectClassDefinition(
        name = "Http Configuration",
        description = "配置URL参数，以对JSON web服务进行HTTP调用")
public @interface HttpConfiguration {


    /**
     * 是否启用配置.
     * @return Boolean.
     */
    @AttributeDefinition(
            name = "Enable config",
            description = "是否启用配置",
            type = AttributeType.BOOLEAN)
    public boolean enableConfig();

    /**
     * 此方法返回正在使用的协议.
     *
     * @return Protocol.
     */
    @AttributeDefinition(
            name = "Protocol",
            description = "选择协议",
            options = {
                    @Option(label = "HTTP", value = "http"), @Option(label = "HTTPS", value = "https") })
    public String getProtocol();

    /**
     * 返回服务器.
     *
     * @return String.
     */
    @AttributeDefinition(
            name = "Server",
            description = "输入服务器名")
    public String getServer();

    /**
     * 返回接口名.
     *
     * @return {@link String}
     */
    @AttributeDefinition(
            name = "Endpoint",
            description = "输入接口名")
    public String getEndpoint();
}
