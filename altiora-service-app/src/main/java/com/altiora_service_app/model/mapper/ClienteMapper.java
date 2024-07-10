package com.altiora_service_app.model.mapper;

import org.mapstruct.Mapper;

import com.altiora_service_app.model.dto.ClienteDto;
import com.altiora_service_app.model.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    public Cliente toEntity(ClienteDto dto);

    public ClienteDto toDto(Cliente entity);

}
