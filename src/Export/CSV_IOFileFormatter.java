package Export;

import Data.MyTableModel;
import Data.Record;


import java.io.*;
import java.util.ArrayList;

public class CSV_IOFileFormatter extends IOFileFormatter {

    public CSV_IOFileFormatter(String filepath, MyTableModel model) {
        super(filepath, model);
    }

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
                os.write((r.print(",")+"\n").getBytes());

            os.close();
        }
        catch (IOException e){
            return true;
        }

        return false;
    }

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
            String line = null;

            while((line = is.readLine())!=null) {
                 Record r = new Record();
                 if(r.setRecord(line, ","))
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
