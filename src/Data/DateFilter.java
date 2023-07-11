package Data;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Classe che filtra le date, permettendo la visualizzazione di quelle comprese tra la data di inizio e fine
 * @author Christofer Fanò
 */
public class DateFilter extends RowFilter<Object, Object> {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Costruttore che inizializza la data di inizio e fine
     * @param startDate data di inizio
     * @param endDate data di fine
     */
    public DateFilter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Metodo che setta la data di inizio
     * @param startDate data di inizio
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Metodo che setta la data di fine
     * @param endDate data di fine
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Metodo che filtra le date per visualizzare solo quelle comprese tra la data di inizio e di fine
     * @param entry entry riferita alla data da filtrare
     * @return true se la data è da visualizzare, false altrimenti
     */
    @Override
    public boolean include(Entry<?,?> entry) {
        if(startDate == null || endDate == null)
            return true;

        LocalDate current;
        try {
            current = (new SimpleDateFormat("dd-MM-yyyy").parse((String) entry.getValue(0))).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(ParseException e){
            e.printStackTrace();
            return false;
        }
        return current.isAfter(startDate.minusDays(1)) && current.isBefore(endDate.plusDays(1));
    }
}
