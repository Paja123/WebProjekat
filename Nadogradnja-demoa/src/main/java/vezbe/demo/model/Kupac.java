package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @Column(name = "porudzbina_id")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="kupac")
    private Set<Porudzbina> listaPorudzbina = new HashSet<>();
    private int bodovi;
    @OneToOne
    private TipKupca tipKupca;


    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Set<Porudzbina> listaPorudzbina, int bodovi, TipKupca tipKupca) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, Uloga.Kupac);
        Porudzbina porudzbina  = new Porudzbina();
        boolean sadrziKorpu = false;
        porudzbina.setKupac(Kupac.this);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.USastavljanu);
        Set<Porudzbina> noviSetPorudzbina = new HashSet<>();
        if(listaPorudzbina==null){
            noviSetPorudzbina.add(porudzbina);
            this.listaPorudzbina = noviSetPorudzbina;
        }else{
            for(Porudzbina p: listaPorudzbina){
                if(p.getStatus().equals(StatusPorudzbine.USastavljanu)){
                    sadrziKorpu = true;
                    break;
                }
            }
            if(sadrziKorpu){
                this.listaPorudzbina = listaPorudzbina;
            }else{
                listaPorudzbina.add(porudzbina);
                this.listaPorudzbina = listaPorudzbina;
            }
        }
        this.bodovi = bodovi;
        this.tipKupca =tipKupca ;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja){
        super(korisnickoIme, lozinka, ime, prezime, pol,datumRodjenja, Uloga.Kupac);
        this.bodovi = 0;
        this.tipKupca = null;
        Porudzbina porudzbina  = new Porudzbina();
        porudzbina.setKupac(Kupac.this);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.USastavljanu);
        this.listaPorudzbina.add(porudzbina);


    }

    public Kupac() {
        Porudzbina porudzbina  = new Porudzbina();
        porudzbina.setKupac(Kupac.this);
        porudzbina.setStatusPorudzbine(StatusPorudzbine.USastavljanu);
        this.listaPorudzbina.add(porudzbina);
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
