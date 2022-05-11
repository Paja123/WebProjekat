package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KupacDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.*;
import vezbe.demo.service.KupacService;


import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
public class KupacRestController {

    @Autowired
    private KupacService kupacService;



    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody KupacDto kupacDto) throws ParseException {

        String sDate1=kupacDto.getDatumRodjenja();
        Date date1= null;
        date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);

        Pol pol = Pol.valueOf(kupacDto.getPol());

        Kupac kupac  = new Kupac(kupacDto.getKorisnickoIme(), kupacDto.getLozinka(), kupacDto.getIme(), kupacDto.getPrezime(),pol, date1);
        this.kupacService.save(kupac);

        return ResponseEntity.ok("Uspesna registracija!");

    }





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
