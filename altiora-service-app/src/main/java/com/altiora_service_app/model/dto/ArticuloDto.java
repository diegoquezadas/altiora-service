package com.altiora_service_app.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {

    private Integer id;
    private String codigo;
    private String nombre;
    private BigDecimal precioUnitario;
    private Integer orden;
}
