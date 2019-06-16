import service.SearchService;

import java.io.IOException;

/*
 * Purpose of this class is to initialize search engine service using command prompt.
 *
 * Author : Harish Deore
 * */

public class Main {

    private static SearchService searchService = new SearchService();

    /* This is main method which call other subsequent method to achieve expected results.
     * @param args  This is the first parameter to accept command line argument.
     * @return void
     * */

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        searchService.search(args);
    }

}