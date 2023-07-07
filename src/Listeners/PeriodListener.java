package Listeners;

import Panels.FilterPanel;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Classe che gestisce il set automatico delle date in base al periodo di filtraggio scelto e attiva o disattiva
 * il bottone per iniziare il filtraggio
 * @author Christofer Fanò
 */

public class PeriodListener implements ActionListener {

    public static final int START = 0;
    public static final int END = 1;
    private static final int ERR = 2;
    private final UtilDateModel start_dateModel, end_dateModel;
    private final JButton search;
    private final JComboBox<String> combo;
    private final int type;

    /**
     * Costruttore che estrae dal FilterPanel i JDatePickerImpl della data di inizio e fine, il bottone per il filtraggio
     * e la JComboBox per la selezione del periodo, settando la variabile type a seconda dell'oggetto ascoltato
     * @param fp FilterPanel da cui estrarre tutte le componenti necessarie
     * @param type START se l'oggetto ascoltato è il JDatePickerImpl della data di inizio, END se della data di fine
     */
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

    /**
     * Metodo che attiva il bottone per il filtraggio attraverso la data solo se la data di inizio è minore o uguale a
     * quella di fine
     * @param end_date data di fine
     */
    private void checkDate(LocalDate end_date){
        if(start_dateModel.getValue() != null) {
            LocalDate start_date = start_dateModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (start_date.isEqual(end_date) || start_date.isBefore(end_date))
                search.setEnabled(true);
        }
    }

    /**
     * In base alla selezione del JComboBox setta la data di inizio, se il periodo inserito è valido attiva il bottone
     * @param e evento che genera la chiamata all'actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = (String) combo.getSelectedItem();
        search.setEnabled(false);

        if (end_dateModel.getValue() != null) {
            LocalDate end_date = end_dateModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (type == START)
                //sono nel periodo arbitrario
                checkDate(end_date);
            if (type == END) {
                if (choice != null) {
                    switch (choice) {
                        case "Periodo arbitrario" -> checkDate(end_date);
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
