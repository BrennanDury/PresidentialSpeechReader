package test;

import main.FileReader;
import main.President;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.File;

public class FileReaderTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void readFileTest() {

    }
}
