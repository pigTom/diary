package com.pigtom.diary.service;

import com.pigtom.diary.model.bean.SystemConfigDTO;

import java.util.List;

public interface RestService {
    /**
     * 通过http请求转发修改配置文件的请求
     * @param systemConfigDTOS 配置文件对象列表
     */
    void forwardRequest(List<SystemConfigDTO> systemConfigDTOS);

    /**
     * 更新property配置文件
     * @param systemConfigDTO 配置文件对象
     */
    void updateProperty(SystemConfigDTO systemConfigDTO);
}
