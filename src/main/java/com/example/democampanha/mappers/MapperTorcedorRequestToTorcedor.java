package com.example.democampanha.mappers;

import com.example.democampanha.dto.TorcedorRequest;
import com.example.democampanha.models.Torcedor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperTorcedorRequestToTorcedor {
    public Torcedor toEntity(TorcedorRequest torcedorRequest);
}
