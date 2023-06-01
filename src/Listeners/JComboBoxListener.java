package Listeners;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JComboBoxListener implements ActionListener {

    private final JDatePickerImpl start_datePicker, end_datePicker;
    public JComboBoxListener(JDatePickerImpl start_datePicker, JDatePickerImpl end_datePicker) {
        this.start_datePicker = start_datePicker;
        this.end_datePicker = end_datePicker;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox combo = (JComboBox) e.getSource();
        String scelta  = (String) combo.getSelectedItem();

        if(scelta!=null) {
            switch (scelta) {
                case "Tutti":
                    start_datePicker.getComponent(1).setEnabled(false);
                    end_datePicker.getComponent(1).setEnabled(false);

                    break;
                case "Periodo arbitrario":
                    start_datePicker.getComponent(1).setEnabled(true);
                    end_datePicker.getComponent(1).setEnabled(true);
                    break;
                case "Singolo giorno":
                case "Ultima settimana":
                case "Ultimo mese":
                case "Ultimo anno":
                    start_datePicker.getComponent(1).setEnabled(false);
                    end_datePicker.getComponent(1).setEnabled(true);
                    break;
            }
        }
    }
}
