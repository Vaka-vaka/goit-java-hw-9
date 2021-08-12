package home_work.home_words;

import java.io.*;
import java.util.*;

public class Words {
    private static final String COUNT_WORDS =
            "src\\home_work\\resources\\words.txt";

    public static void main(String[] args) {
        File file = new File(COUNT_WORDS);
        checkIfFileAvailable(file);
        reedFile(file);
    }

    private static void reedFile(File file) {

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line.trim());
                stringBuilder.append(" ");
            }
            String words = stringBuilder.toString();
            sortedAndUniqueWords(getStrings(words));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] getStrings(String text) {
        return text.split(" ");

    }

    private static void sortedAndUniqueWords(String[] line) {

        List<String> wordList = Arrays.asList(line);
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String uniqueWord : wordList) {
            wordsMap.put(uniqueWord, Collections.frequency(wordList, uniqueWord));
        }
        wordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    private static void checkIfFileAvailable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
