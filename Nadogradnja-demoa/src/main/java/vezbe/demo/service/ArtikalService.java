package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.ArtikalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Set<Artikal> dodajArtikal(Artikal artikal, Restoran restoran){
        artikalRepository.save(artikal);
        restoran.getPonuda().add(artikal);

       /* List<Artikal> lista = new ArrayList<>();
        for(Artikal artikal: restoran.getPonuda()){
            lista.add(artikal);
        }
*/
        return restoran.getPonuda();

    }
    public Artikal findById(Long id){
        Artikal a = null;
        for(Artikal artikal: artikalRepository.findAll()){
            if(artikal.getId().equals(id)) {
                a = artikal;
            }
        }
        return  a;
    }
    public void save(Artikal artikal){
        artikalRepository.save(artikal);
    }
}
