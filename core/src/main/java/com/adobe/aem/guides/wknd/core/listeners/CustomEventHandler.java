package com.adobe.aem.guides.wknd.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.listeners.
 * @Description: 监听Sling事件的事件处理程序.
 * @date 2020/4/13.
 */

@Component(immediate = true, service = EventHandler.class, property = {
    Constants.SERVICE_DESCRIPTION + "= 此事件处理程序监听页面激活上的事件",
    EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
    EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
    EventConstants.EVENT_FILTER + "(&" + "(path=/content/wknd/en/*/jcr:content) (|("
        + SlingConstants.PROPERTY_CHANGED_ATTRIBUTES + "=*jcr:title) " + "(" + ResourceChangeListener.CHANGES
        + "=*jcr:title)))"})
public class CustomEventHandler implements EventHandler {

  /**
   * Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(CustomEventHandler.class);

  @Override
  public void handleEvent(Event event) {
    log.info("-------------<Event is: {}>------------------", event.getTopic());
  }
}
