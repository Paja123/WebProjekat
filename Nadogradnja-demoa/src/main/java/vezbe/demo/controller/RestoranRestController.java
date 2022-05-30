package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vezbe.demo.dto.*;
import vezbe.demo.model.*;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;


    @GetMapping("/api")
    public ResponseEntity<List<RestoranBasicInfoDto>> api(){

        return ResponseEntity.ok(restoranService.getAllBasicInfo());
    }


    @PostMapping("/api/kreiraj-restoran")
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
    public ResponseEntity<RestoranIspisDto> getRestoranByNaziv(@PathVariable(name = "naziv") String naziv){
        Restoran restoran = restoranService.findByName(naziv);
        double prosecnaOcena = restoranService.getProsecnaOcena(restoran.getId());
        Set<KomentarDto> komentari = restoranService.getKomentari(restoran.getId());
        RestoranIspisDto dto = new RestoranIspisDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija(), prosecnaOcena, komentari, restoran.getPonuda());

        return ResponseEntity.ok(dto);
    }
    @GetMapping("/api/tip-restorana/{tipRestorana}")
    public ResponseEntity<List<RestoranIspisDto>> getRestoraniByTipRestorana(@PathVariable(name = "tipRestorana") String tipRestorana){
        List<Restoran> listaRestorana  = restoranService.findByTip(tipRestorana);
        List<RestoranIspisDto> ListaZaIspis  = restoranService.SpremiZaIspis(listaRestorana);

        return ResponseEntity.ok(ListaZaIspis);
    }
    @GetMapping("/api/lokacija-restorana/{lokacija}")
    public ResponseEntity<RestoranIspisDto> getRestoranByLokacija(@PathVariable(name = "lokacija") String lokacija){
        Restoran restoran  = restoranService.findByLokacija(lokacija);
        double prosecnaOcena = restoranService.getProsecnaOcena(restoran.getId());
        Set<KomentarDto> komentari = restoranService.getKomentari(restoran.getId());
        RestoranIspisDto dto = new RestoranIspisDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija(), prosecnaOcena, komentari, restoran.getPonuda());

        return ResponseEntity.ok(dto);
    }
    @PostMapping("/api/dodaj-artikal")
    public ResponseEntity<Set<Artikal>> dodajArtikal(@RequestParam("image") MultipartFile multipartFile,ArtikalDto artikalDto, HttpSession session) throws IOException {
        Menadzer loggedKorisnik = (Menadzer) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik.getRestoran()== null){
            return new ResponseEntity("ne mozes ovako loggedKorinsik", HttpStatus.BAD_REQUEST);
        }
        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        if(artikalDto.getNaziv().isEmpty()){
            return new ResponseEntity("Polje naziv je obavezno!", HttpStatus.BAD_REQUEST);
        }
        if(artikalDto.getTipArtikla().isEmpty()){
            return new ResponseEntity("Polje tipArtikla je obavezno!", HttpStatus.BAD_REQUEST);
        }
        if(artikalDto.getCena() == 0){
            return new ResponseEntity("Polje cena je obavezno!", HttpStatus.BAD_REQUEST);
        }
        if(multipartFile.isEmpty()){
            return new ResponseEntity("Polje slika je obavezno!", HttpStatus.BAD_REQUEST);
        }
        TipArtikla tipArtikla = TipArtikla.valueOf(artikalDto.getTipArtikla());

        Artikal artikal  = new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), tipArtikla);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        artikal.setSlika(fileName);

        //String uploadDir = "artikal-slike/" + artikal.getId();
        String uploadDir = "src/main/resources/static/images/" + artikal.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        Set<Artikal> listaArtikala =  restoranService.dodajArtikal(artikal, loggedKorisnik.getRestoran());


        return ResponseEntity.ok(listaArtikala);

    }


    @PutMapping("/api/restoran/promeni-menadzera")
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
