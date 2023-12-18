package com.cyzy.system.entity.vo;

import com.cyzy.system.entity.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminVo extends BaseDto{
    private String aid ;
    private String name;
    private String account ;
    private String headImage;
    private Map menuMap;

}
