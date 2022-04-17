package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    public String ime;
    @Column
    public float popust;
    @Column
    public int trazeniBrBodova;

    public TipKupca() {
        this.ime = ime;
        this.popust = 0;
        this.trazeniBrBodova = 0;
    }

    public TipKupca(String ime, float popust, int trazeniBrBodova) {
        this.ime = ime;
        this.popust = popust;
        this.trazeniBrBodova = trazeniBrBodova;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public float getPopust() {
        return popust;
    }

    public void setPopust(float popust) {
        this.popust = popust;
    }

    public int getTrazeniBrBodova() {
        return trazeniBrBodova;
    }

    public void setTrazeniBrBodova(int trazeniBrBodova) {
        this.trazeniBrBodova = trazeniBrBodova;
    }
}
