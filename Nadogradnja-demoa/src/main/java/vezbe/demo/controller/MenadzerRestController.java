package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.MenadzerDto;
import vezbe.demo.model.*;
import vezbe.demo.service.MenadzerService;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;



    @PostMapping("/api/kreiraj-menadzera")
    public ResponseEntity<String> kreirajMenadzera(@RequestBody MenadzerDto menadzerDto, HttpSession session) throws ParseException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");

        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Admin){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }

        String sDate1=menadzerDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);

        Pol pol = Pol.valueOf(menadzerDto.getPol());

        Menadzer menadzer  = new Menadzer(menadzerDto.getKorisnickoIme(), menadzerDto.getLozinka(), menadzerDto.getIme(), menadzerDto.getPrezime(),pol, date1);
        this.menadzerService.save(menadzer);

        return ResponseEntity.ok("Uspesno kreiranje menadzera!");

    }
    @GetMapping("/api/pregled-restorana")
    public ResponseEntity<Restoran> getRestoran(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }

        Restoran restoran =  menadzerService.findRestoran(loggedKorisnik.getId());
        return ResponseEntity.ok(restoran);

    }
    @DeleteMapping("/api/ukloni-artikal/{id}")
    public ResponseEntity<List<Artikal>> ukloniArtikal(@PathVariable(name = "id") Long id, HttpSession session){
        Menadzer loggedKorisnik = (Menadzer) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik.getRestoran()== null){
            return new ResponseEntity("ne mozes ovako loggedKorinsik", HttpStatus.BAD_REQUEST);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }



       List<Artikal> l =  menadzerService.removeArtikal(id, loggedKorisnik.getRestoran());
        return ResponseEntity.ok(l);
    }

}
