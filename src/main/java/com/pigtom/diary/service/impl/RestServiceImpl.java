package com.pigtom.diary.service.impl;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemConfigDTO;
import com.pigtom.diary.service.RestService;
import com.pigtom.diary.util.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2019/12/12 8:42 PM
 **/
@Service
public class RestServiceImpl implements RestService {
    Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void

    forwardRequest(List<SystemConfigDTO> systemConfigDTOS) {
        for (SystemConfigDTO dto : systemConfigDTOS) {
            String url = dto.getUrl();
            logger.info("request start: " + new Date());
            ResponseEntity response = restTemplate.postForObject(url,  dto,ResponseEntity.class);
            if (response != null) {
                logger.info(response.getMessage());
            } else {
                logger.info("response lost");
            }
            logger.info("request end: " + new Date());
        }
    }

    @Override
    public void updateProperty(SystemConfigDTO systemConfigDTO) {
        SystemConfig systemConfig = SystemConfig.getSystemConfig();
        File file = new File("application-dev.yml");
        logger.info(file.getAbsolutePath());
        Yaml yaml = new Yaml();
        Map map;
        try {
            // 优先读取jar包目录下的配置文件
            if (!file.exists()) {
                if (!file.createNewFile()){
                    logger.warn("创建文件失败");
                }
            }
            map = yaml.loadAs(new FileInputStream(file), Map.class);
            if (map == null) {
                map = new HashMap(1);
            }
            systemConfig.update(map, systemConfigDTO.getKey(), systemConfigDTO.getValue());
            String yamlStr = yaml.dump(map);
            for (Object o : map.entrySet()) {
                Map.Entry entry = (Map.Entry)o;
                logger.info(entry.getKey()+ ": " + entry.getValue());
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(yamlStr);
            fileWriter.flush();
            fileWriter.close();
            logger.info("yaml string: " + yamlStr);
            logger.info("更新文件成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
