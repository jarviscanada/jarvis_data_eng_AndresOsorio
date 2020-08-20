package ca.jrvs.apps.practice;

public interface RegexExc {

  /**
   * return true if filename extension is jpg or jpeg (case insensitive)
   * @param filename  input string to test
   * @return            true if filename extension is jpg or jpeg (case insensitive), false otherwise
   */
  public boolean matchJpeg(String filename);

  /**
   * return true if ip is valid
   * to simplify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
   * @param ip  ip string to be tested
   * @return    true if ip is valid, false otherwise
   */
  public boolean matchIp(String ip);

  /**
   * return true if line is empty (e.g. empty, white space, tabs, etc..)
   * @param line  string made up of white spaces
   * @return      true if line is empty, false otherwise
   */
  public boolean isEmptyLine(String line);
}