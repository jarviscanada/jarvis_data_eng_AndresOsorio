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
//    JavaGrepInt imp = new JavaGrepImp();

    // JavaGrepLambdaImp object to work with
    JavaGrepInt impLambda = new JavaGrepLambdaImp();

    // Set the object's fields according to the args given by user
    impLambda.setRegex(args[0]);
    impLambda.setRootPath(args[1]);
    impLambda.setOutFile(args[2]);

    // Look for lines matching 'regex' in the files under 'rootPath' and write those lines to 'outfile'
    // Handle IOException that has been propagated until here
    try {
      impLambda.process();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }
  }
}