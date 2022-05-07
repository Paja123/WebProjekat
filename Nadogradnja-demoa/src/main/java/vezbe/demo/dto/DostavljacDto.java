package vezbe.demo.dto;

import vezbe.demo.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

public class DostavljacDto {

    private Long id;

    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private String pol;

    private String datumRodjenja;

    private Uloga uloga;

    public DostavljacDto(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, String datumRodjenja) {

        this.uloga = Uloga.Kupac;
    }
    public DostavljacDto(Kupac kupac) {
        this.id = kupac.getId();
        this.korisnickoIme = kupac.getKorisnickoIme();
        this.lozinka = kupac.getLozinka();
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.pol = kupac.getPol().name();
        this.datumRodjenja = kupac.getDatumRodjenja().toString();
        this.uloga = Uloga.Kupac;
    }

    public DostavljacDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
