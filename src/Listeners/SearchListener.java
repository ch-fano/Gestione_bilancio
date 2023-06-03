package Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {

    private final JTable table;
    private final JTextField f_text;

    public SearchListener(JTable table, JTextField f_text) {
        this.table = table;
        this.f_text = f_text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        int row = table.getSelectedRow();
        String text = f_text.getText();
        boolean flag = true;

        if (text.equals("")) {
            table.clearSelection();
            return;
        }

        if(name.equals(">")){
            //se row=-1 parto da 0 altrimenti dalla riga succesiva
            for(row++; row < table.getRowCount() && flag; row++) {
                String desc = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1);

                if(desc.contains(text)){
                    flag = false;

                    table.setRowSelectionInterval(row, row);
                }
            }
        }
        else
            if (name.equals("<")){
                if (row == -1)
                    row=table.getRowCount();

                //se nessun elemento è selezionato parto dall'ultimo, altrimenti da quello prima del selezionato
                for(row--; row>=0 && flag; row--) {
                    String desc = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1);

                    if(desc.contains(text)){
                        flag = false;

                        table.setRowSelectionInterval(row, row);
                    }
                }
            }
    }
}
