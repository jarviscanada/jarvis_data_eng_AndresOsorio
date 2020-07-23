package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Functional implementation of the JavaGrep app; same functionality
 * but implemented using Java's Stream and Lambda APIs
 */
public class JavaGrepLambdaImp extends JavaGrepImp {

  private BufferedReader br;
  private BufferedWriter bw;

  public BufferedReader getBR() {
    return this.br;
  }

  public BufferedWriter getBW() {
    return this.bw;
  }


  /**
   * Each file contains its own list of files in 'files'; flatten this lists of files
   * same applies for the lines read from different diff sources.
   *
   * Closing a buffer also closes the streams related to it; therefore close at the end
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    Stream<File> files = listFilesStream(getRootPath());
    Stream<File> flattenedFiles = files.flatMap(dir -> listFilesStream(dir.getAbsolutePath()));
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> readLinesStream(file));
    Stream<String> matchedLines = flattenedLines.filter(line -> containsPattern(line));
    writeToFileStream(matchedLines);
    br.close();
    bw.close();
  }

  @Override
  public Stream<File> listFilesStream(String rootDir) {
    File f = new File(rootDir);
    if (!f.isDirectory()) {
      return Stream.empty();
    }
    return Arrays.stream(f.listFiles());
  }

  @Override
  public Stream<String> readLinesStream(File inputFile) throws IllegalArgumentException {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: argument must be file");
    }

    Stream<String> linesRead = Stream.empty();

    try {
      br = new BufferedReader(new FileReader(inputFile));
      linesRead = br.lines();
    } catch (IOException e) {
      getLogger().error(e.getMessage(), e);
    }
    return linesRead;
  }

  /**
   * Exceptions that are thrown by lambda tasks have to be handled inside
   *
   * @param lines
   * @throws IOException
   */
  @Override
  public void writeToFileStream(Stream<String> lines) throws IOException {
    File out = new File(getOutFile());
    bw = new BufferedWriter(new FileWriter(out));
    lines.forEach(line -> {
      try {
        bw.write(line);
        bw.newLine();
      } catch (IOException e) {
        getLogger().error(e.getMessage(), e);
      }
    });
  }
}