package vezbe.demo.dto;

import vezbe.demo.model.Artikal;
import vezbe.demo.model.Komentar;
import vezbe.demo.model.Lokacija;

import java.util.List;
import java.util.Set;

public class RestoranIspisDto {
    private String naziv;
    private String tip;
    private Lokacija lokacija;
    private double prosecnaOcena;
    private Set<KomentarDto> komentari;
    private Set<Artikal> ponuda;

    public RestoranIspisDto() {
    }

    public RestoranIspisDto(String naziv, String tip, Lokacija lokacija, double prosecnaOcena, Set<KomentarDto> komentari, Set<Artikal> ponuda) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
        this.prosecnaOcena = prosecnaOcena;
        this.komentari = komentari;
        this.ponuda = ponuda;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public Set<KomentarDto> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<KomentarDto> komentari) {
        this.komentari = komentari;
    }

    public Set<Artikal> getPonuda() {
        return ponuda;
    }

    public void setPonuda(Set<Artikal> ponuda) {
        this.ponuda = ponuda;
    }
}
