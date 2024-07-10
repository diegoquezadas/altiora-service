package com.altiora_service_app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altiora_service_app.model.dto.ApiResponseDto;
import com.altiora_service_app.model.dto.ArticuloDto;
import com.altiora_service_app.service.ArticuloService;

@RestController
@RequestMapping("/api/v1/articulos")
public class ArticuloController {

    private static final Logger logger = LoggerFactory.getLogger(ArticuloController.class);

    @Autowired
    private ArticuloService articuloService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ArticuloDto>> getArticuloById(@PathVariable("id") Integer id) {
        logger.info("Buscando Articulo con id: {}", id);
        try {
            ArticuloDto dto = articuloService.getArticuloDto(id);
            ApiResponseDto<ArticuloDto> response = new ApiResponseDto<>("1", "Exito", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar Articulo con id: {}", id, e);
            ApiResponseDto<ArticuloDto> errorResponse = new ApiResponseDto<>("0", "Error al buscar Articulo", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ArticuloDto>>> getAllArticulos() {
        logger.info("Obteniendo todos los Articulos");
        try {
            List<ArticuloDto> dtos = articuloService.getAllArticulosDto();
            ApiResponseDto<List<ArticuloDto>> response = new ApiResponseDto<>("1", "Exito", dtos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener todos los Articulos", e);
            ApiResponseDto<List<ArticuloDto>> errorResponse = new ApiResponseDto<>("0", "Error al obtener todos los Articulos", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ArticuloDto>> createArticulo(@RequestBody ArticuloDto dto) {
        logger.info("Creando Articulo: {}", dto);
        try {
            ArticuloDto createdDto = articuloService.saveArticuloDto(dto);
            ApiResponseDto<ArticuloDto> response = new ApiResponseDto<>("1", "Creado exitosamente", createdDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear Articulo: {}", dto, e);
            ApiResponseDto<ArticuloDto> errorResponse = new ApiResponseDto<>("0", "Error al crear Articulo", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ArticuloDto>> updateArticulo(@PathVariable Integer id, @RequestBody ArticuloDto dto) {
        logger.info("Actualizando Articulo con id: {}, datos: {}", id, dto);
        try {
            ArticuloDto updatedDto = articuloService.updateArticuloDto(id, dto);
            ApiResponseDto<ArticuloDto> response = new ApiResponseDto<>("1", "Actualizado exitosamente", updatedDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al actualizar Articulo con id: {}, datos: {}", id, dto, e);
            ApiResponseDto<ArticuloDto> errorResponse = new ApiResponseDto<>("0", "Error al actualizar Articulo", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteArticulo(@PathVariable Integer id) {
        logger.info("Eliminando Articulo con id: {}", id);
        try {
            articuloService.deleteArticulo(id);
            ApiResponseDto<Void> response = new ApiResponseDto<>("1", "Eliminado exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error al eliminar Articulo con id: {}", id, e);
            ApiResponseDto<Void> errorResponse = new ApiResponseDto<>("0", "Error al eliminar Articulo", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
