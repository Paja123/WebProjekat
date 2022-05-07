package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Menadzer;
import vezbe.demo.repository.MenadzerRepository;

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
}