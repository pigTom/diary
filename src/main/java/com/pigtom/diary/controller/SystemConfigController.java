package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemConfigDTO;
import com.pigtom.diary.service.RestService;
import com.pigtom.diary.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/12/11 3:05 PM
 **/
@RestController
@RequestMapping("/system-config")
public class SystemConfigController {
    SystemConfig systemConfig = SystemConfig.getSystemConfig();
    @Autowired
    private RestService restService;
    @GetMapping("{key}")
    public Object getValue(@PathVariable String key) {
        return systemConfig.get(key);
    }

    @GetMapping("reload")
    public ResponseEntity reload() {
        systemConfig.reload();
        return ResponseEntity.success();
    }

    @PostMapping(value = "forward", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity forwardRequest(@RequestBody List<SystemConfigDTO> configDTOS) {
        restService.forwardRequest(configDTOS);
        return ResponseEntity.success();
    }

    @PostMapping("update")
    public ResponseEntity updateProperties(@RequestBody SystemConfigDTO dto) {
        restService.updateProperty(dto);
        return ResponseEntity.success();
    }
}
