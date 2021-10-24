package io.github.morbidreich;

public class FixSearchEngine implements SearchEngine{
SearchResult sr = new SearchResult();


    @Override
    public SearchResult looseSearch(Airspace airspace, String searchPhrase) {

        if (searchPhrase.length() >= 2) {


            for (Fix f : airspace.getFixList()) {
                if (f.getName().contains(searchPhrase)) {
                    sr.getFixList().add(f);
                }
            }
        }
        return sr;
    }

    @Override
    public SearchResult exactSearch(Airspace airspace, String searchPhrase) {
        if (searchPhrase.length() >= 2) {


            for (Fix f : airspace.getFixList()) {
                if (f.getName().equals(searchPhrase)) {
                    sr.getFixList().add(f);
                }
            }
        }
        return sr;
    }
}
