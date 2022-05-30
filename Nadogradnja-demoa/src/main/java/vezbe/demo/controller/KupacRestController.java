package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KupacDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.*;
import vezbe.demo.service.KupacService;


import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RestController
public class KupacRestController {

    @Autowired
    private KupacService kupacService;



    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody KupacDto kupacDto) throws ParseException {

        String sDate1=kupacDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);

        Pol pol = Pol.valueOf(kupacDto.getPol());

        Kupac kupac  = new Kupac(kupacDto.getKorisnickoIme(), kupacDto.getLozinka(), kupacDto.getIme(), kupacDto.getPrezime(),pol, date1);
        this.kupacService.save(kupac);

        return ResponseEntity.ok("Uspesna registracija!");

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
