package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.Porudzbina;
import vezbe.demo.model.Restoran;
import vezbe.demo.model.StavkaPorudzbine;
import vezbe.demo.repository.PorudzbinaRepository;
import vezbe.demo.repository.StavkaPorudzbineRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PorudzbinaService {
    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    public List<Porudzbina> getListaPorudzbina(Restoran restoran){
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            for(StavkaPorudzbine stavkaPorudzbine: porudzbina.getPoruceniArtikli()){
                for(Artikal artikal: restoran.getPonuda()){
                    if(stavkaPorudzbine.getPoruceniArtikal().getId().equals(artikal.getId())) {
                        listaPorudzbina.add(porudzbina);
                        break;
                    }

                }
            }
        }
        return listaPorudzbina;
    }
}
