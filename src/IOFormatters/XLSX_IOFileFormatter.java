package IOFormatters;

import Data.MyTableModel;
import Data.Record;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe per l'esportazione e l'importazione del vettore delle voci in formato XLSX
 * @author Christofer Fanò
 */
public class XLSX_IOFileFormatter extends IOFileFormatter{

    private final JTable table;

    /**
     * Costruttore che chiama il costruttore della classe padre e inizializza la JTable
     * @param filepath percorso del file
     * @param m modello contenente il vettore delle voci
     * @param table tabella del progetto utilizzata per sapere il numero e il nome delle colonne
     */
    public XLSX_IOFileFormatter(String filepath, MyTableModel m, JTable table) {
        super(filepath, m);
        this.table = table;
    }

    /**
     * Esportazione del vettore delle voci in formato excel
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean exportFile()  {


        /*Tentativo di risoluzione stampa logging
        System.setProperty("log4j.configurationFile","./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/lib/log4j2.xml");
        SimpleLogger log = (SimpleLogger) LogManager.getLogger(SimpleLogger.class.getName());*/


        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);


        //scrivo i nomi delle colonne
        Row headerRow = sheet.createRow(0);
        for(int headings = 0; headings < model.getColumnCount(); headings++){
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
        }

        //scrivo le righe della tabella
        for(int rows = 0; rows < model.getRowCount(); rows++){
            for(int cols = 0; cols < table.getColumnCount(); cols++){
                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString());
            }

            row = sheet.createRow((rows + 3));
        }

        try{
            wb.write(new FileOutputStream(filepath));//Save the file
            wb.close();
        }catch(IOException e)
        {
            e.printStackTrace();
            return true;
        }

        return false;
    }

    /**
     * Importazione del vettore delle voci in formato excel, prima riga per il nome delle colonne, seconda vuota con gli
     * elementi dalla terza riga in poi
     * @return true se si è verificato un errore, false altrimenti
     */
    @Override
    public boolean importFile() {
        ArrayList<Record> v = new ArrayList<>();

        try
        {
            FileInputStream file = new FileInputStream(filepath);

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            try {
                rowIterator.next();
            } catch (NoSuchElementException e){
                e.printStackTrace();
                return true;
            }


            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> it = row.cellIterator();
                LocalDate date = null;
                String desc = "";
                float amount = 0;

                for (int i=0; it.hasNext() && i<3; i++) {
                    Cell c = it.next();
                    String value = c.getStringCellValue();

                    try {
                        switch (i) {
                            case 0 -> date = (new SimpleDateFormat("dd-MM-yyyy").parse(value)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            case 1 -> desc = value;
                            case 2 -> amount = Float.parseFloat(value);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }

                }

                //se date non è null, la riga letta non era vuota e quindi posso inserire la nuova voce
                if(date!=null)
                    v.add(new Record(date, desc, amount));
            }

            ((MyTableModel) table.getModel()).setV(v);

            try {
                wb.close();
                file.close();
            }catch(IOException e){
                e.printStackTrace();
                return true;
            }

            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
           return true;
        }
    }
}
