package br.com.compass.avaliacao.repository;

import br.com.compass.avaliacao.entities.Associados;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssociadosRepository extends JpaRepository<Associados, Long> {
    @Query("SELECT associado FROM Associados associado WHERE (:cargo IS NULL OR :cargo = associado.cargoPolitico)")
    List<Associados> encontrarPorCargo(@Param("cargo") String cargo, Sort sort);

    List<Associados> findAllByPartido_Id(Long id);
}
