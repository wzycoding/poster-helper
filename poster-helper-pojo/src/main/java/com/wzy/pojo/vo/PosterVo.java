package com.wzy.pojo.vo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class PosterVo {

    /**
     * 主键id 海报id
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
    private String discountDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 生成海报图片地址
     */
    @Column(name = "poster_img_url")
    private String posterImgUrl;


}
