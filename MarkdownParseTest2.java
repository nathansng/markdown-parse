/**
javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParse.java MarkdownParseTest.java

java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest

jdb -classpath "lib/*:." org.junit.runner.JUnitCore MarkdownParseTest
 */

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.junit.*;

public class MarkdownParseTest2 {
    @Test
    public void testSnippet3() throws IOException {
        String file = Files.readString(Path.of("snippet_3.md"));
        List<String> results = List.of("https://ucsd-cse15l-w22.github.io/");
        ArrayList<String> links = MarkdownParse.getLinks(file);
        assertEquals(results, links);
    }
}

/**
@Test
    public void testSnippet1() throws IOException {
        String file = Files.readString(Path.of("snippet_1.md"));
        List<String> results = List.of("`google.com", "google.com",
                                        "ucsd.edu");
        ArrayList<String> links = MarkdownParse.getLinks(file);
        assertEquals(results, links);
    }

@Test
    public void testSnippet2() throws IOException {
        String file = Files.readString(Path.of("snippet_2.md"));
        List<String> results = List.of("a.com", "a.com(())", "example.com");
        ArrayList<String> links = MarkdownParse.getLinks(file);
        assertEquals(results, links);
    }
 */