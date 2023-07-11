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

/**
 * Classe che gestisce il frame per l'inserimento delle nuove voci
 * @author Christofer Fanò
 */
@SuppressWarnings("serial")
public class InsertFrame extends JFrame implements DocumentListener {
    private final JTextField t_amount, t_desc;
    private final JButton ok = new JButton("Ok");
    private final JDatePickerImpl datePicker;

    /**
     * Costruttore utilizzato per il frame di inserimento che chiama il costruttore generico
     * @param title titolo del frame da visualizzare
     * @param model modello della tabella
     * @param bp ButtonPanel del progetto
     */
    public InsertFrame(String title, MyTableModel model, ButtonPanel bp){
        this(title, model, bp, -1);
    }

    /**
     * Costruttore che gestisce sia l'inserimento che la modifica delle voci di una riga, discriminando i due casi a
     * seconda del parametro row
     * @param title titolo del frame da visualizzare
     * @param model modello della tabella da cui estrarre i valori della riga specificata
     * @param bp ButtonPanel del progetto per ricalcolare il totale dell'ammontare delle righe visualizzate
     * @param row ha valore -1 se si tratta di un inserimento oppure indica la riga ma modificare nella tabella
     */
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
                e.printStackTrace();
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

    /**
     * Metodo privato utilizzato per abilitare il bottone se i campi di inserimento contengono i valori corretti
     */
    private void enableButton(){
        ok.setEnabled(true);

        /* Controllo opzionale: la data inserita non deve essere dopo la giornata di oggi
        if(((Date) datePicker.getModel().getValue()).after(java.sql.Date.valueOf(LocalDate.now())))
            ok.setEnabled(false);*/

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

    /**
     * Chiama il metodo enableButton per verificare se il bottone può essere attivato
     * @param e evento che ha generato la chiamata all'insertUpdate
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    /**
     * Chiama il metodo enableButton per verificare se il bottone può essere attivato
     * @param e evento che ha generato la chiamata al removeUpdate
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        enableButton();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}

