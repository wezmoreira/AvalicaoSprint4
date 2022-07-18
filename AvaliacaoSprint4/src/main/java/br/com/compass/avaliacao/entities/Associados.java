package br.com.compass.avaliacao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Associados {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cargoPolitico;
    private LocalDate dataNascimento;
    private String sexo;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Partidos partido;
}

