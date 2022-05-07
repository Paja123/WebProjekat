package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.KorisnikRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik login(String username, String password){
        List<Korisnik> listaKorisnika = new ArrayList<>();
        Korisnik korisnik = new Korisnik();
        Menadzer mena = new Menadzer();

        listaKorisnika = korisnikRepository.findAll();
         for(Korisnik k:listaKorisnika){
             if(k.getKorisnickoIme().equals(username) && k.getLozinka().equals(password)){
                 korisnik = k;
             }
         }
        Kupac k;
        Dostavljac d;
        Menadzer m;
        if(korisnik.getUloga().equals(Uloga.Menadzer)){
            m = (Menadzer) korisnik;
            return m;
        }
        if(korisnik.getUloga().equals(Uloga.Kupac)){
            k = (Kupac) korisnik;
            return  k;
        }
        if(korisnik.getUloga().equals(Uloga.Dostavljac)){
            d = (Dostavljac) korisnik;
            return  d;
        }
        return korisnik;

    }


}
