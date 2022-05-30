package vezbe.demo.dto;

public class RestoranImeDto {
    private String naziv;

    public RestoranImeDto() {
    }

    public RestoranImeDto(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
