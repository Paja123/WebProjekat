package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vezbe.demo.dto.LokacijaDto;
import vezbe.demo.dto.RestoranDto;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Lokacija;
import vezbe.demo.model.Restoran;
import vezbe.demo.model.Uloga;
import vezbe.demo.service.LokacijaService;

import javax.servlet.http.HttpSession;

@RestController
public class LokacijaRestController {
    @Autowired
    private LokacijaService lokacijaService;

    @PostMapping("/api/kreirajLokaciju")
    public ResponseEntity<String> kreirajLokaciju(@RequestBody LokacijaDto lokacijaDto, HttpSession session){
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Admin){
            return new ResponseEntity("Nemate dozvolu za ovu funkciju", HttpStatus.BAD_REQUEST);
        }

        Lokacija lokacija  = new Lokacija(lokacijaDto.getGeoDuzina(), lokacijaDto.getGeoSirina(), lokacijaDto.getAdresa());
        this.lokacijaService.save(lokacija);

        return ResponseEntity.ok("Uspesno kreiranje lokacije!");
    }

}
