import service.SearchService;

import java.io.IOException;

/*
 * Purpose of this class is to initialize search service using command prompt.
 *
 * Author : Harish Deore
 * */

public class Main {

    private static SearchService searchService = new SearchService();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        searchService.search(args);
    }

}