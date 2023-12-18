package com.cyzy.system.entity.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据传输dto
 */
@Data
public abstract class BaseDto {
    private int status;
    private String message;
    private Map<String, Object> data;

    /**
     * 存入数据
     * @param key 数据名
     * @param value 数据对象
     */
    public void putData(String key, Object value) {
        if (null == data) data = new HashMap<>();
        data.put(key, value);
    }
}
