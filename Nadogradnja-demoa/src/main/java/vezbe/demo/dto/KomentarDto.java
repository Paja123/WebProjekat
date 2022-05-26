package vezbe.demo.dto;

public class KomentarDto {
    private String korisnickoIme;
    private String tekst;
    private  int ocena;

    public KomentarDto() {
    }

    public KomentarDto(String korisnickoIme, String tekst, int ocena) {
        this.korisnickoIme = korisnickoIme;
        this.tekst = tekst;
        this.ocena = ocena;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
