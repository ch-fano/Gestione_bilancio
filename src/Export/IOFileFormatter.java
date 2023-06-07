package Export;

import Data.MyTableModel;

import java.io.FileNotFoundException;

public abstract class IOFileFormatter {
    protected final String filepath;
    protected final MyTableModel model;

    public IOFileFormatter(String filepath, MyTableModel model) {
        this.filepath = filepath;
        this.model = model;
    }

    public abstract boolean exportFile();
    public abstract boolean importFile();
}
