package service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * This class provides service which read all the text files in the given directory, building an ​in-memory​ representation
 * of the files and their contents, and then give a command prompt at which interactive searches can be performed.
 *
 * Author: Harish Deore
 * */
public class SearchService {

    public static void search(String[] args) throws IOException {

        final File indexableDirectory = new File(args[0]);
        List<Path> files = listFilesForFolder(indexableDirectory);
        System.out.println(files.size() + " files read in directory " + indexableDirectory);
        try (Scanner keyboard = new Scanner(System.in)) {

            while (true) {
                System.out.print("search> ");
                final String line = keyboard.nextLine();
                if (":quit".equals(line))
                    System.exit(0);

                Map<String, Integer> result = new HashMap<>();
                for (Path filePath : files) {
                    Map<String, Integer> output = findOutput(filePath.toString(), line);
                    result.putAll(output);
                }
                if (result.size() != 0)  //Check for count not equal to zero
                {
                    findPercentage(result, line);
                } else {
                    System.out.println("no matches found");
                }
            }
        }
    }

    private static void findPercentage(Map<String, Integer> result, String line) {
        String[] words = line.split(" ");

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int percentage = (value * 100) / words.length;
            entry.setValue(percentage);
        }

        result.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .limit(10)
                .forEach(k -> System.out.println(k.getKey() + ": " + k.getValue() + "%"));
    }

    private static Map<String, Integer> findOutput(String filePath, String input) throws IOException {

        Map<String, Integer> result = new HashMap<>();

        File file = new File(filePath); //Creation of File Descriptor for input file
        String[] words = null;  //Intialize the word Array
        FileInputStream fileStream = new FileInputStream(file);  //Creation of File Reader object
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream)); //Creation of BufferedReader object
        String s;
        String[] inputWords = input.split(" ");
        // Input word to be searched
        int count = 0;
        for (String inputWord : inputWords) {
            Boolean doesItContains = Boolean.FALSE;
            while ((s = bufferedReader.readLine()) != null) {

                words = s.split(" ");  //Split the word using space
                for (String word : words) {
                    if (word.equals(inputWord))   //Search for the given word
                    {
                        //If Present make it TRUE
                        doesItContains = Boolean.TRUE;

                    }
                }
            }
            fileStream.getChannel().position(0);
            bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
            if (doesItContains) {
                count++;
                result.put(file.getName(), count);
            }
        }

        fileStream.close();
        return result;
    }

    private static List<Path> listFilesForFolder(final File folder) throws IOException {
        List<Path> path;

        try (Stream<Path> paths = Files.walk(Paths.get(folder.getPath()))) {
            path = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

        }
        return path;
    }

}
