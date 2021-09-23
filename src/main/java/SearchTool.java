import javax.swing.*;

import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.*;

//container for swing controls and event handlers
public class SearchTool implements KeyListener, ActionListener {

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


        //TODO dependency injection someday?
        //searchEngine = context.getBean("desiredEngine", SearchEngine.class);
        searchEngine = new MyFirstSearchEngine();
        //searchEngine = new FixSearchEngine();

    }

    private void setupUi() {
        jtfSearchText = new JTextField();
        jlSearch = new JLabel("Search: ");
        jbClear = new JButton("Clear results");


        jPopupMenu = new JPopupMenu();
        //jPopupMenu.addKeyListener(new PopupKeyListener());
        jPopupMenu.addMenuKeyListener(new PopupMenuKeyListener());
//
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


    //Event raised
    @Override
    public void keyReleased(KeyEvent e) {
        // user selected element(s) to display
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // if user selected item from jPopup by mouse or key_down/up, then display only that element

            if (searchResult != null) {
                // TODO CLONE SEARCHRESULT
                mapPanel.setSearchResult(searchResult);
            }
            System.out.println(searchResult);

            //cleanup after displaying search results
            jtfSearchText.setText("");
            jPopupMenu.setVisible(false);
            //mapPanel.repaint();

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
            jPopupMenu.grabFocus();

        }
        // update searchPhrase, perform search, display result in popup
        else {
            // TODO - optimize by not performing search if keypressed other than a-z 0-9
            // but remember to handle editing, ie display result when backspace or delete pressed
            jtfSearchText.setText(jtfSearchText.getText().toUpperCase());
            searchPhrase = jtfSearchText.getText();

            //diagnostics
            System.out.println(e.getKeyChar() + " pressed");
            System.out.println("current searchPhrase: " + searchPhrase);

            //call search engine for result with each consecutive keypress
            searchResult = searchEngine.looseSearch(airspace, searchPhrase);

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
        for (Fix f : searchResult.getFixList())
            jPopupMenu.add(f.getName());

        for (Polygon p : searchResult.getPolygonList())
            jPopupMenu.add(p.getName());

        for (Procedure pr : searchResult.getProcedureList())
            jPopupMenu.add(pr.getName());

        // add click listener to each JMenuItem on popup
        // click selects corresponding airspace element to be displayed
        for (Component mi : jPopupMenu.getComponents()) {
            mi.addMouseListener(new PopupMenuListener());
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

    class PopupMenuListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JMenuItem mi = (JMenuItem) e.getSource();

            // perform new search to return only clicked element
            mapPanel.setSearchResult(searchEngine.exactSearch(airspace, mi.getText()));
            System.out.println("drawing " + mi.getText());
            // textField cleanup
            jtfSearchText.setText(mi.getText());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class PopupMenuKeyListener implements MenuKeyListener {

        @Override
        public void menuKeyTyped(MenuKeyEvent e) {

        }

        @Override
        public void menuKeyPressed(MenuKeyEvent e) {

        }

        @Override
        public void menuKeyReleased(MenuKeyEvent e) {


            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                MenuElement[] table = e.getMenuSelectionManager().getSelectedPath();


                JMenuItem mi = (JMenuItem) table[1];
                System.out.println(mi.getText());

                searchResult = searchEngine.exactSearch(airspace, mi.getText());
                System.out.println("Search result from ENTER KEYPRESS");
                System.out.println(searchResult);


                mapPanel.setSearchResult(searchResult);
                jtfSearchText.setText(mi.getText());
                jPopupMenu.setVisible(false);
            }

        }
    }

}
