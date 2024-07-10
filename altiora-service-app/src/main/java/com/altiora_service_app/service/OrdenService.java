package com.altiora_service_app.service;

import java.util.List;

import com.altiora_service_app.model.dto.OrdenDto;
import com.altiora_service_app.model.entity.Orden;

public interface OrdenService {

    Orden getOrden(Integer id);

    OrdenDto saveOrdenDto(OrdenDto dto);

    OrdenDto getOrdenDto(Integer id);

    List<OrdenDto> getAllOrdenesDto();

    OrdenDto updateOrdenDto(Integer id, OrdenDto dto);

    void deleteOrdenDto(Integer id);
}
