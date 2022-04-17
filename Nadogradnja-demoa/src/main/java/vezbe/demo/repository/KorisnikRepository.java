package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
}
