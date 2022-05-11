package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public Artikal() {
    }

    public Artikal(String naziv, int cena, TipArtikla tipArtikla, int kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Artikal(String naziv, double cena, TipArtikla tipArtikla) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
    }

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
