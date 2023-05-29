package Listeners;

import GUI.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {
    private JTable table;
    
    public DeleteListener(JTable table) { this.table = table; }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();

        if(row!=-1)
            ((MyTableModel)table.getModel()).deleteRecord(row);
    }
}
