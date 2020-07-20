package ca.jrvs.apps.grep;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JavaGrepLambdaImpTest extends JavaGrepImpTest {

  JavaGrepLambdaImp impLambda = new JavaGrepLambdaImp();

  @Override
  public void listFiles() {
    assertTrue("listFiles test", impLambda.listFiles(rootPath + "data/").equals(files));
  }

  @Override
  public void readLines() {
    assertTrue("readLines test", impLambda.readLines(file).equals(lines));
  }

  @Override
  public void writeToFile() {
    assertTrue("writeToFile test", impLambda.readLines(outTest).equals(lines2));
  }

  @Override
  public void process() {
    impLambda.setFields("\\bsample\\b", "./data", "./grep.out");
    Stream<File> files = impLambda.listFiles(impLambda.getRootPath()).stream();
    Stream<File> flattenedFiles = files.flatMap(dir -> impLambda.listFiles(dir.getAbsolutePath()).stream());
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> impLambda.readLines(file).stream());
    flattenedLines.filter(line -> impLambda.containsPattern(line)).forEach(matchedLine -> impLambda.getMatchedLines().add(matchedLine));

    try {
      impLambda.writeToFile(impLambda.getMatchedLines());
      bw = new BufferedWriter(new FileWriter(outTest2));
      for (String l : impLambda.getMatchedLines()) {
        bw.write(l);
        bw.newLine();
      }
      bw.close();
      impLambda.setMatchedLines(new ArrayList<>());
      impLambda.process();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }

    assertTrue("process test", impLambda.readLines(outTest2).equals(impLambda.readLines(outTest3)));
  }
}