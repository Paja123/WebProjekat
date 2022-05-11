package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.RestoranDto;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.RestoranRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired MenadzerService menadzerService;

    @Autowired
    private ArtikalService artikalService;

    public Restoran save(Restoran restoran){
        return restoranRepository.save(restoran);
    }
    public List<Restoran> findAll(){return restoranRepository.findAll();};
    public Restoran findByName(String naziv){
        Restoran r = null;
        for(Restoran restoran: restoranRepository.findAll()){
            if(restoran.getNaziv().equals(naziv)){
                r= restoran;
            }
        }
        return r;
    }
    public List<Restoran> findByTip(String tipRestorana){
        List<Restoran> lista= new ArrayList<>();
        for(Restoran restoran: restoranRepository.findAll()){
            if(restoran.getTipRestorana().equals(tipRestorana)){
                lista.add(restoran);
            }
        }
        return lista;
    }
    public Restoran findByLokacija(String adresa){
        Restoran r = null;
        for(Restoran restoran: restoranRepository.findAll()){
            if(restoran.getLokacija().getAdresa().equals(adresa)){
                r= restoran;
            }
        }
        return r;
    }

    public ArtikalService getArtikalService() {
        return artikalService;
    }
    public Set<Artikal> dodajArtikal(Artikal artikal, Restoran restoran){
        Set<Artikal> novaLista = new HashSet<>();
        novaLista = artikalService.dodajArtikal(artikal, restoran);
        restoranRepository.save(restoran);
        return  novaLista;
    }
    public Menadzer findByNaziv(String naziv, String KorisnickoIme){
        Restoran r = null;
        for(Restoran restoran: restoranRepository.findAll()){
            if(restoran.getNaziv().equals(naziv)){
                r= restoran;
            }
        }
        return menadzerService.postaviNovogMenadzera(KorisnickoIme, r);

    }
}
