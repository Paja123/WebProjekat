package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KomentarDto;
import vezbe.demo.dto.RestoranBasicInfoDto;
import vezbe.demo.dto.RestoranIspisDto;
import vezbe.demo.dto.RestoranPretragaDto;
import vezbe.demo.model.*;
import vezbe.demo.repository.RestoranRepository;

import java.util.*;

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
    public RestoranIspisDto spremiZaIspis(Restoran restoran, double prosecnaOcena, Set<KomentarDto> komentari){
        RestoranIspisDto dto = new RestoranIspisDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija(), prosecnaOcena, komentari, restoran.getPonuda());
        return dto;
    }

    public Restoran findById(Long id){
        Restoran r= null;
        for(Restoran restoran: restoranRepository.findAll()){
            if(restoran.getId().equals(id)){
                r = restoran;
            }
        }
        return r;
    }


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
    public Set<RestoranPretragaDto> pronadjiRestorane(String input){
        Set<RestoranPretragaDto> lista = new HashSet<>();
        for(Restoran restoran: restoranRepository.findAll()){
            if(input.contains(restoran.getNaziv())){
                RestoranPretragaDto dto = new RestoranPretragaDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija().getAdresa());
                lista.add(dto);
                continue;
            }
            if(input.contains(restoran.getTipRestorana())){
                RestoranPretragaDto dto = new RestoranPretragaDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija().getAdresa());
                lista.add(dto);
                continue;
            }
            if(input.contains(restoran.getLokacija().getAdresa())){
                RestoranPretragaDto dto = new RestoranPretragaDto(restoran.getNaziv(), restoran.getTipRestorana(), restoran.getLokacija().getAdresa());
                lista.add(dto);
                continue;
            }
        }
            return lista;
    }
    public List<Artikal> removeArtikal(Long id, Restoran restoran) {
        Artikal a = artikalService.findById(id);

        restoran.getPonuda().remove(a);
        restoranRepository.save(restoran);

        List<Artikal> l= new ArrayList<>();
        for(Artikal artikal:restoran.getPonuda()){
            l.add(artikal);
        }
        return l;
    }
    public Artikal promeniArtikal(Artikal artikal, Restoran restoran,Long id){
        Artikal stariArtikal = artikalService.findById(id);
        if(!artikal.getNaziv().isEmpty()){
            stariArtikal.setNaziv(artikal.getNaziv());
        }
        if(artikal.getTipArtikla()!=null){
            stariArtikal.setTipArtikla(artikal.getTipArtikla());
        }
        if(artikal.getCena() != 0){
            stariArtikal.setCena(artikal.getCena());
        }
        if(artikal.getKolicina()!= 0){
            stariArtikal.setKolicina(artikal.getKolicina());
        }
        if(!artikal.getOpis().isEmpty()){
            stariArtikal.setOpis(artikal.getOpis());
        }
        artikalService.save(stariArtikal);
        restoranRepository.save(restoran);
        return stariArtikal;

        }
        public Boolean pronadjiArtikal(Long id, Restoran restoran){
            for(Artikal artikal: restoran.getPonuda()){
                if(artikal.getId().equals(id)){
                    return  true;
                }
            }
            return  false;
        }
    }
