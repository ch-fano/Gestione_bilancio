package Frames;

import Panels.MainPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe che gestisce la visualizzazione del menu e inizializza il MainPanel del progetto
 * @author Christofer Fanò
 */
@SuppressWarnings("serial")
public class MenuFrame extends JFrame implements ActionListener {
    private final String[] item_text = {"Salva bilancio", "Carica bilancio", "Stampa bilancio", "Formato CSV", "Formato testo", "Formato Excel"};
    private final MainPanel main_panel = new MainPanel();

    /**
     * Costruttore che aggiunge al frame il menu e il MainPanel
     * @param titolo titolo da visualizzare nel frame
     */
    public MenuFrame(String titolo) {
        super(titolo);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            {
                int result = JOptionPane.showConfirmDialog(null,
                        "Confermi di voler chiudere il programma?",
                        "Conferma di chiusura",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        JMenuBar mb = new JMenuBar();

        String[] menu_text = {"File", "Esporta"};
        JMenu file = new JMenu(menu_text[0]);
        JMenu export = new JMenu(menu_text[1]);


        JMenuItem s = new JMenuItem(item_text[0]);
        JMenuItem c = new JMenuItem(item_text[1]);
        JMenuItem st = new JMenuItem(item_text[2]);
        JMenuItem e1 = new JMenuItem(item_text[3]);
        JMenuItem e2 = new JMenuItem(item_text[4]);
        JMenuItem e3 = new JMenuItem(item_text[5]);

        file.add(s);
        file.add(c);
        file.add(st);

        export.add(e1);
        export.add(e2);
        export.add(e3);


        s.addActionListener(this);
        c.addActionListener(this);
        st.addActionListener(this);
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        mb.add(file);
        mb.add(export);

        setJMenuBar(mb);

        add(main_panel);
        main_panel.setVisible(true);

        pack();
    }

    /**
     * Effettua il salvataggio, il caricamento e la stampa del bilancio a seconda della voce di menu selezionata
     * @param e evento che ha generato la chiamata all'actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();

        if (action.equals(item_text[0])){
            FileFrame ff = new FileFrame(action, FileFrame.SAVE, main_panel.getButtonPanel(), FileFrame.SER);
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }

        if (action.equals(item_text[1])){
            FileFrame ff = new FileFrame(action, FileFrame.LOAD, main_panel.getButtonPanel(), FileFrame.SER);
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }

        if (action.equals(item_text[2])){
            if(main_panel.printTable())
                errorPane();
        }

        if (action.equals(item_text[3])){
            FileFrame ff = new FileFrame(action, FileFrame.SAVE, main_panel.getButtonPanel(), FileFrame.CSV);
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }

        if (action.equals(item_text[4])){
            FileFrame ff = new FileFrame(action, FileFrame.SAVE, main_panel.getButtonPanel(), FileFrame.TXT);
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }

        if (action.equals(item_text[5])){
            FileFrame ff = new FileFrame(action, FileFrame.SAVE, main_panel.getButtonPanel(), FileFrame.XLSX);
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }
    }

    /**
     * Metodo privato per la visualizzazione di un pannello di errore
     */
    private void errorPane(){
        JOptionPane.showMessageDialog(this,
                "Operazione fallita",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
    }
}
