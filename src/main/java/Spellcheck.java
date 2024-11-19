import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A basic spell checker.
 */
public class Spellcheck {

    /** A file containing a list of valid words. */
    private static final String DICTIONARY_FILE = "/usr/share/dict/words";

    /** Read a word list from the dictionary file. */
    private static List<String> readWords() {
        try (Stream<String> stream = Files.lines(Paths.get(DICTIONARY_FILE))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Return a list of misspelled words in the given target sentence. */
    private static List<String> spellcheck(String sentence) {
        // First, load the word list into a BstMap. Note that we only care
        // about the key set, and so add null values for each entry. This
        // is a common strategy for representing sets using the dictionary ADT.
        BstMap<String, Void> words = new BstMap<>();
        List<String> wordlist = readWords();
        for (String word: wordlist) {
            words.put(word, null);
        }

        // Next, break the sentence into words and verify each word is in
        // the dictionary. Add any word that isn't into a list of misspelled words.
        List<String> misspellings = new LinkedList<>();
        for (String word: sentence.toLowerCase().replaceAll("\\.|,", "").split(" ")) {
            if (!words.containsKey(word)) {
                misspellings.add(word);
            }
        }
        return misspellings;
    }

    /** Check the first argument for spelling errors and report any misspellings. */
    public static void main(String[] args) {
        List<String> misspellings = spellcheck(args[0]);

        if (misspellings.isEmpty()) {
            System.out.println("No misspellings found.");
        } else {
            System.out.println("Misspellings:");
            for (String misspell: misspellings) {
                System.out.println("  " + misspell);
            }
        }
    }
}
