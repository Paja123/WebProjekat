package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.ArtikalDto;
import vezbe.demo.model.*;
import vezbe.demo.service.ArtikalService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class ArtikalRestController {

    @Autowired
    private ArtikalService artikalService;



}
