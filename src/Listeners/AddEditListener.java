package Listeners;

import Frames.InsertFrame;
import Data.MyTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditListener implements ActionListener {
    private MyTableModel model;
    private int row;


    public AddEditListener(MyTableModel model) { this(model, -1); }
    public AddEditListener(MyTableModel model, int row) {
        this.model = model;
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InsertFrame f;
        if(row==-1)
            f = new InsertFrame("Inserisci voce", model);
        else
            f = new InsertFrame("Modifica voce", model, row);

        f.setLocationRelativeTo(null);
    }
}
