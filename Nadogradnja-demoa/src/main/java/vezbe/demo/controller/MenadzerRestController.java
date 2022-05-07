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



    @PostMapping("/api/kreirajMenadzera")
    public ResponseEntity<String> kreirajMenadzera(@RequestBody MenadzerDto menadzerDto) throws ParseException {

        String sDate1=menadzerDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);

        Pol pol = Pol.valueOf(menadzerDto.getPol());

        Menadzer menadzer  = new Menadzer(menadzerDto.getKorisnickoIme(), menadzerDto.getLozinka(), menadzerDto.getIme(), menadzerDto.getPrezime(),pol, date1);
        this.menadzerService.save(menadzer);

        return ResponseEntity.ok("Uspesno kreiranje menadzera!");

    }
    @GetMapping("/api/pregledRestorana")
    public ResponseEntity<Restoran> getRestoran(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        Restoran restoran =  menadzerService.findRestoran(loggedKorisnik.getId());
        return ResponseEntity.ok(restoran);

    }
/*    @GetMapping("/api/pregledPorudzbina")
    public ResponseEntity<List<Porudzbina>> getPorudzbine(HttpSession session){
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("logovaniKorsinik");
        if(loggedKorisnik.getUloga()!= Uloga.Menadzer){
            return new ResponseEntity("Nemate pristupa ovim podacima", HttpStatus.BAD_REQUEST);
        }
        Restoran restoran =  menadzerService.findRestoran(loggedKorisnik.getId());


    }
*/


/*    @GetMapping("/api/kupci")
    public List<Kupac> getKupci(){
        List<Kupac> kupacList = kupacService.findAll();

        return kupacList;
    }

    @GetMapping("/api/employees/{id}")
    public Kupac getEmployee(@PathVariable(name = "id") Long id){
        Kupac employee = employeeService.findOne(id);
        return employee;
    }

    @PostMapping("/api/save-employee")
    public String saveEmployee(@RequestBody Employee employee) {
        this.employeeService.save(employee);
        return "Successfully saved an employee!";
    }
    @GetMapping("/api/delById/{id}")
    public String deleteEmployee(@PathVariable(name = "id") Long id){
        Employee employee = employeeService.findOne(id);
        this.employeeService.delete(employee);
        return "Sucessfully deleted and employee!";
    }
*/
}
