package Frames;

import Data.DateFormatter;
import Data.MyTableModel;
import Data.Record;
import Panels.ButtonPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class InsertFrame extends JFrame implements DocumentListener {
    private final JTextField t_amount, t_desc;
    private final JButton ok = new JButton("Ok");
    private final JDatePickerImpl datePicker;

    public InsertFrame(String title, MyTableModel model, ButtonPanel bp){
        this(title, model, bp, -1);
    }

    public InsertFrame(String title, MyTableModel model, ButtonPanel bp, int row){
        super(title);

        ok.setEnabled(false);

        JPanel p1 = new JPanel(new GridLayout(3,3));

        UtilDateModel dateModel = new UtilDateModel();
        t_desc = new JTextField(10);
        t_amount = new JTextField(10);

        if(row!=-1) {
            //visualizzo i valori relativi alla riga

            //visualizzo LocalDate come stringa e poi converto la stringa in Date
            //dateModel.setValue(java.sql.Date.valueOf((LocalDate) model.getValueAt(row,0)));
            try {
                dateModel.setValue(new SimpleDateFormat("dd-MM-yyyy").parse((String) model.getValueAt(row, 0)));
            }catch(ParseException e){
                System.err.println("InsertFrame: errore nella conversione da String a Date");
            }

            t_desc.setText((String) model.getValueAt(row,1));
            t_amount.setText(""+model.getValueAt(row,2));

            ok.setEnabled(true);
        }
        else
            dateModel.setValue(java.sql.Date.valueOf(LocalDate.now()));

        dateModel.setSelected(true);

        Properties prop = new Properties();
        prop.put("text.today", "Today");
        prop.put("text.month", "Month");
        prop.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, prop);
        datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
        datePicker.setShowYearButtons(true);

        datePicker.addActionListener(e -> {
            if(datePicker.getModel().getValue()==null)
                ok.setEnabled(false);
            else
                enableButton();
        });

        t_desc.getDocument().addDocumentListener(this);
        t_amount.getDocument().addDocumentListener(this);
        ok.addActionListener(e -> {
            LocalDate date = ((Date) datePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String desc= t_desc.getText();
            float amount = Float.parseFloat(t_amount.getText());
            Record r = new Record(date, desc , amount);

            if(row==-1)
                model.addRecord(r);
            else
                model.modifyRecord(row, r);

            bp.calculateTotal();

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

    private void enableButton(){
        ok.setEnabled(true);

        if(t_desc.getText().equals("") || t_amount.getText().equals(""))
            ok.setEnabled(false);
        else {
            try {
                Float.parseFloat(t_amount.getText());
            } catch (NumberFormatException ex) {
                ok.setEnabled(false);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        enableButton();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}

