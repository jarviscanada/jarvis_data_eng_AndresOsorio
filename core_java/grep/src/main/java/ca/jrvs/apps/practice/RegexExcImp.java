package ca.jrvs.apps.practice;

import java.util.regex.*;

public class RegexExcImp implements RegexExc {

  Pattern pattern;
  /**
   * return true if pattern represented by regex matches input
   *
   * @param regex regex pattern
   * @param input input string to test
   * @return true if pattern represented by regex matches input
   */
  private boolean match(String regex, String input, boolean caseInsensitive) {
    if (caseInsensitive) {
      pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    } else {
      pattern = Pattern.compile(regex);
    }
    Matcher matcher = pattern.matcher(input);
    return matcher.find();
  }

  @Override
  public boolean matchJpeg(String filename) {
    return match(".+\\.\\b(jpg|jpeg)\\b", filename, true);
  }

  @Override
  public boolean matchIp(String ip) {
    return match("(([0-9]){1,3}\\.){3}[0-9]{1,3}", ip, false);
  }

  @Override
  public boolean isEmptyLine(String line) {
    return match("^\\s*$", line, false);
  }
}