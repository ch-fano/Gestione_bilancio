package GUI;

import Data.Record;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class InsertFrame extends JFrame implements DocumentListener {
    private JTextField t_amount, t_desc;
    private JButton ok = new JButton("Ok");
    private JDatePickerImpl datePicker;

    public InsertFrame(String title, MyTableModel model){
        this(title, model, -2);
    }
    public InsertFrame(String title, MyTableModel model, int row){
        super(title);

        ok.setEnabled(false);

        JPanel p1 = new JPanel(new GridLayout(3,3));

        t_desc = new JTextField(20);
        t_amount = new JTextField(10);

        Properties prop = new Properties();
        prop.put("text.today", "Today");
        prop.put("text.month", "Month");
        prop.put("text.year", "Year");

        UtilDateModel dateModel = new UtilDateModel();

        if(row!=-2) {
            dateModel.setValue(java.sql.Date.valueOf((LocalDate) model.getValueAt(row,0)));
            t_desc.setText((String) model.getValueAt(row,1));
            t_amount.setText(""+model.getValueAt(row,2));

            ok.setEnabled(true);
        }
        else
            dateModel.setValue(java.sql.Date.valueOf(LocalDate.now()));

        dateModel.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, prop);
        datePicker = new JDatePickerImpl(datePanel, new DateFormatter());

        datePicker.addActionListener(e -> {
            ok.setEnabled(true);
            if(datePicker.getModel().getValue()==null)
                ok.setEnabled(false);
        });

        t_desc.getDocument().addDocumentListener(this);
        t_amount.getDocument().addDocumentListener(this);
        ok.addActionListener(e -> {
            LocalDate date = ((Date) datePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String desc= t_desc.getText();
            float amount = Float.parseFloat(t_amount.getText());
            Record r = new Record(date, desc , amount);

            if(row==-2)
                model.addRecord(r);
            else {
                model.modifyRecord(row, r);
            }
            dispose();
        });

        p1.add(new JLabel("Data:"));
        p1.add(datePicker);

        p1.add(new JLabel("Descrizione:"));
        p1.add(t_desc);

        p1.add(new JLabel("Importo:"));
        p1.add(t_amount);


        JPanel p2 = new JPanel(new BorderLayout());

        p2.add(p1, BorderLayout.NORTH);

        JPanel p3 = new JPanel();
        p3.add(ok);

        p2.add(p3, BorderLayout.CENTER);

        add(p2);
        pack();
        setVisible(true);
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        ok.setEnabled(true);

        if(t_desc.getText().equals("") || t_amount.getText().equals(""))
            ok.setEnabled(false);

        if (e.getDocument()== t_amount.getDocument()){
            try{
                Float.parseFloat(t_amount.getText());
            }
            catch(NumberFormatException ex){
                ok.setEnabled(false);
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}

