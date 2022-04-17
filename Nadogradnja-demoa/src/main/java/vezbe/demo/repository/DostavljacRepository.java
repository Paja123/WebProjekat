package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Dostavljac;

public interface DostavljacRepository extends JpaRepository<Dostavljac, Long> {
}
