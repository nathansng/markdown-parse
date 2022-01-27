/**
javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java

java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import org.junit.*;

public class MarkdownParseTest {
    String file1;

    @Before
    public void setUp() throws IOException {
        file1 = Files.readString(Path.of("/Users/nate/Desktop/_UCSD_Winter_2022/CSE_15L/markdown-parse/test-file.md"));
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testTestFile() throws IOException {
        List<String> results = List.of("https://something.com", "some-page.html");
        ArrayList<String> links = MarkdownParse.getLinks(file1);
        assertEquals(results, links);
    }
}