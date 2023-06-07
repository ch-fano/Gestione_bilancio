package Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

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

    public Record() {
        this(null, "", 0);
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

    public String print(String delimiter){
        return date+delimiter+description+delimiter+amount;
    }
    public boolean setRecord(String formattedRecord, String delimiter){
        int idx1 =formattedRecord.indexOf(delimiter);
        if(idx1==-1)
            return true;

        try {
            date = (new SimpleDateFormat("yyyy-MM-dd").parse(formattedRecord.substring(0, idx1))).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(ParseException e){
            return true;
        }

        int idx2 = formattedRecord.substring(idx1+1).indexOf(delimiter);
        if(idx2 ==-1)
            return true;

        idx2 = idx1+1+idx2;

        description = formattedRecord.substring(idx1+1, idx2);
        try{
            amount = Float.parseFloat(formattedRecord.substring(idx2+1));
        }catch(NumberFormatException e){
            return true;
        }

        return false;
    }
}
