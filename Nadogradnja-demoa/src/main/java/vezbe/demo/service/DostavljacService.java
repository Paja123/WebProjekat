package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.DostavljacRepository;
import vezbe.demo.repository.MenadzerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public Dostavljac findOne(Long id){
        Optional<Dostavljac> foundDostavljac = dostavljacRepository.findById(id);
        if (foundDostavljac.isPresent())
            return foundDostavljac.get();

        return null;
    }

    public List<Dostavljac> findAll(){
        return dostavljacRepository.findAll();
    }

    public void save(Dostavljac dostavljac){
                dostavljacRepository.save(dostavljac);
    }

    public void delete(Dostavljac dostavljac){dostavljacRepository.delete(dostavljac);}

    public Set<Porudzbina> findAllById(Long id){
        return  dostavljacRepository.getById(id).getPorudzbineZaDostavu();
    }

    public void addPorudzbina(Dostavljac dostavljac, Porudzbina porudzbina){
//        for(Porudzbina porudzbina1: dostavljac.getPorudzbineZaDostavu()){
//            if(porudzbina1.getUUID().equals(porudzbina.getUUID())){
//                dostavljac.getPorudzbineZaDostavu().remove(porudzbina1);
//            }
//        }
        dostavljac.getPorudzbineZaDostavu().add(porudzbina);
        dostavljacRepository.save(dostavljac);
    }
    public Dostavljac findDostavljac(String korisnickoIme){
        for(Dostavljac dostavljac: dostavljacRepository.findAll()){
            if(dostavljac.getKorisnickoIme().equals(korisnickoIme)){
                return dostavljac;
            }
        }
        return  null;
    }
    public void addCondition(Dostavljac dostavljac, Porudzbina porudzbina){
        for(Porudzbina p: dostavljac.getPorudzbineZaDostavu()){
            if(p.getUUID().equals(porudzbina.getUUID())){
                p.setStatusPorudzbine(StatusPorudzbine.UTransportu);
                dostavljacRepository.save(dostavljac);
                return;
            }
        }
        dostavljac.getPorudzbineZaDostavu().add(porudzbina);
        dostavljacRepository.save(dostavljac);
    }
}
