package com.altiora_service_app.service;

import java.util.List;

import com.altiora_service_app.model.dto.ClienteDto;
import com.altiora_service_app.model.entity.Cliente;

public interface ClienteService {

    Cliente getCliente(Integer id);

    ClienteDto saveClienteDto(ClienteDto dto);

    ClienteDto getClienteDto(Integer id);

    List<ClienteDto> getAllClientesDto();

    ClienteDto updateClienteDto(Integer id, ClienteDto dto);

    void deleteClienteDto(Integer id);
}
