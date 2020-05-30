package com.wzy.pojo.bo;

import java.time.LocalDateTime;

/**
 * 创建海报入参
 */
public class PosterBo {
    private String itemName;
    private int priceNormal;
    private int priceDiscount;
    private LocalDateTime discountDate;
    private String description;
    private String imgUrl;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(int priceNormal) {
        this.priceNormal = priceNormal;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public LocalDateTime getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(LocalDateTime discountDate) {
        this.discountDate = discountDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
