package com.buybuybuy.ui.home;

import com.alibaba.fastjson.JSON;
import com.buybuybuy.bean.Home3Bean;
import com.buybuybuy.http.OkHttpClientUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.J on 2016/10/14.
 */

public class DownoadJsonImg {
    public static List<String> JsonImg(String path){
        try {
            String url = OkHttpClientUtils.getStringFromUrl(path);
            JSONObject json = new JSONObject(url);
            String slides = json.getString("slides");
            List<Home3Bean> list = JSON.parseArray(slides, Home3Bean.class);
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Home3Bean home3Bean = list.get(i);
                String img = home3Bean.getImg();
                urls.add(img);
            }
            return urls;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
