package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KupacDto;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Uloga;
import vezbe.demo.service.KupacService;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
public class KupacRestController {

    @Autowired
    private KupacService kupacService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody KupacDto kupacDto) throws ParseException {

        String sDate1=kupacDto.getDatumRodjenja();
        Date date1= null;

            date1 = new SimpleDateFormat("yyyy/dd/MM").parse(sDate1);


        Kupac kupac  = new Kupac(kupacDto.getKorisnickoIme(), kupacDto.getLozinka(), kupacDto.getIme(), kupacDto.getPrezime(), kupacDto.getPol(), date1);
        this.kupacService.save(kupac);

        return ResponseEntity.ok("Uspesna registracija!");

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
