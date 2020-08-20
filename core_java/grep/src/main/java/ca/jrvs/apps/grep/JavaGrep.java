package ca.jrvs.apps.grep;

import java.io.IOException;
import org.apache.log4j.BasicConfigurator;

public class JavaGrep {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outfile");
    }

    BasicConfigurator.configure();

    JavaGrepInt impLambda = new JavaGrepLambdaImp();

    impLambda.setRegex(args[0]);
    impLambda.setRootPath(args[1]);
    impLambda.setOutFile(args[2]);

    try {
      impLambda.process();
    } catch (IOException e) {
      impLambda.getLogger().error(e.getMessage(), e);
    }
  }
}