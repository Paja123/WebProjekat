package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.MenadzerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    public Menadzer findOne(Long id){
        Optional<Menadzer> foundMenadzer = menadzerRepository.findById(id);
        if (foundMenadzer.isPresent())
            return foundMenadzer.get();

        return null;
    }

    public List<Menadzer> findAll(){
        return menadzerRepository.findAll();
    }

    public Menadzer save(Menadzer menadzer){
        return menadzerRepository.save(menadzer);
    }

    public void delete(Menadzer menadzer){menadzerRepository.delete(menadzer);}

    public Restoran findRestoran(Long ID){
        for(Menadzer menadzer: menadzerRepository.findAll()){
            if(menadzer.getId().equals(ID)){
                return menadzer.getRestoran();

            }
        }
        return null;
    }
    public List<Artikal> removeArtikal(Long id, Restoran restoran) {
        Artikal a= null;
        for(Artikal artikal: restoran.getPonuda()){
            if(artikal.getId().equals(id)){
                 a = artikal;
                 break;
            }
        }
        restoran.getPonuda().remove(a);
        List<Artikal> l= new ArrayList<>();
        for(Artikal artikal:restoran.getPonuda()){
            l.add(artikal);
        }
        return l;
    }
    public Menadzer postaviNovogMenadzera(String korisnickoIme, Restoran restoran){
            Menadzer m = null;
            for(Menadzer menadzer: menadzerRepository.findAll()){
                if(menadzer.getKorisnickoIme().equals(korisnickoIme)){
                    m  = menadzer;
                }
            }
            m.setRestoran(restoran);
            menadzerRepository.save(m);
            return m;
    }

}
