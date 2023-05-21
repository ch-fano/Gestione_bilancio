package Dati;

import java.io.Serializable;

/**
 * Questa classe gestisce i dati relativi ad una singola voce del bilancio
 * @autor Christofer Fanò
 */
public class Voce implements Serializable {
    private String data;
    private String descrizione;
    private float ammontare;

    /**
     * Costruttore della classe Voce
     * @param data
     * @param descrizione
     * @param ammontare
     */
    public Voce(String data, String descrizione, float ammontare) {
        this.data = data;
        this.descrizione = descrizione;
        this.ammontare = ammontare;
    }

    /**
     * Restituisce il valore dell'attributo data
     * @return valore dell'attributo data
     */
    public String getData() {
        return data;
    }

    /**
     * Permette di assegnare all'attributo data il valore in input
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Restituisce il valore dell'attributo descrizione
     * @return valore dell'attributo descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Permette di assegnare all'attributo descrizione il valore in input
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce il valore dell'attributo ammontare
     * @return valore dell'attributo ammontare
     */
    public float getAmmontare() {
        return ammontare;
    }

    /**
     * Permette di assegnare all'attributo ammontare il valore in input
     * @param ammontare
     */
    public void setAmmontare(float ammontare) {
        this.ammontare = ammontare;
    }
}
