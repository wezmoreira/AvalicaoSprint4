package br.com.compass.avaliacao.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAssociadosDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String cargoPolitico;

    @NotNull @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank
    private String sexo;



}
