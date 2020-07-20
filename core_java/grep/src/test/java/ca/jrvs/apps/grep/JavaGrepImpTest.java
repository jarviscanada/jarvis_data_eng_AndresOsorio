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

public class JavaGrepImpTest {

  static final String rootPath = "/home/centos/dev/jarvis_data_eng_Andres/core_java/grep/";

  static List<File> files;
  static List<String> lines, lines2;
  static File file, outTest, outTest2, outTest3;
  static Pattern p;
  static Matcher m;
  static BufferedReader br;
  static BufferedWriter bw;

  /*
      Order of execution:
        @BeforeClass
        Constructor of class under test
        @Before
        @Test
   */

  static JavaGrepImp imp = new JavaGrepImp();

  @BeforeClass
  public static void setUp() {
    files = new ArrayList<>();
    lines = new ArrayList<>();

    files.add(new File(rootPath + "data/txt/"));
    files.add(new File(rootPath + "data/example/"));

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
    lines2 = Arrays.asList("line1", "line2", "line4");
    outTest = new File(rootPath + "out/test.out");
    try {
      bw = new BufferedWriter(new FileWriter(outTest));
      for (String l : lines2) {
        bw.write(l);
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      imp.getLogger().error(e.getMessage(), e);
    }

    // setup for process()
    outTest2 = new File(rootPath + "out/grepTest.out");
    outTest3 = new File(rootPath + "out/grep.out");
  }

  @Test
  public void gettersSetters() {
    String[] args = {"matchall", "./data", "./someting"};
    String two = args[1].substring(2);
    String three = args[2].substring(2);
    imp.setFields(args[0], args[1], args[2]);
    assertTrue("getters and setters test", (imp.getRegex().equals(args[0]) &&
                                          imp.getRootPath().equals(rootPath + two) &&
                                          imp.getOutFile().equals(rootPath + "out/" + three)));
  }

  @Test
  public void listFiles() {
    assertTrue("listFiles test", imp.listFiles(rootPath + "data/").equals(files));
  }

  @Test
  public void readLines() {
    assertTrue("readLines test", imp.readLines(file).equals(lines));
  }

  @Test
  public void containsPattern() {
    String l = "This line contains words\n";
    p = Pattern.compile("\\w");
    m = p.matcher(l);
    assertTrue("containsPattern test", m.find());
  }

  @Test
  public void writeToFile() {
    assertTrue("writeToFile test", imp.readLines(outTest).equals(lines2));
  }

  @Test
  public void process() {
    imp.setFields("\\bsample\\b", "./data", "./grep.out");
    imp.walk(rootPath + "data/");

    try {
      bw = new BufferedWriter(new FileWriter(outTest2));
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

    assertTrue("process test", imp.readLines(outTest2).equals(imp.readLines(outTest3)));
  }
}