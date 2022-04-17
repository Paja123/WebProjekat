package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
}
