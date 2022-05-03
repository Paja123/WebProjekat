package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Kupac;
import vezbe.demo.repository.KupacRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    public Kupac findOne(Long id){
        Optional<Kupac> foundKupac = kupacRepository.findById(id);
        if (foundKupac.isPresent())
            return foundKupac.get();

        return null;
    }

    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }

    public Kupac save(Kupac kupac){
        return kupacRepository.save(kupac);
    }

    public void delete(Kupac kupac){kupacRepository.delete(kupac);}
}
