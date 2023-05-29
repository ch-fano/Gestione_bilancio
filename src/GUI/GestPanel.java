package GUI;

import Listeners.AddEditListener;
import Listeners.DeleteListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GestPanel extends JPanel {

    private MyTableModel model;

    public GestPanel() {
        super();
        
        setLayout(new GridLayout(3,1));

        model = new MyTableModel();
        JTable table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("edit");
                    edit.addActionListener(new AddEditListener(model, table.getSelectedRow()));
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
        );

        JScrollPane s = new JScrollPane(table);

        add(s);

        JButton b_add = new JButton("Add");
        JButton b_del = new JButton("Delete");

        b_add.addActionListener(new AddEditListener(model));
        b_del.addActionListener(new DeleteListener(table));

        JPanel b_panel = new JPanel();
        b_panel.add(b_add);
        b_panel.add(b_del);

        add(b_panel);

    }

    public MyTableModel getModel() {
        return model;
    }

}
