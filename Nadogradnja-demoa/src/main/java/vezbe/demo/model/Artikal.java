package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private double cena;

    @Enumerated(value = EnumType.STRING)
    private TipArtikla tipArtikla;

    private double kolicina;

    private String opis;

    @Column(nullable = true, length = 64)
    private String slika;

    @Transient
    public String SlikaImagePath() {
        if (slika == null || id == null) return null;

        //return "/artikal-slike/" + id + "/" + slika;
        return  "/src/main/resources/static/images/"+ id + "/" + slika;
    }


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
    public Artikal() {
    }

    public Artikal(Long id, String naziv, double cena, TipArtikla tipArtikla, double kolicina, String opis, String slika) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
        this.slika = slika;
    }

    public Artikal(String naziv, int cena, TipArtikla tipArtikla, int kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }


    public Artikal(String naziv, double cena, TipArtikla tipArtikla) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
    }
   /* public Artikal(String naziv, double cena, TipArtikla tipArtikla, byte[] slika) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.slika = slika;
    }

    */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public TipArtikla getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(TipArtikla tip_artikla) {
        this.tipArtikla = tip_artikla;
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
