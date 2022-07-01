package vezbe.demo.dto;

import vezbe.demo.model.Pol;
import vezbe.demo.model.Uloga;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

public class KorisnikDto {
    private String korisnickoIme;
    private  String lozinka;
    private String ime;
    private String prezime;
    private String datumRodjenja;
    private String pol;
    private String uloga;

    public KorisnikDto() {
    }

    public KorisnikDto(String korisnickoIme, String lozinka, String ime, String prezime, String datumRodjenja,String pol,String uloga) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.uloga = uloga;
    }
    public KorisnikDto(String korisnickoIme, String lozinka, String ime, String prezime, Date datumRodjenja,Pol pol,Uloga uloga) {

        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja.toString();
        this.pol = pol.name();
        this.uloga = uloga.name();
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
