package vezbe.demo.dto;

import javax.persistence.Column;

public class LokacijaDto {
    private double geoDuzina;
    private double geoSirina;
    private String adresa;


    public double getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public LokacijaDto() {
    }

    public LokacijaDto(double geoDuzina, double geoSirina, String adresa) {
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
        this.adresa = adresa;
    }
}
