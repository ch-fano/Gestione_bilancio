package Data;

import Data.Record;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe che gestisce la visualizzazione della tabella del bilancio
 * @autor Christofer Fanò
 */

public class MyTableModel extends AbstractTableModel {
    private ArrayList<Record> v;
    private final String [] col_name = {"Data", "Descrizione", "Importo"};
    public MyTableModel(ArrayList <Record> v) { this.v = v; }
    public MyTableModel(){ v = new ArrayList<>(); }

    public ArrayList<Record> getV() {
        return v;
    }

    public void setV(ArrayList<Record> v) {
        this.v = v;
        fireTableDataChanged();
    }

    public void addRecord (Record el){

        v.add(el);
        fireTableDataChanged();
    }

    public void deleteRecord (int index){
        v.remove(index);
        fireTableDataChanged();
    }

    public void modifyRecord (int index, Record el){
        v.set(index, el);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount(){
        return col_name.length;
    }

    @Override
    public int getRowCount(){
        if(v == null)
            return 0;
        return v.size();
    }

    @Override
    public Object getValueAt(int row, int col){
        return switch (col) {
            case 0 -> v.get(row).getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            case 1 -> v.get(row).getDescription();
            case 2 -> v.get(row).getAmount();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int col){
        return col_name[col];
    }

    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }

}
