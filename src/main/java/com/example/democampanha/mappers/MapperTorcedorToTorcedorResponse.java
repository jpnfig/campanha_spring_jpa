package com.example.democampanha.mappers;

import com.example.democampanha.dto.TorcedorResponse;
import com.example.democampanha.models.Torcedor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperTorcedorToTorcedorResponse {
    public TorcedorResponse toResponse(Torcedor torcedor);
}
