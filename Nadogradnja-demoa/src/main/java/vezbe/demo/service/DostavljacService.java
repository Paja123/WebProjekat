package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Dostavljac;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Porudzbina;
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
}
