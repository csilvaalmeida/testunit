package com.clodoaldo.api.cerveja.mapper;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.model.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CervejaMapperTest {

    private static Cerveja Cerveja;
    private static CervejaDTO CervejaDTO;

    CervejaMapperTest INSTANCE = Mappers.getMapper(CervejaMapperTest.class);

    public static Cerveja toModel(CervejaDTO cervejaDTO) {
        return Cerveja;
    }

    public static  CervejaDTO toDTO(Cerveja cerveja)  {
        return CervejaDTO; }
}
