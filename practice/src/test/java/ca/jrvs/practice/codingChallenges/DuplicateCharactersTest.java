package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class DuplicateCharactersTest {

  String s1 = "A black cat";
  String s2 = "black burbon and others";
  String s3 = "";
  Set<Character> r = new HashSet<>();

  @Test
  public void findDuplicates() {
    r.add('c');
    r.add('a');
    assertTrue(r.equals(DuplicateCharacters.findDuplicates(s1)));

    r.clear();
    r.add('b');
    r.add('a');
    r.add('n');
    r.add('o');
    r.add('r');
    assertTrue(r.equals(DuplicateCharacters.findDuplicates(s2)));

    assertTrue(DuplicateCharacters.findDuplicates(s3).isEmpty());
  }
}