package com.altiora_service_app.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.altiora_service_app.service.OrdenService;

/**
 *
 * Author: diegoquezada
 */
@Component
public class MappingContext {

    @Lazy
    @Autowired
    public OrdenService ordenService;

}
