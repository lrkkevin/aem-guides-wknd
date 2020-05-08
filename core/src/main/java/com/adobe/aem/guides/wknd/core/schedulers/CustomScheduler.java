package com.adobe.aem.guides.wknd.core.schedulers;


import com.adobe.aem.guides.wknd.core.config.SlingSchedulerConfiguration;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.schedulers.
 * {@link https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/package-summary.html}
 * @Description: 一个使用OSGi注解的Sling调度器示例.
 * @date 2020/4/10.
 */
@Component(immediate = true, service = CustomScheduler.class)
@Designate(ocd = SlingSchedulerConfiguration.class)
public class CustomScheduler implements Runnable {


  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(CustomScheduler.class);

  /**
   * 要从配置中读取的自定义参数.
   */
  private String customParameter;

  /**
   * 调度程序的Id(基于其名称).
   */
  private int schedulerId;

  /**
   * 调度器实例注入.
   */
  @Reference
  private Scheduler scheduler;

  /**
   * 初始化方法.
   *
   * @param config 调度器配置.
   */
  @Activate
  protected void activate(SlingSchedulerConfiguration config) {
    //1.获取scheduler id
    schedulerId = config.schdulerName().hashCode();
    //2.获取用户自定义参数.
    customParameter = config.customParameter();
  }

  /**
   * 服务组件的修改方法.
   *
   * @param config 调度器配置.
   */
  @Modified
  protected void modified(SlingSchedulerConfiguration config) {

    //1.删除调度器.
    removeScheduler();
    //2.更新调度程序id.
    schedulerId = config.schdulerName().hashCode();
    //3.再次添加调度程序.
    addScheduler(config);
  }

  /**
   * 服务组件的停用方法.
   *
   * @param config 调度器配置.
   */
  @Deactivate
  protected void deactivate(SlingSchedulerConfiguration config) {
    removeScheduler();
  }


  /**
   * 删除调度器.
   */
  private void removeScheduler() {
    log.info("删除调度器: {}", schedulerId);
    scheduler.unschedule(String.valueOf(schedulerId));
  }

  /**
   * 添加调度程序.
   *
   * @param config 调度器配置.
   */
  private void addScheduler(SlingSchedulerConfiguration config) {

    //检查调度程序是否已启用.
    if (config.enabled()) {

      //1.Scheduler将cron表达式作为参数并相应地运行.
      ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());

      //2.添加一些属性.
      scheduleOptions.name(config.schdulerName());
      scheduleOptions.canRunConcurrently(false);

      //3.添加调度工作。
      scheduler.schedule(this, scheduleOptions);
      log.info("Scheduler added");

    } else {
      log.info("调度器未启用");
    }
  }

  /**
   * 执行作业。
   */
  @Override
  public void run() {
    log.info("自定义调度器现在使用传递的自定义参数customParameter运行 {}", customParameter);
  }

}
