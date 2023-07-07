package Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Questa classe gestisce i dati relativi ad una singola voce del bilancio
 * @author Christofer Fanò
 */
public class Record implements Serializable {
    private LocalDate date;
    private String description;
    private float amount;

    /**
     * Costruttore della classe Record
     * @param date data della transazione
     * @param description descrizione della transazione
     * @param amount importo della transazione
     */
    public Record(LocalDate date, String description, float amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Costruttore che inizializza i campi con valori nulli
     */
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
     * Restituisce il valore dell'attributo descrizione
     * @return valore dell'attributo descrizione
     */
    public String getDescription() {
        return description;
    }

    /**
     * Restituisce il valore dell'attributo importo
     * @return valore dell'attributo importo
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Stampa  campi del record separati dal delimitatore
     * @param delimiter delimitatore da interporre tra i campi
     * @return stringa contenente i campi separati da delimitatore
     */
    public String print(String delimiter){
        return date+delimiter+description+delimiter+amount;
    }

    /**
     * Metodo che estrae i campi da una stringa formattata e li setta
     * @param formattedRecord stringa formattata
     * @param delimiter delimitatore della stringa formattata
     * @return true se si è verificato un errore, false altrimenti
     */
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
