package com.altiora_service_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altiora_service_app.model.entity.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {

}
