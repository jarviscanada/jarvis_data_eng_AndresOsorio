package ca.jrvs.apps.grep;

import java.io.IOException;
import org.apache.log4j.BasicConfigurator;

public class JavaGrep {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outfile");
    }

    // Use default logger config
    BasicConfigurator.configure();

    // JavaGrepImp object to work with
    JavaGrepImp imp = new JavaGrepImp();

    // Set the object's fields according to the args given by user
    imp.setRegex(args[0]);
    imp.setRootPath(args[1]);
    imp.setOutFile(args[2]);

    // Look for lines matching 'regex' in the files under 'rootPath' and write those lines to 'outfile'
    try {
      imp.process();
    } catch (IOException e) {
      imp.logger.error(e.getMessage(), e);
    }
  }
}