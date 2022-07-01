package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@CrossOrigin("http://localhost:8080")
@RequestMapping(value = "/api/")
public class RestoranRestController {

    private final RestoranService restoranService;

    @Autowired
    public RestoranRestController(RestoranService restoranService) {
        this.restoranService = restoranService;
    }


    @GetMapping(
            value = "/restorani",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
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
        Lokacija lokacija = new Lokacija(restoranDto.getGeoSirina(), restoranDto.getGeoDuzina(), restoranDto.getAdresa());
        restoran.setLokacija(lokacija);
        this.restoranService.save(restoran);

        return ResponseEntity.ok("Uspesno kreiranje restorana!");
    }
    
    @GetMapping("/pretraga")
    public ResponseEntity<Set<RestoranPretragaDto>> pretragaRestorana(@RequestBody String input){
        Set<RestoranPretragaDto> restorani = restoranService.pronadjiRestorane(input);
       return ResponseEntity.ok(restorani);

    }

    @GetMapping(
            value = "/restorani/{naziv}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RestoranIspisDto> prikazJednogRestorana(@PathVariable(name = "naziv") String naziv){
        Restoran restoran = restoranService.findByName(naziv);
        double prosecnaOcena = restoranService.getProsecnaOcena(restoran.getId());
        Set<KomentarDto> komentari = restoranService.getKomentari(restoran.getId());
        if (restoran == null)
            return new ResponseEntity<>(new RestoranIspisDto(), HttpStatus.OK);

        return new ResponseEntity<>(restoranService.spremiZaIspis(restoran, prosecnaOcena, komentari), HttpStatus.OK);
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
    @DeleteMapping("/ukloni-artikal/{id}")
    public ResponseEntity<List<Artikal>> ukloniArtikal(@PathVariable(name = "id") Long id, HttpSession session){
        Menadzer loggedKorisnik = (Menadzer) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik.getRestoran()== null){
            return new ResponseEntity("ne mozes ovako loggedKorinsik", HttpStatus.BAD_REQUEST);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        if(!restoranService.pronadjiArtikal(id, loggedKorisnik.getRestoran())){
            return new ResponseEntity("U vasoj ponudi nema ovog artikla", HttpStatus.BAD_REQUEST);
        }



        List<Artikal> l =  restoranService.removeArtikal(id, loggedKorisnik.getRestoran());
        return ResponseEntity.ok(l);
    }
    @PutMapping("/api/promeni-artikal/{id}")
    public ResponseEntity<Artikal> promeniArtikal(@PathVariable(name = "id") Long id, @RequestBody Artikal artikal, HttpSession session){
        Menadzer loggedKorisnik = (Menadzer) session.getAttribute("logovaniKorsinik");

        if(loggedKorisnik.getRestoran()== null){
            return new ResponseEntity("ne mozes ovako loggedKorinsik", HttpStatus.BAD_REQUEST);
        }

        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        if(!restoranService.pronadjiArtikal(id, loggedKorisnik.getRestoran())){
            return new ResponseEntity("U vasoj ponudi nema ovog artikla", HttpStatus.BAD_REQUEST);
        }

        //SLIKU NISI MENJAOOOOOOOOOOOOOOOOOOOOOOOOO
        return ResponseEntity.ok(restoranService.promeniArtikal(artikal, loggedKorisnik.getRestoran(), id));

    }






}
