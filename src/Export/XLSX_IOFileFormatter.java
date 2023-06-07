package Export;

import Data.MyTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLSX_IOFileFormatter extends IOFileFormatter{

    private final JTable table;

    public XLSX_IOFileFormatter(String filepath, MyTableModel m, JTable table) {
        super(filepath, m);
        this.table = table;
    }

    @Override
    public boolean exportFile()  {


        /*Tentativo di risoluzione stampa logging
        System.setProperty("log4j.configurationFile","./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/lib/log4j2.xml");
        SimpleLogger log = (SimpleLogger) LogManager.getLogger(SimpleLogger.class.getName());*/


        Workbook wb = new XSSFWorkbook(); //Excel workbook
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(2); //Row created at line 3



        Row headerRow = sheet.createRow(0); //Create row at line 0
        for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }

        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
            }

            //Set the row to the next one in the sequence
            row = sheet.createRow((rows + 3));
        }

        try{
            wb.write(new FileOutputStream(filepath));//Save the file
        }catch(IOException e)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean importFile() {
        return false;
    }
}
