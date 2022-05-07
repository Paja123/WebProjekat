package vezbe.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.*;
import vezbe.demo.service.KorisnikService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()){
            return new ResponseEntity("Invalid login data", HttpStatus.BAD_REQUEST);
        }
        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (loggedKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        Korisnik prijavljeniKorisnik  = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        session.setAttribute("logovaniKorsinik", loggedKorisnik);


        return ResponseEntity.ok("Uspesno prijavljivanje!");
    }

    @GetMapping("/api/profil")
    public ResponseEntity<Korisnik> getProfil(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedKorisnik);
        }
        return ResponseEntity.ok(loggedKorisnik);

    }
}
