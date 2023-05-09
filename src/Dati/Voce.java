package Dati;

public class Voce {
    private String data;
    private String descrizione;
    private String ammontare;

    public Voce(String data, String descrizione, String ammontare) {
        this.data = data;
        this.descrizione = descrizione;
        this.ammontare = ammontare;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getAmmontare() {
        return ammontare;
    }

    public void setAmmontare(String ammontare) {
        this.ammontare = ammontare;
    }
}
