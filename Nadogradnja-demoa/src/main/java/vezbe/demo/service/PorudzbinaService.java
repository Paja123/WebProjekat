package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.KupacRepository;
import vezbe.demo.repository.PorudzbinaRepository;
import vezbe.demo.repository.StavkaPorudzbineRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbinaService {
    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    KupacRepository kupacRepository;

    public List<Porudzbina> getListaPorudzbina(Restoran restoran) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        for (Porudzbina porudzbina : porudzbinaRepository.findAll()) {
            for (StavkaPorudzbine stavkaPorudzbine : porudzbina.getPoruceniArtikli()) {
                for (Artikal artikal : restoran.getPonuda()) {
                    if (stavkaPorudzbine.getPoruceniArtikal().getId().equals(artikal.getId())) {
                        listaPorudzbina.add(porudzbina);
                        break;
                    }

                }
            }
        }
        return listaPorudzbina;
    }
  public Set<Porudzbina> findCekaDostavljaca(){
        Set<Porudzbina> setp = new HashSet<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            if(porudzbina.getStatus().equals(StatusPorudzbine.CekaDostavljača)){
                setp.add(porudzbina);
            }
        }
        return setp;
  }
}

