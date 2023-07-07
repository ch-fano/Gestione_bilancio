package Export;

import Data.MyTableModel;
import Data.Record;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe per l'esportazione e l'importazione del vettore delle voci in formato SER
 * @author Christofer Fanò
 */
public class SER_IOFileFormatter extends IOFileFormatter{

    /**
     * Costruttore che chiama il costruttore della classe padre
     * @param filepath percorso del file
     * @param model modello contenente il vettore delle voci
     */
    public SER_IOFileFormatter(String filepath, MyTableModel model) {
        super(filepath, model);
    }

    /**
     * Serializzazione del vettore delle voci nel file
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean exportFile() {
        FileOutputStream f;
        ArrayList<Record> v = model.getV();

        try {
            f = new FileOutputStream(filepath);
        }catch(FileNotFoundException e){
            return true;
        }

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(f);
            os.writeObject(v);
            os.flush();
            os.close();
        } catch (IOException e) {
            return true;
        }

        return false;
    }

    /**
     * Deserializzazione del vettore delle voci da file
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean importFile() {
        FileInputStream f;
        ObjectInputStream is;

        try {
            f = new FileInputStream(filepath);
            is = new ObjectInputStream(f);
        } catch (IOException e) {
            return true;
        }

        try {
            model.setV((ArrayList<Record>) (is.readObject()));
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            return true;
        }

        return false;
    }


}
