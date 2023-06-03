package Frames;

import Data.MyTableModel;
import Data.Record;
import Panels.ButtonPanel;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;

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

    /**
     * Costruttore della classe FileFrame. Permette di scegliere un file tra le cartelle del proprio dispositivo
     * @param title titolo visualizzato sul JFileChooser
     * @param type differenzia una finestra di salavataggio da una di caricamento, mantenendo il codice comune
     */
    public FileFrame(String title, int type, ButtonPanel bp) {
        if(type == SAVE || type == LOAD) {
            this.type = type;
            this.bp = bp;

            JFileChooser chooser = new JFileChooser() {
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

            chooser.addChoosableFileFilter(new FileNameExtensionFilter("File SER ","ser"));
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
     * @param m istanza della classe MyTableModel da cui poter estrarre e modificare l'ArrayList
     * @return true nel caso qualche operazione fallisca, false se tutto è andato a buon fine o non è stato
     * selezionato nessun file
     */
    public boolean operation(MyTableModel m){
        if(do_operation) {
            if (type == SAVE) {

                FileOutputStream fout;
                try {
                    if(f.exists())
                        fout = new FileOutputStream(f.getAbsoluteFile());
                    else
                        fout = new FileOutputStream(f.getAbsoluteFile()+".ser");
                } catch (FileNotFoundException e) {
                    return true;
                }

                ObjectOutputStream os;

                try {
                    os = new ObjectOutputStream(fout);
                    os.writeObject(m.getV());
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    return true;
                }

            } else {
                FileInputStream fin;
                ObjectInputStream is;

                try {
                    fin = new FileInputStream(f.getAbsoluteFile());
                    is = new ObjectInputStream(fin);
                } catch (IOException e) {
                    return true;
                }

                try {
                    m.setV((ArrayList<Record>) (is.readObject()));
                    is.close();
                } catch (IOException | ClassNotFoundException e) {
                    return true;
                }

                bp.calculateTotal();
            }

            do_operation = false;
        }

        return false;
    }



}