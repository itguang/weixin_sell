package com.itguang.weixinsell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回给前台的对象
 *
 * @author itguang
 * @create 2017-11-27 13:32
 **/
@Data
public class ProductVO {


    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private int categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;



}
