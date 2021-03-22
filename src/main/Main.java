package main;

import java.io.File;
import java.util.TreeSet;

public class Main {

    /**
     * Returns a chronologically ordered set of all presidents
     * @return a chronologically ordered set of all presidents
     */
    private static TreeSet<President> presidents() {
        TreeSet<President> presidents = presidentsByNotation("./src/resources/texts");
        presidents.addAll(presidentsByNotation("./src/resources/rev-texts"));
        return presidents;
    }

    private static TreeSet<President> presidentsByNotation(String notationFolder) {
        TreeSet<President> presidents = new TreeSet<President>(new PresidentChronologicalComparator());
        File folder = new File(notationFolder);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            President president = new President(file);
            presidents.add(president);
        }
        return presidents;
    }

    public static void main(String[] args) {
        TreeSet<President> presidents = presidents();
        for (President president : presidents) {
            int uses = president.getNumberUsesOfWord("want");
            int total = president.getTotalWords();
            System.out.println(president.getName() + ": " + uses + " out of " + total + " ratio: " + uses * 100000.0 / total);
        }
    }
}
