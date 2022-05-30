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

    public Korisnik login(String username, String password) {
        List<Korisnik> listaKorisnika = new ArrayList<>();


        listaKorisnika = korisnikRepository.findAll();
        Korisnik korisnik = null;
        for (Korisnik k : listaKorisnika) {
            if (k.getKorisnickoIme().equals(username) && k.getLozinka().equals(password)) {
                korisnik = k;
            }
        }

        return korisnik;

    }
    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }


}
