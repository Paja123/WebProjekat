package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.RestoranImeDto;
import vezbe.demo.model.*;
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

    @GetMapping("/api/dostavljac/ceka-dostavljaca")
    public ResponseEntity<Set<Porudzbina>> porudzbineStanjeCekaDostavljaca(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }



        return ResponseEntity.ok(porudzbinaService.findCekaDostavljaca());

    }
    @PostMapping("/api/izaberi-restoran")
    public ResponseEntity<Porudzbina> izaberRestoran(@RequestBody RestoranImeDto restoranImeDto, HttpSession session){
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

        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);

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

        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);

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
        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);
        porudzbinaService.checkIfEmpty(porudzbina);
        if(porudzbina.getPoruceniArtikli().isEmpty()){
            return  new ResponseEntity("Porudzbina je prazna", HttpStatus.BAD_REQUEST);
        }else{
            return  ResponseEntity.ok(porudzbina);
        }

    }
    @PutMapping("/api/menadezer/promeni-status/{id}")//GRESKA U KUCANJU
    public ResponseEntity<Porudzbina> uPripremi(@PathVariable(name = "id") String id, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.promeniStatusMenadzer(menadzer.getRestoran(), id);


        return ResponseEntity.ok(porudzbina);
    }
    @PutMapping("/api/dostavljac/promeni-status/{id}")
    public ResponseEntity<Porudzbina> uTransportu(@PathVariable(name = "id") String id, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Dostavljac){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Dostavljac dostavljac = (Dostavljac) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.promeniStatusDostavljac(dostavljac,id);
        if(porudzbina==null){
            return new ResponseEntity("Nemate pristup ovoj porudzbini", HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok(porudzbina);
    }


}
