package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vezbe.demo.service.LokacijaService;

@RestController
public class LokacijaRestController {
    @Autowired
    private LokacijaService lokacijaService;



}
