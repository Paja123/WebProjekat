package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Artikal;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
}
