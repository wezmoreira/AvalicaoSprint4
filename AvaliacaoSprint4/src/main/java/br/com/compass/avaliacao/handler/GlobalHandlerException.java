package br.com.compass.avaliacao.handler;

import br.com.compass.avaliacao.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    private static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado";
    private static final String PARTIDO_NAO_ENCONTRADO = "Partido não encontrado.";
    private static final String CARGO_INVALIDO = "Cargo inválido, os valores precisam ser exatos aos pré-estabelecidos!";
    private static final String IDEOLOGIA_INVALIDO = "Ideologia inválida, os valores precisam ser exatos aos pré-estabelecidos!";
    private static final String SEXO_INVALIDO = "Sexo inválido, os valores precisam ser exatos aos pré-estabelecidos!";
    private static final String DATA_INVALIDA = "Data inválida, os valores precisam ser exatos aos pré-estabelecidos!";


    @ExceptionHandler(value = AssociadoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNaoEncontrado(AssociadoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ASSOCIADO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = PartidoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerPartidoNaoEncontrado(PartidoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(PARTIDO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = CargoPoliticoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerCargoNaoEncontrado(CargoPoliticoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(CARGO_INVALIDO));
    }

    @ExceptionHandler(value = IdeologiaNaoEncontradaException.class)
    protected ResponseEntity<MensagemErro> handlerIdeologiaNaoEncontrado(IdeologiaNaoEncontradaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(IDEOLOGIA_INVALIDO));
    }

    @ExceptionHandler(value = SexoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerSexoNaoEncontrado(SexoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(SEXO_INVALIDO));
    }

    @ExceptionHandler(value = DataInvalidaException.class)
    protected ResponseEntity<MensagemErro> handlerDataNaoEncontrado(DataInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(DATA_INVALIDA));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        "Campo: " + fieldError.getField() +
                                " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(validationList));
    }
}
