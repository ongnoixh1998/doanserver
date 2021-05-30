package com.vanhoang.demo.services.impl;

import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.repository.ProviderRepository;
import com.vanhoang.demo.request.ProviderRequest;
import com.vanhoang.demo.services.ProviderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ProviderEntity register(ProviderRequest providerRequest) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setClientId(providerRequest.getClientId());
        providerEntity.setClientSecrect(passwordEncoder.encode(providerRequest.getClientSecrect()));
        providerEntity.setUrlWebhook(providerRequest.getUrlWebhook());
        providerEntity.setKeyAPI(RandomStringUtils.randomAlphabetic(20));
        return providerRepository.save(providerEntity);
    }


}
