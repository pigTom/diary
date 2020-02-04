package com.pigtom.diary.service;

import com.pigtom.diary.model.bean.SystemConfigDTO;
import com.pigtom.diary.model.bean.SystemConfigList;

public interface RestService {
    /**
     * 通过http请求转发修改配置文件的请求
     * @param list 配置文件对象列表
     */
    void forwardRequest(SystemConfigList list);

    /**
     * 更新property配置文件
     * @param dto 配置文件对象
     */
    void updateProperty(SystemConfigDTO dto);
}
