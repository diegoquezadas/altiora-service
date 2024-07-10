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
import com.altiora_service_app.model.dto.OrdenDto;
import com.altiora_service_app.service.OrdenService;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenController.class);

    @Autowired
    private OrdenService ordenService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<OrdenDto>> getOrdenById(@PathVariable("id") Integer id) {
        logger.info("Buscando Orden con id: {}", id);
        try {
            OrdenDto dto = ordenService.getOrdenDto(id);
            ApiResponseDto<OrdenDto> response = new ApiResponseDto<>("1", "Exito", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar Orden con id: {}", id, e);
            ApiResponseDto<OrdenDto> errorResponse = new ApiResponseDto<>("0", "Error al buscar Orden", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<OrdenDto>>> getAllOrdenes() {
        logger.info("Obteniendo todas las Ordenes");
        try {
            List<OrdenDto> dtos = ordenService.getAllOrdenesDto();
            ApiResponseDto<List<OrdenDto>> response = new ApiResponseDto<>("1", "Exito", dtos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener todas las Ordenes", e);
            ApiResponseDto<List<OrdenDto>> errorResponse = new ApiResponseDto<>("0", "Error al obtener todas las Ordenes", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<OrdenDto>> createOrden(@RequestBody OrdenDto dto) {
        logger.info("Creando Orden: {}", dto);
        try {
            OrdenDto createdDto = ordenService.saveOrdenDto(dto);
            ApiResponseDto<OrdenDto> response = new ApiResponseDto<>("1", "Creado exitosamente", createdDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear Orden: {}", dto, e);
            ApiResponseDto<OrdenDto> errorResponse = new ApiResponseDto<>("0", "Error al crear Orden", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<OrdenDto>> updateOrden(@PathVariable Integer id, @RequestBody OrdenDto dto) {
        logger.info("Actualizando Orden con id: {}, datos: {}", id, dto);
        try {
            OrdenDto updatedDto = ordenService.updateOrdenDto(id, dto);
            ApiResponseDto<OrdenDto> response = new ApiResponseDto<>("1", "Actualizado exitosamente", updatedDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al actualizar Orden con id: {}, datos: {}", id, dto, e);
            ApiResponseDto<OrdenDto> errorResponse = new ApiResponseDto<>("0", "Error al actualizar Orden", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteOrden(@PathVariable Integer id) {
        logger.info("Eliminando Orden con id: {}", id);
        try {
            ordenService.deleteOrdenDto(id);
            ApiResponseDto<Void> response = new ApiResponseDto<>("1", "Eliminado exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error al eliminar Orden con id: {}", id, e);
            ApiResponseDto<Void> errorResponse = new ApiResponseDto<>("0", "Error al eliminar Orden", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
