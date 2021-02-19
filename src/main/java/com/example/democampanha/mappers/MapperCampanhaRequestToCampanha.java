package com.example.democampanha.mappers;

import com.example.democampanha.dto.CampanhaRequest;
import com.example.democampanha.models.Campanha;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCampanhaRequestToCampanha {
    public Campanha toEntity(CampanhaRequest campanhaRequest);
}
