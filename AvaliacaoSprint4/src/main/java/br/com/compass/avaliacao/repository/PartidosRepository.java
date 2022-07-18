package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entities.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PartidosRepository extends JpaRepository<Partidos, Long> {
    List<Partidos> findByIdeologia(String ideologia);
}
