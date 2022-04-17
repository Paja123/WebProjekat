package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Lokacija;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
}
