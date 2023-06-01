package Data;

import javax.swing.*;
import java.time.LocalDate;

public class DateFilter extends RowFilter<Object, Object> {
    private LocalDate startDate;
    private LocalDate endDate;


    public DateFilter(LocalDate startDate,LocalDate endDate) {
        super();
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean include(Entry entry) {
        LocalDate current=(LocalDate)entry.getValue(0);
        return current.isAfter(startDate.minusDays(1)) && current.isBefore(endDate.plusDays(1));
    }
}
