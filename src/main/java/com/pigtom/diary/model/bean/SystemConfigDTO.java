package com.pigtom.diary.model.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module system-config
 * @since 2019/12/12 8:45 PM
 **/
@Data
public class SystemConfigDTO implements Serializable {
    String key;
    String value;
    String url;
}
