package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.PorudzbinaDto;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Porudzbina;
import vezbe.demo.model.StatusPorudzbine;
import vezbe.demo.repository.KupacRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    public Kupac findOne(Long id){
        Kupac k = null;
        for(Kupac kupac: kupacRepository.findAll()){
            if(kupac.getId().equals(id)){
                k = kupac;
            }
        }
        return k;
    }

    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }

    public Kupac save(Kupac kupac){
        return kupacRepository.save(kupac);
    }

    public void delete(Kupac kupac){kupacRepository.delete(kupac);}

    public boolean checkIfUsernameIsAvailable(String korisnickoIme){
        for(Kupac kupac: kupacRepository.findAll()){
            if(korisnickoIme.equals(kupac.getKorisnickoIme())){
                return false;
            }
        }
        return true;
    }


    public Set<Porudzbina> findAllPorudzbineByID(Long id){
        Set<Porudzbina> listaPorudzbina = kupacRepository.getById(id).getListaPorudzbina();
        Set<Porudzbina> bezPrazneListe = new HashSet<>();
        for(Porudzbina porudzbina: listaPorudzbina) {
            if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.USastavljanu)) {
                continue;
            }
            bezPrazneListe.add(porudzbina);
        }
        return bezPrazneListe;
    }
    public void updateKupac(Kupac kupac, Porudzbina porudzbina){
        kupac.getListaPorudzbina().add(porudzbina);
        kupacRepository.save(kupac);
    }
    public void dodajBodove(double cena, Kupac kupac){
        double noviBodovi = cena/1000 * 133;
        kupac.setBodovi((int) (kupac.getBodovi() + noviBodovi));
        kupacRepository.save(kupac);
    }
    public Set<PorudzbinaDto> svePorudzbineBasicInfo(Long id){
        Set<Porudzbina> porudzbine = kupacRepository.getById(id).getListaPorudzbina();

        Set<PorudzbinaDto> dto = new HashSet<>();
        for(Porudzbina porudzbina: porudzbine){
            if(porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.USastavljanu)){
                continue;
            }
            Date date1 = porudzbina.getDatumIVreme();


            PorudzbinaDto item = new PorudzbinaDto(porudzbina.getUUID().toString(),porudzbina.getCena(),porudzbina.getDatumIVreme().toString());
            dto.add(item);
            System.out.println(item.getId());
            System.out.println(item.getCena());
            System.out.println(item.getDatum());
        }

        return dto;
    }

}
