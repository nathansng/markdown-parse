// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {

     public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }
  

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
	    if (nextOpenBracket < 0) {
		break;
	    }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
	    if (nextCloseBracket < 0) {
		break;
	    }
            int openParen = markdown.indexOf("(", nextCloseBracket);
	    if (openParen < 0) {
		break;
	    }
            int closeParen = markdown.indexOf(")", openParen);
	    if (closeParen < 0) {
		break;
	    }
            // Check to make sure it's not an image
            if (nextOpenBracket > 0 && markdown.charAt(nextOpenBracket-1) == '!') {
                currentIndex = closeParen + 1;
                continue;
            }
            // Check that close bracket is followed by open parenthesis
            if (openParen - nextCloseBracket > 1) {
                currentIndex = closeParen + 1;
                continue;
            }
            // Check for valid substrings
            if (closeParen <= openParen) {
                break;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;

    }
    public static void main(String[] args) throws IOException {
	// Path fileName = Path.of(args[0]);
	// String contents = Files.readString(fileName);
        // ArrayList<String> links = getLinks(contents);
	File file = new File(args[0]);
        System.out.println(getLinks(file));
    }
}
