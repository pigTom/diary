package com.pigtom.diary.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.util.Strings;

import java.io.*;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module util
 * @since 2020/1/2 8:49 PM
 **/
public class ConfigFileHandleUtil {

    private static final String REPLACE = "\\\\";
    private static final String REPLACEMENT = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBDDDDDDDHHHHH";
    private static final String DOUBLE_QUOTAITON_REPLACE = "GGGGGGGGGGGGGGGGGGGGDDDDDDDDDDDYYYYYYYYYYQQ";

    public static void parseNginxConfig(String path, String key, Object value) {
        String jsonContent = parseNginxToJSONStr(path);
        jsonContent = updateJSONContent(key, value, jsonContent);
        String formatJson = formatJsonStr(jsonContent);
        String jsonConfig = "test.json";
        writeStringToFile(jsonConfig, formatJson);

        String nginxConfig = parseJsonToNginx(jsonContent);
        writeStringToFile("nginx-test.conf", nginxConfig);
    }
    /**
     * jsonContent必须是格式化好的json字符串，用换行符进行换行
     * @param jsonContent json字符串
     * @return nginx字符
     */
    private static String parseJsonToNginx(String jsonContent) {
        // jsonContent必须是格式化好的json字符串，用换行符进行换行
        jsonContent = formatJsonStr(jsonContent);


        StringBuilder sb = new StringBuilder();
        String[] ss = jsonContent.split("\r\n")
                ;
        // i start from 1 and end with ss.length - 1
        for (int i = 1; i < ss.length-1; i++) {
            String s  =ss[i];

            // if s '}' or \r\n, just split ,
            if (!s.contains(":")) {
                sb.append(s.split(",")[0]);
            }

            // "key" : "value", or "key" : {
            else {
                // 保证键中没有冒号
                String[] entry = s.split(":", 2);
                String key = entry[0];
                String value = entry[1];
                int firstDQuotation = key.indexOf("\"");
                int lastDQuotation = key.lastIndexOf("\"");
                String indentStr = key.substring(0, firstDQuotation);
                key = key.substring(firstDQuotation+1, lastDQuotation);

                // sb append intent
                sb.append(indentStr);
                sb.append(key);
                sb.append("\t");

                // "key: {    -> key \t {
                if (value.contains("{")) {
                    sb.append("{");

                }
                // "key" : "value", -> key \t value
                else {
                    firstDQuotation = value.indexOf("\"");
                    lastDQuotation = value.lastIndexOf("\"");
                    String valueStr = value.substring(firstDQuotation + 1, lastDQuotation);
                    sb.append(valueStr);
                    sb.append(";");
                }
            }
            sb.append("\r\n");
        }
        return sb.toString().replaceAll(REPLACEMENT, REPLACE).replaceAll(DOUBLE_QUOTAITON_REPLACE, "\"");
    }

    private static String formatJsonStr(String jsonContent) {
        char[] chars = jsonContent.toCharArray();
        StringBuilder sb = new StringBuilder();
        int intend = 0;
        // 0 表示值的右引号与键的左引号之间
        // 1 表示键的左引号与值的右引号之间
        // 2 表示键的右引号与值的左引号之间
        // 3 表示值的左引号与值的右引号之间
        int quotationStatus = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // 如果c是 左大括号,后面加换行，且需要加缩进
            if (quotationStatus == 0 && c == '{') {
                sb.append(c);
                sb.append("\r\n");
                intend += 2;
            }

            // 如果c是右大括号，前面加换行
            else if (quotationStatus == 0 && c == '}') {
                sb.append("\r\n");
                intend -= 2;
                sb.append(getIntendSpace(intend));
                sb.append(c);
            }

            // 如果c是逗号，后面加换行
            else if (quotationStatus == 0 && c == ',') {
                sb.append(c);
                sb.append("\r\n");
            }

            // 键的开始引号，需要加缩进，引号状态需要改变
            else if (quotationStatus == 0 && c == '"') {
                sb.append(getIntendSpace(intend));
                sb.append(c);
                quotationStatus = (quotationStatus + 1) % 4;
            }

            // 其它状态的绰号，不需要加缩进，引号状态需要改变
            else if (c == '"') {
                sb.append(c);
                quotationStatus = (quotationStatus + 1) % 4;
            }

            // 如果 : 后面没有引号，则说明值不是由引号包裹，引号状态变成0
            else if (quotationStatus == 2 && c == ':' && chars[i + 1] != '"') {
                sb.append(c);
                quotationStatus = 0;
            }

            // 其它普通状态
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static String parseNginxToJSONStr(String path) {
        String content = readStringFromFile(path);
        content = content.replaceAll("\"", DOUBLE_QUOTAITON_REPLACE);
        content = content.replaceAll(REPLACE, REPLACEMENT);
        String[] cs = content.split("\n");
        StringBuilder sb = new StringBuilder();
        sb.append("{\r\n");
        for (String s : cs) {
            s = s.trim();

            // 去掉注释
            int commentIndex = s.indexOf("#");
            if (commentIndex != -1) {
                s = s.substring(0, commentIndex);
            }

            if (Strings.isBlank(s)) {
                continue;
            }

            // 去掉分号
            int semicolonIndex = s.lastIndexOf(";");
            if (semicolonIndex != -1) {
                s = s.substring(0, semicolonIndex);
            }

            // 在左大括号前加冒号,并把左大括号前的key加上引号
            if (s.endsWith("{")) {
                // 将{ 前的字符串当作key
                String key = s.substring(0, s.length() - 2).trim();
                sb.append("\"");
                sb.append(key);
                sb.append("\":{\n");
            }

            // 在右大括号后加逗号
            else if (s.endsWith("}")) {
                // 去掉逗号, 以 value + , 或者 } + ,的字符串
                int commaIndex = sb.lastIndexOf(",");
                if (commaIndex != -1) {
                    sb.deleteCharAt(commaIndex);
                }
                sb.append("},\r\n");
            }

            // 键值对，将第一节连续非空字符当作键， 后面的所有字符串当作值
            else {
                String[] ss = s.split("\\s", 2);
                sb.append("\"");
                String key = ss[0];
                sb.append(key);
                sb.append("\":\"");
                String value="";
                if (ss[1] != null) {
                    value = ss[1].trim();
                }
                sb.append(value);
                sb.append("\",\n");
            }

        }
        // 去掉逗号 } + ,
        int commaIndex = sb.lastIndexOf(",");
        if (commaIndex != -1) {
            sb.deleteCharAt(commaIndex);
        }
        sb.append("}");
        return sb.toString();
    }

    private static String getIntendSpace(int intend) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intend; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private static String readStringFromFile(String path) {
        int size = 1024;
        char[] buffer = new char[size];
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(path);
        if (!file.exists()) {
            return "";
        }
        Reader reader=null;
        try{
            reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            int len = reader.read(buffer);
            while (len > -1) {
                stringBuilder.append(buffer, 0, len);
                len = reader.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串读入指定的文件中
     * @param path 文件路径
     * @param content 内容
     */
    private static void writeStringToFile(String path, String content) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(path));
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 更新Json内容
     * @param key 形如a.b.c的键
     * @param value 键对应在的
     * @param jsonContent json字符串
     * @return 更新过后的字符串
     */
    private static String updateJSONContent(String key, Object value, String jsonContent) {
        JSONObject jsonObject = JSON.parseObject(jsonContent);
        SystemConfig config = SystemConfig.getSystemConfig();
        config.update(jsonObject, key, value);
        return jsonObject.toString();
    }
}
