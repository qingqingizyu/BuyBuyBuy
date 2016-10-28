package com.buybuybuy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.J on 2016/10/11.
 */

public class HomeBean implements Serializable{
    private int ok;
    private String update_time;



    @Override
    public String toString() {
        return "HomeBean{" +
                "ok=" + ok +
                ", update_time='" + update_time + '\'' +
                ", poster=" + poster +
                ", slides=" + slides +
                ", data=" + data +
                '}';
    }

    /**
     * url : http://s.click.taobao.com/x866hTx
     * data :
     * type : webview
     */

    private PosterBean poster;
    /**
     * id : 35
     * title : 国庆大放价
     * img : http://app1101060396.qzoneapp.com/uploads/A0/14/A0141CC2D306A1FE492C7F71135B3463.jpg
     * type : 1
     * url : http://s.click.taobao.com/Odo6VSx
     */

    private List<SlidesBean> slides;


    private List<DataBean> data;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public PosterBean getPoster() {
        return poster;
    }

    public void setPoster(PosterBean poster) {
        this.poster = poster;
    }

    public List<SlidesBean> getSlides() {
        return slides;
    }

    public void setSlides(List<SlidesBean> slides) {
        this.slides = slides;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PosterBean implements Serializable{
        private String url;
        private String data;
        private String type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class SlidesBean implements Serializable {
        private String id;
        private String title;
        private String img;
        private String type;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DataBean implements Serializable {
        private String sectionTitle;
        private String sectionDate;
        /**
         * pid : 798408
         * title : 兔子毛绒玩具50cm
         * desc :
         * img : http://img3.tbcdn.cn/tfscom/i4/698286075/TB2zpQ_rVXXXXcBXXXXXXXXXXXX_!!698286075.jpg_430x430q90.jpg
         * price : 7.8
         * item_id : 528261827731
         * source : taobao
         * original_price : 11.8
         * buy_num : 14285
         * url : http://s.click.taobao.com/t?e=m%3D2%26s%3DXx4w6enPWS0cQipKwQzePOeEDrYVVa64LKpWJ%2Bin0XJRAdhuF14FMUKOlSsgAe1CRitN3%2FurF3yjzoOPBMY9nmisZ%2FXOnDwqdIMyWQqW33mRFErZbUmkfMPivL76hvmxrBGe%2B8aM7SJhMEJphl2itcYOae24fhW0
         * mark :
         * img_1 : http://img3.tbcdn.cn/tfscom/i4/698286075/TB2zpQ_rVXXXXcBXXXXXXXXXXXX_!!698286075.jpg_320x320q90.jpg
         * img_2 : http://img3.tbcdn.cn/tfscom/i4/698286075/TB2zpQ_rVXXXXcBXXXXXXXXXXXX_!!698286075.jpg_160x160q90.jpg
         */

        private List<GoodsBean> goods;

        public String getSectionTitle() {
            return sectionTitle;
        }

        public void setSectionTitle(String sectionTitle) {
            this.sectionTitle = sectionTitle;
        }

        public String getSectionDate() {
            return sectionDate;
        }

        public void setSectionDate(String sectionDate) {
            this.sectionDate = sectionDate;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean implements Serializable {
            private String pid;
            private String title;
            private String desc;
            private String img;
            private String price;
            private String item_id;
            private String source;
            private String original_price;
            private String buy_num;
            private String url;
            private String mark;
            private String img_1;
            private String img_2;


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

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
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
        }
    }
}
