package vezbe.demo.dto;


public class RestoranDto {
    private String naziv;
    private String tipRestorana;
    private double geoDuzina;
    private double geoSirina;
    private String adresa;

    public RestoranDto() {
    }


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

    public RestoranDto(String naziv, String tipRestorana, double geoDuzina, double geoSirina, String adresa) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
        this.adresa = adresa;
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
}
