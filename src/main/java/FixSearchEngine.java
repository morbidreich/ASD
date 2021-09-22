public class FixSearchEngine implements SearchEngine{



    @Override
    public SearchResult doSearch(Airspace airspace, String searchPhrase) {


        SearchResult sr = null;
        if (searchPhrase.length() >= 2) {
            sr = new SearchResult();


            for (Fix f : airspace.getFixList()) {
                if (f.getName().contains(searchPhrase)) {
                    sr.getFixList().add(f);
                }
            }
        }
        return sr;
    }
}
