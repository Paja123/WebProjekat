package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @Column(name = "porudzbina_id")
    @OneToMany(cascade = CascadeType.ALL, mappedBy="kupac")
    private Set<Porudzbina> listaPorudzbina = new HashSet<>();
    private int bodovi;
    @OneToOne
    private TipKupca tipKupca;


    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Set<Porudzbina> listaPorudzbina, int bodovi, TipKupca tipKupca) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Kupac);
        this.listaPorudzbina = listaPorudzbina;
        this.bodovi = bodovi;
        this.tipKupca =tipKupca ;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja){
        super(korisnickoIme, lozinka, ime, prezime, pol,datumRodjenja, Uloga.Kupac);
        this.bodovi = 0;
        this.tipKupca = null;
    }

    public Kupac() {

    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    public Set<Porudzbina> getListaPorudzbina() {
        return listaPorudzbina;
    }

    public void setListaPorudzbina(Set<Porudzbina> listaPorudzbina) {
        this.listaPorudzbina = listaPorudzbina;
    }


}
