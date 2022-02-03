/**
javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParse.java MarkdownParseTest.java

java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import org.junit.*;

public class MarkdownParseTest {
    String file1;
    String file2;
    String file3;
    String file4;

    ArrayList<String> links;

    @Before
    public void setUp() throws IOException {
        // Works on GitHub
        file1 = Files.readString(Path.of("test-file.md"));
        file2 = Files.readString(Path.of("test-file-2.md"));
        file3 = Files.readString(Path.of("test-file-3.md"));
        file4 = Files.readString(Path.of("test-file-4.md"));


        // Works locally
        // file1 = Files.readString(Path.of("/Users/nate/Desktop/_UCSD_Winter_2022/CSE_15L/markdown-parse/test-file.md"));

        // file2 = Files.readString(Path.of("/Users/nate/Desktop/_UCSD_Winter_2022/CSE_15L/markdown-parse/test-file-2.md"));

        // file3 = Files.readString(Path.of("/Users/nate/Desktop/_UCSD_Winter_2022/CSE_15L/markdown-parse/test-file-3.md"));

        // file4 = Files.readString(Path.of("/Users/nate/Desktop/_UCSD_Winter_2022/CSE_15L/markdown-parse/test-file-4.md"));
    }

    /**
     * Starting test that checks simple addition
     */
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    /**
     * Tests results of using the MarkdownParse function on given
     * default test file
     * @throws IOException
     */
    @Test
    public void testTestFile() {
        List<String> results = List.of("https://something.com", "some-page.html");
        links = MarkdownParse.getLinks(file1);
        assertEquals(results, links);
    }

    /**
     * Tests results of using MarkdownParse function on test file
     * containing image tag
     * @throws IOException
     */
    @Test
    public void testTestFile2() {
        List<String> results = List.of();
        links = MarkdownParse.getLinks(file2);
        assertEquals(results, links);
    }

    /**
     * Tests on file containing multiple links
     */
    @Test
    public void testTestFile3() throws IOException {
        List<String> results = List.of();
        links = MarkdownParse.getLinks(file3);
        assertEquals(results, links);
    }

    /**
     * Tests on file with empty link
     */
    @Test
    public void testTestFile4() {
        List<String> results = List.of("Extract this text here.com");
        links = MarkdownParse.getLinks(file4);
        assertEquals(results, links);
    }

    @Test
    public void failTest() {
        assertEquals(10, 5 + 5);
    }
}