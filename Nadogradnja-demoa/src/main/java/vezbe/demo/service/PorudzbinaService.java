package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.PorudzbinaRepository;
import java.util.*;

@Service
public class PorudzbinaService {
    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    RestoranService restoranService;

    @Autowired
    StavkaPorudzbineService stavkaPorudzbineService;

    @Autowired
    KupacService kupacService;

    @Autowired
    DostavljacService dostavljacService;


    public List<Porudzbina> getListaPorudzbina(Restoran restoran) {
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        for (Porudzbina porudzbina : porudzbinaRepository.findAll()) {
            for (StavkaPorudzbine stavkaPorudzbine : porudzbina.getPoruceniArtikli()) {
                for (Artikal artikal : restoran.getPonuda()) {
                    if (stavkaPorudzbine.getPoruceniArtikal().getId().equals(artikal.getId())) {
                        listaPorudzbina.add(porudzbina);
                        break;
                    }

                }
            }
        }
        return listaPorudzbina;
    }
  public Set<Porudzbina> findCekaDostavljaca(){
        Set<Porudzbina> setp = new HashSet<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            if(porudzbina.getStatus().equals(StatusPorudzbine.CekaDostavljača)){
                setp.add(porudzbina);
            }
        }
        return setp;
  }
  public void dodajStavku(StavkaPorudzbine stavkaPorudzbine, Porudzbina porudzbina){
        Set<StavkaPorudzbine> lista = new HashSet<>();
        lista = porudzbina.getPoruceniArtikli();
        lista.add(stavkaPorudzbine);
        porudzbina.setPoruceniArtikli(lista);
        porudzbinaRepository.save(porudzbina);
  }
  public void makePorudzbina(Kupac kupac, String restoranName){//TREBA FORMATIRATI OVE DATUME KAD ZAVRSIS
        Restoran restoran = restoranService.findByRestoranIme(restoranName);
    //  Date date= null;
   //   date = new Date("yyyy/dd/MM");
       // Porudzbina porudzbina = new Porudzbina(kupac, restoran1, StatusPorudzbine.USastavljanu, new Date());
      Porudzbina porudzbina = null;
        for(Porudzbina porudzbina1: porudzbinaRepository.findAll()){
            if(porudzbina1.getKupac().getId().equals(kupac.getId())){
                for(Porudzbina p: kupac.getListaPorudzbina()){
                    if(p.getStatusPorudzbine().equals(StatusPorudzbine.USastavljanu)){
                        porudzbina = p;
                        porudzbina.setRestoran(restoran);
                        porudzbina.setDatumIVreme(new Date());
                    }
                }
            }
        }
        porudzbinaRepository.save(porudzbina);
        restoranService.save(restoran);
        kupacService.updateKupac(kupac, porudzbina);

  }
  public Porudzbina getAll(){
      Porudzbina p  = null;
      for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            if(porudzbina.getStatus().equals(StatusPorudzbine.Obrada)){
                p = porudzbina;
            }
        }
        return p;
  }
  public Porudzbina findByStatus(){
        Porudzbina p = null;
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            if(porudzbina.getStatus().equals(StatusPorudzbine.Obrada)) {
                p = porudzbina;
            }
        }
        return  p;
  }
  public Porudzbina findPorduzbinaUSastavljanju(Kupac kupac){
      Porudzbina p= null;
      for(Porudzbina porudzbina: kupac.getListaPorudzbina()){
          if(porudzbina.getStatus().equals(StatusPorudzbine.USastavljanu)){
              p = porudzbina;
          }
      }
      return p;
  }
  public Porudzbina kreirajStavkuPorudzbine(String naziv, int kolicina, Porudzbina porudzbina){
        StavkaPorudzbine stavkaPorudzbine = new StavkaPorudzbine();
        double temp = 0;
          for(Artikal artikal: porudzbina.getRestoran().getPonuda()){
            if(artikal.getNaziv().equals(naziv)){
                stavkaPorudzbine.setPoruceniArtikal(artikal);
                stavkaPorudzbine.setPorucenaKolicina(kolicina);
                stavkaPorudzbineService.saveStavka(stavkaPorudzbine);
                temp =  artikal.getCena() * kolicina;

            }
        }
        porudzbina.getPoruceniArtikli().add(stavkaPorudzbine);
        porudzbina.setCena(porudzbina.getCena() + temp);

        porudzbinaRepository.save(porudzbina);

        return  porudzbina;
  }
    public Porudzbina ukloniStavkuPorudzbine(String naziv, int kolicina, Porudzbina porudzbina){
        double temp = 0;
        for(StavkaPorudzbine stavkaPorudzbine: porudzbina.getPoruceniArtikli()){
            if(stavkaPorudzbine.getPoruceniArtikal().getNaziv().equals(naziv)){
                if(kolicina>=stavkaPorudzbine.getPorucenaKolicina()){
                    temp = kolicina * stavkaPorudzbine.getPoruceniArtikal().getCena();
                    stavkaPorudzbineService.removeStavka(stavkaPorudzbine);
                    porudzbina.getPoruceniArtikli().remove(stavkaPorudzbine);
                    break;

                }else{
                    stavkaPorudzbine.setPorucenaKolicina(stavkaPorudzbine.getPorucenaKolicina() - kolicina);
                    temp = kolicina * stavkaPorudzbine.getPoruceniArtikal().getCena();
                    break;

                }
            }
        }

        porudzbina.setCena(porudzbina.getCena() - temp);
        porudzbinaRepository.save(porudzbina);

        return  porudzbina;
    }
    public void  checkIfEmpty(Porudzbina porudzbina){
        if(porudzbina.getPoruceniArtikli().isEmpty()){
            porudzbinaRepository.delete(porudzbina);
        }else{
            porudzbina.setStatusPorudzbine(StatusPorudzbine.Obrada);
            porudzbinaRepository.save(porudzbina);
        }
    }
    public Porudzbina promeniStatusMenadzer(Restoran restoran, String ID){
        Porudzbina p= null;
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        listaPorudzbina = porudzbinaRepository.findAll();
        for(Porudzbina porudzbina: listaPorudzbina) {
            String result = String.valueOf(porudzbina.getId());
             result = result.replaceAll("[-+.^:,]",""); //UUID daje string sa crticama u njemu koje se ne vide u bazi

            if (result.equals(ID)) {
                if (porudzbina.getStatus().equals(StatusPorudzbine.Obrada)) {
                    p = porudzbina;
                    p.setStatusPorudzbine(StatusPorudzbine.UPripremi);
                    porudzbinaRepository.save(p);
                    restoranService.save(restoran);
                    return p;
                }else if(porudzbina.getStatus().equals(StatusPorudzbine.UPripremi)){
                    p = porudzbina;
                    p.setStatusPorudzbine(StatusPorudzbine.CekaDostavljača);
                    porudzbinaRepository.save(p);
                    restoranService.save(restoran);
                    return p;
                }
            }
        }
     return p;
    }
    public Porudzbina promeniStatusDostavljac(Dostavljac dostavljac, String id){
        List<Porudzbina> listaPorudzbina = new ArrayList<>();
        Porudzbina p = null;
        listaPorudzbina = porudzbinaRepository.findAll();
        for(Porudzbina porudzbina: listaPorudzbina) {
            if (porudzbina.getStatus().equals(StatusPorudzbine.CekaDostavljača)) {
                p = porudzbina;
                p.setStatusPorudzbine(StatusPorudzbine.UTransportu);
                porudzbinaRepository.save(p);
                dostavljac.getPorudzbineZaDostavu().add(p);
                dostavljacService.save(dostavljac);
                return p;
            } else if (porudzbina.getStatus().equals(StatusPorudzbine.UTransportu)) {
                for (Porudzbina porudzbina1 : dostavljacService.findAllById(dostavljac.getId())) {
                    if (porudzbina1.getId().equals(porudzbina.getId())) {

                        p = porudzbina;
                        p.setStatusPorudzbine(StatusPorudzbine.Dostavljena);
                        porudzbinaRepository.save(p);
                        dostavljacService.save(dostavljac);
                        kupacService.dodajBodove(porudzbina.getCena(), porudzbina.getKupac());

                        return p;
                    }
                }
            }
        }
        return p;
    }


}

