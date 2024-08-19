package app2;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Zaposleni implements Serializable{
    private String ime, prezime, id;
    private String pocetakRada, zavrsetakRada;

    public String getZavrsetakRada() {
        return zavrsetakRada;
    }

    public void setZavrsetakRada(String zavrsetakRada) {
        this.zavrsetakRada = zavrsetakRada;
    }
   
    
    public Zaposleni() {
    }
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
     public String getPocetakRada() {
        return pocetakRada;
    }

    public void setPocetakRada(String pocetakRada) {
        this.pocetakRada = pocetakRada;
    }
}
