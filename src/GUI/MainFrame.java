package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JMenu file, export;
    private String menu_text[] = {"File", "Esporta"};
    private String item_text[] = {"Salva bilancio", "Carica bilancio", "Stampa bilancio", "Formato CSV", "Formato testo", "Formato Excel"};
    private GestPanel gpanel = new GestPanel();

    public MainFrame(String titolo) {
        super(titolo);

        JMenuBar mb = new JMenuBar();

        file = new JMenu(menu_text[0]);
        export = new JMenu(menu_text[1]);


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

        add(gpanel);
        gpanel.setVisible(true);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String button = e.getActionCommand();

        if (button.equals(item_text[0])){
            FileFrame ff = new FileFrame(button, FileFrame.SAVE);
            if(ff.operation(gpanel.getModel()))
                errorPane();
        }
        if (button.equals(item_text[1])){
            FileFrame ff = new FileFrame(button, FileFrame.LOAD);
            if(ff.operation(gpanel.getModel()))
                errorPane();
        }
        if (button.equals(item_text[2])){

        }
        if (button.equals(item_text[3]) || button.equals(item_text[4]) || button.equals(item_text[5])){

        }
    }

    private void errorPane(){
        JOptionPane.showMessageDialog(this,
                "Operazione fallita",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
    }
}
