package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.StavkaPorudzbine;
import vezbe.demo.repository.StavkaPorudzbineRepository;

@Service
public class StavkaPorudzbineService {
    @Autowired
    StavkaPorudzbineRepository stavkaPorudzbineRepository;


    public void addArtikal(Artikal artikal, int kolicina){
        StavkaPorudzbine stavkaPorudzbine = new StavkaPorudzbine(artikal, kolicina);
        stavkaPorudzbineRepository.save(stavkaPorudzbine);
    }
    public void saveStavka(StavkaPorudzbine stavkaPorudzbine){
        stavkaPorudzbineRepository.save(stavkaPorudzbine);
    }
    public void removeStavka(StavkaPorudzbine stavkaPorudzbine){
        stavkaPorudzbineRepository.delete(stavkaPorudzbine);
    }
}
