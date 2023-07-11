package Data;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Classe con i metodi della tabella del bilancio
 * @author Christofer Fanò
 */

public class MyTableModel extends AbstractTableModel {
    private ArrayList<Record> v;
    private final String [] col_name = {"Data", "Descrizione", "Importo"};

    /**
     * Costruttore che setta il vettore delle voci di bilancio
     * @param v vettore delle voci di bilancio
     */
    public MyTableModel(ArrayList <Record> v) { this.v = v; }

    /**
     * Costruttore che inizilizza il vettore delle voci di bilancio ad un vettore vuoto
     */
    public MyTableModel(){ v = new ArrayList<>(); }

    /**
     * Metodo che restituisce il vettore delle voci di bilancio
     * @return vettore delle voci di bilancio
     */
    public ArrayList<Record> getV() {
        return v;
    }

    /**
     * Metodo che setta il vettore delle voci di bilancio e aggiorna la tabella
     * @param v vettore delle voci di bilancio
     */
    public void setV(ArrayList<Record> v) {
        this.v = v;
        fireTableDataChanged();
    }

    /**
     * Metodo che aggiunge una voce al vettore e aggiorna la tabella
     * @param el voce da aggiungere al vettore
     */
    public void addRecord (Record el){
        v.add(el);
        fireTableDataChanged();
    }

    /**
     * Metodo che rimuove un elemento dal vettore delle voci e aggiorna la tabella
     * @param index indice dell'elemento da rimuovere
     */
    public void deleteRecord (int index){
        v.remove(index);
        fireTableDataChanged();
    }

    /**
     * Meotodo che sostituisce un elemento del vettore delle voci con quello passato e aggiorna la tabella
     * @param index indice dell'elemento da sostituire
     * @param el nuovo elemento da inserire nella posizione del precedente
     */
    public void modifyRecord (int index, Record el){
        v.set(index, el);
        fireTableDataChanged();
    }

    /**
     * Metodo che restituisce il numero di colonne della tabella
     * @return numero di colonne della tabella
     */
    @Override
    public int getColumnCount(){
        return col_name.length;
    }

    /**
     * Metodo che restituisce il numero di righe della tabella
     * @return numero di righe della tabella
     */
    @Override
    public int getRowCount(){
        if(v == null)
            return 0;
        return v.size();
    }

    /**
     * Restituisce l'elemento della tabella contenuto nel numero di riga e colonna specificato
     * @param row numero di riga dell'elemento da restituire
     * @param col numero di colonna dell'elemento da restituire
     * @return elemento contenuto nella riga e colonna specificate
     */
    @Override
    public Object getValueAt(int row, int col){
        return switch (col) {
            case 0 -> v.get(row).getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            case 1 -> v.get(row).getDescription();
            case 2 -> v.get(row).getAmount();
            default -> "";
        };
    }

    /**
     * Restituisce il nome della colonna indicata
     * @param col numero di colonna
     * @return nome della colonna
     */
    @Override
    public String getColumnName(int col){
        return col_name[col];
    }

    /**
     * Restituisce la classe degli elementi della colonna indicata
     * @param col numero di colonna
     * @return classe degli elementi della colonna indicata
     */
   @Override
    public Class getColumnClass(int col){
        if(getRowCount()==0)
            return Object.class;

        return getValueAt(0, col).getClass();
    }

}
