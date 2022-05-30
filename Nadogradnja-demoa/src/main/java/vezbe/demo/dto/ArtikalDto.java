package vezbe.demo.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import vezbe.demo.model.TipArtikla;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class ArtikalDto {
    private String naziv;
    private double cena;

    private String tipArtikla;

    private double kolicina;

    private String opis;

    /*@Lob
    @Column(name = "slika")
    private byte[] slika;

    @JsonSetter("slika")
    public void setSlika(String content) {
        try {
            this.slika = Base64.getDecoder().decode(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSlika() {
        return slika;
    }
    */

    //SLIKU??????????????????
    //OPCIONI?????????????

    public ArtikalDto(String naziv, double cena, String tipArtikla, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    /*public ArtikalDto(String naziv, double cena, String tipArtikla, double kolicina, String opis, byte[] slika) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
        this.slika = slika;
    }
    */

    public ArtikalDto() {
    }

    public ArtikalDto(String naziv, double cena, String tipArtikla) {

        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
    }
    /*

    public ArtikalDto(String naziv, double cena, String tipArtikla, byte[] slika) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.slika = slika;
    }
    */

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(String tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
