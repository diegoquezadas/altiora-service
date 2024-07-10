package com.altiora_service_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altiora_service_app.model.dto.ArticuloDto;
import com.altiora_service_app.model.entity.Articulo;
import com.altiora_service_app.model.mapper.ArticuloMapper;
import com.altiora_service_app.model.mapper.MappingContext;
import com.altiora_service_app.repository.ArticuloRepository;
import com.altiora_service_app.service.ArticuloService;
import com.altiora_service_app.service.OrdenService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ArticuloServiceImpl implements ArticuloService {

@Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ArticuloMapper articuloMapper;
    
    @Autowired
    private OrdenService ordenService;

    @Autowired
    private MappingContext mappingContext;

    @Override
    public Articulo getArticulo(Integer id) {
        Articulo entity = articuloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Articulo not found with id " + id));
        return entity;
    }

    @Override
    public ArticuloDto saveArticuloDto(ArticuloDto dto) {
        Articulo entity = articuloMapper.toEntity(dto, mappingContext);
        entity = articuloRepository.save(entity);
        return articuloMapper.toDto(entity);
    }

    @Override
    public ArticuloDto getArticuloDto(Integer id) {
        Articulo entity = articuloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Articulo not found with id " + id));
        return articuloMapper.toDto(entity);
    }

    @Override
    public List<ArticuloDto> getAllArticulosDto() {
        return articuloRepository.findAll()
            .stream()
            .map(articuloMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public ArticuloDto updateArticuloDto(Integer id, ArticuloDto dto) {
        Articulo existingEntity = articuloRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Articulo not found with id " + id));

        existingEntity.setCodigo(dto.getCodigo());
        existingEntity.setNombre(dto.getNombre());
        existingEntity.setPrecioUnitario(dto.getPrecioUnitario());
        existingEntity.setOrden(ordenService.getOrden(dto.getOrden()));

        Articulo updatedEntity = articuloRepository.save(existingEntity);
        return articuloMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteArticulo(Integer id) {
        if (!articuloRepository.existsById(id)) {
            throw new EntityNotFoundException("Articulo not found with id " + id);
        }
        articuloRepository.deleteById(id);
    }
}
