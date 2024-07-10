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
import com.altiora_service_app.model.dto.ClienteDto;
import com.altiora_service_app.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ClienteDto>> getClienteById(@PathVariable("id") Integer id) {
        logger.info("Buscando Cliente con id: {}", id);
        try {
            ClienteDto dto = clienteService.getClienteDto(id);
            ApiResponseDto<ClienteDto> response = new ApiResponseDto<>("1", "Exito", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar Cliente con id: {}", id, e);
            ApiResponseDto<ClienteDto> errorResponse = new ApiResponseDto<>("0", "Error al buscar Cliente", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ClienteDto>>> getAllClientes() {
        logger.info("Obteniendo todos los Clientes");
        try {
            List<ClienteDto> dtos = clienteService.getAllClientesDto();
            ApiResponseDto<List<ClienteDto>> response = new ApiResponseDto<>("1", "Exito", dtos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener todos los Clientes", e);
            ApiResponseDto<List<ClienteDto>> errorResponse = new ApiResponseDto<>("0", "Error al obtener todos los Clientes", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ClienteDto>> createCliente(@RequestBody ClienteDto dto) {
        logger.info("Creando Cliente: {}", dto);
        try {
            ClienteDto createdDto = clienteService.saveClienteDto(dto);
            ApiResponseDto<ClienteDto> response = new ApiResponseDto<>("1", "Creado exitosamente", createdDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear Cliente: {}", dto, e);
            ApiResponseDto<ClienteDto> errorResponse = new ApiResponseDto<>("0", "Error al crear Cliente", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ClienteDto>> updateCliente(@PathVariable Integer id, @RequestBody ClienteDto dto) {
        logger.info("Actualizando Cliente con id: {}, datos: {}", id, dto);
        try {
            ClienteDto updatedDto = clienteService.updateClienteDto(id, dto);
            ApiResponseDto<ClienteDto> response = new ApiResponseDto<>("1", "Actualizado exitosamente", updatedDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error al actualizar Cliente con id: {}, datos: {}", id, dto, e);
            ApiResponseDto<ClienteDto> errorResponse = new ApiResponseDto<>("0", "Error al actualizar Cliente", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteCliente(@PathVariable Integer id) {
        logger.info("Eliminando Cliente con id: {}", id);
        try {
            clienteService.deleteClienteDto(id);
            ApiResponseDto<Void> response = new ApiResponseDto<>("1", "Eliminado exitosamente", null);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error al eliminar Cliente con id: {}", id, e);
            ApiResponseDto<Void> errorResponse = new ApiResponseDto<>("0", "Error al eliminar Cliente", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
