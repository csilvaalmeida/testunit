package com.clodoaldo.api.cerveja.controller;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.DTO.QuantidadeDTO;
import com.clodoaldo.api.cerveja.exception.CervejaAlreadyRegisteredException;
import com.clodoaldo.api.cerveja.exception.CervejaNotFoundException;
import com.clodoaldo.api.cerveja.exception.CervejaStockExceededException;
import com.clodoaldo.api.cerveja.service.CervejaService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cervejas")
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Controller implements CervejaControllerDocs {


    private final CervejaService cervejaService;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public CervejaDTO createCerveja(@RequestBody @Valid CervejaDTO cervejaDTO)
                throws CervejaAlreadyRegisteredException {
            return cervejaService.createCerveja(cervejaDTO);
        }

        @GetMapping("/{name}")
        public CervejaDTO findByName(@PathVariable String name)
                throws CervejaNotFoundException {
            return cervejaService.findByName(name);
        }

        @GetMapping
        public List<CervejaDTO> listCervejas() {
            return cervejaService.listAll();
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteById(@PathVariable Long id) throws CervejaNotFoundException {
            cervejaService.deleteById(id);
        }

        @PatchMapping("/{id}/increment")
        public CervejaDTO increment(@PathVariable Long id,
                                    @RequestBody @Valid QuantidadeDTO quantidadeDTO)
                throws CervejaNotFoundException, CervejaStockExceededException {
            return cervejaService.increment(id, quantidadeDTO.getQuantidade());
        }
    }

