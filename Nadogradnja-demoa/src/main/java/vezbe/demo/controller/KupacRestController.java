package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KorisnikDto;
import vezbe.demo.dto.KupacDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.*;
import vezbe.demo.service.KupacService;
import vezbe.demo.service.RestoranService;


import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping(value = "/api/")
public class KupacRestController {

    private final KupacService kupacService;

    @Autowired
    public KupacRestController(KupacService kupacService) {
        this.kupacService = kupacService;
    }



    @PostMapping(
            value = "/registracija",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registracija(@RequestBody KorisnikDto korisnikDto) throws ParseException {

//        if(!kupacService.checkIfUsernameIsAvailable(korisnikDto.getKorisnickoIme())){
//            return new ResponseEntity<>("korisnicko ime nije slobodno", HttpStatus.OK);
//        }

        String sDate1=korisnikDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

        Pol pol = Pol.valueOf(korisnikDto.getPol());

        Kupac kupac  = new Kupac(korisnikDto.getKorisnickoIme(), korisnikDto.getLozinka(), korisnikDto.getIme(), korisnikDto.getPrezime(),pol, date1);
        this.kupacService.save(kupac);

        return  new ResponseEntity<>("Uspesna registracija!", HttpStatus.OK);

    }
    @GetMapping("/api/sve-porudzbine")
    public ResponseEntity<Set<Porudzbina>> sveProduzbine(HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");

        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Kupac){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        //Kupac kupac = (Kupac) loggedKorisnik;
        if(kupacService.findAllPorudzbineByID(loggedKorisnik.getId()).isEmpty()){
            System.out.println("PRAZNA LISTA");
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok( kupacService.findAllPorudzbineByID(loggedKorisnik.getId()));

    }
}
