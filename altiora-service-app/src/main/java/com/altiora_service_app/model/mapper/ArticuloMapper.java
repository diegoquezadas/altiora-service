package com.altiora_service_app.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.altiora_service_app.model.dto.ArticuloDto;
import com.altiora_service_app.model.entity.Articulo;
import com.altiora_service_app.model.entity.Orden;

@Mapper(componentModel = "spring")
public interface ArticuloMapper {

    @Mapping(target = "orden", source = "orden", qualifiedByName = "mapOrden")
    public Articulo toEntity(ArticuloDto dto, @Context MappingContext mappingContext);


    public ArticuloDto toDto(Articulo entity);

    @Named("mapOrden")
    default Orden mapOrden(Integer id, @Context MappingContext mappingContext) {
        if (id == null) {
            return null;
        }
        return mappingContext.ordenService.getOrden(id);
    }

    default Integer map(Orden orden) {
        return orden != null ? orden.getId() : null;
    }
}
