package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * The set of all lines said by a candidate. Supports the notation folders "Texts" and "RevTexts".
 */
public class President {
    //Rep Invariant: linesByCandidate != null
    //Abstraction Function: the set of all lines said by a candidate = linesByCandidate

    private Set<String> linesByPresident;
    private String name;

    /**
     *
     * @param candidateFolder the candidate folder (Texts/coolidge)
     * @throws FileNotFoundException if either parameter do not exist
     */
    public President(File candidateFolder){
        this.name = candidateFolder.getName();
        this.linesByPresident = new HashSet<String>();
        File[] speechFiles = candidateFolder.listFiles();
        for (File file : speechFiles) {
            linesByPresident.addAll(FileReader.readFile(file));
        }
        assert(linesByPresident != null);
        assert (!linesByPresident.isEmpty()) : candidateFolder.getName();
    }

    /**
     * returns the set of all lines which contain this word
     * @param word the word to search for
     * @return the set of all lines which contain this word
     */
    public Set<String> getEachLineWithWord(String word) {
        Set<String> linesWithWord = new HashSet<String>();
        for (String line : linesByPresident) {
            if (appearsInLine(line, word)) {
                linesWithWord.add(line);
            }
        }
        return linesWithWord;
    }

    /**
     * returns the number of times this word is said by this candidate
     * @param word the word to search for
     * @return the number of times this word is said by this candidate
     */
    public int getNumberUsesOfWord(String word) {
        int total = 0;
        for (String line : linesByPresident) {
            total += numAppearancesInLine(line, word);
        }
        return total;
    }

    /**
     * returns the number of words said by this candidate
     * @return the number of words said by this candidate
     */
    public int getTotalWords() {
        int total = 0;
        for (String line : linesByPresident) {
            String[] words = line.split(" ");
            total += words.length;
        }
        return total;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns the number of times the phrase appears in the line. Not case sensitive. If the phrase is directly preceded or directly
     * followed by a letter, it does not count.
     * @param line the line to look through
     * @param target the phrase to look for
     * @return the number of times the phrase appears in the line
     */
    public static int numAppearancesInLine(String line, String target) {
        line = line.toUpperCase();
        target = target.toUpperCase();
        int uses = 0;
        String[] words = line.split(" ");
        for (String word : words) {
            if (checkWordIsTarget(word, target)) {
                uses++;
            }
        }
        return uses;
    }

    /**
     * Returns true if the line has the target word. Not case sensitive. If the word is directly preceded or directly
     * followed by a letter, it does not count.
     * @param line the line to look through
     * @param target the word to look for
     * @return true if the line has the target word, false
     */
    private static boolean appearsInLine(String line, String target) {
        line = line.toUpperCase();
        target = target.toUpperCase();
        String[] words = line.split(" ");
        for (String word : words) {
            if (checkWordIsTarget(word, target)) {
                 return true;
            }
        }
        return false;
    }

    /**
     * Checks if the word is the target word, considering punctuation. A word made of multiple words combined with a punctuation mark like a hyphen
     * count as the target word if the target word is any of those words.
     * @param word the word to check
     * @param target the target word
     * @return true if the word is the target word
     */
    private static boolean checkWordIsTarget(String word, String target) {
        if (word.contains(target)) {
            char charBefore = word.charAt(word.indexOf(target) - 1);
            if (charBefore < 65 || charBefore > 90) {
                word = word.substring(word.indexOf(target));
            }
            int indexAfter = word.indexOf(target) + target.length();
            char charAfter = word.charAt(indexAfter);
            if (charAfter < 65 || charAfter > 90) {
                word = word.substring(0, indexAfter);
            }
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
}
