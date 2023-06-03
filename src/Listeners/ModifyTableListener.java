package Listeners;

import Frames.InsertFrame;
import Data.MyTableModel;
import Panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyTableListener implements ActionListener {
    private final JTable table;
    private final ButtonPanel bp;

    public ModifyTableListener(JTable table, ButtonPanel bp) {
        this.table = table;
        this.bp = bp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        InsertFrame f;
        MyTableModel model = (MyTableModel) table.getModel();

        int row = table.getSelectedRow();
        if(row!=-1)
            row = table.convertRowIndexToModel(row);


        if(name.equals("Add")) {
            f = new InsertFrame("Inserisci voce", model, bp);
            f.setLocationRelativeTo(null);
        }
        else {
            if (name.equals("Delete")) {
                if (row != -1) {
                    model.deleteRecord(row);
                    bp.calculateTotal();
                }
            } else {
                f = new InsertFrame("Modifica voce", model, bp, row);
                f.setLocationRelativeTo(null);
            }
        }
    }
}
