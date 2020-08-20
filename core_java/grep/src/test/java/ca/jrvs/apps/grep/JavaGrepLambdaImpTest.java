package ca.jrvs.apps.grep;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImpTest extends JavaGrepImpTest {

  JavaGrepLambdaImp impLambda = new JavaGrepLambdaImp();

  @Override
  public void listFiles() {
    assertTrue("listFiles test",
        impLambda.listFilesStream(rootPath + "/data/").collect(Collectors.toList()).equals(files));
  }

  @Override
  public void readLines() {
    assertTrue("readLines test",
        impLambda.readLinesStream(file).collect(Collectors.toList()).equals(lines));
  }

  @Override
  public void writeToFile() {
    impLambda.setOutFile("./out/grep.out");
    try {
      impLambda.writeToFileStream(otherLines.stream());
      impLambda.getBW().close();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }

    assertTrue("writeToFile test",
        impLambda.readLinesStream(grepOut).collect(Collectors.toList()).equals(otherLines));
  }

  @Override
  public void process() {
    impLambda.setFields("\\bsample\\b", "./data", "./out/grep.out");
    Stream<File> files = impLambda.listFilesStream(impLambda.getRootPath());
    Stream<File> flattenedFiles = files.flatMap(dir -> impLambda.listFilesStream(dir.getAbsolutePath()));
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> impLambda.readLinesStream(file));
    Stream<String> matchedLines = flattenedLines.filter(line -> impLambda.containsPattern(line));

    try {
      bw = new BufferedWriter(new FileWriter(testOut));
      matchedLines.forEach(l -> {
        try {
          bw.write(l);
          bw.newLine();
        } catch (IOException e) {
          impLambda.getLogger().error(e.getMessage(), e);
        }
      });
      bw.close();
      impLambda.process();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }

    assertTrue("process test",
        impLambda.readLinesStream(testOut).collect(Collectors.toList())
            .equals(impLambda.readLinesStream(grepOut).collect(Collectors.toList())));
  }
}