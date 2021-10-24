package io.github.morbidreich;

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
        //searchEngine = context.getBean("desiredEngine", io.github.morbidreich.SearchEngine.class);
        searchEngine = new MyFirstSearchEngine();
        //searchEngine = new io.github.morbidreich.FixSearchEngine();
    }

    // set up io.github.morbidreich.SearchTool structure
    private void setupUi() {
        jtfSearchText = new JTextField();
        jlSearch = new JLabel("Search: ");
        jbClear = new JButton("Clear results");

        jPopupMenu = new JPopupMenu();
        jPopupMenu.addMenuKeyListener(new PopupMenuKeyListener());
//
        //jtfSearchText.add(jPopupMenu);
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
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    //main event handler for key-driven textBox events
    @Override
    public void keyReleased(KeyEvent e) {
        // user selected element(s) to display
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // if user selected item from jPopup by mouse or key_down/up, then display only that element
            if (searchResult != null) {
                mapPanel.setSearchResult(searchResult);
            }
            //diagnostics
            //System.out.println(searchResult);

            //cleanup after displaying search results
            jtfSearchText.setText("");
            jPopupMenu.setVisible(false);
        }
        // clear searchPhrase JTextField, send empty io.github.morbidreich.SearchResult object to mapPanel
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // reset tool
            searchPhrase = "";
            jtfSearchText.setText(searchPhrase);
            jPopupMenu.setVisible(false);
            mapPanel.setSearchResult(new SearchResult());
        }

        // pass focus to jPopupMenu and handle arrow navigation
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            jPopupMenu.grabFocus();
        }
        // select previous item on jPopup result list
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            jPopupMenu.grabFocus();

        }
        // update searchPhrase, perform search, display result in popup
        else {
            // TODO - optimize by not performing search if keypressed other than aA-zZ or 0-9
            // but remember to handle editing, ie display result when backspace or delete pressed
            jtfSearchText.setText(jtfSearchText.getText().toUpperCase());
            searchPhrase = jtfSearchText.getText();

            //call search engine for result with each consecutive keypress
            searchResult = searchEngine.looseSearch(airspace, searchPhrase);
            System.out.println(searchResult);

            //show popupmenu and populate it with search results
            generatePopupMenu(searchResult);
            //showing popup steals focus so bring it back to textfield to allow further typing
            jtfSearchText.requestFocus();
        }
    }

    /**
     * prepare JPopupMenu displaying list of searchResult
     * @param searchResult
     */
    private void generatePopupMenu(SearchResult searchResult) {
        //clear popupMenu
        jPopupMenu.removeAll();

        //populate popupMenu with latest search results
        for (Fix f : searchResult.getFixList())
            jPopupMenu.add(f.getName());

        for (Polygon p : searchResult.getPolygonList())
            jPopupMenu.add(p.getName());

        for (Procedure pr : searchResult.getProcedureList())
            jPopupMenu.add(pr.getName());

        // add click listener to each JMenuItem added to jPopupList
        // click selects corresponding airspace element to be displayed
        for (Component mi : jPopupMenu.getComponents()) {
            mi.addMouseListener(new PopupMenuListener());
        }
        //position popup below textField
        jPopupMenu.setLocation(jtfSearchText.getLocation());
        jPopupMenu.show(jtfSearchText, 0, jtfSearchText.getHeight());


    }

    /**
     * action listener for JButton jbClear.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jbClear)) {

            // clear search textField
            jtfSearchText.setText("");

            // send empty searchResult to mapPanel
            mapPanel.setSearchResult(new SearchResult());

        }
    }

    /**
     * Mouse Listener for jPopupMenu
     */
    class PopupMenuListener implements MouseListener {
        @Override // not used
        public void mouseClicked(MouseEvent e) {}

        @Override // not used
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            // get clicked JMenuItem
            JMenuItem mi = (JMenuItem) e.getSource();
            // perform new exactSearch to return only clicked element, mi.getText() as searchPhrase
            mapPanel.setSearchResult(searchEngine.exactSearch(airspace, mi.getText()));
            // textField cleanup
            jtfSearchText.setText(mi.getText());
            //dont have to hide jPopupMenu manually, already handled by mouseReleased
        }

        @Override // not used
        public void mouseEntered(MouseEvent e) {}

        @Override //not used
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * MenuKey Listener for jPopupMenu
     */
    class PopupMenuKeyListener implements MenuKeyListener {

        @Override
        public void menuKeyTyped(MenuKeyEvent e) {}

        @Override
        public void menuKeyPressed(MenuKeyEvent e) {}

        // listen to MenuKeyReleased event performed on jPopupMenu
        // if key was ENTER then perform exactSearch for airspace element
        // matching selected MenuItem name
        @Override
        public void menuKeyReleased(MenuKeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // store selected path in table. Selected path for my menu structure always
                // return 2 elements array where [0]: JPopupMenu, [1]: JMenuItem
                MenuElement[] table = e.getMenuSelectionManager().getSelectedPath();

                // cast JmenuItem from table[1]
                JMenuItem mi = (JMenuItem) table[1];

                // perform exactSearch using JMenuItem .getText() as searchPhrase
                searchResult = searchEngine.exactSearch(airspace, mi.getText());
                //diagnostics
                System.out.println(searchResult);

                //send search result to mapPanel
                mapPanel.setSearchResult(searchResult);

                //cleanup
                jtfSearchText.setText(mi.getText());
                jPopupMenu.setVisible(false);
            }
        }
    }
}
