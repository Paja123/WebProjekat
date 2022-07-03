package vezbe.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KorisnikDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.*;
import vezbe.demo.service.KorisnikService;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin(origins = "http://localhost:8080")
public class KorisnikRestController {
    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikRestController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }


    @PostMapping(
            value="login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDto> login(@RequestBody KorisnikDto loginDto, HttpSession session) throws Exception{
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()){
            return new ResponseEntity<>(new LoginDto(), HttpStatus.OK);
        }
        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (loggedKorisnik == null){
            return new ResponseEntity<>(new LoginDto(), HttpStatus.OK);}
        session.setAttribute("logovaniKorsinik", loggedKorisnik);

        KorisnikDto korisnikDto= new KorisnikDto(loggedKorisnik.getKorisnickoIme(), loggedKorisnik.getLozinka(), loggedKorisnik.getIme(), loggedKorisnik.getPrezime(), loggedKorisnik.getDatumRodjenja(), loggedKorisnik.getPol(), loggedKorisnik.getUloga());
        LoginDto dto = new LoginDto(loggedKorisnik.getKorisnickoIme(), loggedKorisnik.getLozinka(), loggedKorisnik.getIme(), loggedKorisnik.getPrezime(), loggedKorisnik.getDatumRodjenja(), loggedKorisnik.getPol(), loggedKorisnik.getUloga());
       dto.setSessionId(session.getId());
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "profil",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getProfil(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedKorisnik);
        }
        return ResponseEntity.ok(loggedKorisnik);

    }
    @GetMapping(value = "korisnici",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDto>> getSveKorisnike(HttpSession session){
        List<Korisnik> listaKorisnika = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if(loggedKorisnik.getUloga()!=Uloga.Admin){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        List<KorisnikDto> dtoList =  korisnikService.findAll();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
}
