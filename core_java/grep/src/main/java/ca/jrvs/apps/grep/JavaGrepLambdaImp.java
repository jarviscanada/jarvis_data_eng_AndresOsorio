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
import java.util.stream.Stream;

/**
 * Functional implementation of the JavaGrep app; same functionality
 * but implemented using Java's Stream and Lambda APIs
 */
public class JavaGrepLambdaImp extends JavaGrepImp {

  @Override
  public void process() throws IOException {
    // Get the list of files as a stream
    Stream<File> files = listFiles(getRootPath()).stream();
    // If a file is a directory then it will contain its own list of files;
    // therefore we need to flatten the stream 'files' to get the actual regular files nested within all dirs in the list
    Stream<File> flattenedFiles = files.flatMap(dir -> listFiles(dir.getAbsolutePath()).stream());
    // We also need to access all the lines from all the 'flattenedFiles';
    // therefore also flatten the stream 'flattenedFiles' to get all the lines from each file
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> readLines(file).stream());
    // Go through all the lines in all the files and only add those that match 'regex' to 'matchedLines'
    flattenedLines.filter(line -> containsPattern(line)).forEach(matchedLine -> getMatchedLines().add(matchedLine));
    // Write all the matche lines to the 'outputFile'
    writeToFile(getMatchedLines());
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File f = new File(rootDir);
    // If rootDir is not a dir then it has no files under it
    if (!f.isDirectory()) {
      return new ArrayList<>();
    }
    List<File> files = new ArrayList<>();
    Arrays.stream(f.listFiles()).forEach(file -> files.add(file));
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: argument must be file");
    }

    List<String> readLines = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      // returns a Stream<String> containing the lines in the buffer;
      // use this stream to populate 'readLines'
      br.lines().forEach(line -> readLines.add(line));
      br.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return readLines;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File out = new File(getOutFile());
    // Propagate IOException from BufferedWriter and FileWriter
    BufferedWriter bw = new BufferedWriter(new FileWriter(out));
    lines.stream().forEach(line -> {
      // Exceptions that are thrown by lambda tasks have to be handled inside
      try {
        bw.write(line);
        bw.newLine();
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
      }
    });
    bw.close();
  }
}