package Data;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateFilter extends RowFilter<Object, Object> {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateFilter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean include(Entry entry) {
        if(startDate == null || endDate == null)
            return true;
        //LocalDate current=(LocalDate)entry.getValue(0);
        LocalDate current = null;
        try {
            current = (new SimpleDateFormat("dd-MM-yyyy").parse((String) entry.getValue(0))).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch(ParseException e){
            System.err.println("InsertFrame: errore nella conversione da String a Date");
        }
        return current.isAfter(startDate.minusDays(1)) && current.isBefore(endDate.plusDays(1));
    }
}
