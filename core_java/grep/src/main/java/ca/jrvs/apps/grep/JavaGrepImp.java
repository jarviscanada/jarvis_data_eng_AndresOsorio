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
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrepInt {

  // Logger object for reporting errors/debugging
  private final Logger logger = LoggerFactory.getLogger(JavaGrepInt.class);

  /*
      regex, rootPath and outFile are given as args by user;
      matchedLines acts as a placeholder for lines to be written to outFile
   */
  private final String projectPath = System.getProperty("user.dir");
  // Regular expression to match lines in file(s) against
  private String regex;
  // Absolute path of directory to traverse recursively
  private String rootPath;
  // File to write matched lines to
  private String outFile;
  // List containing the lines from files matching 'regex'
  private List<String> matchedLines = new ArrayList<>();

  // Setter; only for testing purposes
  public void setFields(String regex, String rootPath, String outFile) {
    setRegex(regex);
    setRootPath(rootPath);
    setOutFile(outFile);
  }

  // Traverse files under 'root' dir recursively;
  // if item is a dir then call walk recursively;
  // if item is a file then read it and store any
  // lines matching 'regex' in 'matchedLines'
  public void walk(String root) {
    List<File> files = listFiles(root);
    for (File f : files) {
      if (f.isDirectory()) {
        walk(f.getAbsolutePath());
      } else if (f.isFile()) {
        List<String> lines = readLines(f);
        for (String l : lines) {
          if (containsPattern(l)) {
            matchedLines.add(l);
          }
        }
      }
    }
  }

  // Look for lines matching 'regex' in the files under 'rootPath' and write those lines to 'outfile'
  @Override
  public void process() throws IOException {
    // Traverse 'rootPath' recursively
    walk(this.rootPath);

    // Write lines matching 'regex' to 'outFile'
    writeToFile(matchedLines);
  }

  /*
      File f points to the directory rootDir;
      f.listFiles() returns an array containing all Files (both dirs and files)
      under rootDir (each entry in the array that is a dir points to its own files)
   */
  @Override
  public List<File> listFiles(String rootDir) {
    File f = new File(rootDir);
    // If rootDir is not a dir then it has no files under it
    if (!f.isDirectory()) {
      return new ArrayList<>();
    }
    List<File> files = Arrays.asList(f.listFiles());
    return files;
  }

  @Override
  public Stream<File> listFiles2(String rootDir) {
    return null;
  }

  // Read 'inputFile' and return all its lines in a list
  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: argument must be file");
    }

    List<String> lines = new ArrayList<>();

    // Can't propagate IOException bc this method only
    // throws IllegalArgumentException; therefore handle here
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String s = br.readLine();
      while (s != null) {
        lines.add(s);
        s = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return lines;
  }

  @Override
  public Stream<String> readLines2(File inputFile) throws IllegalArgumentException {
    return null;
  }

  // Check if 'line' matches 'this.regex'
  @Override
  public boolean containsPattern(String line) {
    Pattern p = Pattern.compile(this.regex);
    Matcher m = p.matcher(line);
    return m.find();
  }

  // Write 'lines' to 'this.outFile'
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File out = new File(this.outFile);
    // Propagate IOException (new BufferedWriter and new FileWriter also throw the same exception)
    BufferedWriter bw = new BufferedWriter(new FileWriter(out));
    for (String s : lines) {
      bw.write(s);
      bw.newLine();
    }
    bw.close();
  }

  @Override
  public void writeToFile2(Stream<String> lines) throws IOException {
  }

  // Setters and getters
  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    String dirName = rootPath.substring(1);
    this.rootPath = this.projectPath + dirName;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    String fileName = outFile.substring(1);
    this.outFile = this.projectPath + fileName;
  }

  @Override
  public List<String> getMatchedLines() {
    return this.matchedLines;
  }

  @Override
  public Stream<String> getMatchedLines2() {
    return null;
  }

  @Override
  public void setMatchedLines(List<String> lines) {
    this.matchedLines = lines;
  }

  @Override
  public Logger getLogger() {
    return this.logger;
  }
}