package Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Questa classe gestisce i dati relativi ad una singola voce del bilancio
 * @autor Christofer Fanò
 */
public class Record implements Serializable {
    private LocalDate date;
    private String description;
    private float amount;


    /**
     * Costruttore della classe Record
     * @param date datq della transazione
     * @param description descrizione della transazione
     * @param amount importo della transazione
     */
    public Record(LocalDate date, String description, float amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Restituisce il valore dell'attributo data
     * @return valore dell'attributo data
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Permette di assegnare all'attributo data il valore in input
     * @param date data da assegnare alla voce
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Restituisce il valore dell'attributo descrizione
     * @return valore dell'attributo descrizione
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permette di assegnare all'attributo descrizione il valore in input
     * @param description descrizione da assegnare alla voce
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce il valore dell'attributo importo
     * @return valore dell'attributo importo
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Permette di assegnare all'attributo importo il valore in input
     * @param amount importo da assegnare alla voce
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
