package vezbe.demo.dto;

import vezbe.demo.model.StatusPorudzbine;

public class PorudzbinaDto {
    private String id;
    private double cena;
    private String datum;
    private String status;

    public PorudzbinaDto(String id, double cena, String datum) {
        this.id = id;
        this.cena = cena;
        this.datum = datum;
    }

    public PorudzbinaDto(String id, double cena, String datum, StatusPorudzbine status) {
        this.id = id;
        this.cena = cena;
        this.datum = datum;
        this.status = status.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(StatusPorudzbine status) {
        this.status = status.toString();
    }

    public PorudzbinaDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
