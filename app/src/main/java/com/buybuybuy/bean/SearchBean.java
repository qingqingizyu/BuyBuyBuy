package com.buybuybuy.bean;


/*
 * @author: ZTC
 * @Description: ${TODO}
 * @创建时间:  2016/10/12 16:31 
 */


public class SearchBean {

    /**
     * url : http://ai.taobao.com/search/index.htm?pid=mm_43590817_6508898_22828999&channelId=4&cat=50012777&key=%E7%BE%8A%E7%BB%92%E8%A3%A4
     * title : 羊绒裤
     * tip : 热搜
     */

    private String url;
    private String title;
    private String tip;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
