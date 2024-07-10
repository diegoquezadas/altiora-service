package com.altiora_service_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altiora_service_app.model.dto.OrdenDto;
import com.altiora_service_app.model.entity.Orden;
import com.altiora_service_app.model.mapper.OrdenMapper;
import com.altiora_service_app.repository.OrdenRepository;
import com.altiora_service_app.service.OrdenService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenMapper ordenMapper;

    @Override
    public Orden getOrden(Integer id) {
        Orden entity = ordenRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Orden not found with id " + id));
        return entity;
    }

    @Override
    public OrdenDto saveOrdenDto(OrdenDto dto) {
        Orden entity = ordenMapper.toEntity(dto);
        entity = ordenRepository.save(entity);
        return ordenMapper.toDto(entity);
    }

    @Override
    public OrdenDto getOrdenDto(Integer id) {
        Orden entity = ordenRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Orden not found with id " + id));
        return ordenMapper.toDto(entity);
    }

    @Override
    public List<OrdenDto> getAllOrdenesDto() {
        return ordenRepository.findAll()
            .stream()
            .map(ordenMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public OrdenDto updateOrdenDto(Integer id, OrdenDto dto) {
        Orden existingEntity = ordenRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Orden not found with id " + id));

        existingEntity.setCodigo(dto.getCodigo());
        existingEntity.setFecha(dto.getFecha());
        existingEntity.setCliente(dto.getCliente());

        Orden updatedEntity = ordenRepository.save(existingEntity);
        return ordenMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteOrdenDto(Integer id) {
        if (!ordenRepository.existsById(id)) {
            throw new EntityNotFoundException("Orden not found with id " + id);
        }
        ordenRepository.deleteById(id);
    }
}
