package com.pigtom.diary.service.impl;

import com.pigtom.diary.util.ConfigFileHandleUtil;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module system-config
 * @since 2019/12/13 1:30 PM
 **/
public class JSONFileReader {

    public static void main(String[] args) {
        String path = "nginx.conf";
        String key = "http.server.listen";
        String value = "8080";
        ConfigFileHandleUtil.parseNginxConfig(path, key, value);
    }
}
