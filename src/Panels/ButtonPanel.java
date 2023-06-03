package Panels;

import Listeners.ModifyTableListener;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private final JLabel total = new JLabel("Totale: 0€");
    private final JTable table;
    public ButtonPanel(JTable table) {
        super();

        this.table = table;
        setLayout(new BorderLayout());

        JButton b_add = new JButton("Add");
        JButton b_del = new JButton("Delete");

        b_add.addActionListener(new ModifyTableListener(table, this));
        b_del.addActionListener(new ModifyTableListener(table, this));

        JPanel b_panel = new JPanel();
        b_panel.add(b_add);
        b_panel.add(b_del);

        JPanel tot_panel = new JPanel();
        tot_panel.add(total);

        add(b_panel, BorderLayout.NORTH);
        add(tot_panel, BorderLayout.CENTER);
    }

    public void calculateTotal(){
        float tot = 0;
        for(int row=0; row < table.getRowCount(); row++)
            tot += (Float) table.getModel().getValueAt(table.convertRowIndexToModel(row), 2);

        total.setText("Totale: "+tot+"€");
    }

}
