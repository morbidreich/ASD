public interface SearchEngine {
    /**
     * Queries airspace object for elements which name CONTAIN searchPhrase
     * @param airspace  airspace object
     * @param searchPhrase search phrase typed by user
     * @return SearchResult containing query result
     */
    SearchResult looseSearch(Airspace airspace, String searchPhrase);

    /**
     * Queries airspace object for elements which name is EQUAL to searchPhrase
     * @param airspace  airspace object
     * @param searchPhrase search phrase typed by user
     * @return SearchResult containing query result
     */
    SearchResult exactSearch(Airspace airspace, String searchPhrase);
}
