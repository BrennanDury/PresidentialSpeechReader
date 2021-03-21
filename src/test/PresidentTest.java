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
}
