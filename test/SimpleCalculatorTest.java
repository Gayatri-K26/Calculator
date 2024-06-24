
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the SimpleCalculator class.
 */
public class SimpleCalculatorTest extends ACalculatorTest {

  /**
   * Helper method to create a new type of Calculator for tests.
   *
   * @return a SimpleCalculator for tests.
   */
  protected SimpleCalculator createCalc() {
    return new SimpleCalculator();
  }

  /**
   * Tests for the same value after inputting multiple equals signs.
   */
  @Test
  public void testMultipleEquals() {
    Calculator copy = new SimpleCalculator();
    copy = copy.input('7');
    assertEquals("7", copy.getResult());
    copy = copy.input('-');
    assertEquals("7-", copy.getResult());
    copy = copy.input('3');
    assertEquals("7-3", copy.getResult());
    copy = copy.input('=');
    assertEquals("4", copy.getResult());
    copy = copy.input('=');
    assertEquals("4", copy.getResult());
    copy = copy.input('=');
    assertEquals("4", copy.getResult());
  }


  /**
   * Tests for an invalid sequence with a missing operand 2.
   */
  @Test
  public void testInvalidSequenceMissingOperand2() {
    Calculator copy = new SimpleCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    try {
      copy = copy.input('=');
    } catch (Exception e) {
      assertEquals("Missing Inputs", e.getMessage());
    }
  }

  /**
   * Tests for an invalid sequence missing the first operand.
   */
  @Test
  public void testInvalidFirstInputOperator() {
    Calculator copy = new SimpleCalculator();
    try {
      copy = copy.input('+');
    } catch (Exception e) {
      assertEquals("Missing Inputs", e.getMessage());
    }
  }

  /**
   * Tests for an invalid sequence inputting two operators.
   */
  @Test
  public void testInvalid2ndInput() {
    Calculator copy = new SimpleCalculator();
    copy = copy.input('2');
    assertEquals("2", copy.getResult());
    copy = copy.input('+');
    assertEquals("2+", copy.getResult());
    try {
      copy = copy.input('*');
    } catch (Exception e) {
      assertEquals("Operator already exists", e.getMessage());
    }
  }

  /**
   * Tests for a sequence with two operators.
   */
  @Test
  public void test2Operators() {
    Calculator copy = new SimpleCalculator();
    copy = copy.input('2');
    assertEquals("2", copy.getResult());
    copy = copy.input('+');
    assertEquals("2+", copy.getResult());
    try {
      copy = copy.input('-');
    } catch (Exception e) {
      assertEquals("Operator already exists", e.getMessage());
    }
    assertEquals("2+", copy.getResult());
  }
}
