package Frames;

import Data.MyTableModel;
import Export.*;
import Panels.ButtonPanel;

import javax.swing.*;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Questa classe permette la selezione di un file per il salvataggio e il caricamento del bilancio
 * @autor Christofer Fanò
 */
public class FileFrame {
    public static final int SAVE = 0;
    public static final int LOAD = 1;
    private boolean do_operation = false;
    private int type;
    private ButtonPanel bp;
    private File f;
    private final String[] file_desc = {"File SER", "File CSV", "File TXT", "File XLSX"};
    private final String[] file_ext = {"ser", "csv", "txt", "xlsx"};
    private JFileChooser chooser = null;

    /**
     * Costruttore della classe FileFrame. Permette di scegliere un file tra le cartelle del proprio dispositivo
     * @param title titolo visualizzato sul JFileChooser
     * @param type differenzia una finestra di salavataggio da una di caricamento, mantenendo il codice comune
     */
    public FileFrame(String title, int type, ButtonPanel bp) {
        if(type == SAVE || type == LOAD) {
            this.type = type;
            this.bp = bp;

            chooser = new JFileChooser() {
                public void approveSelection() {
                    File selFile = getSelectedFile();
                    do_operation = true;
                    if (selFile.exists() && getDialogType() == JFileChooser.SAVE_DIALOG) {
                        do_operation = false;
                        int result = JOptionPane.showConfirmDialog(this,
                                selFile.getName() + " è già esistente.\nSostituire il file?",
                                "Conferma salvataggio",
                                JOptionPane.YES_NO_OPTION);

                        if (result == JOptionPane.YES_OPTION)
                            do_operation = true;
                        else
                            return;
                    }
                    super.approveSelection();
                }
            };

            chooser.setDialogTitle(title);
            chooser.setCurrentDirectory(new File("./src/Download_balance"));

            for(int i=0; i<4; i++)
                chooser.addChoosableFileFilter(new FileNameExtensionFilter(file_desc[i],file_ext[i]));
            chooser.setAcceptAllFileFilterUsed(false);

            if (type ==SAVE)
                chooser.showSaveDialog(null);
            else
                chooser.showOpenDialog(null);

            f = chooser.getSelectedFile();

        }
        else
            System.err.println("FileFrame: type deve essere uno tra FileFrame.SAVE e FileFrame.LOAD");
    }

    /**
     * Serializza o deserializza l'ArrayList del bilancio a seconda del parametro save, agendo sul file f
     * @param table istanza della classe JTable da cui poter estrarre il modello
     * @return true nel caso qualche operazione fallisca, false se tutto è andato a buon fine o non è stato
     * selezionato nessun file
     */
    public boolean operation(JTable table){
        MyTableModel m = (MyTableModel) table.getModel();

        if(do_operation) {
            String description = chooser.getFileFilter().getDescription();

            boolean result;
            IOFileFormatter fileFormatter = null;

            if(description.equals(file_desc[0])) {
                if (f.exists())
                    fileFormatter = new SER_IOFileFormatter(f.getPath(), m);
                else
                    fileFormatter = new SER_IOFileFormatter(f.getPath()+"."+file_ext[0], m);
            }

            if(description.equals(file_desc[1])) {
                if (f.exists())
                    fileFormatter = new CSV_IOFileFormatter(f.getPath(), m);
                else
                    fileFormatter = new CSV_IOFileFormatter(f.getPath()+"."+file_ext[1], m);
            }

            if(description.equals(file_desc[2])) {
                if (f.exists())
                    fileFormatter = new TXT_IOFileFormatter(f.getPath(), m);
                else
                    fileFormatter = new TXT_IOFileFormatter(f.getPath()+"."+file_ext[2], m);
            }

            if(description.equals(file_desc[3])) {
                if (f.exists())
                    fileFormatter = new XLSX_IOFileFormatter(f.getPath(), m, table);
                else
                    fileFormatter = new XLSX_IOFileFormatter(f.getPath()+"."+file_ext[3], m, table);
            }


            if (type == SAVE)

                result = fileFormatter.exportFile();

            else{

                result = fileFormatter.importFile();
                bp.calculateTotal();
            }

            do_operation = false;
            return result;
        }

        return false;
    }



}