package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.LokacijaDto;
import vezbe.demo.dto.RestoranImeDto;
import vezbe.demo.model.*;
import vezbe.demo.service.MenadzerService;
import vezbe.demo.service.PorudzbinaService;
import vezbe.demo.dto.KorpaDto;


import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;


    @GetMapping("/api/pregledPorudzbina")
    public ResponseEntity<List<Porudzbina>> getPorudzbine(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Menadzer) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Restoran r = ((Menadzer) loggedKorisnik).getRestoran();
        listaPorudzbina = porudzbinaService.getListaPorudzbina(r);

        return ResponseEntity.ok(listaPorudzbina);

    }

    @GetMapping("/api/dostavljac/cekaDostavljaca")
    public ResponseEntity<Set<Porudzbina>> porudzbineStanjeCekaDostavljaca(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }



        return ResponseEntity.ok(porudzbinaService.findCekaDostavljaca());

    }
    @PostMapping("/api/kreiraj-porudzbinu")
    public ResponseEntity<Porudzbina> kreirajPorudzbinu(@RequestBody RestoranImeDto restoranImeDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Kupac){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac = (Kupac) loggedKorisnik;

        porudzbinaService.makePorudzbina(kupac, restoranImeDto.getNaziv());

        return ResponseEntity.ok(porudzbinaService.getAll());

    }
    @PostMapping("/api/dodaj-u-korpu")
    public ResponseEntity<Porudzbina> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Kupac){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac =(Kupac) loggedKorisnik;

        Porudzbina porudzbina = porudzbinaService.findByStatusAndKupac(kupac);

        return ResponseEntity.ok(porudzbinaService.kreirajStavkuPorudzbine(korpaDto.getArtikal(), korpaDto.getKolicina(), porudzbina));

    }
    @PostMapping("/api/ukloni-iz-korpe")
    public ResponseEntity<Porudzbina> ukloniIzKorpe(@RequestBody KorpaDto korpaDto, HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Kupac){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac =(Kupac) loggedKorisnik;

        Porudzbina porudzbina = porudzbinaService.findByStatusAndKupac(kupac);

        return ResponseEntity.ok(porudzbinaService.ukloniStavkuPorudzbine(korpaDto.getArtikal(), korpaDto.getKolicina(), porudzbina));

    }
    @PostMapping("/api/poruci")
    public ResponseEntity<Porudzbina> poruci( HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Kupac){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac =(Kupac) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.findByStatusAndKupac(kupac);
        porudzbinaService.checkIfEmpty(porudzbina);
        if(porudzbina.getPoruceniArtikli().isEmpty()){
            return  new ResponseEntity("Porudzbina je prazna", HttpStatus.BAD_REQUEST);
        }else{
            return  ResponseEntity.ok(porudzbina);
        }

    }



}
