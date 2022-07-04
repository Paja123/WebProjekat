package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.PorudzbinaDto;
import vezbe.demo.dto.RestoranImeDto;
import vezbe.demo.model.*;
import vezbe.demo.service.PorudzbinaService;
import vezbe.demo.dto.KorpaDto;


import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping(value = "/api/")
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;


    @GetMapping(value = "/pregled-porudzbina",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbine(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Menadzer) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Restoran r = ((Menadzer) loggedKorisnik).getRestoran();
        List<PorudzbinaDto> dtoList = porudzbinaService.getListaPorudzbina(r);

        return ResponseEntity.ok(dtoList);

    }

    @GetMapping(value = "ceka-dostavljaca",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PorudzbinaDto>> porudzbineStanjeCekaDostavljaca(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok(porudzbinaService.findCekaDostavljaca());

    }

    @PostMapping("/api/izaberi-restoran")
    public ResponseEntity<Porudzbina> izaberRestoran(@RequestBody RestoranImeDto restoranImeDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.Kupac) {
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac = (Kupac) loggedKorisnik;

        porudzbinaService.makePorudzbina(kupac, restoranImeDto.getNaziv());

        return ResponseEntity.ok(porudzbinaService.getAll());

    }

    @PostMapping(
            value = "dodaj-u-korpu",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Porudzbina> dodajUKorpu(@RequestBody KorpaDto korpaDto, HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.Kupac) {
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac = (Kupac) loggedKorisnik;

        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);

        return new ResponseEntity<>(porudzbinaService.kreirajStavkuPorudzbine(korpaDto.getArtikal(), korpaDto.getKolicina(), porudzbina), HttpStatus.OK);


    }

    @PostMapping("/api/ukloni-iz-korpe")
    public ResponseEntity<Porudzbina> ukloniIzKorpe(@RequestBody KorpaDto korpaDto, HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.Kupac) {
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac = (Kupac) loggedKorisnik;

        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);

        return ResponseEntity.ok(porudzbinaService.ukloniStavkuPorudzbine(korpaDto.getArtikal(), korpaDto.getKolicina(), porudzbina));

    }

    @PostMapping("/api/poruci")
    public ResponseEntity<Porudzbina> poruci(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.Kupac) {
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        Kupac kupac = (Kupac) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.findPorduzbinaUSastavljanju(kupac);
        porudzbinaService.checkIfEmpty(porudzbina);
        if (porudzbina.getPoruceniArtikli().isEmpty()) {
            return new ResponseEntity("Porudzbina je prazna", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(porudzbina);
        }

    }

    @PutMapping("/api/menadzer/promeni-status/{id}")
    public ResponseEntity<Porudzbina> uPripremi(@PathVariable(name = "id") String id, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if (loggedKorisnik.getUloga() != Uloga.Menadzer) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer = (Menadzer) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.promeniStatusMenadzer(menadzer.getRestoran(), id);


        return ResponseEntity.ok(porudzbina);
    }

        @PutMapping(value = "dostavljac/promeni-status/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> uTransportu(@PathVariable(name = "id") String id, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Dostavljac dostavljac = (Dostavljac) loggedKorisnik;
        Porudzbina porudzbina = porudzbinaService.promeniStatusDostavljac(dostavljac, id);
        if (porudzbina == null) {
            return new ResponseEntity("Nemate pristup ovoj porudzbini", HttpStatus.BAD_REQUEST);
        }
        PorudzbinaDto porudzbinaDto = new PorudzbinaDto(porudzbina.getUUID().toString(), porudzbina.getCena(), porudzbina.getDatumIVreme().toString(), porudzbina.getStatus());

        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @GetMapping(value = "dostavljac/porudzbine/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PorudzbinaDto>> porudzbineDostavljaca(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null) {
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        Dostavljac dostavljac = (Dostavljac) loggedKorisnik;
        Set<PorudzbinaDto> set = new HashSet<>();
        for(Porudzbina porudzbina: dostavljac.getPorudzbineZaDostavu()){
            PorudzbinaDto dto = new PorudzbinaDto(porudzbina.getUUID().toString(), porudzbina.getCena(), porudzbina.getDatumIVreme().toString(), porudzbina.getStatus());
            set.add(dto);
        }

        return new ResponseEntity<>(set, HttpStatus.OK);

    }
}
