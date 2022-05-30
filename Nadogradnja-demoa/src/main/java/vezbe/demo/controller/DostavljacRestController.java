package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.DostavljacDto;
import vezbe.demo.dto.MenadzerDto;
import vezbe.demo.model.*;
import vezbe.demo.service.DostavljacService;
import vezbe.demo.service.MenadzerService;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@RestController
public class DostavljacRestController {

    @Autowired
    private DostavljacService dostavljacService;


    @PostMapping("/api/kreiraj-dostavljaca")
    public ResponseEntity<String> kreirajDostavljaca(@RequestBody DostavljacDto dostavljacDto, HttpSession session) throws ParseException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Admin){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }
        String sDate1=dostavljacDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);

        Pol pol = Pol.valueOf(dostavljacDto.getPol());

        Dostavljac dostavljac  = new Dostavljac(dostavljacDto.getKorisnickoIme(), dostavljacDto.getLozinka(), dostavljacDto.getIme(), dostavljacDto.getPrezime(),pol, date1);
        this.dostavljacService.save(dostavljac);

        return ResponseEntity.ok("Uspesno kreiranje dostavljaca!");

    }

    @GetMapping("/api/dostavljac/pregled-porudzbina")
    public ResponseEntity<Set<Porudzbina>> pregledPorudzbina(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Dostavljac ) {//Da li treba dodati i mogucnost da admin moze da pristupa ovom edpointu
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }



        return ResponseEntity.ok(dostavljacService.findAllById(loggedKorisnik.getId()));

    }
}
