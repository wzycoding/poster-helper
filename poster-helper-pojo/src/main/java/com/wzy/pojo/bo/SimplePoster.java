package com.wzy.pojo.bo;

import com.quaint.poster.annotation.PosterBackground;
import com.quaint.poster.annotation.PosterFontCss;
import com.quaint.poster.annotation.PosterImageCss;
import com.quaint.poster.core.abst.AbstractDefaultPoster;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import java.awt.image.BufferedImage;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class SimplePoster extends AbstractDefaultPoster {

    /**
     * 背景图
     */
    @PosterBackground(width = 1080,height = 1503)
    private BufferedImage backgroundImage;

    /**
     * 福利价
     */
    @PosterFontCss(position = {55,950},center = true, size = 55, color = {200,98,48})
    private String priceDiscount;

    /**
     * 正常价
     */
    @PosterFontCss(position = {55,1040},center = true, size = 42, color = {128,128,128})
    private String priceNormal;

    /**
     * 主图
     */
    @PosterImageCss(position = {55,300},width = 600,height = 600)
    private BufferedImage mainImage;

    /**
     * 商品描述
     */
    @PosterFontCss(position = {55,1100},center = true, size = 42, color = {102,102,102}, canNewLine={1,600,7})
    private String description;

    /**
     * 特价日期
     */
    @PosterFontCss(position = {170,130},center = true, size = 48, color = {102,102,102})
    private String discountDate;

    /**
     * slogan
     */
    @PosterFontCss(position = {170,1400},center = true, size = 42)
    private String slogan;

    /**
     * 二维码
     */
    @PosterImageCss(position = {770,1150},width = 260,height = 260)
    private BufferedImage qrCode;

    @Tolerate
    public SimplePoster() {}
}
