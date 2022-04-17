package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine,Long> {
}
