package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;

public interface JavaGrepInt {

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  List<File> listFiles(String rootDir);

  /**
   * Traverse a given dir and return all files as a stream
   *
   * @param rootDir
   * @return Stream<File>
   */
  Stream<File> listFilesStream(String rootDir);

  /**
   * Read a file and return all the lines
   * Explore: {@link java.io.FileReader}, {@link java.io.BufferedReader}, and character encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  List<String> readLines(File inputFile) throws IllegalArgumentException;

  /**
   * Read a file and return all the lines as a Stream
   * `readLines()` returns a List<String> containing all the lines from a file;
   * if this file is bigger than the app's declared max heap-size then the app will
   * crash bc there's not enough memory to even hold the data from the file in List<String> returned.
   *
   * Returning a Stream instead of a Collection and using BufferReader solves the problem;
   * BufferReader reads the file in chunks, as opposed to all at once;
   * One of the key differences between a Stream and a Collection in Java:
   *    while Collections represent a set of data stored entirely in memory, Streams are simply storage-agnostic series of data.
   *    Streaming through large datasets allows only the item(s) being currently manipulated to be held in memory,
   *    as opposed to the complete collection.
   *
   * @param inputFile
   * @return Stream<String>
   * @throws IllegalArgumentException
   */
  Stream<String> readLinesStream(File inputFile) throws IllegalArgumentException;

  /**
   * Check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file
   * Explore: {@link java.io.FileOutputStream}, {@link java.io.OutputStreamWriter}, and {@link java.io.BufferedWriter}
   *
   * @param lines matched lines
   * @throws IOException if write failed
   */
  void writeToFile(List<String> lines) throws IOException;

  /**
   * Write lines to a file using stream
   *
   * @param lines
   * @throws IOException
   */
  void writeToFileStream(Stream<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);

  List<String> getMatchedLines();

  void setMatchedLines(List<String> lines);

  void setFields(String regex, String rootPath, String outFile);

  Logger getLogger();
}