package GreenPrint.api.tipo_papelao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPapelaoRepository extends JpaRepository<TipoPapelao, Long> {
}
