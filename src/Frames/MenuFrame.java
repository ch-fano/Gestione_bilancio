package Frames;

import Export.CSV_IOFileFormatter;
import Export.IOFileFormatter;
import Export.TXT_IOFileFormatter;
import Panels.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {
    private final String[] item_text = {"Salva bilancio", "Carica bilancio", "Stampa bilancio", "Formato CSV", "Formato testo", "Formato Excel"};
    private final MainPanel main_panel = new MainPanel();

    public MenuFrame(String titolo) {
        super(titolo);

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

    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();

        if (action.equals(item_text[0]) || action.equals(item_text[3]) || action.equals(item_text[4]) || action.equals(item_text[5])){
            FileFrame ff = new FileFrame(action, FileFrame.SAVE, main_panel.getButtonPanel());
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }
        if (action.equals(item_text[1])){
            FileFrame ff = new FileFrame(action, FileFrame.LOAD, main_panel.getButtonPanel());
            if(ff.operation(main_panel.getTable()))
                errorPane();
        }
        if (action.equals(item_text[2])){

        }
    }

    private void errorPane(){
        JOptionPane.showMessageDialog(this,
                "Operazione fallita",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
    }
}
