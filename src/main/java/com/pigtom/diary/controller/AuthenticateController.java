package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.config.AuthenticationRequest;
import com.pigtom.diary.config.AuthenticationResponse;
import com.pigtom.diary.config.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pigtom.diary.config.RestApiUrl.BASE_API;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/15 9:23 PM
 **/
@Api(tags = "AuthenticateController", description = "用户身份验证控制器")
@RequestMapping(BASE_API + "authenticate")
@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * authenticate方法会使其重定向到这个方法
     * @param request 请求
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request) throws Exception{
        return authenticate(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception{

        try {
            // 通过authentication manager 去验证
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.success(new AuthenticationResponse(jwt));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody AuthenticationRequest request) {
//        authenticationManager.
//    }
}
