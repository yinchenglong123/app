package com.example.rk2;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * data:2018/05/10.
 * author : 殷成龙(Administrator)
 * function :网络请求工具类
 */

public class url {
    public static String getData(){
        String path = "http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
        String data = "";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int i = urlConnection.getResponseCode();
            if (i == 200){
                InputStream inputStream = urlConnection.getInputStream();
                int len = 0;
                byte[] b = new byte[1024*1024];
                while ((len = inputStream.read(b))!=-1){
                    String s = new String(b, 0, len);
                    data += s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
