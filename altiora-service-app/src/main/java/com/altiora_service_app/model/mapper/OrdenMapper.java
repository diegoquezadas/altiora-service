package com.altiora_service_app.model.mapper;

import org.mapstruct.Mapper;

import com.altiora_service_app.model.dto.OrdenDto;
import com.altiora_service_app.model.entity.Orden;

@Mapper(componentModel = "spring")
public interface OrdenMapper {

    public Orden toEntity(OrdenDto dto);

    public OrdenDto toDto(Orden entity);

}
