package com.adobe.aem.guides.wknd.core.services;

import java.util.Map;

/**
 * @author kevin.
 * @Title: aem-guides-wknd.
 * @Package com.adobe.aem.guides.wknd.core.services.
 * @Description: todo.
 * @date 2020/5/6.
 */
public interface NotificationService {

  /**
   * 通知保存接口.
   * @param map 入参.
   * @return 结果.
   */
  String saveNotification(Map<String,Object> map);

}
