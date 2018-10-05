package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        Map<String, Integer> wordsAndAmounts = new HashMap<>();

        String text = String.join(" ", readAllLines(tome12Path, Charset.forName("windows-1251")));
        text += String.join(" ", readAllLines(tome34Path, Charset.forName("windows-1251")));

        Stream.of(text.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase().split(" "))
                .filter(word -> word.length() >= 4)
                .forEach(word ->
                        wordsAndAmounts.put(word,
                                wordsAndAmounts.getOrDefault(word, 0) + 1));

        Stream<Map.Entry<String, Integer>> strm = wordsAndAmounts.entrySet().stream()
                .sorted((e1, e2) ->
                        (e1.getValue().equals(e2.getValue())) ?
                                e1.getKey().compareTo(e2.getKey()) :
                                e2.getValue().compareTo(e1.getValue()))
                .filter(e -> e.getValue() >= 10);

        StringBuilder ans = new StringBuilder();

        strm.forEach(e -> ans.append(e.getKey()).append(" - ").append(e.getValue()).append("\n"));

        return ans.substring(0, ans.length() - 1);
    }
}