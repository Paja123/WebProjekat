package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @OneToMany
    @JoinColumn(name ="dostavljac_id")
    private Set<Porudzbina> porudzbineZaDostavu= new HashSet<>();

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Set<Porudzbina> porudzbineZaDostavu) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Dostavljac);
        this.porudzbineZaDostavu = porudzbineZaDostavu;
    }

    public Dostavljac() {
        super();
    }

    public Set<Porudzbina> getPorudzbineZaDostavu() {
        return porudzbineZaDostavu;
    }

    /*public void setPorudzbine_za_dostavu(Set<Porudzbina> porudzbine_za_dostavu) {
        this.porudzbine_za_dostavu = porudzbine_za_dostavu;
    }*/
}
