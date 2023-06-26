package com.openpay.ejercicioopenpay.repository;

import com.openpay.ejercicioopenpay.model.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<ApiModel, Long> {
}
