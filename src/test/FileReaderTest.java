package test;

import main.FileReader;
import main.President;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public class FileReaderTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void readFileTest() throws FileNotFoundException {
        Set<String> set = FileReader.readRevTextsFile(new File("./src/resources/test-rev-texts/test-rev-president/test-rev-speech"));
        assert set.size() == 3;
        assert set.contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Odio ut sem nulla pharetra diam sit. Eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis. Venenatis cras sed felis eget velit. Senectus et netus et malesuada fames ac turpis. Turpis egestas sed tempus urna et pharetra pharetra massa massa. Ac orci phasellus egestas tellus rutrum tellus. Imperdiet nulla malesuada pellentesque elit. Cursus mattis molestie a iaculis at. Non pulvinar neque laoreet suspendisse interdum. Nisl rhoncus mattis rhoncus urna neque viverra justo. Aliquam purus sit amet luctus. Est velit egestas dui id ornare arcu odio ut. Id diam vel quam elementum pulvinar etiam non quam lacus. Nullam vehicula ipsum a arcu cursus vitae congue. Varius quam quisque id diam.") : set.toString();
        assert set.contains("Sit amet nulla facilisi morbi tempus iaculis urna. Sit amet porttitor eget dolor. Aliquet porttitor lacus luctus accumsan. Fringilla est ullamcorper eget nulla facilisi etiam. Eleifend donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum. Sit amet nisl suscipit adipiscing bibendum est ultricies integer quis. Tristique senectus et netus et malesuada fames ac. Urna id volutpat lacus laoreet non curabitur gravida. Mi ipsum faucibus vitae aliquet nec ullamcorper sit amet risus. Aliquam ut porttitor leo a diam sollicitudin tempor id. Felis eget nunc lobortis mattis aliquam faucibus purus. Bibendum arcu vitae elementum curabitur vitae nunc sed. Maecenas accumsan lacus vel facilisis. Et sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque. Eget nullam non nisi est sit amet facilisis magna.") : set.toString();
        assert set.contains("Porttitor leo a diam sollicitudin tempor id eu. Pellentesque massa placerat duis ultricies. Consequat id porta nibh venenatis cras sed. Nec dui nunc mattis enim ut. Donec massa sapien faucibus et molestie ac feugiat. Velit dignissim sodales ut eu sem integer vitae justo. Scelerisque eleifend donec pretium vulputate. Luctus venenatis lectus magna fringilla urna porttitor. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Maecenas accumsan lacus vel facilisis. Tellus elementum sagittis vitae et leo. Commodo viverra maecenas accumsan lacus vel facilisis volutpat est. Suspendisse interdum consectetur libero id faucibus nisl. Cras ornare arcu dui vivamus.")  : set.toString();
    }
}
