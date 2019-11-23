package com.pigtom.diary.config;

import lombok.Data;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/19 8:27 PM
 **/
@Data
public class AuthenticationRequest {
    private String username;

    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
