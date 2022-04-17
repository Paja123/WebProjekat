package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
}
