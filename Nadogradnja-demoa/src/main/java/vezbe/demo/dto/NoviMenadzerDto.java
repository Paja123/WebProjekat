package vezbe.demo.dto;

public class NoviMenadzerDto {
    private String korisnickoIme;
    private String naziv;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public NoviMenadzerDto() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public NoviMenadzerDto(String korisnickoIme, String naziv) {
        this.korisnickoIme = korisnickoIme;
        this.naziv = naziv;
    }
}
