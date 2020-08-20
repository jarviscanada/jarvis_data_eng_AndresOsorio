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

  private final Logger logger = LoggerFactory.getLogger(JavaGrepInt.class);
  private final String projectPath = System.getProperty("user.dir");

  private String regex;
  private String rootPath;
  private String outFile;
  private List<String> matchedLines = new ArrayList<>();

  /**
   * Setter for testing purposes
   *
   * @param regex
   * @param rootPath
   * @param outFile
   */
  public void setFields(String regex, String rootPath, String outFile) {
    setRegex(regex);
    setRootPath(rootPath);
    setOutFile(outFile);
  }

  /**
   * Traverse files under 'root' dir recursively;
   * if item is a dir then call walk recursively;
   * if item is a file then read it and store any
   * lines matching 'regex' in 'matchedLines'
   *
   * @param root
   */
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

  @Override
  public void process() throws IOException {
    walk(this.rootPath);

    writeToFile(matchedLines);
  }

  /**
   * f.listFiles() returns an array containing all Files (both dirs and files)
   * under rootDir (each entry in the array that is a dir points to its own files)
   *
   * @param rootDir input directory
   * @return
   */
  @Override
  public List<File> listFiles(String rootDir) {
    File f = new File(rootDir);
    if (!f.isDirectory()) {
      return new ArrayList<>();
    }
    List<File> files = Arrays.asList(f.listFiles());
    return files;
  }

  @Override
  public Stream<File> listFilesStream(String rootDir) {
    return null;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: argument must be file");
    }

    List<String> lines = new ArrayList<>();

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
  public Stream<String> readLinesStream(File inputFile) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(this.regex);
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File out = new File(this.outFile);
    BufferedWriter bw = new BufferedWriter(new FileWriter(out));
    for (String s : lines) {
      bw.write(s);
      bw.newLine();
    }
    bw.close();
  }

  @Override
  public void writeToFileStream(Stream<String> lines) throws IOException {
  }

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
  public void setMatchedLines(List<String> lines) {
    this.matchedLines = lines;
  }

  @Override
  public Logger getLogger() {
    return this.logger;
  }
}