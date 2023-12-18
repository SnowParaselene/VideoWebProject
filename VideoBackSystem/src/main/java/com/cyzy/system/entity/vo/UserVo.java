package com.cyzy.system.entity.vo;

import com.cyzy.system.entity.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseDto {
    private String uid;
    private String name;
    private String account;
    private String headImg;
    private Double remainingSum;
    private boolean vip;
    private Date expirationTime;

    public UserVo(String uid, String name, String account, String headImg, Double remainingSum, boolean vip) {
        this.uid = uid;
        this.name = name;
        this.account = account;
        this.headImg = headImg;
        this.remainingSum = remainingSum;
        this.vip = vip;
    }

    public UserVo(String uid, String name, String account, String headImg, boolean vip) {
        this.uid = uid;
        this.name = name;
        this.account = account;
        this.headImg = headImg;
        this.vip = vip;
    }
}
