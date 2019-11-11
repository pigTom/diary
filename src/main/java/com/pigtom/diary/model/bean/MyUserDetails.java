package com.pigtom.diary.model.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/10 4:10 PM
 **/
public class MyUserDetails implements UserDetails {
    private String username;

    private String password;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    private boolean enable;

    private boolean credentialsNonExpired;


    public MyUserDetails() {}

    private List<GrantedAuthority> authorities;

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public MyUserDetails(SystemUser user) {
        this.username = user.getName();
        this.password =  new BCryptPasswordEncoder().encode(user.getAuthenticationString());
        this.accountNonExpired = ! user.isPasswordLocked();
        this.accountNonLocked = ! user.isAccountLocked();
        this.credentialsNonExpired = ! user.isPasswordExpired();
        this.enable = ! user.isAccountLocked() && !user.isPasswordLocked();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
