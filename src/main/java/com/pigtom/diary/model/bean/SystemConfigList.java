package com.pigtom.diary.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2020/1/6 5:04 PM
 **/
@Data
public class SystemConfigList {
    private List<SystemConfigDTO> list;
}
