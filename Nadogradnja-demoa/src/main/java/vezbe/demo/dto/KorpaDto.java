package vezbe.demo.dto;

import vezbe.demo.model.Artikal;

public class KorpaDto {
    private String artikal;
    private int kolicina;

    public KorpaDto() {

    }

    public KorpaDto( String artikal, int kolicina) {
        this.artikal = artikal;
        this.kolicina = kolicina;
    }

    public String getArtikal() {
        return artikal;
    }

    public void setArtikal(String artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
