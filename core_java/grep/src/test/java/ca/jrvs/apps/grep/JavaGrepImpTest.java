package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Order of execution:
 * @BeforeClass
 * Constructor of class under test
 * @Before
 * @Test
 *
 */
public class JavaGrepImpTest {

  static final String rootPath = System.getProperty("user.dir");

  static List<File> files;
  static List<String> lines, otherLines;
  static File file, grepOut, testOut;
  static Pattern pattern;
  static Matcher matcher;
  static BufferedReader br;
  static BufferedWriter bw;

  static JavaGrepImp imp = new JavaGrepImp();

  @BeforeClass
  public static void setUp() {
    files = new ArrayList<>();
    lines = new ArrayList<>();

    files.add(new File(rootPath + "/data/txt/"));
    files.add(new File(rootPath + "/data/example/"));

    // setup for readLines()
    file = files.get(1).listFiles()[0];
    try {
      br = new BufferedReader(new FileReader(file));
      String line = br.readLine();
      while (line != null) {
        lines.add(line);
        line = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      imp.getLogger().error(e.getMessage(), e);
    }

    // setup for writeToFile()
    otherLines = Arrays.asList("line1", "line2", "line5");
    testOut = new File(rootPath + "/out/test.out");
    try {
      bw = new BufferedWriter(new FileWriter(testOut));
      for (String l : otherLines) {
        bw.write(l);
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      imp.getLogger().error(e.getMessage(), e);
    }

    // setup for process()
    grepOut = new File(rootPath + "/out/grep.out");
  }

  @Test
  public void gettersSetters() {
    String[] args = {"matchall", "./data", "./out/grep.out"};
    String two = args[1].substring(1);
    String three = args[2].substring(1);
    imp.setFields(args[0], args[1], args[2]);
    assertTrue("getters and setters test", (imp.getRegex().equals(args[0]) &&
                                          imp.getRootPath().equals(rootPath + two) &&
                                          imp.getOutFile().equals(rootPath + three)));
  }

  @Test
  public void listFiles() {
    assertTrue("listFiles test", imp.listFiles(rootPath + "/data/").equals(files));
  }

  @Test
  public void readLines() {
    assertTrue("readLines test", imp.readLines(file).equals(lines));
  }

  @Test
  public void containsPattern() {
    String l = "This line contains words\n";
    pattern = Pattern.compile("\\w");
    matcher = pattern.matcher(l);
    assertTrue("containsPattern test", matcher.find());
  }

  @Test
  public void writeToFile() {
    imp.setOutFile("./out/grep.out");
    try {
      imp.writeToFile(otherLines);
    } catch (IOException e) {
      imp.getLogger().error(e.getMessage(), e);
    }

    assertTrue("writeToFile test", imp.readLines(grepOut).equals(otherLines));
  }

  @Test
  public void process() {
    imp.setFields("\\bsample\\b", "./data", "./out/grep.out");
    imp.walk(rootPath + "/data/");

    try {
      bw = new BufferedWriter(new FileWriter(testOut));
      for (String l : imp.getMatchedLines()) {
        bw.write(l);
        bw.newLine();
      }
      bw.close();
      imp.setMatchedLines(new ArrayList<>());
      imp.process();
    } catch (IOException e) {
      imp.getLogger().error(e.getMessage(), e);
    }

    assertTrue("process test", imp.readLines(testOut).equals(imp.readLines(grepOut)));
  }
}