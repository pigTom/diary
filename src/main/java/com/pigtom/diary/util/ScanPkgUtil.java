package com.pigtom.diary.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2020/1/3 11:01 AM
 **/
public class ScanPkgUtil implements ResourceLoaderAware {
    /**
     * Spring容器注入
     */
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
