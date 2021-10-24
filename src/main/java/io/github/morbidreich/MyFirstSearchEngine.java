package io.github.morbidreich;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyFirstSearchEngine implements SearchEngine {
    private final int MIN_SEARCH_PHRASE_LENGTH = 2;

    /**
     * Search @airspace for every element containing @searchPhrase as name. Returns
     * empty @io.github.morbidreich.SearchResult unless searchPhrase is longer than 2 characters.
     * @param airspace
     * @param searchPhrase
     * @return
     */
    public SearchResult looseSearch(Airspace airspace, String searchPhrase) {
        SearchResult searchResult = new SearchResult();
        searchPhrase = searchPhrase.toUpperCase(Locale.ROOT);

        if (searchPhrase.length() >= MIN_SEARCH_PHRASE_LENGTH) {
            searchResult.setFixList(searchFix(airspace, searchPhrase, SearchType.LOOSE));
            searchResult.setPolygonList(searchPolygon(airspace, searchPhrase, SearchType.LOOSE));
            searchResult.setProcedureList(searchProcedure(airspace, searchPhrase, SearchType.LOOSE));
        }
        return searchResult;

        //searchResult.getFixList().add(airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null));
    }

    /**
     * Searches @param airspace object for items which name exactly matches @param searchPhrase
     * @param airspace  airspace object
     * @param searchPhrase search phrase typed by user
     * @return
     */
    @Override
    public SearchResult exactSearch(Airspace airspace, String searchPhrase) {
        SearchResult searchResult = new SearchResult();
        searchPhrase = searchPhrase.toUpperCase(Locale.ROOT);

        if (searchPhrase.length() >= MIN_SEARCH_PHRASE_LENGTH) {
            searchResult.setFixList(searchFix(airspace, searchPhrase, SearchType.EXACT));
            searchResult.setPolygonList(searchPolygon(airspace, searchPhrase, SearchType.EXACT));
            searchResult.setProcedureList(searchProcedure(airspace, searchPhrase, SearchType.EXACT));
        }
        return searchResult;
    }

    //
    // TODO all of these got same method. I should create some kind of parent class, like AirspaceEntity
    // but that will mess with my hibernate annotations and sql data structure, so i'll leave this like it is for now.
    // will handle that later
    private List<Fix> searchFix(Airspace airspace, String searchPhrase, Enum searchType) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Fix> out = new ArrayList<>();
        // check for all fixes
        for (Fix fix : airspace.getFixList()) {
            // if searchType == EXACT then search using .equals(searchPhrase)
            if (searchType == SearchType.EXACT) {
                if (fix.getName().equals(searchPhrase)) {
                    // clone object to modify some of it's properties
                    // without affecting original io.github.morbidreich.Fix
                    Fix f = (Fix) fix.clone();
                    // by default all fixes ale invisible
                    f.setVisible(true);
                    f.setFixType(FixType.SEARCH_RESULT);
                    out.add(f);
                }
                // if searchType == LOOSE then search using .contains(searchPhrase)
            } else if (searchType == SearchType.LOOSE) {
                if (fix.getName().contains(searchPhrase)) {
                    // by default all fixes ale invisible
                    Fix f = (Fix) fix.clone();
                    f.setVisible(true);
                    f.setFixType(FixType.SEARCH_RESULT);
                    out.add(f);
                }
            }
        }
        return out;
    }
    private List<Polygon> searchPolygon(Airspace airspace, String searchPhrase, Enum searchType) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Polygon> out = new ArrayList<>();
        for (Polygon polygon : airspace.getPolygonList()) {
            if (searchType == SearchType.LOOSE) {
                if (polygon.getName().contains(searchPhrase)) {
                    // by default all polygons ale invisible
                    Polygon p = (Polygon) polygon.clone();
                    p.setVisible(true);
                    p.setPolygonType(PolygonType.SEARCH_RESULT);
                    out.add(p);
                }
            } else {
                if (polygon.getName().equals(searchPhrase)) {
                    // by default all polygons ale invisible
                    Polygon p = (Polygon) polygon.clone();
                    p.setVisible(true);
                    p.setPolygonType(PolygonType.SEARCH_RESULT);
                    out.add(p);
                }
            }
        }
        return out;
    }
    private List<Procedure> searchProcedure(Airspace airspace, String searchPhrase, Enum searchType) {
        // TODO replace that with .stream().filter() as soon as you learn lambdas xD
        //airspace.getFixList().stream().filter(p -> p.getName().equals(searchPhrase)).findAny().orElse(null);
        List<Procedure> out = new ArrayList<>();

        for (Procedure procedure : airspace.getProcedureList()) {
            if (searchType == SearchType.LOOSE) {
                if (procedure.getName().contains(searchPhrase)) {
                    // by default all procedures ale invisible
                    Procedure p = (Procedure) procedure.clone();
                    p.setVisibility(true);
                    for (Fix f : p.getFixList()) {
                        f.setVisible(true);
                        f.setFixType(FixType.SEARCH_RESULT);
                    }
                    p.setProcedureType(ProcedureType.SEARCH_RESULT);

                    out.add(p);
                }
            } else if (searchType == SearchType.EXACT){
                if (procedure.getName().equals(searchPhrase)) {
                    // by default all procedures ale invisible
                    Procedure p = (Procedure) procedure.clone();
                    p.setVisibility(true);
                    for (Fix f : p.getFixList()) {
                        f.setVisible(true);
                        f.setFixType(FixType.SEARCH_RESULT);
                    }
                    p.setProcedureType(ProcedureType.SEARCH_RESULT);

                    out.add(p);
                }
            }
        }
        return out;
    }

    /**
     * determines whether search engine uses .contains() or .equals()
     * while querying airspace for result
     */
    private enum SearchType {
        LOOSE,
        EXACT
    };
}
