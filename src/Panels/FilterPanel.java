package Panels;

import Data.DateFilter;
import Data.DateFormatter;
import Data.MyTableModel;
import Listeners.JComboBoxListener;
import Listeners.PeriodListener;
import Listeners.SearchListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Properties;

/**
 * Classe che permette il filtraggio delle voci della tabella in base al periodo e la ricerca con campo di testo
 * @author Christofer Fanò
 */

public class FilterPanel extends JPanel {
    private final JComboBox<String> combo;
    private final JDatePickerImpl start_datePicker, end_datePicker;
    private final JButton search;
    private final DateFilter filter = new DateFilter(null, null);

    /**
     * Costruttore che inizializza i calendari, campi di testo e bottoni per la ricerca e il filtraggio delle voci
     * @param table JTable del progetto per settare il filtro e evidenziare le righe in base al testo
     * @param bp ButtonPanel utilizzato per aggiornare il totale degli importi delle voci visualizzate
     */
    public FilterPanel(JTable table, ButtonPanel bp) {
        super();

        setLayout(new BorderLayout());

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

        String[] period = {"Tutti", "Periodo arbitrario", "Singolo giorno", "Ultima settimana", "Ultimo mese", "Ultimo anno"};
        combo = new JComboBox<>(period);
        combo.setEditable(false);
        start_datePicker.setShowYearButtons(true);
        end_datePicker.setShowYearButtons(true);

        search = new JButton("Cerca");
        search.addActionListener(e -> {
            if(table.getModel().getRowCount()!=0) {
                if (Objects.requireNonNull(combo.getSelectedItem()).equals("Tutti")) {
                    filter.setStartDate(null);
                    filter.setEndDate(null);
                }
                else {
                    LocalDate start_date = start_dateModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate end_date = end_dateModel.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    filter.setStartDate(start_date);
                    filter.setEndDate(end_date);
                }

                ((TableRowSorter<MyTableModel>) table.getRowSorter()).setRowFilter(filter);
                bp.calculateTotal();
            }
        });

        //in questo ordine
        combo.addActionListener(new JComboBoxListener(this));
        combo.setSelectedItem("Tutti");

        start_datePicker.addActionListener(new PeriodListener(this, PeriodListener.START));
        end_datePicker.addActionListener(new PeriodListener(this, PeriodListener.END));

        JPanel date_panel = new JPanel();
        date_panel.add(combo);
        date_panel.add(new JLabel("dal "));
        date_panel.add(start_datePicker);
        date_panel.add(new JLabel("al "));
        date_panel.add(end_datePicker);
        date_panel.add(search);




        JLabel f_label = new JLabel("Ricerca: ");
        JTextField f_text = new JTextField(10);
        f_text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                table.clearSelection();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                table.clearSelection();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        JButton prev = new JButton("<");
        JButton next = new JButton(">");

        prev.addActionListener(new SearchListener(table, f_text));
        next.addActionListener(new SearchListener(table, f_text));

        JPanel filter_panel = new JPanel();
        filter_panel.add(f_label);
        filter_panel.add(f_text);
        filter_panel.add(prev);
        filter_panel.add(next);

        add(date_panel, BorderLayout.NORTH);
        add(filter_panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce l'istanza della JComboBox per la selezione del tipo di filtraggio per data
     * @return JComboBox per la selezione del periodo
     */
    public JComboBox<String> getCombo() {
        return combo;
    }

    /**
     * Restituisce l'istanza di JDatePicker della data di inizio
     * @return JDatePickerImpl per la selezione della data di inizio
     */
    public JDatePickerImpl getStart_datePicker() {
        return start_datePicker;
    }

    /**
     * Restituisce l'istanza di JDatePicker della data di fine
     * @return JDatePickerImpl per la selezione della data di fine
     */
    public JDatePickerImpl getEnd_datePicker() {
        return end_datePicker;
    }

    /**
     * Restituisce l'istanza di JButton per il filtraggio per data
     * @return JButton per iniziare il filtraggio per data
     */
    public JButton getSearch() {
        return search;
    }
}
