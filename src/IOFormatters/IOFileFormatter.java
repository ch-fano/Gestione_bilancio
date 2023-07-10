package IOFormatters;

import Data.MyTableModel;

/**
 * Classe astratta da cui ereditano tutte le classi per l'importazione e esportazione del progetto
 * @author Christofer Fanò
 */
public abstract class IOFileFormatter {
    protected final String filepath;
    protected final MyTableModel model;

    /**
     * Costruttore che memorizza il percorso del file e il modello
     * @param filepath percorso del file
     * @param model modello del progetto contenente il vettore delle voci su cui bisogna agire
     */
    public IOFileFormatter(String filepath, MyTableModel model) {
        this.filepath = filepath;
        this.model = model;
    }

    public abstract boolean exportFile();
    public abstract boolean importFile();
}
