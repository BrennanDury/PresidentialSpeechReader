package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;

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
        while (line.contains(target)) {
            if (firstAppearanceIsWord(line, target)) {
                uses++;
            }
            line = line.substring(line.indexOf(target) + target.length());
            while (!line.isEmpty() && line.charAt(0) >= 'A' && line.charAt(0) <= 'Z') {
                line = line.substring(1);
            }
        }
        return uses;
    }

    /**
     * Returns true if the first appearance of the string target is the word target
     * @param line the line to look for the first appearance of target of
     * @param target the target word
     * @return true if the first appearance of target is the word target
     * @spec.requires line contains target
     */
    private static boolean firstAppearanceIsWord(String line, String target) {
        assert line.contains(target);
        boolean found = true;
        int indexBefore = line.indexOf(target) - 1;
        if (indexBefore > -1) {
            char charBefore = line.charAt(indexBefore);
            if (charBefore >= 'A' && charBefore <= 'Z') {
                found = false;
            }
        }
        int indexAfter = line.indexOf(target) + target.length();
        if (indexAfter < line.length()) {
            char charAfter = line.charAt(indexAfter);
            if (charAfter >= 'A' && charAfter <= 'Z') {
                found = false;
            }
        }
        return found;
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
        while (line.contains(target)) {
            if (firstAppearanceIsWord(line, target)) {
                 return true;
            }
            line = line.substring(line.indexOf(target) + 1);
        }
        return false;
    }
}
