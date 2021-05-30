package com.vanhoang.demo.services;

import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.request.ProviderRequest;

public interface ProviderService {
    ProviderEntity register(ProviderRequest providerRequest);

}
