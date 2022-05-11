package vezbe.demo.dto;

import vezbe.demo.model.TipArtikla;


public class ArtikalDto {
    private String naziv;
    private double cena;

    private String tipArtikla;

    private double kolicina;

    private String opis;

    //SLIKU??????????????????
    //OPCIONI?????????????

    public ArtikalDto(String naziv, double cena, String tipArtikla, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public ArtikalDto() {
    }

    public ArtikalDto(String naziv, double cena, String tipArtikla) {

        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
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
