import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchEngine {
    private Airspace airspace;
    private SearchResult searchResult;
    private List<Fix> fixList;
    private List<Polygon> polygonList;
    private List<Procedure> procedureList;
    private int minSearchPhraseLength;

    public SearchEngine(Airspace airspace) {
        this.airspace = airspace;
        minSearchPhraseLength = 2;
    }

    /**
     * Search @airspace for every element containing @searchPhrase as name. Returns
     * empty @SearchResult unless searchPhrase is longer than 2 characters.
     * @param airspace
     * @param searchPhrase
     * @return
     */
    public SearchResult doSearch(Airspace airspace, String searchPhrase) {
        searchResult = new SearchResult();
        searchPhrase = searchPhrase.toUpperCase(Locale.ROOT);

        if (searchPhrase.length() >= minSearchPhraseLength) {
            searchResult.setFixList(searchFix(searchPhrase));
            searchResult.setPolygonList(searchPolygon(searchPhrase));
            searchResult.setProcedureList(searchProcedure(searchPhrase));
        }
        return searchResult;

        //searchResult.getFixList().add(airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null));
    }

    //
    // TODO all of these got same method. I should create some kind of parent class, like AirspaceEntity
    // but that will mess with my hibernate annotations and sql data structure, so i'll leave this like it is for now.
    // will handle that later
    private List<Fix> searchFix (String searchPhrase) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Fix> out = new ArrayList<>();
        for (Fix fix : airspace.getFixList()) {
            if (fix.getName().contains(searchPhrase)) {
                // by default all fixes ale invisible
                fix.setVisible(true);
                out.add(fix);
            }
        }
        return out;
    }
    private List<Polygon> searchPolygon (String searchPhrase) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Polygon> out = new ArrayList<>();
        for (Polygon polygon : airspace.getPolygonList()) {
            if (polygon.getName().contains(searchPhrase)) {
                // by default all polygons ale invisible
                polygon.setVisible(true);
                out.add(polygon);
            }
        }
        return out;
    }
    private List<Procedure> searchProcedure (String searchPhrase) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Procedure> out = new ArrayList<>();
        for (Procedure procedure : airspace.getProcedureList()) {
            if (procedure.getName().contains(searchPhrase)) {
                // by default all procedures ale invisible
                procedure.setVisibility(true);
                out.add(procedure);
            }
        }
        return out;
    }
}
