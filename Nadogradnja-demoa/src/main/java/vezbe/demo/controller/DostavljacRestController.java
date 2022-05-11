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
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
public class DostavljacRestController {

    @Autowired
    private DostavljacService dostavljacService;


    //TREBA DODATI DA LI JE NA SESIJI ADMIN, AKO NIJE ONDA NEKI BAD REQUEST, TO SAM DODAO SAD
    @PostMapping("/api/kreirajDostavljaca")
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
    //  @PostMapping("/api/registracija")
   /* public ResponseEntity<String> registracija(@RequestBody KupacDto kupacDto){


        return ResponseEntity.ok("Successfully logged in!");
    }*/



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
