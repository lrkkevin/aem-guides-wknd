package com.adobe.aem.guides.wknd.core.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.listeners.
 * @Description: 监听JCR事件的事件监听器.
 * @date 2020/4/13.
 */
@Component(service = EventListener.class, immediate = true)
public class CustomEventListener implements EventListener {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(CustomEventListener.class);

  /**
   * 资源解析器工厂.
   */
  @Reference
  private ResourceResolverFactory resolverFactory;


  @Reference
  private SlingRepository repository;

  /**
   * 会话对象.
   */
  private Session session;


  @Activate
  protected void activate(ComponentContext componentContext) {

    log.info("-------<初始化开始>----------");

    try {

      //1.此Map将用于通过getServiceResourceResolver()方法获取会话.
      Map<String, Object> params = new HashMap<>();

      //2.在Map中添加服务名称.
      params.put(ResourceResolverFactory.SUBSERVICE, "eventingService");

      //3.从服务工厂获取资源解析器.
      ResourceResolver resolver = resolverFactory.getServiceResourceResolver(params);

      //4、将资源解析器转换为会话对象
      session = resolver.adaptTo(Session.class);

      log.info("-------< 创建session >----------");

      //5.添加 event listener。
      session.getWorkspace().getObservationManager().addEventListener(this,
          Event.PROPERTY_ADDED | Event.NODE_ADDED, "/apps/wknd", true, null, null, false);

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Deactivate
  protected void deactivate() {
    if (session != null) {
      session.logout();
    }
  }

  @Override
  public void onEvent(EventIterator events) {

    try {
      while (events.hasNext()) {
        log.info("路径: {} ", events.nextEvent().getPath());
      }
    } catch (Exception e) {
      log.error("异常发生", e);
    }
  }
}
