package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.dto.request.RequestPartidosDto;
import br.com.compass.avaliacao.dto.response.ResponseAssociadosDto;
import br.com.compass.avaliacao.dto.response.ResponsePartidosDto;
import br.com.compass.avaliacao.entities.Associados;
import br.com.compass.avaliacao.entities.Partidos;
import br.com.compass.avaliacao.exceptions.PartidoNaoEncontradoException;
import br.com.compass.avaliacao.repository.AssociadosRepository;
import br.com.compass.avaliacao.repository.PartidosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidosService {
    @Autowired
    private PartidosRepository partidosRepository;
    @Autowired
    private AssociadosRepository associadosRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidacaoService validacaoService;

    //atualizar
    public List<ResponsePartidosDto> listar(String ideologia) {
        List<Partidos> partidoEntities;
        if(ideologia == null) {
            partidoEntities = partidosRepository.findAll();
        }else {
            partidoEntities = partidosRepository.findByIdeologia(ideologia);
        }
        List<ResponsePartidosDto> responsePartidosDtos = partidoEntities.stream().map(partido -> modelMapper
                        .map(partido, ResponsePartidosDto.class))
                        .collect(Collectors.toList());
        return responsePartidosDtos;
    }

    public ResponsePartidosDto listar(Long id) {
        Partidos partidos = partidosRepository.findById(id)
                .orElseThrow(PartidoNaoEncontradoException::new);
        return modelMapper.map(partidos, ResponsePartidosDto.class);
    }

    public List<ResponseAssociadosDto> listarAssociados(Long id) {
        List<Associados> associados = associadosRepository.findAllByPartido_Id(id);
        return associados.stream().map(associado -> modelMapper.map(associado, ResponseAssociadosDto.class))
                .collect(Collectors.toList());
    }

    public ResponsePartidosDto cadastrar(RequestPartidosDto partido) {
        validacaoService.validaIdeologia(partido);
        Partidos partidos = modelMapper.map(partido, Partidos.class);
        Partidos partidosSalvo = partidosRepository.save(partidos);
        return modelMapper.map(partidosSalvo, ResponsePartidosDto.class);
    }

    public void atualizar(RequestPartidosDto partido, Long id) {
        validacaoService.validaIdeologia(partido);
        Partidos partidos = partidosRepository.findById(id).orElseThrow(PartidoNaoEncontradoException::new);
        modelMapper.map(partido, partidos);
        partidosRepository.save(partidos);
    }

    public void deletar(Long id) {
        partidosRepository.findById(id).orElseThrow(PartidoNaoEncontradoException::new);
        partidosRepository.deleteById(id);
    }
}
