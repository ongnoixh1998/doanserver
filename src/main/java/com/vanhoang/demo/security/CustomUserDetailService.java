package com.vanhoang.demo.security;

import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    ProviderRepository providerRepository;

    @Override
    public CustomUserDetail loadUserByUsername(String s) throws UsernameNotFoundException {
        ProviderEntity providerEntity = providerRepository.findByClientId(s);
        if (providerEntity==null){
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        CustomUserDetail userDetail = new CustomUserDetail(providerEntity.getClientId(),providerEntity.getClientSecrect(),true,true,true,true,authorities,providerEntity.getId());
        return userDetail;

    }
}
