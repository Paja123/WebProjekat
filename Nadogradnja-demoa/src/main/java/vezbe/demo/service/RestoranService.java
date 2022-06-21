package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KomentarDto;
import vezbe.demo.dto.RestoranBasicInfoDto;
import vezbe.demo.dto.RestoranDto;
import vezbe.demo.dto.RestoranIspisDto;
import vezbe.demo.model.*;
import vezbe.demo.repository.RestoranRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private StavkaPorudzbineService stavkaPorudzbineService;

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private LokacijaService lokacijaService;


    /*  @Autowired
      private PorudzbinaService porudzbinaService;
  */
    public List<RestoranBasicInfoDto> getAllBasicInfo(){
        List<RestoranBasicInfoDto> dtoList = new ArrayList<>();
        for(Restoran restoran: restoranRepository.findAll()){

            RestoranBasicInfoDto dto = new RestoranBasicInfoDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija().getAdresa());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public Restoran save(Restoran restoran) {
        lokacijaService.save(restoran.getLokacija());
        return restoranRepository.save(restoran);
    }

    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    ;

    public Restoran findByName(String naziv) {
        Restoran r = null;
        for (Restoran restoran : restoranRepository.findAll()) {
            if (restoran.getNaziv().equals(naziv)) {
                r = restoran;
            }
        }
        return r;
    }

    public List<Restoran> findByTip(String tipRestorana) {
        List<Restoran> lista = new ArrayList<>();
        for (Restoran restoran : restoranRepository.findAll()) {
            if (restoran.getTipRestorana().equals(tipRestorana)) {
                lista.add(restoran);
            }
        }
        return lista;
    }

    public Restoran findByLokacija(String adresa) {
        Restoran r = null;
        for (Restoran restoran : restoranRepository.findAll()) {
            if (restoran.getLokacija().getAdresa().equals(adresa)) {
                r = restoran;
            }
        }
        return r;
    }

    public ArtikalService getArtikalService() {
        return artikalService;
    }

    public Set<Artikal> dodajArtikal(Artikal artikal, Restoran restoran) {
        Set<Artikal> novaLista = new HashSet<>();
        novaLista = artikalService.dodajArtikal(artikal, restoran);
        restoranRepository.save(restoran);
        return novaLista;
    }

    public Menadzer findByNaziv(String naziv, String KorisnickoIme) {
        Restoran r = null;
        for (Restoran restoran : restoranRepository.findAll()) {
            if (restoran.getNaziv().equals(naziv)) {
                r = restoran;
            }
        }
        return menadzerService.postaviNovogMenadzera(KorisnickoIme, r);//IZMENI NAZIV OVE FUNKCIJE

    }

    public Restoran findByRestoranIme(String naziv) {
        Restoran r = null;
        for (Restoran restoran : restoranRepository.findAll()) {
            if (restoran.getNaziv().equals(naziv)) {
                r = restoran;
            }
        }
        return r;
    }

    public double getProsecnaOcena(Long restoranId){
        return komentarService.getProsecnaOcena(restoranId);
    }
    public Set<KomentarDto> getKomentari(Long restoranId){
        return komentarService.getByRestoran(restoranId);
    }
    public List<RestoranIspisDto> SpremiZaIspis(List<Restoran> listaRestorana){
        List<RestoranIspisDto> ispisLista = new ArrayList<>();
        for(Restoran restoran: listaRestorana){
            double prosecnaOcena = getProsecnaOcena(restoran.getId());
            Set<KomentarDto> komentari = getKomentari(restoran.getId());
            RestoranIspisDto dto = new RestoranIspisDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija(), prosecnaOcena, komentari, restoran.getPonuda());
            ispisLista.add(dto);
        }
        return ispisLista;
    }
}
