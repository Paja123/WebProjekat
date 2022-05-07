package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.MenadzerDto;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Pol;
import vezbe.demo.service.MenadzerService;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

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
