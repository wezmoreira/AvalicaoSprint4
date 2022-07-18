package br.com.compass.avaliacao.service;

import br.com.compass.avaliacao.dto.request.RequestAssociacaoDto;
import br.com.compass.avaliacao.dto.request.RequestAssociadosDto;
import br.com.compass.avaliacao.dto.response.ResponseAssociadosDto;
import br.com.compass.avaliacao.entities.Associados;
import br.com.compass.avaliacao.entities.Partidos;
import br.com.compass.avaliacao.exceptions.AssociadoNaoEncontradoException;
import br.com.compass.avaliacao.exceptions.PartidoNaoEncontradoException;
import br.com.compass.avaliacao.repository.AssociadosRepository;
import br.com.compass.avaliacao.repository.PartidosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadosService {
    @Autowired
    private AssociadosRepository associadosRepository;

    @Autowired
    private PartidosRepository partidosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidacaoService validacaoService;


    public List<ResponseAssociadosDto> listar(String cargo, String ordenar) {
        List<Associados> associados = associadosRepository.encontrarPorCargo
                (cargo, Sort.by(Sort.Direction.ASC, ordenar));
        return associados.stream().map(associado -> modelMapper
                        .map(associado, ResponseAssociadosDto.class))
                .collect(Collectors.toList());
    }

    public ResponseAssociadosDto listar(Long id) {
        Associados associados = associadosRepository.findById(id)
                .orElseThrow(AssociadoNaoEncontradoException::new);
        return modelMapper.map(associados, ResponseAssociadosDto.class);
    }

    public ResponseAssociadosDto cadastrar(RequestAssociadosDto requestAssociadosDto) {
        validacaoService.validaCargo(requestAssociadosDto);
        validacaoService.validaSexo(requestAssociadosDto);
        Associados associados = modelMapper.map(requestAssociadosDto, Associados.class);
        Associados associadosSalvos = associadosRepository.save(associados);
        return modelMapper.map(associadosSalvos, ResponseAssociadosDto.class);
    }

    public void associacaoPartido(RequestAssociacaoDto requestAssociacaoDto) {
        Associados associados = associadosRepository.findById(requestAssociacaoDto.getIdAssociado())
                .orElseThrow(AssociadoNaoEncontradoException::new);
        Partidos partidos = partidosRepository.findById(requestAssociacaoDto.getIdPartido())
                .orElseThrow(PartidoNaoEncontradoException::new);
        associados.setPartido(partidos);
        associadosRepository.save(associados);
    }

    public void atualizar(RequestAssociadosDto requestAssociadosDto, Long id) {
        validacaoService.validaCargo(requestAssociadosDto);
        validacaoService.validaSexo(requestAssociadosDto);
        Associados associados = associadosRepository.findById(id)
                .orElseThrow(AssociadoNaoEncontradoException::new);
        modelMapper.map(requestAssociadosDto, associados);
        associadosRepository.save(associados);
    }

    public ResponseAssociadosDto removeAssociado(Long id, Long idPartido) {
        partidosRepository.findById(idPartido).orElseThrow(PartidoNaoEncontradoException::new);
        Associados associados = associadosRepository.findById(id)
                .orElseThrow(AssociadoNaoEncontradoException::new);
        associados.setPartido(null);
        associadosRepository.save(associados);
        return modelMapper.map(associados, ResponseAssociadosDto.class);
    }

    public void deletar(Long id) {
        Associados associados = associadosRepository.findById(id)
                .orElseThrow(AssociadoNaoEncontradoException::new);
        associadosRepository.delete(associados);
    }

}
