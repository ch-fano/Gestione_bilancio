package Listeners;

import Panels.FilterPanel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PeriodListener implements ActionListener {

    public static final int START = 0;
    public static final int END = 1;
    private static final int ERR = 2;
    private final UtilDateModel start_dateModel, end_dateModel;
    private final JButton search;
    private final JComboBox<String> combo;
    private final int type;
    public PeriodListener(FilterPanel fp, int type) {
        this.start_dateModel = (UtilDateModel) fp.getStart_datePicker().getModel();
        this.end_dateModel = (UtilDateModel) fp.getEnd_datePicker().getModel();
        this.search = fp.getSearch();
        this.combo = fp.getCombo();
        if(type == START || type == END)
            this.type = type;
        else {
            this.type = ERR;
            System.err.println("PeriodListener: type deve essere uno tra PeriodListener.START e PeriodListener.END");
        }
    }

    private void checkDate(LocalDate end_date){
        if(start_dateModel.getValue() != null) {
            LocalDate start_date = ((Date) start_dateModel.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (start_date.isEqual(end_date) || start_date.isBefore(end_date))
                search.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = (String) combo.getSelectedItem();
        search.setEnabled(false);

        if (end_dateModel.getValue() != null) {
            LocalDate end_date = ((Date) end_dateModel.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (type == START)
                //sono nel periodo arbitrario
                checkDate(end_date);
            if (type == END) {
                if (choice != null) {
                    switch (choice) {
                        case "Periodo arbitrario" -> {
                            checkDate(end_date);
                        }
                        case "Singolo giorno" -> {
                            start_dateModel.setValue(java.sql.Date.valueOf(end_date));
                            search.setEnabled(true);
                        }
                        case "Ultima settimana" -> {
                            start_dateModel.setValue(java.sql.Date.valueOf(end_date.minusDays(7)));
                            search.setEnabled(true);
                        }
                        case "Ultimo mese" -> {
                            start_dateModel.setValue(java.sql.Date.valueOf(end_date.minusDays(30)));
                            search.setEnabled(true);
                        }
                        case "Ultimo anno" -> {
                            start_dateModel.setValue(java.sql.Date.valueOf(end_date.minusDays(365)));
                            search.setEnabled(true);
                        }
                    }
                }
            }
        }
    }
}
