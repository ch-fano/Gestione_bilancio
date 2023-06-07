package Export;

import Data.MyTableModel;
import Data.Record;

import java.io.*;
import java.util.ArrayList;

public class SER_IOFileFormatter extends IOFileFormatter{

    public SER_IOFileFormatter(String filepath, MyTableModel model) {
        super(filepath, model);
    }

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
