package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestPanel extends JPanel implements ActionListener {

    private MyTableModel model;

    public GestPanel() {
        super();
        
        setLayout(new GridLayout(3,1));

        model = new MyTableModel();
        JScrollPane s = new JScrollPane(new JTable(model));

        add(s);

        JButton b_add = new JButton("Add");
        b_add.addActionListener(this);
        add(b_add);

    }

    public MyTableModel getModel() {
        return model;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel p = new JPanel();
        setLayout(new SpringLayout());

        JTextField t_date = new JTextField(10);
        JLabel l_date = new JLabel("Data:");
        l_date.setLabelFor(t_date);

        JTextField t_desc = new JTextField(20);
        JLabel l_desc = new JLabel("Descrizione:");
        l_desc.setLabelFor(t_desc);

        JTextField t_amount = new JTextField(10);
        JLabel l_amount = new JLabel("Importo:");
        l_amount.setLabelFor(t_amount);

        p.add(l_date);
        p.add(t_date);

        p.add(l_desc);
        p.add(t_desc);

        p.add(l_amount);
        p.add(t_amount);



        JOptionPane.showMessageDialog(this, p,
                "Inserisci voce",
                JOptionPane.PLAIN_MESSAGE);

    }
}
