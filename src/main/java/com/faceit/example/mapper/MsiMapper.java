package com.faceit.example.mapper;

import com.faceit.example.dto.MsiResponse;
import com.faceit.example.model.Msi;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MsiMapper {

    MsiResponse msiToMsiResponse(Msi msi);

    List<MsiResponse> msiListToMsiResponses(List<Msi> msi);
}
