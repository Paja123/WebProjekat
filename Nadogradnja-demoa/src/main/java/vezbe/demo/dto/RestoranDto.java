package vezbe.demo.dto;


public class RestoranDto {
    private String naziv;
    private String tipRestorana;

    public RestoranDto() {
    }

    public RestoranDto(String naziv, String tipRestorana) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
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
