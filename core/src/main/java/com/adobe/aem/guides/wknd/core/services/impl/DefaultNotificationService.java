package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.NotificationService;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;
import org.apache.jackrabbit.commons.JcrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.services.impl.
 * @Description: todo.
 * @date 2020/5/6.
 */
public class DefaultNotificationService implements NotificationService {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultNotificationService.class);

  /**
   * 通知保存接口.
   *
   * @param map 入参.
   * @return 结果.
   */
  @Override
  public String saveNotification(Map<String, Object> map) {
    try {
      //Create a connection to the CQ repository running on local host
      Repository repository = JcrUtils.getRepository("http://10.60.69.92:4502/crx/server");

      //Create a Session
      javax.jcr.Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

      //Get a node that represents the root node
      Node root = session.getRootNode();
      System.out.println(root.getPath());

      Node adobe = root.addNode("apps/wknd");
      Node day = adobe.addNode("notification");
      day.setPrimaryType("nt:unstructured");
      day.setProperty("message", "Adobe CQ is part of the Adobe Digital Marketing Suite!");

      // Retrieve content
      Node node = root.getNode("apps/adobe/day");
      System.out.println("NodePath: " + node.getPath());
      System.out.println("Node Property Value: " + node.getProperty("message").getString());

      session.save();
      session.logout();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {

    NotificationService notificationService = new DefaultNotificationService();
    notificationService.saveNotification(Maps.newHashMap());

  }

}
