package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Long> {
}
