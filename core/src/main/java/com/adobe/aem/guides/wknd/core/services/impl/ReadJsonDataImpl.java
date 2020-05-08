package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.constants.AppConstants;
import com.adobe.aem.guides.wknd.core.services.ReadJsonService;
import com.adobe.aem.guides.wknd.core.utils.Network;
import org.osgi.service.component.annotations.Component;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.services.impl.
 * @Description: 服务接口实现类..
 * @date 2020/3/3011:11 上午.
 */
@Component(immediate = true, service = ReadJsonService.class)
public class ReadJsonDataImpl implements ReadJsonService {

  /**
   * @return JSON String
   */
  @Override
  public String getData() {
    return Network.readJson(AppConstants.URL);
  }
}
