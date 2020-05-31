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
    @PosterBackground(width = 365,height = 666)
    private BufferedImage backgroundImage;

    /**
     * 头像
     */
    @PosterImageCss(position = {27,27},width = 36, height = 36, circle = true)
    private BufferedImage head;

    /**
     * 昵称
     */
    @PosterFontCss(position = {71,32}, color = {255,255,255})
    private String nickName;

    /**
     * 广告语
     */
    @PosterFontCss(position = {27,70},center = true, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String slogan;

    /**
     * 商品名称
     */
    @PosterFontCss(position = {57,70},center = true, size = 22, color = {255,255,255}, canNewLine={1,221,7})
    private String itemName;

    /**
     * 主图
     */
    @PosterImageCss(position = {27,172},width = 168,height = 168)
    private BufferedImage mainImage;

    @Tolerate
    public SimplePoster() {}
}
