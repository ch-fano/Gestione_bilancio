package Panels;

import Data.DateFormatter;
import Data.MyTableModel;
import Listeners.AddEditListener;
import Listeners.DeleteListener;
import Listeners.JComboBoxListener;
import Listeners.TableMouseAdapter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;


public class TablePanel extends JPanel {

    private final MyTableModel model;
    private final JLabel totale = new JLabel("Totale: ");

    private final JComboBox combo;
    private final JDatePickerImpl start_datePicker, end_datePicker;

    public TablePanel() {
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

        JPanel selection_panel = new JPanel(new BorderLayout());
        selection_panel.add(date_panel, BorderLayout.NORTH);
        selection_panel.add(filter_panel, BorderLayout.CENTER);

        add(selection_panel, BorderLayout.NORTH);

        model = new MyTableModel();
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Float.class, centerRenderer);

        table.addMouseListener(new TableMouseAdapter(table));
        TableRowSorter<MyTableModel> sorter = new TableRowSorter<>(model);

        //sorter.setRowFilter(filter);
        table.setRowSorter(sorter);

        JScrollPane s = new JScrollPane(table);

        add(s, BorderLayout.CENTER);

        JButton b_add = new JButton("Add");
        JButton b_del = new JButton("Delete");

        b_add.addActionListener(new AddEditListener(model));
        b_del.addActionListener(new DeleteListener(table));

        JPanel button_panel = new JPanel();
        button_panel.add(b_add);
        button_panel.add(b_del);

        JPanel tot_panel = new JPanel();
        tot_panel.add(totale);

        JPanel down_panel = new JPanel(new BorderLayout());
        down_panel.add(button_panel, BorderLayout.NORTH);
        down_panel.add(tot_panel, BorderLayout.CENTER);

        add(down_panel, BorderLayout.SOUTH);

    }

    public MyTableModel getModel() {
        return model;
    }

}
