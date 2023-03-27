package com.example.xrig;

public class pc_model {

private String imgUrl, name, price, desc;

    public pc_model() {
    }

    public pc_model(String imgUrl, String name, String price, String desc) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
