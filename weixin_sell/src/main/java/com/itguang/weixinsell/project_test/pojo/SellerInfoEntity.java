package com.itguang.weixinsell.project_test.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author itguang
 * @create 2017-11-25 14:02
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerInfoEntity {

    private String id;
    private String username;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String openid;

    private Timestamp createTime;
    private Timestamp updateTime;


    public SellerInfoEntity() {
    }

    public SellerInfoEntity(String id, String username, String password, String openid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.openid = openid;
    }
}
