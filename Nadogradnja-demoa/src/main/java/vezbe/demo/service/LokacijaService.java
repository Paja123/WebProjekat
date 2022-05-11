package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Lokacija;
import vezbe.demo.repository.LokacijaRepository;

@Service
public class LokacijaService {
    @Autowired
    private LokacijaRepository lokacijaRepository;

    public void save(Lokacija lokacija){
        this.lokacijaRepository.save(lokacija);
    }
}
