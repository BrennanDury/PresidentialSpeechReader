package test;

import main.President;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.File;

public class PresidentTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void wantTest() {
        President hclinton = new President(new File("./src/resources/texts/hclinton"));
        President trump = new President(new File("./src/resources/texts/trump"));
        assert (hclinton.getNumberUsesOfWord("want") == 488) : hclinton.getNumberUsesOfWord("want");
        assert (trump.getNumberUsesOfWord("want") == 1359);
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
    public void separatedByHypthen() {
        String line = "the-the";
        String target = "the";
        assert President.numAppearancesInLine(line, target) == 2 : President.numAppearancesInLine(line, target);
    }
}
