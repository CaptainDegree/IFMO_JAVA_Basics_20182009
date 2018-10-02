package ru.ifmo.cet.javabasics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        Map<String, Integer> wordsAndAmounts = new HashMap<>();

        gainWords(tome12Path, wordsAndAmounts);
        gainWords(tome34Path, wordsAndAmounts);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordsAndAmounts.entrySet());

        entryList.sort(Map.Entry.comparingByKey());
        entryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        StringBuilder ans = new StringBuilder();

        for (Map.Entry<String, Integer> entry : entryList)
            if (entry.getValue() >= 10)
                ans.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");

        return ans.substring(0, ans.length() - 1);
    }

    private static void gainWords(Path tome, Map<String, Integer> wordsAndAmounts) {
        try (BufferedReader br = new BufferedReader(new FileReader(tome.toFile()))) {
            String line;

            while ((line = br.readLine()) != null)
                for (String word : line.replaceAll("[^а-яА-Яa-zA-Z]", " ").split(" "))
                    if ((word = word.toLowerCase()).length() >= 4)
                        if (wordsAndAmounts.containsKey(word))
                            wordsAndAmounts.replace(word, wordsAndAmounts.get(word) + 1);
                        else
                            wordsAndAmounts.put(word, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}