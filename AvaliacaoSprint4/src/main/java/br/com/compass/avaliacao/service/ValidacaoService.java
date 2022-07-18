package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.dto.request.RequestAssociadosDto;
import br.com.compass.avaliacao.dto.request.RequestPartidosDto;
import br.com.compass.avaliacao.enums.Cargos;
import br.com.compass.avaliacao.enums.Ideologia;
import br.com.compass.avaliacao.enums.Sexo;
import br.com.compass.avaliacao.exceptions.CargoPoliticoNaoEncontradoException;
import br.com.compass.avaliacao.exceptions.IdeologiaNaoEncontradaException;
import br.com.compass.avaliacao.exceptions.SexoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidacaoService {

    public void validaIdeologia(RequestPartidosDto politicalPartyDTO){
        boolean isValid = Arrays.stream(Ideologia.values()).anyMatch(ideologiaEnum ->
                ideologiaEnum.getIdeologia().equals(politicalPartyDTO.getIdeologia()));
        if (!isValid){
            throw new IdeologiaNaoEncontradaException();
        }
    }

    public void validaCargo(RequestAssociadosDto requestAssociateDTO){
        boolean isValid = Arrays.stream(Cargos.values()).anyMatch(cargoPoliticoEnum ->
                cargoPoliticoEnum.getCargos().equals(requestAssociateDTO.getCargoPolitico()));
        if (!isValid){
            throw new CargoPoliticoNaoEncontradoException();
        }
    }

    public void validaSexo(RequestAssociadosDto requestAssociateDTO){
        boolean isValid = Arrays.stream(Sexo.values()).anyMatch(sexoEnum ->
                sexoEnum.getSexo().equalsIgnoreCase(requestAssociateDTO.getSexo()));
        if (!isValid){
            throw new SexoNaoEncontradoException();
        }
    }



}
