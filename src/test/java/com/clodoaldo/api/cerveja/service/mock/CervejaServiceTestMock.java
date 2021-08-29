package com.clodoaldo.api.cerveja.service.mock;

import com.clodoaldo.api.cerveja.DTO.CervejaDTO;
import com.clodoaldo.api.cerveja.exception.CervejaAlreadyRegisteredException;
import com.clodoaldo.api.cerveja.exception.CervejaNotFoundException;
import com.clodoaldo.api.cerveja.exception.CervejaStockExceededException;
import com.clodoaldo.api.cerveja.mapper.CervejaMapper;
import com.clodoaldo.api.cerveja.model.Cerveja;
import com.clodoaldo.api.cerveja.repository.CervejaRepository;
import com.clodoaldo.api.cerveja.service.CervejaService;
import com.clodoaldo.api.model.test.CervejaTestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CervejaServiceTestMock {


    private static final long INVALID_CERVEJA_ID = 2L;

    @Mock
    private CervejaRepository cervejaRepository;

    private CervejaMapper cervejaMapper = CervejaMapper.INSTANCE;

    @InjectMocks
    private CervejaService cervejaService;

    @Test
    void whenCervejaInformedThenItShouldBeCreated() throws CervejaAlreadyRegisteredException {
        // given
        CervejaDTO expectedCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedSavedCerveja =
                cervejaMapper.toModel(expectedCervejaDTO);

        // when
        when(cervejaRepository.findByNome(expectedCervejaDTO
                .getNome())).thenReturn(Optional.empty());
        when(cervejaRepository.save(expectedSavedCerveja))
                .thenReturn(expectedSavedCerveja);

        //then
        CervejaDTO createdCervejaDTO =
                cervejaService.createCerveja(expectedCervejaDTO);

        assertThat(createdCervejaDTO.getiD(),
                is(equalTo(expectedCervejaDTO.getiD())));
        assertThat(createdCervejaDTO.getNome(),
                is(equalTo(expectedCervejaDTO.getNome())));
        assertThat(createdCervejaDTO.getQuantidade(),
                is(equalTo(expectedCervejaDTO.getQuantidade())));
    }

    @Test
    void whenAlreadyRegisteredCervejaInformedThenAnExceptionShouldBeThrown() {
        // given
        CervejaDTO expectedCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja duplicatedCerveja =
                cervejaMapper.toModel(expectedCervejaDTO);

        // when
        when(cervejaRepository.findByNome(expectedCervejaDTO.getNome()))
                .thenReturn(Optional.of(duplicatedCerveja));

        // then
        assertThrows(CervejaAlreadyRegisteredException.class,
                () -> cervejaService.createCerveja(expectedCervejaDTO));
    }

    @Test
    void whenValidCervejaNameIsGivenThenReturnACerveja() throws CervejaNotFoundException {
        // given
        CervejaDTO expectedFoundCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedFoundCerveja =
                cervejaMapper.toModel(expectedFoundCervejaDTO);

        // when
        when(cervejaRepository.findByNome(expectedFoundCerveja.getNome()))
                .thenReturn(Optional.of(expectedFoundCerveja));

        // then
        CervejaDTO foundCervejaDTO =
                cervejaService.findByName(expectedFoundCervejaDTO.getNome());
        assertEquals("BHAMMA", expectedFoundCervejaDTO.getNome() );
        assertThat(foundCervejaDTO, is(equalTo(expectedFoundCervejaDTO)));
    }

    @Test
    void whenNotRegisteredCervejaNameIsGivenThenThrowAnException() {
        // given
        CervejaDTO expectedFoundCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();

        // when
        when(cervejaRepository.findByNome(expectedFoundCervejaDTO.getNome()))
                .thenReturn(Optional.empty());

        // then
        assertThrows(CervejaNotFoundException.class,
                () -> cervejaService.findByName(expectedFoundCervejaDTO.getNome()));
    }

    @Test
    void whenListCervejaIsCalledThenReturnAListOfCervejas() {
        // given
        CervejaDTO expectedFoundCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedFoundCerveja = cervejaMapper.toModel(expectedFoundCervejaDTO);

        //when
        when(cervejaRepository.findAll())
                .thenReturn(Collections.singletonList(expectedFoundCerveja));

        //then
        List<CervejaDTO> foundListCervejasDTO = cervejaService.listAll();

        assertThat(foundListCervejasDTO, is(not(empty())));
        assertThat(foundListCervejasDTO.get(0), is(equalTo(expectedFoundCervejaDTO)));
    }

    @Test
    void whenListCervejaIsCalledThenReturnAnEmptyListOfCervejas() {
        //when
        when(cervejaRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<CervejaDTO> foundListCervejasDTO = cervejaService.listAll();

        assertThat(foundListCervejasDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenACervejaShouldBeDeleted()
            throws CervejaNotFoundException {
        // given
        CervejaDTO expectedDeletedCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedDeletedCerveja =
                cervejaMapper.toModel(expectedDeletedCervejaDTO);

        // when
        when(cervejaRepository.findById(expectedDeletedCervejaDTO.getiD()))
                .thenReturn(Optional.of(expectedDeletedCerveja));
        doNothing().when(cervejaRepository).deleteById(expectedDeletedCervejaDTO.getiD());

        // then
        cervejaService.deleteById(expectedDeletedCervejaDTO.getiD());

        verify(cervejaRepository, times(1))
                .findById(expectedDeletedCervejaDTO.getiD());
        verify(cervejaRepository, times(1))
                .deleteById(expectedDeletedCervejaDTO.getiD());
    }

    @Test
    void whenIncrementIsCalledThenIncrementCervejaStock()
            throws CervejaNotFoundException, CervejaStockExceededException {
        //given
        CervejaDTO expectedCervejaDTO =
                CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedCerveja = cervejaMapper.toModel(expectedCervejaDTO);

        //when
        when(cervejaRepository.findById(expectedCervejaDTO.getiD()))
                .thenReturn(Optional.of(expectedCerveja));
        when(cervejaRepository.save(expectedCerveja))
                .thenReturn(expectedCerveja);

        int quantityToIncrement = 10;
        int expectedQuantityAfterIncrement =
                expectedCervejaDTO.getQuantidade() + quantityToIncrement;

        // then
        CervejaDTO incrementedCervejaDTO =
                cervejaService.increment(expectedCervejaDTO.getiD(), quantityToIncrement);

        assertThat(expectedQuantityAfterIncrement,
                equalTo(incrementedCervejaDTO.getQuantidade()));
        assertThat(expectedQuantityAfterIncrement,
                lessThan(expectedCervejaDTO.getQuantidadeMaxima()));
    }

    @Test
    void whenIncrementIsGreatherThanMaxThenThrowException() {
        CervejaDTO expectedCervejaDTO = CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedCerveja = cervejaMapper.toModel(expectedCervejaDTO);

        when(cervejaRepository.findById(expectedCervejaDTO.getiD())).thenReturn(Optional.of(expectedCerveja));

        int quantityToIncrement = 1000;

        assertThrows(CervejaStockExceededException.class,
                () -> cervejaService.increment(expectedCervejaDTO.getiD(), quantityToIncrement));
    }

    @Test
    void whenIncrementAfterSumIsGreatherThanMaxThenThrowException() {
        CervejaDTO expectedCervejaDTO = CervejaTestDTO.builder().build().toCervejaDTO();
        Cerveja expectedCerveja = cervejaMapper.toModel(expectedCervejaDTO);

        when(cervejaRepository.findById(expectedCervejaDTO.getiD())).thenReturn(Optional.of(expectedCerveja));

        int quantityToIncrement = 45;
        assertThrows(CervejaStockExceededException.class,
                () -> cervejaService.increment(expectedCervejaDTO.getiD(), quantityToIncrement));
    }

    @Test
    void whenIncrementIsCalledWithInvalidIdThenThrowException() {
        int quantityToIncrement = 10;

        when(cervejaRepository.findById(INVALID_CERVEJA_ID)).thenReturn(Optional.empty());

        assertThrows(CervejaNotFoundException.class,
               () -> cervejaService.increment(INVALID_CERVEJA_ID, quantityToIncrement));
    }
}











