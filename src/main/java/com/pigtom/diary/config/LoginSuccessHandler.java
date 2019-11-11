package com.pigtom.diary.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/10 10:52 PM
 **/
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = Logger.getGlobal();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.log(Level.ALL, "login success");
    }
}
