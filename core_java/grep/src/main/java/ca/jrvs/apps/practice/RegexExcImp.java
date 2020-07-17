package ca.jrvs.apps.practice;

import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  Pattern p;
  /**
   * return true if pattern represented by regex matches input
   * @param regex regex pattern
   * @param input input string to test
   * @return true if pattern represented by regex matches input
   */
  private boolean match(String regex, String input, boolean caseInsensitive) {
    if (caseInsensitive) {
      p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    } else {
      p = Pattern.compile(regex);
    }
    Matcher matcher = p.matcher(input);
    return matcher.find();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean matchJpeg(String filename) {
    // match any char occurring 1 or more times followed by a '.'
    // and the whole word 'jpg' or 'jpeg'
    return match(".+\\.\\b(jpg|jpeg)\\b", filename, true);
  }

  @Override
  public boolean matchIp(String ip) {
    // match any number 1 to 3 digits long followed by a '.'; do this match 3 times;
    // then match any number 1 to 3 digits long once
    return match("(([0-9]){1,3}\\.){3}[0-9]{1,3}", ip, false);
  }

  @Override
  public boolean isEmptyLine(String line) {
    // In multiline mode, ^ and $ also match the beginning and end of the line.
    // Therefore, in multiline mode this means:
    // match any number of whitespace chars (0 or more), which would match an empty line
    return match("^\\s*$", line, false);
  }
}