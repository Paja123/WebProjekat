package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.ArtikalDto;
import vezbe.demo.dto.NoviMenadzerDto;
import vezbe.demo.dto.RestoranDto;
import vezbe.demo.model.*;
import vezbe.demo.service.ArtikalService;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;


    @GetMapping("/api")
    public ResponseEntity<List<Restoran>> api(){

        return ResponseEntity.ok(restoranService.findAll());
    }


    @PostMapping("/api/kreirajRestoran")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Admin){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }

        Restoran restoran  = new Restoran(restoranDto.getNaziv(), restoranDto.getTipRestorana());
        this.restoranService.save(restoran);

        return ResponseEntity.ok("Uspesno kreiranje restorana!");
    }
    @GetMapping("/api/{naziv}")
    public ResponseEntity<Restoran> getRestoranByNaziv(@PathVariable(name = "naziv") String naziv){

        return ResponseEntity.ok(restoranService.findByName(naziv));
    }
    @GetMapping("/api/tipRestorana/{tipRestorana}")
    public ResponseEntity<List<Restoran>> getRestoranByTipRestorana(@PathVariable(name = "tipRestorana") String tipRestorana){

        return ResponseEntity.ok(restoranService.findByTip(tipRestorana));
    }
    @GetMapping("/api/lokacijaRestorana/{lokacija}")
    public ResponseEntity<Restoran> getRestoranByLokacija(@PathVariable(name = "lokacija") String lokacija){

        return ResponseEntity.ok(restoranService.findByLokacija(lokacija));
    }
    @PostMapping("/api/dodajArtikal")
    public ResponseEntity<Set<Artikal>> dodajArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session){
        Menadzer loggedKorisnik = (Menadzer) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik.getRestoran()== null){
            return new ResponseEntity("ne mozes ovako loggedKorinsik", HttpStatus.BAD_REQUEST);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        TipArtikla tipArtikla = TipArtikla.valueOf(artikalDto.getTipArtikla());

        Artikal artikal  = new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), tipArtikla);

        Set<Artikal> l =  restoranService.dodajArtikal(artikal, loggedKorisnik.getRestoran());


        return ResponseEntity.ok(l);

    }
    @PutMapping("/api/restoran/promeniMenadzera")
    public ResponseEntity<Menadzer> promeniMenadzera(@RequestBody NoviMenadzerDto dto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Admin){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }

        Menadzer menadzer= restoranService.findByNaziv(dto.getNaziv(), dto.getKorisnickoIme());

        return ResponseEntity.ok(menadzer);
    }


}
