//package com.pigtom.diary.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// *
// * 验证用户名密码正确后，生成一个token，并将token返回给客户端
// * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
// * attemptAuthentication ：接收并解析用户凭证。
// * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
// * @author tangdunhong
// * @blame tangdunhong
// * @module diary
// * @since 2019/11/12 9:17 PM
// **/
//public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
//    private AuthenticationManager authenticationManager;
//
//    public JwtLoginFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    // 接收并解析用户凭证
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException {
//        try {
//            User user = new ObjectMapper()
//                    .readValue(req.getInputStream(), User.class);
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            user.getUsername(),
//                            user.getPassword(),
//                            new ArrayList<>())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     *     用户成功登录后，这个方法会被调用，我们在这个方法里生成token
//      */
////    @Override
////    protected void successfulAuthentication(HttpServletRequest req,
////                                            HttpServletResponse res,
////                                            FilterChain chain,
////                                            Authentication auth) throws IOException, ServletException {
////                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
////                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
////                .compact();
////        res.addHeader("Authorization", "Bearer " + token);
////    }
//}
