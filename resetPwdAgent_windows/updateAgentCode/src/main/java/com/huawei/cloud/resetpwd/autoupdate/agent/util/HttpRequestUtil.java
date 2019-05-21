package com.huawei.cloud.resetpwd.autoupdate.agent.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Archers Law <archerslaw@163.com> on 2017/8/10.
 */
public class HttpRequestUtil {
    private static Logger logger = LogManager.getLogger(HttpRequestUtil.class);
    private static String charset = "utf-8";

    public static String sendGetMethod(String url) throws Exception {

        URL remoteUrl = null;
        URLConnection connection = null;
        HttpURLConnection httpURLConnection = null;

        InputStream inputStream = null;
        String result = null;
        try {
            remoteUrl = new URL(url);
            connection = remoteUrl.openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestProperty("Accept-Charset", charset);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(3000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(3000);//读取超时 单位毫秒
            
            inputStream = httpURLConnection.getInputStream();
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            byte[] bytes = new byte[count];
            inputStream.read(bytes, 0, inputStream.available());
            result = new String(bytes);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }

        return result;
    }
}
