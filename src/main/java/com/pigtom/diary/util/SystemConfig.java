package com.pigtom.diary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * config.get("spring.dev.a")
 * @author tangdunhong
 * @blame tangdunhong
 * @module testconfig
 * @since 2019/12/11 10:34 AM
 **/
public class SystemConfig extends HashMap<Object, Object>{
    private Logger logger = LoggerFactory.getLogger(SystemConfig.class);
    private static SystemConfig instance = new SystemConfig();
    private Map activateMap;
    public static SystemConfig getSystemConfig() {
        return instance;
    }

    private static final String APPLICATION = "application";
    private static final String YAML_SUFFIX = ".yml";
    private static final String SPRING_PROFILES_ACTIVATE = "spring.profiles.active";
    private static final String APPLICATION_PATH = "application.yml";
    private List<String> keyList;
    {
        keyList = new ArrayList<>(5);
        keyList.add("a1");
        keyList.add("a2");
        keyList.add("a3");
        keyList.add("a4");
        keyList.add("a5");
    }
    private SystemConfig() {
        init();
    }
    private void init() {
        String activateYml = getActivateYml();
        activateMap = getMapFromYaml(activateYml);

        // 将数据库中定义的键存入SystemConfig中
        for (Object object : activateMap.entrySet()) {
            Entry entry = (Entry)object;
            super.put(entry.getKey(), entry.getValue());
        }
    }

    public void reload() {
        super.clear();
        this.init();
    }

    public List<String> getConfigNames () {
        return keyList;
    }

    @Override
    public Object get(Object key) {
        if (super.containsKey(key)) {
            return super.get(key);
        }
        else if (key instanceof String) {
            String keyS = (String)key;
            return getValue(this, keyS);
        }
        return null;
    }

    public boolean update(Object key, Object value) {
        if (this.containsKey(key)) {
            this.put(key, value);

            // update activate resource
            if (this.activateMap.containsKey(key)) {

            }
            return true;
        }
        return false;
    }


    private Object superGet(Object key) {
        return super.get(key);
    }
    /**
     * 读配置文件，返回配置文件内容
     *
     * @param path
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getMapFromYaml(String path) {
        File file = new File(path);
        logger.info(file.getAbsolutePath());
        Yaml yaml = new Yaml();
        Map map;
        try {
            // 优先读取jar包目录下的配置文件
            if (file.exists()) {
                logger.info(file.getAbsolutePath());
                map = yaml.loadAs(new FileInputStream(file), Map.class);
                return map;
            }
            logger.info("read from inner of jar");
            // 读取jar包内部的配置文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
            map = yaml.loadAs(is, Map.class);
            is.close();
        } catch (IOException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
        return map;
    }

    private String getSpringActivateProfile() {
        Map applicationYmlMap = getMapFromYaml(APPLICATION_PATH);
        return (String)getValue(applicationYmlMap, SPRING_PROFILES_ACTIVATE);
    }

    private String getActivateYml() {
        return APPLICATION + "-" + getSpringActivateProfile() + YAML_SUFFIX;
    }

    /**
     * 从资源Map中读取key对应的值，
     * 形如a.b.c的key，可以先读a的值，再将a中的b读出，最后将b中的c读出
     * @param allResource 包含当前激活配置文件内容的map
     * @param key 要查询的key
     * @return
     */
    private Object getValue(Map allResource, String key) {
        if (allResource.containsKey(key)) {
            return allResource.get(key);
        }


        String[] keys = key.split("\\.");
        int i = 0;
        if (keys.length <= 1) {
            return null;
        }

        if (!allResource.containsKey(keys[i])) {
            return null;
        }

        Map tempMap = (Map)allResource.get(keys[i]);
        while (i < keys.length - 2) {
            i ++;
            if (!tempMap.containsKey(keys[i])) {
                return null;
            }

            Object obj = tempMap.get(keys[i]);
            if (obj instanceof Map) {
                tempMap = (Map) obj;
            } else {
                return null;
            }
        }
        i++;
        if (tempMap.containsKey(keys[i])) {
            Object value = tempMap.get(keys[i]);
            super.put(key, value);
            return value;
        }
        return null;
    }

    public void update(Map allResource, Object key, Object value) {
        if (allResource.containsKey(key)) {
            allResource.put(key, value);
            return;
        }

        if (key instanceof String) {
            String keyS = (String)key;
            String[] keys = keyS.split("\\.");
            // 如果不存在则需要加入
            int i = 0;
            for (; i < keys.length-1; i++) {
                if (allResource.containsKey(keys[i])) {
                    Object object = allResource.get(keys[i]);
                    if (object instanceof Map) {
                        allResource = (Map)object;
                    }
                }
                else {
                    // 如果不存在allResource中，则需要加入一个map
                    // 或者存在allResource中，但是该value不是对象则需要更新
                    allResource.put(keys[i], generateValueForKeys(keys, i, value));
                    return;
                }
            }
            // 最后一个节点，如果已经存在，则需要更新，如果不存在则新增
            allResource.put(keys[i], value);
        }


    }

    /**
     * 生成从keys[start]之后的map链
     * a.b.c=value,
     * 如果 start = 0
     * 返回 b: {
     *     c: value
     * }
     *
     * 如果 start = 1
     * 返回 c: value
     *
     * 如果 start >= 2
     * 返回 value
     * @param keys a.b.c分割成的键列表
     * @param start start下一个索引要转成map链
     * @param value keys对应的原生值
     * @return 返回的map链
     */
    private Object generateValueForKeys(String[] keys, int start, Object value) {
        int lastIndex = keys.length -1;
        Map tempMap;
        for (int i = lastIndex; i > start; i--) {
            tempMap = new HashMap(1);
            tempMap.put(keys[i], value);
            value = tempMap;
        }
        return value;
    }

    /**
     * 首行判断'key'是否存在map的键中，或者形如a.b.c的key, a是否存在map中，如果不是
     * 返回false
     * @param key
     * @return
     */
    private boolean checkKey(String key) {
        if (Objects.isNull(key)) {
            return false;
        }
        String[] keys = key.split(".");
        return super.containsKey(key) || super.containsKey(keys[0]);
    }
}
