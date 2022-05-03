package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.model.Kupac;
import vezbe.demo.service.KupacService;


import java.util.Date;
import java.util.List;

@RestController
public class KupacRestController {

    @Autowired
    private KupacService kupacService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("/api/registracija")
    public String registracijaKupca(@RequestBody Kupac kupac) {
        this.kupacService.save(kupac);
        return "Uspesna registracija!";
    }
/*
    @GetMapping("/api/employees")
    public List<Kupac> getKupci(){
        List<Kupac> employeeList = kupacService.findAll();

        return employeeList;
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
