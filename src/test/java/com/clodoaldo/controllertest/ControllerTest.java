package com.clodoaldo.controllertest;
import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.exception.CervejaNotFoundException;
import com.clodoaldo.api.cerveja.mapper.CervejaMapper;

import com.clodoaldo.api.cerveja.model.Cerveja;

import com.clodoaldo.api.cerveja.service.CervejaService;
import com.clodoaldo.api.model.test.CervejaTestDTO;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    private CervejaMapper cervejaMapper = CervejaMapper.INSTANCE;

    @Test
    public void deveCriarUmaCerveja() {
        CervejaDTO cervejaDTO = CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja esrepaEncontrarCerveja = cervejaMapper.toModel(cervejaDTO);
        assertEquals("1 BHAMMA LAGER 16 10 40", esrepaEncontrarCerveja.toString());
    }
}

