package com.altiora_service_app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * Author: diegoquezada
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T>  {


    //clase generica a  usar para todas las repsuestas de los servicios
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private T objeto;

}
