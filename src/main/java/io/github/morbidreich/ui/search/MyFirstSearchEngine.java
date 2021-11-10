package io.github.morbidreich.ui.search;

import io.github.morbidreich.airspaceElements.*;

import java.util.List;
import java.util.stream.Collectors;

public class MyFirstSearchEngine implements SearchEngine {
    private final int MIN_SEARCH_PHRASE_LENGTH = 2;

    /**
     * Search @airspace for every element containing @searchPhrase as name. Returns
     * empty @io.github.morbidreich.ui.search.SearchResult unless searchPhrase is longer than 2 characters.
     *
     * @param airspace
     * @param searchPhrase
     * @return
     */
    public SearchResult looseSearch(Airspace airspace, String searchPhrase) {
        SearchResult searchResult = new SearchResult();
        searchPhrase = searchPhrase.toUpperCase();

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
     *
     * @param airspace     airspace object
     * @param searchPhrase search phrase typed by user
     * @return
     */
    @Override
    public SearchResult exactSearch(Airspace airspace, String searchPhrase) {
        SearchResult searchResult = new SearchResult();
        searchPhrase = searchPhrase.toUpperCase();

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
        return airspace.getFixList().stream()
                .filter((f) -> {
                    if (searchType == SearchType.EXACT) return f.getName().equals(searchPhrase);
                    else return f.getName().contains(searchPhrase);
                })
                .map(f->(Fix) f.clone())
                .peek(f -> f.setVisible(true))
                .peek(f -> f.setFixType(FixType.SEARCH_RESULT))
                .collect(Collectors.toList());
    }

    private List<Polygon> searchPolygon(Airspace airspace, String searchPhrase, Enum searchType) {
        return airspace.getPolygonList().stream()
                .filter(p -> {
                    if (searchType == SearchType.EXACT) return p.getName().equals(searchPhrase);
                    else return p.getName().contains(searchPhrase);
                })
                .map(p -> (Polygon) p.clone())
                .peek(p -> {p.setVisible(true); p.setPolygonType(PolygonType.SEARCH_RESULT);})
                .collect(Collectors.toList());
    }

    private List<Procedure> searchProcedure(Airspace airspace, String searchPhrase, Enum searchType) {
        return airspace.getProcedureList().stream()
                .filter(p -> {
                    if (searchType == SearchType.EXACT) return p.getName().equals(searchPhrase);
                    else return p.getName().contains(searchPhrase);
                })
                .map(p -> (Procedure) p.clone())
                .map(this::updateProcedureFixes)
                .peek(p->p.setVisibility(true))
                .collect(Collectors.toList());
    }

    private Procedure updateProcedureFixes(Procedure p) {
        for (Fix f : p.getFixList())
            f.setFixType(FixType.SEARCH_RESULT);
        return p;
    }

    /**
     * determines whether search engine uses .contains() or .equals()
     * while querying airspace for result
     */
    private enum SearchType {
        LOOSE,
        EXACT
    }
}
