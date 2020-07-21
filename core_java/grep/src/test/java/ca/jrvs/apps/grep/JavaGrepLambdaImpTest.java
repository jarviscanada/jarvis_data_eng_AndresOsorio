package ca.jrvs.apps.grep;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImpTest extends JavaGrepImpTest {

  JavaGrepLambdaImp impLambda = new JavaGrepLambdaImp();

  @Override
  public void listFiles() {
    assertTrue("listFiles test",
        impLambda.listFiles2(rootPath + "/data/").collect(Collectors.toList()).equals(files));
  }

  @Override
  public void readLines() {
    assertTrue("readLines test",
        impLambda.readLines2(file).collect(Collectors.toList()).equals(lines));
  }

  @Override
  public void writeToFile() {
    impLambda.setOutFile("./out/grep.out");
    try {
      impLambda.writeToFile2(lines2.stream());
      impLambda.getBW().close();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }

    assertTrue("writeToFile test",
        impLambda.readLines2(grepOut).collect(Collectors.toList()).equals(lines2));
  }

  @Override
  public void process() {
    impLambda.setFields("\\bsample\\b", "./data", "./out/grep.out");
    Stream<File> files = impLambda.listFiles2(impLambda.getRootPath());
    Stream<File> flattenedFiles = files.flatMap(dir -> impLambda.listFiles2(dir.getAbsolutePath()));
    Stream<String> flattenedLines = flattenedFiles.flatMap(file -> impLambda.readLines2(file));
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
        impLambda.readLines2(testOut).collect(Collectors.toList())
            .equals(impLambda.readLines2(grepOut).collect(Collectors.toList())));
  }
}