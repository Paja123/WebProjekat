package vezbe.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany                                                              //ManyToMany nova klasa stavka pordurdzbine artikal i kolicina polja
    @JoinColumn(name = "porudzbina_id")
    private Set<StavkaPorudzbine> poruceniArtikli = new HashSet<>();
    @ManyToOne
    private Restoran restoran;

    @Column(name = "datum_i_vreme")
    private Date datumIVreme;
    private double cena;
    @ManyToOne
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;
    @Enumerated(EnumType.STRING)
    private StatusPorudzbine statusPorudzbine;


    public UUID getUUID() {
        return id;
    }

    public Porudzbina() {
    }

    public Porudzbina( Set<StavkaPorudzbine> poruceniArtikli, Restoran restoran, Date datumIVreme, long cena, Kupac kupac, StatusPorudzbine statusPorudzbine) {
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        this.datumIVreme = datumIVreme;
        this.cena = cena;
        this.kupac =kupac;
        this.statusPorudzbine = statusPorudzbine;
    }

    public void setUUID(UUID id) {
        this.id = id;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(Date datum) {
        this.datumIVreme = datum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac imeKupca) {
        this.kupac = imeKupca;
    }

    public StatusPorudzbine getStatus() {
        return statusPorudzbine;
    }

    public void setStatus(StatusPorudzbine statusPorudzbine) {
        this.statusPorudzbine = statusPorudzbine;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<StavkaPorudzbine> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<StavkaPorudzbine> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public StatusPorudzbine getStatusPorudzbine() {
        return statusPorudzbine;
    }

    public void setStatusPorudzbine(StatusPorudzbine statusPorudzbine) {
        this.statusPorudzbine = statusPorudzbine;
    }
}