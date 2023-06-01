package Listeners;

import Data.MyTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseAdapter extends MouseAdapter {

    private final JTable table;
    public TableMouseAdapter(JTable table) {
        this.table = table;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int r = table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
        } else {
            table.clearSelection();
        }

        int rowindex = table.getSelectedRow();
        if (rowindex > 0 && e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem edit = new JMenuItem("Edit");

            edit.addActionListener(new AddEditListener( (MyTableModel)table.getModel(), rowindex));
            popup.add(edit);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            mouseReleased(e);
        }
    }
}
