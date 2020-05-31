package com.wzy.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 创建海报入参
 */
@Data
public class PosterBo {
    @NotBlank(message = "商品名称不能为空")
    private String itemName;

    @Min(value = 1, message = "商品正常价格不能小于零")
    private int priceNormal;
    @Min(value = 1, message = "商品福利价格不能小于零")
    private int priceDiscount;
    @NotNull(message = "特价时间不为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date discountDate;

    @NotBlank(message = "商品描述不能为空")
    private String description;
    @NotBlank(message = "商品图片地址不能为空")
    private String imgUrl;
}
