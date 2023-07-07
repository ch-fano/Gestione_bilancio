package Listeners;

import Panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che gestisce l'editing delle voci delle righe evidenziate quando cliccate con il tasto destro
 * @author Christofer Fanò
 */
public class TableMouseAdapter extends MouseAdapter {

    private final JTable table;
    private final ButtonPanel bp;

    /**
     * Costruttore che memorizza la JTable e il ButtonPanel del progetto
     * @param table JTable su cui lavorare sulle righe
     * @param bp ButtonPanel da passare alla classe ModifyTableListener se necessario
     */
    public TableMouseAdapter(JTable table, ButtonPanel bp) {
        this.table = table;
        this.bp = bp;
    }

    /**
     * Trova la riga su cui si è verificato il rilascio del mouse e genera un JPopupMenu sulla riga
     * @param e evento che genera la chiamata al mouseRelesed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int r = table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
        } else {
            table.clearSelection();
        }

        int rowindex = table.convertRowIndexToModel(table.getSelectedRow());
        if (rowindex > 0 && e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            JMenuItem edit = new JMenuItem("Edit");

            edit.addActionListener(new ModifyTableListener(table, bp));
            popup.add(edit);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    /**
     * Gestisce il click sul pulsante con le stesse azioni del rilascio, necessario per il funzionamento su MacOS
     * @param e evento che genera la chiamata al mousePressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            mouseReleased(e);
        }
    }
}
