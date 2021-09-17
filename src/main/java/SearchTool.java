import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//container for swing controls and event handlers
public class SearchTool implements KeyListener, ActionListener {

    private Airspace airspace;
    private MapPanel mapPanel;
    private JTextField jtfSearchText;
    private JLabel jlSearch;
    private JButton jbClear;
    private String searchPhrase;
    private SearchResult searchResult;
    private SearchEngine searchEngine;

    public SearchTool(Airspace airspace, MapPanel mapPanel) {
        setupUi();

        this.airspace = airspace;
        this.mapPanel = mapPanel;

        searchEngine = new SearchEngine(airspace);
    }

    private void setupUi() {
        jtfSearchText = new JTextField();
        jlSearch = new JLabel("Search: ");
        jbClear = new JButton("Clear results");

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
        // perform search and send result to mapPanel
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (searchResult != null) {
                // CLONE SEARCHRESULT
                mapPanel.setSearchResult(searchResult);
            }
            System.out.println(searchResult);
            mapPanel.repaint();
        }
        // clear searchPhrase and JTextField
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // reset tool
            searchPhrase = "";
            jtfSearchText.setText(searchPhrase);
        }
        // update searchPhrase, perform search, display result in popup
        else {
            // TODO - optimize by not performing search if keypressed other than a-z 0-9
            searchPhrase = jtfSearchText.getText();
            System.out.println(e.getKeyChar() + " pressed");
            System.out.println("current searchPhrase: " + searchPhrase);

            searchResult = searchEngine.doSearch(airspace, searchPhrase);
            System.out.println(searchResult);
        }
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
}
