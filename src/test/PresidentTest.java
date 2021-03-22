package test;

import main.President;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.File;

public class PresidentTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void revTextsIntegrationTest() {
        President testPresident = new President(new File("./src/resources/test-rev-texts/test-rev-president"));
        assert (testPresident.getNumberUsesOfWord("ipsum") == 3) : testPresident.getNumberUsesOfWord("ipsum");
        assert (testPresident.getNumberUsesOfWord("ac") == 6) : testPresident.getNumberUsesOfWord("ac");
        assert (testPresident.getNumberUsesOfWord("lorem") == 1) : testPresident.getNumberUsesOfWord("lorem");
        assert (testPresident.getNumberUsesOfWord("id") == 8) : testPresident.getNumberUsesOfWord("id");
        assert (testPresident.getNumberUsesOfWord("kitty") == 0) : testPresident.getNumberUsesOfWord("kitty");
    }

    @Test
    public void textsIntegrationTest() {
        President testPresident = new President(new File("./src/resources/test-texts/test-president"));
        assert (testPresident.getNumberUsesOfWord("ipsum") == 3) : testPresident.getNumberUsesOfWord("ipsum");
        assert (testPresident.getNumberUsesOfWord("ac") == 6) : testPresident.getNumberUsesOfWord("ac");
        assert (testPresident.getNumberUsesOfWord("lorem") == 1) : testPresident.getNumberUsesOfWord("lorem");
        assert (testPresident.getNumberUsesOfWord("id") == 8) : testPresident.getNumberUsesOfWord("id");
        assert (testPresident.getNumberUsesOfWord("kitty") == 0) : testPresident.getNumberUsesOfWord("kitty");
    }

    @Test
    public void edgeCases() {
        String line = "the the thethe their bathe the catheter the. .the ..the .the. th.e ..the.. the-the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 10 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void lineIsTarget() {
        String line = "the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void oneAppearance() {
        String line = "the cat";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void twoAppearances() {
        String line = "the the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 2 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void doubleWord() {
        String line = "thethe";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void startOfAnotherWord() {
        String line = "their";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void endOfAnotherWord() {
        String line = "bathe";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void middleOfAnotherWord() {
        String line = "catheter";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void punctuationAfter() {
        String line = "the.";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void punctuationBefore() {
        String line = ".the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void punctuationMiddle() {
        String line = "th.e";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void separatedByHyphen() {
        String line = "the-the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 2 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void sentence() {
        String line = "The quick brown fox jumps over the lazy dog.";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 2 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTarget() {
        String line = "a a";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTarget2To3() { //what do I do with this
        String line = "a a a";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 1 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTargetStartOtherWord() {
        String line = "a ab";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTargetEndOtherWord() {
        String line = "ba a";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTargetMiddleOtherWord() {
        String line = "ba ab";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTarget1() {
        String line = "a aa a";
        String target = "a a";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTarget2() {
        String line = "the thethe the";
        String target = "the the";
        assert President.numAppearancesInLine(line, target) == 0 : President.numAppearancesInLine(line, target);
    }

    @Test
    public void multiPartTarget3() {
        String line = "the the-the the";
        String target = "the the";
        assert President.numAppearancesInLine(line, target) == 2 : President.numAppearancesInLine(line, target);
    }
}
