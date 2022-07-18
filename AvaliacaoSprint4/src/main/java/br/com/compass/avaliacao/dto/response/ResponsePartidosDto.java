package br.com.compass.avaliacao.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsePartidosDto {

    private long id;
    private String nome;
    private String sigla;
    private String ideologia;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataFundacao;
}
