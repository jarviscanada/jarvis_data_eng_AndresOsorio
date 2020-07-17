package ca.jrvs.apps.grep;

import org.junit.*;
import static org.junit.Assert.*;

public class JavaGrepImpTest {

  JavaGrepImp imp;

  @Before
  public void setUp() {
    imp = new JavaGrepImp();
  }

  @Test
  public void argsOk() {
    String[] args = {"matchall", "/root/something", "/out/file"};
    imp.setFields(args[0], args[1], args[2]);
    assertTrue("correct args", (imp.getRegex() == args[0] &&
                                          imp.getRootPath() == args[1] &&
                                          imp.getOutFile() == args[2]));
  }
}