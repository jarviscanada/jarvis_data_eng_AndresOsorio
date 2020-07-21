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


  @Override
  public void process() throws IOException {
    // Get the list of files as a stream
    Stream<File> files = listFiles2(getRootPath());
    // If a file is a directory then it will contain its own list of files;
    // therefore we need to flatten the stream 'files' to get the actual regular files nested within all dirs in the list
    Stream<File> flattenedFiles = files.flatMap(dir -> listFiles2(dir.getAbsolutePath()));
    // We also need to access all the lines from all the 'flattenedFiles';
    // therefore also flatten the stream 'flattenedFiles' to get all the lines from each file
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> readLines2(file));
    // Go through all the lines in all the files and only add those that match 'regex' to 'matchedLines'
    Stream<String> matchedLines2 = flattenedLines.filter(line -> containsPattern(line));
    // Write all the matched lines to the 'outputFile'
    writeToFile2(matchedLines2);
    // The stream returned by 'readLines2' is the stream returned by the BufferedReader 'br';
    // when you close a BufferedReader, all its underlying streams and writers (FileWriter, any Stream returned by that BufferedReader)
    // are closed as well; therefore if we close 'br' within the 'readLines2' method, we also close the Stream<String> it returns;
    // since streams are just pipelines and not actual datastructures, we can only pass them around as references
    // (not possible to create a deep copy of a stream); therefore if we close 'br' in 'readLines2', we also close the stream it returns
    // and any subsequent operations on that returned reference to the stream will fail.
    // Any BufferedReader and/or BufferedWriter should be closed after the streams manipulated by them are no longer needed.
    br.close();
    bw.close();
  }

  @Override
  public Stream<File> listFiles2(String rootDir) {
    File f = new File(rootDir);
    // If rootDir is not a dir then it has no files under it
    if (!f.isDirectory()) {
      return Stream.empty();
    }
    return Arrays.stream(f.listFiles());
  }

  @Override
  public Stream<String> readLines2(File inputFile) throws IllegalArgumentException {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: argument must be file");
    }

    Stream<String> linesRead = Stream.empty();

    try {
      br = new BufferedReader(new FileReader(inputFile));
      // returns a Stream<String> containing the lines in the buffer;
      // use this stream to populate 'readLines'
      linesRead = br.lines();
      // See 'process()' for closing instructions
    } catch (IOException e) {
      getLogger().error(e.getMessage(), e);
    }
    return linesRead;
  }

  @Override
  public void writeToFile2(Stream<String> lines) throws IOException {
    File out = new File(getOutFile());
    // Propagate IOException from BufferedWriter and FileWriter
    bw = new BufferedWriter(new FileWriter(out));
    lines.forEach(line -> {
      // Exceptions that are thrown by lambda tasks have to be handled inside
      try {
        bw.write(line);
        bw.newLine();
      } catch (IOException e) {
        getLogger().error(e.getMessage(), e);
      }
    });
    // See 'process()' for closing instructions
  }
}