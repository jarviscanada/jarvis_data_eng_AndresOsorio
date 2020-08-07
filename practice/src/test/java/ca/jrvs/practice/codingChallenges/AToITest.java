package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class AToITest {

  @Test
  public void convertJavaApi() {
    assertTrue(AToI.convertJavaApi("   -234") == -234);
    assertTrue(AToI.convertJavaApi("+-3245") == 0);
    assertTrue(AToI.convertJavaApi("sometext") == 0);
    assertTrue(AToI.convertJavaApi("-98147483648") == -2147483648);
    assertTrue(AToI.convertJavaApi("98147483648") == 2147483647);
  }

  @Test
  public void convert() {
    assertTrue(AToI.convert("   -234") == -234);
    assertTrue(AToI.convert("+-3245") == 0);
    assertTrue(AToI.convert("sometext") == 0);
    assertTrue(AToI.convert("-98147483648") == -2147483648);
    assertTrue(AToI.convert("98147483648") == 2147483647);
  }
}