package Panels;

import Data.MyTableModel;
import Listeners.TableMouseAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JTable table;
    private final ButtonPanel bp;
    public MainPanel() {
        super();

        setLayout(new BorderLayout());


        MyTableModel model = new MyTableModel();
        table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Float.class, centerRenderer);

        TableRowSorter<MyTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane s = new JScrollPane(table);
        add(s, BorderLayout.CENTER);

        bp = new ButtonPanel(table);
        table.addMouseListener(new TableMouseAdapter(table, bp));
        FilterPanel fp = new FilterPanel(table, bp);

        add(fp, BorderLayout.NORTH);
        add(bp, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }
    public ButtonPanel getButtonPanel(){ return bp; }
}
