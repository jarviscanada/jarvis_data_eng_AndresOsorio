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

public class JavaGrepLambdaImp extends JavaGrepImp {

//  public Stream<File> rootFilesStream() {
//    return listFiles(getRootPath()).stream();
//  }
//
//  public void fileAction(Stream<File> s) {
//    s.forEach(f -> readLines(f)
//        .stream()
//        .filter(l -> containsPattern(l))
//        .forEach(l -> getMatchedLines()
//            .add(l))
//    );
//  }
//
//  @Override
//  public void process() throws IOException {
//    listFiles(getRootPath())
//        .stream()
//        .filter(file -> file.isFile())
//        .forEach(file -> readLines(file)
//            .stream()
//            .filter(line -> containsPattern(line))
//            .forEach(line -> getMatchedLines().add(line))
//        );
//  }

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