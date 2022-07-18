package br.com.compass.avaliacao.controller;

import br.com.compass.avaliacao.dto.request.RequestAssociacaoDto;
import br.com.compass.avaliacao.dto.request.RequestAssociadosDto;
import br.com.compass.avaliacao.dto.response.ResponseAssociadosDto;
import br.com.compass.avaliacao.service.AssociadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadosController {

    @Autowired
    private AssociadosService associadosService;

    @GetMapping
    public ResponseEntity<List<ResponseAssociadosDto>> get
            (@RequestParam(required = false) String cargo,
             @RequestParam(required = false, defaultValue = "id") String ordenar) {
        List<ResponseAssociadosDto> associados = associadosService.listar(cargo, ordenar);
        return ResponseEntity.ok(associados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociadosDto> get(@PathVariable Long id) {
        ResponseAssociadosDto responseAssociadosDto = associadosService.listar(id);
        return ResponseEntity.ok(responseAssociadosDto);
    }

    @PostMapping
    public ResponseEntity<ResponseAssociadosDto> post
    (@RequestBody @Valid RequestAssociadosDto requestAssociadosDto) {
        ResponseAssociadosDto responseAssociadosDto = associadosService
                .cadastrar(requestAssociadosDto);
        return ResponseEntity.ok(responseAssociadosDto);
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> post(@RequestBody @Valid RequestAssociacaoDto requestAssociacaoDto) {
        associadosService.associacaoPartido(requestAssociacaoDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestAssociadosDto requestAssociadosDto,
                                       @PathVariable Long id) {
        associadosService.atualizar(requestAssociadosDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        associadosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/partidos/{idPartido}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long idPartido) {
        associadosService.removeAssociado(id, idPartido);
        return ResponseEntity.noContent().build();
    }
}
