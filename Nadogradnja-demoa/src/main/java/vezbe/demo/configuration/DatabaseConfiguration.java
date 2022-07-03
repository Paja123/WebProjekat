package vezbe.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vezbe.demo.model.*;
import vezbe.demo.repository.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;
    @Autowired
    private StavkaPorudzbineRepository stavkaPorudzbineRepository;

    @Bean
    public boolean instantiate(){
       Lokacija lokacija = new Lokacija(334,33,  "Njegoseva ulica 32");
       Lokacija lokacija2 = new Lokacija(353,30,  "Fruskogorska ulica 2");
       Lokacija lokacija3 = new Lokacija(323,31,  "Ulica Jug Bogdana 21");
       Lokacija lokacija4 = new Lokacija(352,32,  "Dunavska ulica 7");
       Lokacija lokacija5 = new Lokacija(351,30,  "Futoska ulica 2");

        lokacijaRepository.save(lokacija);
        lokacijaRepository.save(lokacija2);
        lokacijaRepository.save(lokacija3);
        lokacijaRepository.save(lokacija4);
        lokacijaRepository.save(lokacija5);


        TipKupca bronzani = new TipKupca("Bronzani", 5,  500);
        TipKupca srebrni = new TipKupca("Srebrni", 10,  1000);
        TipKupca zlatni = new TipKupca("Zlatni", 12,  2000);
        TipKupca platinum = new TipKupca("Platinum", 15,  4000);
        TipKupca dijamant = new TipKupca("Srebrni", 20,  8000);

        tipKupcaRepository.saveAll(List.of(bronzani, srebrni, zlatni, platinum, dijamant));

       Artikal artikal1 = new Artikal("pica mala", 250, TipArtikla.Jelo, 100, "pica cipriciossa, 23cm, pelat, sir, šunka, šampinjoni ");
       artikal1.setSlika("/src/main/resources/static/images/kapricoza.jpg");

       artikalRepository.save(artikal1);
        Artikal artikal2 = new Artikal("pica normalna", 520, TipArtikla.Jelo, 210, "pica cipriciossa, 35cm, pelat, sir, šunka, šampinjoni");
        artikalRepository.save(artikal2);
        Artikal artikal3 = new Artikal("pica velika", 900, TipArtikla.Jelo, 100, "pica cipriciossa, 50cm, pelat, sir, šunka, šampinjoni");
        artikalRepository.save(artikal3);
        Artikal artikal4 = new Artikal("naziv4", 123, TipArtikla.Pice, 100, "opis4");
        artikalRepository.save(artikal4);
        Artikal artikal5 = new Artikal("naziv5", 321, TipArtikla.Jelo, 210, "opis5");
        artikalRepository.save(artikal5);
        Artikal artikal6 = new Artikal("naziv6", 500, TipArtikla.Jelo, 10, "opis6");
        artikalRepository.save(artikal6);
        Artikal artikal7 = new Artikal("naziv7", 321, TipArtikla.Pice, 110, "opis7");
        artikalRepository.save(artikal7);
        Artikal artikal8 = new Artikal("naziv8", 500, TipArtikla.Jelo, 500, "opis8");
        artikalRepository.save(artikal8);
        Artikal artikal9 = new Artikal("naziv9", 123, TipArtikla.Pice, 99, "opis9");
        artikalRepository.save(artikal9);
        Artikal artikal10 = new Artikal("naziv10", 321, TipArtikla.Jelo, 210, "opis10");
        artikalRepository.save(artikal10);
        Artikal artikal11= new Artikal("naziv11", 500, TipArtikla.Jelo, 10, "opis11");
        artikalRepository.save(artikal11);

        StavkaPorudzbine stavkaPorudzbine1 = new StavkaPorudzbine(artikal1,1);
        StavkaPorudzbine stavkaPorudzbine2 = new StavkaPorudzbine(artikal2,2);
        StavkaPorudzbine stavkaPorudzbine3 = new StavkaPorudzbine(artikal3,3);
        StavkaPorudzbine stavkaPorudzbine4 = new StavkaPorudzbine(artikal4,4);
        StavkaPorudzbine stavkaPorudzbine5 = new StavkaPorudzbine(artikal5,5);
        StavkaPorudzbine stavkaPorudzbine6 = new StavkaPorudzbine(artikal6,6);
        StavkaPorudzbine stavkaPorudzbine7 = new StavkaPorudzbine(artikal1,7);
    /*    StavkaPorudzbine stavkaPorudzbine8 = new StavkaPorudzbine(artikal8,100);
        StavkaPorudzbine stavkaPorudzbine9 = new StavkaPorudzbine(artikal9,100);
        StavkaPorudzbine stavkaPorudzbine10 = new StavkaPorudzbine(artikal10,100);
*/
        stavkaPorudzbineRepository.saveAll(List.of(stavkaPorudzbine1,stavkaPorudzbine2,stavkaPorudzbine3, stavkaPorudzbine4, stavkaPorudzbine5, stavkaPorudzbine6, stavkaPorudzbine7/*, stavkaPorudzbine8, stavkaPorudzbine9, stavkaPorudzbine10*/));




        Set<StavkaPorudzbine> lista_artikala = new HashSet<>();
        Set<StavkaPorudzbine> lista_artikala2 = new HashSet<>();
        Set<StavkaPorudzbine> lista_artikala3 = new HashSet<>();
        Set<StavkaPorudzbine> lista_artikala4 = new HashSet<>();
        Set<StavkaPorudzbine> lista_artikala5 = new HashSet<>();
        Set<StavkaPorudzbine> lista_artikala6 = new HashSet<>();


        lista_artikala.add(stavkaPorudzbine1);
        lista_artikala.add(stavkaPorudzbine2);
        lista_artikala.add(stavkaPorudzbine3);

        lista_artikala2.add(stavkaPorudzbine4);
        lista_artikala2.add(stavkaPorudzbine5);
        lista_artikala2.add(stavkaPorudzbine6);
        lista_artikala2.add(stavkaPorudzbine7);

        lista_artikala3.add(stavkaPorudzbine1);
        lista_artikala3.add(stavkaPorudzbine4);

       // lista_artikala4.add(stavkaPorudzbine7);
       // lista_artikala4.add(stavkaPorudzbine8);

      //  lista_artikala5.add(stavkaPorudzbine9);
      //  lista_artikala5.add(stavkaPorudzbine10);
//
     //   lista_artikala6.add(stavkaPorudzbine8);
      //  lista_artikala6.add(stavkaPorudzbine10);




        Restoran restoran = new Restoran("Padrino's", "picerija", lokacija);
        restoran.getPonuda().add(artikal1);
        restoran.getPonuda().add(artikal2);
        restoran.getPonuda().add(artikal3);
        restoranRepository.save(restoran);

        Restoran restoran2 = new Restoran("Pekara Krosti", "pekara", lokacija2);
        restoran2.getPonuda().add(artikal4);
        restoran2.getPonuda().add(artikal5);
        restoran2.getPonuda().add(artikal6);
        restoranRepository.save(restoran2);

        Restoran restoran3 = new Restoran("Plava Frajla", "restoran", lokacija3);
        restoran2.getPonuda().add(artikal4);
        restoran2.getPonuda().add(artikal5);
        restoran2.getPonuda().add(artikal6);
        restoranRepository.save(restoran3);

        Restoran restoran4 = new Restoran("Basta", "kafe&restoran", lokacija4);
        restoran2.getPonuda().add(artikal4);
        restoran2.getPonuda().add(artikal7);
        restoran2.getPonuda().add(artikal11);
        restoranRepository.save(restoran4);

        Restoran restoran5 = new Restoran("McDonald's ", "brza hrana", lokacija5);
        restoran2.getPonuda().add(artikal8);
        restoran2.getPonuda().add(artikal9);
        restoran2.getPonuda().add(artikal10);
        restoranRepository.save(restoran5);


        Date date1 = new Date();
        Set<Porudzbina> novaListaPorudzbina = new HashSet<>();

        Kupac kupac2= new Kupac("korisnickoIme2", "lozinka2", "Predrag", "Nenadic",Pol.muski,new Date(100, Calendar.JANUARY, 10), novaListaPorudzbina, 1100, srebrni);
        kupacRepository.save(kupac2);


        Porudzbina porudzbina2 = new Porudzbina();
        porudzbina2.setPoruceniArtikli(lista_artikala2);
        porudzbina2.setRestoran(restoran);
        porudzbina2.setDatumIVreme(date1);
        porudzbina2.setCena(2000);
        porudzbina2.setKupac(kupac2);
        porudzbina2.setStatus(StatusPorudzbine.Obrada);
        porudzbinaRepository.save(porudzbina2);
        kupacRepository.save(kupac2);
        kupac2.getListaPorudzbina().add(porudzbina2);
        Porudzbina porudzbina = new Porudzbina( lista_artikala, restoran, date1 , 5000,kupac2, StatusPorudzbine.CekaDostavljača);
        porudzbinaRepository.save(porudzbina);
        kupacRepository.save(kupac2);
        Date date = new Date();
        Menadzer menadzer = new Menadzer("menadzer123", "lozinka", "Milos", "Subotic", Pol.muski,date , restoran);
        menadzerRepository.save(menadzer);
        Menadzer menadzer2 = new Menadzer("menadzerski321", "asfjhioaf", "Andrija", "Blesic", Pol.muski,date , restoran2);
        menadzerRepository.save(menadzer2);

        Set<Porudzbina> lista_porudzbina = new HashSet<>();
        lista_porudzbina.add(porudzbina);
        Set<Porudzbina> lista_porudzbina2 = new HashSet<>();
        lista_porudzbina2.add(porudzbina2);
        Dostavljac dostavljac = new Dostavljac("dostavljac123", "lozinka", "Marko", "Markovic", Pol.muski,new Date(98, Calendar.DECEMBER, 3), lista_porudzbina);
        dostavljacRepository.save(dostavljac);
        Dostavljac dostavljac2 = new Dostavljac("dostavljac321", "lozinka", "Petar", "Petrovic", Pol.muski,new Date(99, Calendar.MARCH, 21), lista_porudzbina2);
        //Dostavljac dostavljac3 = new Dostavljac("dostavljac123", "lozinka", "Jovan", "Jovanovic", "muski",new Date(97, Calendar.JULY, 11), lista_porudzbina);
        dostavljacRepository.save(dostavljac2);
        //dostavljacRepository.save(dostavljac3);





        Kupac kupac= new Kupac("kupac123", "!@$@$343", "Petra", "Milosevic", Pol.zenski,new Date(101, Calendar.SEPTEMBER, 10), null, 1001, srebrni);
        kupacRepository.save(kupac);

       // Porudzbina porudzbina3 = new Porudzbina( lista_artikala3, restoran, date1 , 123000,kupac, StatusPorudzbine.UPripremi);
        //porudzbinaRepository.save(porudzbina3);

        kupac2.setTipKupca(srebrni);
        kupacRepository.save(kupac2);

        Komentar komentar = new Komentar(kupac2, restoran, "Sjajan restoran :D", 5);
        komentarRepository.save(komentar);
        Komentar komentar2 = new Komentar(kupac, restoran2, "Me likey, very nice", 5);
        komentarRepository.save(komentar2);

        Korisnik admin = new Korisnik("admin", "sifra", "Admin", "Adminovski" , Pol.muski,new Date(101, Calendar.SEPTEMBER, 10), Uloga.Admin);
        korisnikRepository.save(admin);
        /*Department department1 = new Department("first department");
        Department department2 = new Department("second department");

        department1.setCompany(company);
        department2.setCompany(company);
        departmentRepository.saveAll(
                List.of(department1, department2)
        );

        Employee pera = new Employee(
                "Pera", "Peric", "Rukovodilac", department1
        );
        Employee mika = new Employee(
                "Mika", "Mikic", "Menadzer", department1
        );
        Employee zika = new Employee(
                "Zika", "Zikic", "Radnik", department2
        );

        employeeRepository.saveAll(
                List.of(pera, mika, zika)
        );

        Project project1 = new Project(
                "Projekat 1", new Date(125, Calendar.JULY, 4)
        );

        Project project2 = new Project(
                "Projekat 2", new Date(129, Calendar.DECEMBER, 3)
        );

        projectRepository.saveAll(
                List.of(project1, project2)
        );

        mika.getProjects().add(project1);
        mika.getProjects().add(project2);

        zika.getProjects().add(project2);

        employeeRepository.save(mika);
        employeeRepository.save(zika);
    */
        return true;
    }
}