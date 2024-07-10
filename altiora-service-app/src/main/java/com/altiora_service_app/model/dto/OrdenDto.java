package com.altiora_service_app.model.dto;

import java.time.LocalDateTime;

import com.altiora_service_app.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDto {

    private Integer id;
    private String codigo;
    private LocalDateTime fecha;
    private Cliente cliente;
}
