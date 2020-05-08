package com.adobe.aem.guides.wknd.core.services;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.services.
 * @Description: 该接口公开了调用JSON Web服务的功能.
 * @date 2020/4/9.
 */
public interface HttpService {
    /**
     * 调用.
     *
     * @return String.
     */
    public String makeHttpCall();
}
