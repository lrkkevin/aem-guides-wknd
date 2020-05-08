package com.adobe.aem.guides.wknd.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author kevin.
 * @Title: rhtj.
 * @Package com.adobe.aem.guides.wknd.core.utils.
 * @Description: 网络相关的调用类.
 * @date 2020/3/3011:16 上午.
 */
public class Network {

  private static final String USER_AGENT = "Mozilla/5.0";

  public static String readJson(String url) {

    try {
      URL requestUrl = new URL(url);

      HttpsURLConnection connection = (HttpsURLConnection) requestUrl.openConnection();

      connection.setRequestMethod("GET");

      connection.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = connection.getResponseCode();

      if (responseCode == HttpsURLConnection.HTTP_OK) {

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;

        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {

          response.append(inputLine);
        }

        in.close();

        return response.toString();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

}
