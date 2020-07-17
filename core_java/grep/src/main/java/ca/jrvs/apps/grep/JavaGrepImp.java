package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrepInt {

  // Logger object for reporting errors/debugging
  final Logger logger = LoggerFactory.getLogger(JavaGrepInt.class);

  /*
      regex, rootPath and outFile are given as args by user;
      matchedLines acts as a placeholder for lines to be written to outFile
   */
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
    this.regex = regex;
    this.rootPath = rootPath;
    this.outFile = outFile;
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
    List<File> files = Arrays.asList(f.listFiles());
    if (files != null) {
      return files;
    }
    return new ArrayList<>();
  }

  // Read 'inputFile' and return all its lines in a list
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
    } catch (FileNotFoundException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return lines;
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
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(out));
      for (String s : lines) {
        bw.write(s);
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new IOException("ERROR: error writing to output file");
    }
  }

  /*
   * Setters and getters
   * It is assumed that the 'rootPath' provided by user is under /home/centos/dev/jarvis_data_eng_Andres/core_java/grep/
   * It is assumed that the 'outFile' provided by user is under /home/centos/dev/jarvis_data_eng_Andres/core_java/grep/out/
   */
  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    String dirName = rootPath.substring(2);
    this.rootPath = "/home/centos/dev/jarvis_data_eng_Andres/core_java/grep/" + dirName;
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
    String fileName = outFile.substring(2);
    this.outFile = "/home/centos/dev/jarvis_data_eng_Andres/core_java/grep/out/" + fileName;
  }
}