package com.adobe.aem.guides.wknd.core.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.config.
 * @Description: 这是一个配置类，它获取调度程序要运行的相关属性.
 * @date 2020/4/10.
 */
@ObjectClassDefinition(name = "SlingSchedulerConfiguration", description = "Sling scheduler 配置")
public @interface SlingSchedulerConfiguration {

    /**
     * 调度器的名称.
     *
     * @return {@link String}
     */
    @AttributeDefinition(
            name = "Scheduler name",
            description = "调度器的名称",
            type = AttributeType.STRING)
    public String schdulerName() default "自定义Sling调度器配置";

    /**
     * 调度程序是否启动.
     *
     * @return {@link Boolean}
     */
    @AttributeDefinition(
            name = "Enabled",
            description = "True代表启动",
            type = AttributeType.BOOLEAN)
    public boolean enabled() default false;

    /**
     * Cron表达式.
     *
     * @return {@link String}
     */
    @AttributeDefinition(
            name = "Cron Expression",
            description = "调度程序使用的Cron表达式",
            type = AttributeType.STRING)
    public String cronExpression() default "0 * * * * ?";

    /**
     * 自定义参数.
     *
     * @return {@link String}
     */
    @AttributeDefinition(
            name = "Custom Parameter",
            description = "自定义参数",
            type = AttributeType.STRING)
    public String customParameter() default "AEM Scheduler Demo";
}