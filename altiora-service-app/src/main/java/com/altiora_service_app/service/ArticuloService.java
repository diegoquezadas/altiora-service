package com.altiora_service_app.service;

import java.util.List;

import com.altiora_service_app.model.dto.ArticuloDto;
import com.altiora_service_app.model.entity.Articulo;

public interface ArticuloService {

    Articulo getArticulo(Integer id);

    ArticuloDto saveArticuloDto(ArticuloDto dto);

    ArticuloDto getArticuloDto(Integer id);

    List<ArticuloDto> getAllArticulosDto();

    ArticuloDto updateArticuloDto(Integer id, ArticuloDto dto);

    void deleteArticulo(Integer id);
}
