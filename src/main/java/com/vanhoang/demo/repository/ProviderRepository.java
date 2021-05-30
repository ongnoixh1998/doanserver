package com.vanhoang.demo.repository;

import com.vanhoang.demo.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderEntity,Integer> {
    ProviderEntity findByClientId(String clientId);
}
