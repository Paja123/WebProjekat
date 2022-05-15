package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vezbe.demo.model.*;
import vezbe.demo.service.MenadzerService;
import vezbe.demo.service.PorudzbinaService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/api/dostavljac/cekaDostavljaca")
    public ResponseEntity<Set<Porudzbina>> porudzbineStanjeCekaDostavljaca(HttpSession session) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if (loggedKorisnik.getUloga() != Uloga.Dostavljac) {
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }



        return ResponseEntity.ok(porudzbinaService.findCekaDostavljaca());

    }

}
