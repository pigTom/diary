package com.pigtom.diary.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pigtom.diary.util.SystemConfig;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module system-config
 * @since 2019/12/13 1:30 PM
 **/
public class JSONFileReader {
    public static String readStringFromFile(String path) {
        String string;
        int size = 2048;
        char[] buffer = new char[size];
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(path);
        if (!file.exists()) {
            return "";
        }
        Reader reader=null;
        try{
             reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            int len = 0;
            len = reader.read(buffer);
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

    public static void main(String[] args) {
//        String path = JSONFileReader.class.getClassLoader().getResource("config.json").getPath();
        String path = "nginx.conf";
        parseNginxConfig(path);
    }

    public static void writeFile(String path, String content) {
        FileWriter fileWriter = null;
        try {
//            String content = readStringFromFile(path);
//            fileWriter = new FileWriter(new File(path));
//            String s = updateJSONContent("http.url", "127.0.0.2", content);
//            fileWriter.write(formatJSONStr(s));
//
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

    static String getIntendSpace(int intend) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intend; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    static String updateJSONContent(String key, String value, String jsonContent) {
        JSONObject jsonObject = JSON.parseObject(jsonContent);
        SystemConfig config = SystemConfig.getSystemConfig();
        config.update(jsonObject, key, value);
        return jsonObject.toString();
    }

    static String formatJSONStr(String s) {
        // s忆经去除了所有的空格
        // 冒号 + "value" + 逗号
        String regex1 = "(?<=[^:]\",)";
        // 冒号 + 数字 + 逗号
        String regex2 = "(?<=\\d,)";
        // 冒号 + 布尔 + 逗号
        String regex3 = "(?<=(true)|(false),)";

        // 左大括号 {
        String regex4 = "(?<=\\{)";
        // 右大括号 } + 逗号
        String regex5 = "(?<=},)";
        // 右大括号 } 前加换行
        String regex6 = "(?=})";
        System.out.println("regex start" + new Date());
        String regex = regex1 + "|" + regex2 + "|" + regex3 + "|"+ regex4 + "|"+ regex5 + "|"+ regex6 ;
        String newS= s.replaceAll(regex, "\r\n");
        String[] ss = newS.split("\r\n");
        StringBuilder sb = new StringBuilder();
        int intend = 0;
        for (String s1 : ss) {
            String stemp = getIntendSpace(intend) + s1;
            sb.append(stemp);
            sb.append("\r\n");

            if (s1.endsWith("{")) {
                intend += 2;
            }


            else if (s1.matches(".*(\"|\\d|true|false|})")) {
                intend -= 2;
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        System.out.println("regex end" + new Date());
        return sb.toString();
    }

    static void parseNginxConfig(String path) {

        String replace = "\\\\";
        String replacement = "AAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
        String jsonContent = parseNginxToJSONStr(path, replace, replacement);
        jsonContent = updateJSONContent("http.server.listen", "8080", jsonContent);
        String temp = "nginx1.conf";
        String jsonStr = formatJSONStr(jsonContent);
        String nginxConfig = parseJsonToNginx(jsonStr, replacement, replace);
        writeFile(temp, nginxConfig);

    }

   private static Pattern firstWordPattern = Pattern.compile("\\w");

    static String parseJsonToNginx(String content, String replace, String replacement) {
        if (Strings.isBlank(replace) || Strings.isBlank(replacement)) {
            replace = "a";
            replacement = "a";
        }

        StringBuilder sb = new StringBuilder();
        String[] ss = content.split("\r\n");
        for (int i = 1; i < ss.length-1; i++) {
            String s  =ss[i];

            // if s '}' or \r\n, just split ,
            if (!s.contains(":")) {
                sb.append(s.split(",")[0]);
            }

            // "key" : "value", or "key" : {
            else {
                String[] entry = s.split(":", 2);
                String key = entry[0];
                String value = entry[1];

                try {

                    key = key.replaceAll(replace, replacement);
                    value = value.replaceAll(replace, replacement);
                } catch (Exception e) {
                    System.out.println(String.format("key %s ", key));
                    System.out.println(String.format("value %s ", value));
                    System.out.println(String.format("replace -> %s, replacement %s ", replace, replacement));
                }

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
                    try {
                        firstDQuotation = value.indexOf("\"");
                        lastDQuotation = value.lastIndexOf("\"");
                        String valueStr = value.substring(firstDQuotation + 1, lastDQuotation);
                        sb.append(valueStr);
                    }
                    catch (Exception e) {
                        System.out.println("value + " + value);
                        throw new RuntimeException(e);
                    }
                }
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
    static String parseNginxToJSONStr(String path, String replace, String replacement) {
        String content = readStringFromFile(path);

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
                try {
                    key = key.replaceAll(replace, replacement);

                } catch (Exception e) {
                    System.out.println(String.format("key: %s, replace %s, replacement %s", key, replace, replacement));
                    throw new RuntimeException(e);
                }
                sb.append(key);
                sb.append("\"");
                sb.append(":{\r\n");
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
                key = key.replaceAll(replace, replacement);
                sb.append(key);
                sb.append("\"");
                sb.append(":");
                sb.append("\"");
                String value="";
                if (ss[1] != null) {
                    value = ss[1].trim();
                    value = value.replaceAll(replace, replacement);
                }
                sb.append(value);
                sb.append("\",");
                sb.append("\r\n");
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


}
