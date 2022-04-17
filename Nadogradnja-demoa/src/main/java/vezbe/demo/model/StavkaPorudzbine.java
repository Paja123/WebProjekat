package vezbe.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class StavkaPorudzbine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Artikal poruceniArtikal;
    private int porucenaKolicina;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Artikal poruceniArtikal, int porucenaKolicina) {
        this.poruceniArtikal = poruceniArtikal;
        this.porucenaKolicina = porucenaKolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artikal getPoruceniArtikal() {
        return poruceniArtikal;
    }

    public void setPoruceniArtikal(Artikal poruceniArtikal) {
        this.poruceniArtikal = poruceniArtikal;
    }

    public int getPorucenaKolicina() {
        return porucenaKolicina;
    }

    public void setPorucenaKolicina(int porucenaKolicina) {
        this.porucenaKolicina = porucenaKolicina;
    }
}
