package IOFormatters;

import Data.MyTableModel;
import Data.Record;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe per l'esportazione e l'importazione del vettore delle voci in formato TXT
 * @author Christofer Fanò
 */
public class TXT_IOFileFormatter extends IOFileFormatter {
    /**
     * Costruttore che chiama il costruttore della classe padre
     * @param filepath percorso del file
     * @param model modello contenente il vettore delle voci
     */
    public TXT_IOFileFormatter(String filepath, MyTableModel model) {
        super(filepath, model);
    }

    /**
     * Esportazione del vettore delle voci nel file, con i campi separati da una tabulazione
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean exportFile() {
        ArrayList<Record> v = model.getV();
        FileOutputStream f;

        try {
            f = new FileOutputStream(filepath);
        }catch(FileNotFoundException e){
            return true;
        }

        DataOutputStream os = new DataOutputStream(f);

        try {
            for (Record r : v)
                os.write((r.print("\t")+"\n").getBytes());

            os.close();
        }
        catch (IOException e){
            return true;
        }

        return false;
    }

    /**
     * Importazione del vettore delle voci da file, con i campi separati da una tabulazione
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean importFile() {
        FileInputStream f;
        ArrayList<Record> v = new ArrayList<>();

        try {
            f = new FileInputStream(filepath);
        } catch (IOException e) {
            return true;
        }

        BufferedReader is = new BufferedReader(new InputStreamReader(f));
        try {
            String line;

            while((line = is.readLine())!=null) {
                Record r = new Record();
                if(r.setRecord(line, "\t"))
                    return true;
                v.add(r);
            }
            is.close();
        } catch (IOException e) {
            return true;
        }

        model.setV(v);

        return false;
    }
}
