package vezbe.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KomentarDto;
import vezbe.demo.model.Komentar;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.KomentarRepository;


@Service
public class KomentarService {
    @Autowired
    KomentarRepository komentarRepository;

    public double getProsecnaOcena(Long restoranId) {
        double ocena = 0;
        for (Komentar komentar : komentarRepository.findAll()) {
            if (komentar.getRestoran().getId().equals(restoranId)) {
                ocena += komentar.getOcena();
            }
        }
        return ocena;
    }

    public Set<KomentarDto> getByRestoran(Long restoranId) {
        Set<KomentarDto> komentarList = new HashSet<>();
        for (Komentar komentar : komentarRepository.findAll()) {
            if (komentar.getRestoran().getId().equals(restoranId)) {
                KomentarDto komentarDto = new KomentarDto(komentar.getKupac().getKorisnickoIme(), komentar.getTekst(), komentar.getOcena());
                komentarList.add(komentarDto);
            }
        }
        return komentarList;
    }
}