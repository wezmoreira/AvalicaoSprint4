package br.com.compass.avaliacao.controller;

import br.com.compass.avaliacao.dto.request.RequestPartidosDto;
import br.com.compass.avaliacao.dto.response.ResponseAssociadosDto;
import br.com.compass.avaliacao.dto.response.ResponsePartidosDto;
import br.com.compass.avaliacao.service.PartidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidosController {

    @Autowired
    private PartidosService partidosService;

    @GetMapping
    public ResponseEntity<List<ResponsePartidosDto>> listaAssociados
                    (@RequestParam(required = false) String ideologia){
        List<ResponsePartidosDto> responseDTOList = partidosService.listar(ideologia);
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartidosDto> get(@PathVariable Long id) {
        ResponsePartidosDto partido = partidosService.listar(id);
        return ResponseEntity.ok(partido);
    }

    @GetMapping("{id}/associados")
    public ResponseEntity<List<ResponseAssociadosDto>> getAssociados(@PathVariable Long id) {
        List<ResponseAssociadosDto> associados = partidosService.listarAssociados(id);
        return ResponseEntity.ok(associados);
    }

    @PostMapping
    public ResponseEntity<ResponsePartidosDto> post(@RequestBody @Valid RequestPartidosDto requestPartidosDto) {
        ResponsePartidosDto responsePartidosDto = partidosService.cadastrar(requestPartidosDto);
        return ResponseEntity.ok(responsePartidosDto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody @Valid RequestPartidosDto partido, @PathVariable Long id) {
        partidosService.atualizar(partido, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partidosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
