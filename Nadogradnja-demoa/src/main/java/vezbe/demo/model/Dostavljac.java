package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name ="dostavljac_id")
    @JsonIgnore
    private Set<Porudzbina> porudzbineZaDostavu= new HashSet<>();

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Set<Porudzbina> porudzbineZaDostavu) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Dostavljac);
        this.porudzbineZaDostavu = porudzbineZaDostavu;
    }
    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Dostavljac);
        this.porudzbineZaDostavu = new HashSet<>();
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
