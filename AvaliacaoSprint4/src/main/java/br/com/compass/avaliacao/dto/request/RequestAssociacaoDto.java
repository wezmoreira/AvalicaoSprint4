package br.com.compass.avaliacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAssociacaoDto {

    @Positive
    private Long idAssociado;
    @Positive
    private Long idPartido;
}