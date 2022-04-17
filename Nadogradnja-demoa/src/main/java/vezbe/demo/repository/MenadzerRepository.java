package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vezbe.demo.model.Menadzer;

public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {
}
