package com.altiora_service_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altiora_service_app.model.dto.ClienteDto;
import com.altiora_service_app.model.entity.Cliente;
import com.altiora_service_app.model.mapper.ClienteMapper;
import com.altiora_service_app.repository.ClienteRepository;
import com.altiora_service_app.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente getCliente(Integer id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found with id " + id));
        return entity;
    }

    @Override
    public ClienteDto saveClienteDto(ClienteDto dto) {
        Cliente entity = clienteMapper.toEntity(dto);
        entity = clienteRepository.save(entity);
        return clienteMapper.toDto(entity);
    }

    @Override
    public ClienteDto getClienteDto(Integer id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found with id " + id));
        return clienteMapper.toDto(entity);
    }

    @Override
    public List<ClienteDto> getAllClientesDto() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto updateClienteDto(Integer id, ClienteDto dto) {
        Cliente existingEntity = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found with id " + id));

        existingEntity.setNombre(dto.getNombre());
        existingEntity.setApellido(dto.getApellido());

        Cliente updatedEntity = clienteRepository.save(existingEntity);
        return clienteMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteClienteDto(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente not found with id " + id);
        }
        clienteRepository.deleteById(id);
    }
}
