package com.vanhoang.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static CustomUserDetail getPrincipal(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext!=null){
            Authentication authentication = securityContext.getAuthentication();
            if (authentication!=null){
                CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
                if (userDetail!=null){
                    return userDetail;
                }
            }
        }
        return null;
    }

}
