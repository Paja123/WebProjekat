package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String tipRestorana;

    @OneToMany
    @JoinColumn(name = "restoran_id")
    private Set<Artikal> ponuda = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "location_id")
    private Lokacija lokacija;

    public Restoran() {
    }

    public Restoran(String naziv, String tipRestorana, Set<Artikal> ponuda, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.ponuda = ponuda;
        this.lokacija = lokacija;
    }

    public Restoran(String naziv, String tipRestorana, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
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

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Set<Artikal> getPonuda() {
        return ponuda;
    }

    public void setPonuda(Set<Artikal> ponuda) {
        this.ponuda = ponuda;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
