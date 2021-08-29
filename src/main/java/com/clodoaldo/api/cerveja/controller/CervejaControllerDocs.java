package com.clodoaldo.api.cerveja.controller;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.exception.CervejaAlreadyRegisteredException;
import com.clodoaldo.api.cerveja.exception.CervejaNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CervejaControllerDocs {

    @ApiOperation(value = "Cerveja creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success cerveja creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    CervejaDTO createCerveja(CervejaDTO cervejaDTO) throws CervejaAlreadyRegisteredException;

    @ApiOperation(value = "Returns cerveja found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success cerveja found in the system"),
            @ApiResponse(code = 404, message = "Cerveja with given name not found.")
    })
    CervejaDTO findByName(@PathVariable String name) throws CervejaNotFoundException;

    @ApiOperation(value = "Returns a list of all cervejas registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all cervejas registered in the system"),
    })
    List<CervejaDTO> listCervejas();

    @ApiOperation(value = "Delete a cerveja found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success cerveja deleted in the system"),
            @ApiResponse(code = 404, message = "Cerveja with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws CervejaNotFoundException;
}
