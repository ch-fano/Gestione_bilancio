package Listeners;

import GUI.InsertFrame;
import GUI.MyTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditListener implements ActionListener {
    private MyTableModel model;
    private int row;


    public AddEditListener(MyTableModel model) { this(model, -2); }
    public AddEditListener(MyTableModel model, int row) {
        this.model = model;
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InsertFrame f;
        if(row==-2)
            f = new InsertFrame("Inserisci voce", model);
        else
            f = new InsertFrame("Modifica voce", model, row);
    }
}
