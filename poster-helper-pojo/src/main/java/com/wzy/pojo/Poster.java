package com.wzy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Poster {
    /**
     * 主键id 用户id
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 福利价
     */
    @Column(name = "price_discount")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @Column(name = "price_normal")
    private Integer priceNormal;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品描述
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 创建用户id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 特价日期
     */
    @Column(name = "discount_date")
    private Date discountDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键id 用户id
     *
     * @return id - 主键id 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id 用户id
     *
     * @param id 主键id 用户id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取福利价
     *
     * @return price_discount - 福利价
     */
    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    /**
     * 设置福利价
     *
     * @param priceDiscount 福利价
     */
    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    /**
     * 获取原价
     *
     * @return price_normal - 原价
     */
    public Integer getPriceNormal() {
        return priceNormal;
    }

    /**
     * 设置原价
     *
     * @param priceNormal 原价
     */
    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
    }

    /**
     * 获取商品描述
     *
     * @return description - 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述
     *
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取商品描述
     *
     * @return img_url - 商品描述
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置商品描述
     *
     * @param imgUrl 商品描述
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取创建用户id
     *
     * @return create_user_id - 创建用户id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建用户id
     *
     * @param createUserId 创建用户id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取特价日期
     *
     * @return discount_date - 特价日期
     */
    public Date getDiscountDate() {
        return discountDate;
    }

    /**
     * 设置特价日期
     *
     * @param discountDate 特价日期
     */
    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}