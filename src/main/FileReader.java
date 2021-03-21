package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains a single static method to read a file for a candidate's lines. Supports the notation of the folders "texts" and "rev-texts".
 */
public final class FileReader {
    private static final File[] texts = new File("./texts").listFiles();
    private static final File[] revTexts = new File("./rev-texts").listFiles();

    private static final Map<String, Set<String>> revTags = new HashMap<String, Set<String>>(); //folder name to final name
    static {
        revTags.put("Trump2020", new HashSet<String>(Arrays.asList("Donald Trump:", "Donald J. Trump:", "Main.President Trump:"))); //relies on an inconsistent rule of the text files to notate who is talking
        revTags.put("Biden2020", new HashSet<String>(Arrays.asList("Joe Biden:", "Joe Biden :"))); //relies on an inconsistent rule of the text files to notate who is talking
    }

    /**
     * Reads the file, returning a list of all lines spoken by the speaker as they appear in the file (case sensitive)
     * @param speechFile the file to read (./texts/coolidge/coolidge_speeches_000)
     * @return a list of all lines spoken by the speaker
     * @throws FileNotFoundException if the file is not in any folder
     */
    public static Set<String> readFile(File speechFile) {
        try {
            if (speechFile.getPath().startsWith("texts")) {
                return readTextsFile(speechFile);
            }
            if (speechFile.getPath().startsWith("rev-texts")) {
                return readRevTextsFile(speechFile);
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound in readFile");
            System.exit(0);
            return null;
        }
    }

    private static Set<String> readTextsFile(File speechFile) throws FileNotFoundException {
        Set<String> linesBySpeaker = new HashSet<String>();
        Set<String> tags = createUpperCaseTags(speechFile.getParentFile().getName());
        Scanner sc = new Scanner(speechFile);
        while (sc.hasNext()) {
            String originalLine = sc.nextLine();
            String line = originalLine.toUpperCase();
            boolean isSpeaker = true; //default true unless theres a tag
            if (line.startsWith("<")) {
                isSpeaker = false; //there is a tag, false until proven the tag is correct
                for (String tag : tags) {
                    if (line.length() >= tag.length() && line.startsWith(tag)) {
                        isSpeaker = true;
                        line = line.substring(tag.length());
                        break;
                    }
                }
            }
            if (isSpeaker) {
                linesBySpeaker.add(line);
            }
        }
        return linesBySpeaker;
    }

    /**
     * Defines the set of tags to indicate that the candidate is speaking. All tags are in full caps, so to check if a tag matches,
     * @param candidateName the name of the candidate ("coolidge")
     * @return a set of all tags that could indicate that the candidate is speaking
     */
    private static Set<String> createUpperCaseTags(String candidateName) { //relies on an inconsistent rule of the text files to notate who is talking
        Set<String> tags = new HashSet<String>();
        tags.add("<" + candidateName.toUpperCase() + ":>");
        tags.add("<THE PRESIDENT.>");
        tags.add("<THE PRESIDENT:>");
        tags.add("<PRESIDENT " + candidateName.toUpperCase() + ":>");
        return tags;
    }

    private static Set<String> readRevTextsFile(File speechFile) throws FileNotFoundException {
        Set<String> linesBySpeaker = new HashSet<String>();
        Set<String> tags = revTags.get(speechFile.getParentFile().getName());
        boolean speaker = false;
        Scanner sc = new Scanner(speechFile);
        while (sc.hasNext()) {
            String line = sc.nextLine().toUpperCase();
            if (speaker) {
                linesBySpeaker.add(line);
                speaker = false;
            } else {
                for (String tag : tags) {
                    if (line.contains(tag.toUpperCase())) {
                        speaker = true;
                        break;
                    }
                }
            }
        }
        return linesBySpeaker;
    }
}