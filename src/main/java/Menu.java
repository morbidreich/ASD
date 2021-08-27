import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener, MenuListener {
    private JFrame mainFrame;
    private final JMenu menuOptions;
    private final JMenu menuElements;
    private final JMenu menuClearRbls;
    private final JMenu menuClose;
    private final JMenuBar menuBar;
    private final JMenuItem miSettings;
    private final JCheckBoxMenuItem cbTma;
    private final JCheckBoxMenuItem cbCtr;
    private final JCheckBoxMenuItem cbAllFixes;
    private final JCheckBoxMenuItem cbTmaFixes;
    private final JCheckBoxMenuItem cbSid01;
    private final JCheckBoxMenuItem cbSid19;
    private final JCheckBoxMenuItem cbStar01;
    private final JCheckBoxMenuItem cbStar19;
    private final JMenuItem miAbout;

    public Menu(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        menuBar = new JMenuBar();

        menuOptions = new JMenu("Options");
        menuElements = new JMenu("Select elements");
        menuClearRbls = new JMenu("Clear RBLs");
        menuClose = new JMenu("Close");
        menuClose.addMenuListener(this);

        menuBar.add(menuElements);
        menuBar.add(menuClearRbls);
        menuBar.add(menuOptions);
        menuBar.add(menuClose);

        miSettings = new JMenuItem("Settings");
        miAbout = new JMenuItem("About...");
        miAbout.addActionListener(this);

        menuOptions.add(miSettings);
        menuOptions.add(miAbout);

        cbTma = new JCheckBoxMenuItem("TMA");
        cbCtr = new JCheckBoxMenuItem("CTR");
        cbAllFixes = new JCheckBoxMenuItem("All fixes");
        cbTmaFixes = new JCheckBoxMenuItem("TMA entry fixes");
        cbSid01 = new JCheckBoxMenuItem("SID 01");
        cbSid19 = new JCheckBoxMenuItem("SID 19");
        cbStar01 = new JCheckBoxMenuItem("STAR 01");
        cbStar19 = new JCheckBoxMenuItem("STAR 19");

        menuElements.add(cbTma);
        menuElements.add(cbCtr);
        menuElements.addSeparator();
        menuElements.add(cbAllFixes);
        menuElements.add(cbTmaFixes);
        menuElements.addSeparator();
        menuElements.add(cbSid01);
        menuElements.add(cbSid19);
        menuElements.add(cbStar01);
        menuElements.add(cbStar19);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miAbout) {
            AboutWindow aw = new AboutWindow();
            aw.setLocationRelativeTo(mainFrame);
            aw.setVisible(true);
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == menuClose)
            System.exit(0);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
