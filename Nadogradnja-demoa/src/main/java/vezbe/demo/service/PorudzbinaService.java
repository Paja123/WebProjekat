package vezbe.demo.service;

import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.KupacRepository;
import vezbe.demo.repository.PorudzbinaRepository;

import java.text.SimpleDateFormat;
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
  public void makePorudzbina(Kupac kupac, String restoran){//TREBA FORMATIRATI OVE DATUME KAD ZAVRSIS
        Restoran restoran1 = restoranService.findByRestoranIme(restoran);
    //  Date date= null;
   //   date = new Date("yyyy/dd/MM");
        Porudzbina porudzbina = new Porudzbina(kupac, restoran1, StatusPorudzbine.Obrada, new Date());
        porudzbinaRepository.save(porudzbina);
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
  public Porudzbina findByStatusAndKupac(Kupac kupac){
      Porudzbina p= null;
      for(Porudzbina porudzbina: kupac.getListaPorudzbina()){
          if(porudzbina.getStatus().equals(StatusPorudzbine.Obrada)){
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
}

