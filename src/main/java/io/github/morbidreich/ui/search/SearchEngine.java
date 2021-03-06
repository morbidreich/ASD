package io.github.morbidreich.ui.search;

import io.github.morbidreich.airspaceElements.Airspace;

public interface SearchEngine {
    /**
     * Queries airspace object for elements which name CONTAIN searchPhrase
     * @param airspace  airspace object
     * @param searchPhrase search phrase typed by user
     * @return io.github.morbidreich.ui.search.SearchResult containing query result
     */
    SearchResult looseSearch(Airspace airspace, String searchPhrase);

    /**
     * Queries airspace object for elements which name is EQUAL to searchPhrase
     * @param airspace  airspace object
     * @param searchPhrase search phrase typed by user
     * @return io.github.morbidreich.ui.search.SearchResult containing query result
     */
    SearchResult exactSearch(Airspace airspace, String searchPhrase);
}
