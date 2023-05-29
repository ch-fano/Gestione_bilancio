package GUI;

import Listeners.AddListener;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Properties;


public class GestPanel extends JPanel {

    private MyTableModel model;

    public GestPanel() {
        super();
        
        setLayout(new GridLayout(3,1));

        model = new MyTableModel();
        JScrollPane s = new JScrollPane(new JTable(model));

        add(s);

        JButton b_add = new JButton("Add");
        JButton b_del = new JButton("Delete");
        b_add.addActionListener(new AddListener(model));
        add(b_add);

    }

    public MyTableModel getModel() {
        return model;
    }

}
