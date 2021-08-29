package com.clodoaldo.api.cerveja.service;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.exception.CervejaAlreadyRegisteredException;
import com.clodoaldo.api.cerveja.exception.CervejaNotFoundException;
import com.clodoaldo.api.cerveja.exception.CervejaStockExceededException;
import com.clodoaldo.api.cerveja.mapper.CervejaMapper;
import com.clodoaldo.api.cerveja.model.Cerveja;
import com.clodoaldo.api.cerveja.repository.CervejaRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CervejaService {

    private final CervejaRepository cervejaRepository;
    private final CervejaMapper cervejaMapper = CervejaMapper.INSTANCE;

    public CervejaDTO createCerveja(CervejaDTO cervejaDTO) throws CervejaAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(cervejaDTO.getNome());
        Cerveja cerveja = cervejaMapper.toModel(cervejaDTO);
        Cerveja savedCerveja = cervejaRepository.save(cerveja);
        return cervejaMapper.toDTO(savedCerveja);
    }

    public CervejaDTO findByName(String name) throws CervejaNotFoundException {
        Cerveja foundCerveja = cervejaRepository.findByNome(name)
                .orElseThrow(() -> new CervejaNotFoundException(name));
        return cervejaMapper.toDTO(foundCerveja);
    }

    public List<CervejaDTO> listAll() {
        return cervejaRepository.findAll()
                .stream()
                .map(cervejaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws CervejaNotFoundException {
        verifyIfExists(id);
        cervejaRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String nome) throws CervejaAlreadyRegisteredException {
        Optional<Cerveja> optSavedCerveja = cervejaRepository.findByNome(nome);
        if (optSavedCerveja.isPresent()) {
            throw new CervejaAlreadyRegisteredException(nome);
        }
    }

    private Cerveja verifyIfExists(Long id) throws CervejaNotFoundException {
        return cervejaRepository.findById(id)
                .orElseThrow(() -> new CervejaNotFoundException(id));
    }

    public CervejaDTO increment(Long id, int quantityToIncrement) throws CervejaNotFoundException,
            CervejaStockExceededException {

        Cerveja cervejaToIncrementStock = verifyIfExists(id);
        System.out.println("increment= " + quantityToIncrement);
        System.out.println("qtde =" +cervejaToIncrementStock.getQuantidade());
        System.out.println("qtde max =" +cervejaToIncrementStock.getQuantidadeMaxima());
        int quantityAfterIncrement = quantityToIncrement + cervejaToIncrementStock.getQuantidade();
        System.out.println("quantityAfterIncrement =" +quantityAfterIncrement);
        if (quantityAfterIncrement <= cervejaToIncrementStock.getQuantidadeMaxima()) {
            cervejaToIncrementStock.setQuantidade(cervejaToIncrementStock.getQuantidade() + quantityToIncrement);
            Cerveja incrementedCervejaStock = cervejaRepository.save(cervejaToIncrementStock);
            return cervejaMapper.toDTO(incrementedCervejaStock);
        }
            System.out.println("quantityAfterIncrement =" +quantityAfterIncrement);
            throw new CervejaStockExceededException(id, quantityToIncrement);




    }

}
