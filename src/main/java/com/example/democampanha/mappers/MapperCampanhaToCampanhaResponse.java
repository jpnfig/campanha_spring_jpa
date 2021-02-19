package com.example.democampanha.mappers;

import com.example.democampanha.dto.CampanhaResponse;
import com.example.democampanha.models.Campanha;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MapperTorcedorToTorcedorResponse.class})
public interface MapperCampanhaToCampanhaResponse {
    public CampanhaResponse toResponse(Campanha campanha);
}
