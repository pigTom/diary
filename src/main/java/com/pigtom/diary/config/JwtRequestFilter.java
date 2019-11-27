package com.pigtom.diary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 每次请求都会调用的方法
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/19 10:52 PM
 **/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailService userDetailService;

    private final static String BEARER = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        Enumeration<String> h = request.getHeaderNames();
        System.out.println(h);
        String username = null;
        String jwt = null;

        // 如果请求头中有authorization, 则将authorization中的jwt拿出
        // 并将jwt解析，获取用户名
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            jwt = authorizationHeader.substring(BEARER.length());
            if (jwtUtil.validateToken(jwt)) {
                username = jwtUtil.extractUsername(jwt);
            }
        }

        // 用户名为真,且该用户没有放入SpringContextHolder.context中
        // 先验证jwt是否合法，如果合法将生成一个合法的authentication并放入
        // SecurityContextHolder的context中，保证这一次的请求可以顺利通过
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 根据用户名查询用户信息
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

            if (jwtUtil.validateUser(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
