package com.buybuybuy.ui.hot;


/*
 * @author: ZTC
 * @Description:  hotjson解析
 * @创建时间:  2016/10/11 18:58 
 */


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.buybuybuy.bean.HotBean;
import com.buybuybuy.bean.SearchBean;
import com.buybuybuy.http.OkHttpClientUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class HotJson {
    public static List<HotBean> jsonHot(String path){
        try {
            String url = OkHttpClientUtils.getStringFromUrl(path);
            JSONObject json = new JSONObject(url);
            String list = json.getString("data");
            List<HotBean> hot = JSON.parseArray(list, HotBean.class);
            Log.e("TGA","++++++++++++++"+list.toString());
            return hot;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SearchBean> jsonSearch(String path){
        try {
            String url = OkHttpClientUtils.getStringFromUrl(path);
            JSONObject json = new JSONObject(url);
            String list = json.getString("data");
            Log.i("MLOG",list);
            List<SearchBean> hot = JSON.parseArray(list, SearchBean.class);
            return hot;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
