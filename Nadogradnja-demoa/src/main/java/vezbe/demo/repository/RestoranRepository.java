package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Restoran;

public interface RestoranRepository extends JpaRepository<Restoran, Long> {
}
