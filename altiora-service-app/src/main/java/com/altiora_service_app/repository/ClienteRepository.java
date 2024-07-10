package com.altiora_service_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altiora_service_app.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
