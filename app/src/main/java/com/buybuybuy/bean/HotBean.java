package com.buybuybuy.bean;


/*
 * @author: ZTC
 * @Description: ${TODO}
 * @创建时间:  2016/10/11 16:18 
 */


import android.os.Parcel;
import android.os.Parcelable;

public class HotBean implements Parcelable {

    /**
     * pid : 844709
     * title : 遇见香芬香水洗发水沐浴露套装
     * desc :
     * source : taobao
     * item_id : 40804795154
     * img : http://img3.tbcdn.cn/tfscom/i2/2085695885/TB2hlPTX_gX61BjSspmXXaFcFXa_!!2085695885.jpg_320x320q90.jpg
     * price : 5.8
     * original_price : 48
     * buy_num : 3403
     * mark :
     * url : http://s.click.taobao.com/t?e=m%3D2%26s%3DLLHDJc6sZVscQipKwQzePOeEDrYVVa64K7Vc7tFgwiFRAdhuF14FMfj2FyJapjMZt4hWD5k2kjOjzoOPBMY9nmisZ%2FXOnDwqdIMyWQqW33n7osd9P%2FFWmehSg1yj7h0zRhXF%2FamAi%2FAEaTy6sNbrVsYOae24fhW0
     * img_1 : http://img3.tbcdn.cn/tfscom/i2/2085695885/TB2hlPTX_gX61BjSspmXXaFcFXa_!!2085695885.jpg_320x320q90.jpg
     * img_2 : http://img3.tbcdn.cn/tfscom/i2/2085695885/TB2hlPTX_gX61BjSspmXXaFcFXa_!!2085695885.jpg_160x160q90.jpg
     * coupon_url : https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=2085695885&activityId=bfcce024e2374415b9ce0be056a929bc
     * coupon_pay_price : 5.8
     * coupon_price : 3
     * coupon_id : 48355
     */

    private String pid;
    private String title;
    private String desc;
    private String source;
    private String item_id;
    private String img;
    private String price;
    private String original_price;
    private String buy_num;
    private String mark;
    private String url;
    private String img_1;
    private String img_2;
    private String coupon_url;
    private String coupon_pay_price;
    private String coupon_price;
    private String coupon_id;

/*    //TODO:需要存入数据库的数据以及存入的顺序
    public HotBean(String img, String title, String price, String original_price, String buy_num, String source*//*, String url*//*) {
        this.img = img;
        this.title = title;
        this.price = price;
        this.original_price = original_price;
        this.buy_num = buy_num;
        this.source = source;
//        this.url = url;
    }*/

    public HotBean() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(String buy_num) {
        this.buy_num = buy_num;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg_1() {
        return img_1;
    }

    public void setImg_1(String img_1) {
        this.img_1 = img_1;
    }

    public String getImg_2() {
        return img_2;
    }

    public void setImg_2(String img_2) {
        this.img_2 = img_2;
    }

    public String getCoupon_url() {
        return coupon_url;
    }

    public void setCoupon_url(String coupon_url) {
        this.coupon_url = coupon_url;
    }

    public String getCoupon_pay_price() {
        return coupon_pay_price;
    }

    public void setCoupon_pay_price(String coupon_pay_price) {
        this.coupon_pay_price = coupon_pay_price;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pid);
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.source);
        dest.writeString(this.item_id);
        dest.writeString(this.img);
        dest.writeString(this.price);
        dest.writeString(this.original_price);
        dest.writeString(this.buy_num);
        dest.writeString(this.mark);
        dest.writeString(this.url);
        dest.writeString(this.img_1);
        dest.writeString(this.img_2);
        dest.writeString(this.coupon_url);
        dest.writeString(this.coupon_pay_price);
        dest.writeString(this.coupon_price);
        dest.writeString(this.coupon_id);
    }

    protected HotBean(Parcel in) {
        this.pid = in.readString();
        this.title = in.readString();
        this.desc = in.readString();
        this.source = in.readString();
        this.item_id = in.readString();
        this.img = in.readString();
        this.price = in.readString();
        this.original_price = in.readString();
        this.buy_num = in.readString();
        this.mark = in.readString();
        this.url = in.readString();
        this.img_1 = in.readString();
        this.img_2 = in.readString();
        this.coupon_url = in.readString();
        this.coupon_pay_price = in.readString();
        this.coupon_price = in.readString();
        this.coupon_id = in.readString();
    }

    public static final Parcelable.Creator<HotBean> CREATOR = new Parcelable.Creator<HotBean>() {
        @Override
        public HotBean createFromParcel(Parcel source) {
            return new HotBean(source);
        }

        @Override
        public HotBean[] newArray(int size) {
            return new HotBean[size];
        }
    };
}
