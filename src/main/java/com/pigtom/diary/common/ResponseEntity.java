package com.pigtom.diary.common;

import lombok.Data;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/9 8:03 PM
 **/
@Data
public class ResponseEntity<T> {
    private final static String SUCCESS = "success";
    private final static String FAILED = "failed";

    /**
     * 实际内容，可以是任何类型数据
     */
    private T data;

    /**
     * 返回消息：
     */
    private String message;

    /**
     * 是否正常, true表示正常， false表示失败
     */
    private boolean status;

    /**
     * 状态码
     * 0表示正常
     */
    private int code;

    private ResponseEntity(String message, boolean status) {
        this(null, message, status);
    }


    private ResponseEntity(T data, String message, boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static ResponseEntity success(){
        return new ResponseEntity(SUCCESS, true);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(data, SUCCESS, true);
    }

    public static ResponseEntity failed() {
        return new ResponseEntity(FAILED, false);
    }

    public static <T> ResponseEntity<T> failed(T data) {
        return new ResponseEntity<>(data, FAILED, false);
    }
}
