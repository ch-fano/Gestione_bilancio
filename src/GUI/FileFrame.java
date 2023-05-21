package GUI;

import Dati.Voce;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class FileFrame {

    private boolean salva;
    private File f;


    public FileFrame(String titolo, boolean salva) {

        this.salva = salva;

        JFileChooser chooser = new JFileChooser()
        {
            public void approveSelection(){
                File selFile = getSelectedFile();
                if (selFile.exists() && getDialogType() == JFileChooser.SAVE_DIALOG)
                {
                    int result = JOptionPane.showConfirmDialog(this,
                            selFile.getName() + " è già esistente.\nSostituire il file?",
                            "Conferma salvataggio",
                            JOptionPane.YES_NO_OPTION);

                    if (result != JOptionPane.YES_OPTION)
                    {
                        //cancelSelection();
                        return;
                    }
                }
                super.approveSelection();
            }
        };

        chooser.setDialogTitle(titolo);

        if(salva)
            chooser.showSaveDialog(null);
        else
            chooser.showOpenDialog(null);

        f = chooser.getSelectedFile();
    }

    public boolean operazione(MyTableModel m){
        if(f!=null) {
            if (salva) {

                FileOutputStream fout = null;
                try {
                    fout = new FileOutputStream(f.getAbsoluteFile());
                } catch (FileNotFoundException e) {
                    return false;
                }

                ObjectOutputStream os = null;

                try {
                    os = new ObjectOutputStream(fout);
                    os.writeObject(m.getV());
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    return false;
                }

            } else {
                FileInputStream fin = null;
                ObjectInputStream is = null;
                try {
                    fin = new FileInputStream(f.getAbsoluteFile());
                    is = new ObjectInputStream(fin);
                } catch (FileNotFoundException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }

                try {
                    m.setV((ArrayList<Voce>) (is.readObject()));
                    is.close();
                } catch (IOException e) {
                    return false;
                } catch (ClassNotFoundException e) {
                    return false;
                }
            }
        }

        return true;
    }



}