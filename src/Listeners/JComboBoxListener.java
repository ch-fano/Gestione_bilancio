package Listeners;

import Panels.FilterPanel;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener della JComboBox per la selezione del periodo di filtraggio che rende selezionabili o meno i JDatePickerImpl
 * e il bottone per iniziare il filtraggio
 * @author Christofer Fanò
 */

public class JComboBoxListener implements ActionListener {

    private final JDatePickerImpl start_datePicker, end_datePicker;
    private final JComboBox<String> combo;
    private final JButton search;

    /**
     * Costruttore che memorizza i JDatePickerImpl di inizio e fine, il bottone per iniziare il filtraggio e la JComboBox
     * @param fp FilterPanel da cui estrarre tutte le componenti necessarie
     */
    public JComboBoxListener(FilterPanel fp) {
        this.start_datePicker = fp.getStart_datePicker();
        this.end_datePicker = fp.getEnd_datePicker();
        this.combo = fp.getCombo();
        this.search = fp.getSearch();
    }

    /**
     * In base alla scelta della JComboBox attiva o disattiva il bottone e le JDatePickerImpl
     * @param e evento che genera la chiamata all'actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice  = (String) combo.getSelectedItem();

        search.setEnabled(false);
        if(choice!=null) {
            switch (choice) {
                case "Tutti" -> {
                    start_datePicker.getComponent(1).setEnabled(false);
                    end_datePicker.getComponent(1).setEnabled(false);
                    start_datePicker.getJFormattedTextField().setText("");
                    end_datePicker.getJFormattedTextField().setText("");
                    search.setEnabled(true);
                }
                case "Periodo arbitrario" -> {
                    start_datePicker.getComponent(1).setEnabled(true);
                    end_datePicker.getComponent(1).setEnabled(true);
                }
                case "Singolo giorno", "Ultima settimana", "Ultimo mese", "Ultimo anno" -> {
                    start_datePicker.getJFormattedTextField().setText("");
                    start_datePicker.getComponent(1).setEnabled(false);
                    end_datePicker.getComponent(1).setEnabled(true);
                }
            }
        }
    }
}
