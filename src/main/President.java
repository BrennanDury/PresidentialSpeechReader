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
            if (word.contains(target)) {
                if (word.charAt(0) < 65 || word.charAt(0) > 90) {
                    word = word.substring(1);
                }
                if (word.charAt(word.length() - 1) < 65 || word.charAt(word.length() - 1) > 90) {
                    word = word.substring(0, word.length() - 1);
                }
                if (word.equals(target)) {
                    uses++;
                }
            }
        }
        return uses;
    }

    /**
     * Returns true if the line has the target phrase. Not case sensitive. If the phrase is directly preceded or directly
     * followed by a letter, it does not count.
     * @param line the line to look through
     * @param target the phrase to look for
     * @return true if the line has the target phrase, false
     */
    private static boolean appearsInLine(String line, String target) {
        line = line.toUpperCase();
        target = target.toUpperCase();
        while (line.contains(target)) { //the target is in the line, unless it is preceded or followed by a letter, then look for the next appearance
            int start = line.indexOf(target);
            if (       (start == 0 || line.charAt(start - 1) < 65 || line.charAt(start - 1) > 90) //checks theres not a letter immediately before
                    && (start + target.length() == line.length() || line.charAt(start + target.length()) < 65 || line.charAt(start + target.length()) > 90)   ) { //checks theres not a letter immediately after
                return true;
            }
            line = line.substring(line.indexOf(target) + 1);
        }
        return false;
    }
}
