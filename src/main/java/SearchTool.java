import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;

//container for swing controls and event handlers
public class SearchTool implements KeyListener, ActionListener, MouseListener {

    private Airspace airspace;
    private MapPanel mapPanel;
    private JTextField jtfSearchText;
    private JLabel jlSearch;
    private JButton jbClear;

    private JPopupMenu jPopupMenu;

    private String searchPhrase;
    private SearchResult searchResult;
    private SearchEngine searchEngine;

    public SearchTool(Airspace airspace, MapPanel mapPanel) {
        setupUi();

        this.airspace = airspace;
        this.mapPanel = mapPanel;


        searchEngine = new MyFirstSearchEngine(airspace);
    }

    private void setupUi() {
        jtfSearchText = new JTextField();
        jlSearch = new JLabel("Search: ");
        jbClear = new JButton("Clear results");

        jPopupMenu = new JPopupMenu();
        jPopupMenu.addMouseListener(this);

        jtfSearchText.add(jPopupMenu);
        jtfSearchText.setComponentPopupMenu(jPopupMenu);

        jtfSearchText.addKeyListener(this);
        jtfSearchText.setSize(new Dimension(100, 30));
        jtfSearchText.setMinimumSize(new Dimension(100, 30));

        jbClear.addActionListener(this);
    }

    public JTextField getJtfSearchText() {
        return jtfSearchText;
    }

    public JLabel getJlSearch() {
        return jlSearch;
    }

    public JButton getJbClear() {
        return jbClear;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // user selected element(s) to display
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // if user selected item from jPopup by mouse or key_down/up, then display only that element
            if (jPopupMenu.hasFocus()) {
                System.out.println("handling jPopup selection");
            }
            // if user pressed enter when in JTextField, then display all matching results
            else if (jtfSearchText.hasFocus()) {
                if (searchResult != null) {
                    // TODO CLONE SEARCHRESULT
                    mapPanel.setSearchResult(searchResult);
                }
                System.out.println(searchResult);
                jtfSearchText.setText("");
                jPopupMenu.setVisible(false);
                //mapPanel.repaint();
            }
        }
        // clear searchPhrase and JTextField
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // reset tool
            searchPhrase = "";
            jtfSearchText.setText(searchPhrase);
        }
        // select next item on jPopup result list
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            jPopupMenu.grabFocus();
        }
        // select previous item on jPopup result list
        else if (e.getKeyCode() == KeyEvent.VK_UP) {

        }
        // update searchPhrase, perform search, display result in popup
        else {
            // TODO - optimize by not performing search if keypressed other than a-z 0-9
            // but remember to handle editing, ie display result when backspace or delete pressed
            searchPhrase = jtfSearchText.getText();

            //diagnostics
            System.out.println(e.getKeyChar() + " pressed");
            System.out.println("current searchPhrase: " + searchPhrase);

            //call search engine for result with each consecutive keypress
            searchResult = searchEngine.doSearch(airspace, searchPhrase);

            //show popupmenu and populate it with search results
            showPopupMenu(e, searchResult);
            //showing popup steals focus so bring it back to textfield to allow further typing
            jtfSearchText.requestFocus();

            //diagnostics
            System.out.println(searchResult);
        }
    }

    private void showPopupMenu(KeyEvent e, SearchResult searchResult) {
        //get event source to get position
        JTextField tf = (JTextField) e.getSource();
        //clear popupMenu
        jPopupMenu.removeAll();

        //populate popupMenu with latest search results
        for (Fix fix :searchResult.getFixList()) {
            JMenuItem item = new JMenuItem(fix.getName());
            jPopupMenu.add(item);

        }
        for (Polygon p :searchResult.getPolygonList()) {
            jPopupMenu.add(p.getName());
        }
        for (Procedure pr :searchResult.getProcedureList()) {
            jPopupMenu.add(pr.getName());
        }

        for (Component mi : jPopupMenu.getComponents()) {
            mi.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {
                    JMenuItem mi = (JMenuItem) e.getSource();
                    mapPanel.setSearchResult(searchEngine.doSearch(airspace, mi.getText()));
                    jtfSearchText.setText("");
                }
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }

        //position popup below textField
        jPopupMenu.setLocation(tf.getLocation());
        jPopupMenu.show(tf, 0, tf.getHeight());


    }

    @Override
    // clear search result, remove result from mapPanel, repaint map panel
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jbClear)) {
            jtfSearchText.setText("");
            searchResult = new SearchResult();

            mapPanel.setSearchResult(searchResult);
            mapPanel.repaint();

            System.out.println(searchResult);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
