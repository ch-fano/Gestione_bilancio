package Panels;

import Data.DateFormatter;
import Listeners.JComboBoxListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class FilterPanel extends JPanel {
    private final JComboBox combo;
    private final JDatePickerImpl start_datePicker, end_datePicker;

    public FilterPanel() {
        UtilDateModel start_dateModel = new UtilDateModel();
        UtilDateModel end_dateModel = new UtilDateModel();
        Properties prop = new Properties();
        prop.put("text.today", "Today");
        prop.put("text.month", "Month");
        prop.put("text.year", "Year");
        JDatePanelImpl start_datePanel = new JDatePanelImpl(start_dateModel, prop);
        JDatePanelImpl end_datePanel = new JDatePanelImpl(end_dateModel, prop);
        start_datePicker = new JDatePickerImpl(start_datePanel, new DateFormatter());
        end_datePicker = new JDatePickerImpl(end_datePanel, new DateFormatter());

        String[] period = {"Tutti", "Periodo arbitrario", "Singolo giorno", "Ultima Settimana", "Ultimo Mese", "Ultimo Anno"};
        combo = new JComboBox(period);
        combo.setEditable(false);
        combo.addActionListener(new JComboBoxListener(start_datePicker, end_datePicker));
        combo.setSelectedItem("Tutti");
        start_datePicker.setShowYearButtons(true);
        end_datePicker.setShowYearButtons(true);

        JButton search = new JButton("Cerca");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel date_panel = new JPanel();
        date_panel.add(combo);
        date_panel.add((Component) start_datePicker);
        date_panel.add((Component) end_datePicker);
        date_panel.add(search);




        JLabel f_label = new JLabel("Ricerca: ");
        JTextField f_text = new JTextField(10);
        JButton prev = new JButton("<");
        JButton next = new JButton(">");

        JPanel filter_panel = new JPanel();
        filter_panel.add(f_label);
        filter_panel.add(f_text);
        filter_panel.add(prev);
        filter_panel.add(next);
    }
}
