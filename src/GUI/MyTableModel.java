package GUI;

import Dati.Voce;

import javax.swing.table.AbstractTableModel;;

public class MyTableModel extends AbstractTableModel {
    private Voce[] v;
    private String [] nome_col = {"Data", "Descrizione", "Importo"};

    public MyTableModel(){
        this.v= null;
    }

    public MyTableModel(Voce[] v) {
        this.v = v;
    }

    @Override
    public int getColumnCount(){
        return nome_col.length;
    }

    @Override
    public int getRowCount(){
        if(v == null)
            return 0;
        return v.length;
    }

    @Override
    public Object getValueAt(int row, int col){
        switch(col){
            case 0: return v[row].getData();
            case 1: return v[row].getDescrizione();
            case 2: return v[row].getAmmontare();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int col){
        return nome_col[col];
    }

    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }
}
