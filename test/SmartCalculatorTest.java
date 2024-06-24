
import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the SmartCalculator class.
 */
public class SmartCalculatorTest extends ACalculatorTest {

  /**
   * Helper method to create a new type of Calculator for tests.
   *
   * @return a SmartCalculator for tests.
   */
  protected SmartCalculator createCalc() {
    return new SmartCalculator();
  }

  /**
   * Tests for multiple equal to signs.
   */
  @Test
  public void testMultipleEquals() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('+');
    assertEquals("5+", copy.getResult());
    copy = copy.input('2');
    assertEquals("5+2", copy.getResult());
    copy = copy.input('=');
    assertEquals("7", copy.getResult());
    copy = copy.input('=');
    assertEquals("9", copy.getResult());
    copy = copy.input('=');
    assertEquals("11", copy.getResult());
    copy = copy.input('=');
    assertEquals("13", copy.getResult());
  }

  /**
   * Tests for multiple equal to signs with larger numbers.
   */
  @Test
  public void testMultipleEqualsLargerNumbers() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    copy = copy.input('2');
    assertEquals("32+2", copy.getResult());
    copy = copy.input('4');
    assertEquals("32+24", copy.getResult());
    copy = copy.input('=');
    assertEquals("56", copy.getResult());
    copy = copy.input('=');
    assertEquals("80", copy.getResult());
    copy = copy.input('=');
    assertEquals("104", copy.getResult());
  }

  /**
   * Tests for multiple equal to signs in a subtraction equation.
   */
  @Test
  public void testMultipleEqualsSubtraction() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('-');
    assertEquals("32-", copy.getResult());
    copy = copy.input('2');
    assertEquals("32-2", copy.getResult());
    copy = copy.input('4');
    assertEquals("32-24", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
    copy = copy.input('=');
    assertEquals("-16", copy.getResult());
    copy = copy.input('=');
    assertEquals("-40", copy.getResult());
  }

  /**
   * Tests for multiple equal to signs in a multiplication equation.
   */
  @Test
  public void testMultipleEqualsMultiplication() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('*');
    assertEquals("5*", copy.getResult());
    copy = copy.input('2');
    assertEquals("5*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("10", copy.getResult());
    copy = copy.input('=');
    assertEquals("20", copy.getResult());
    copy = copy.input('=');
    assertEquals("40", copy.getResult());
    copy = copy.input('=');
    assertEquals("80", copy.getResult());
  }

  /**
   * Tests for multiple two operators resulting in a subtraction equation.
   */
  @Test
  public void testTwoOperatorsSubtraction() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    copy = copy.input('-');
    assertEquals("32-", copy.getResult());
    copy = copy.input('2');
    assertEquals("32-2", copy.getResult());
    copy = copy.input('4');
    assertEquals("32-24", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
  }

  /**
   * Tests for multiple operators resulting in a subtraction equation.
   */
  @Test
  public void testMultipleOperatorsSubtraction() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    copy = copy.input('-');
    assertEquals("32-", copy.getResult());
    copy = copy.input('*');
    assertEquals("32*", copy.getResult());
    copy = copy.input('2');
    assertEquals("32*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("64", copy.getResult());
  }

  /**
   * Tests for multiple two operators.
   */
  @Test
  public void testMultipleOperators() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('-');
    assertEquals("32-", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    copy = copy.input('2');
    assertEquals("32+2", copy.getResult());
    copy = copy.input('4');
    assertEquals("32+24", copy.getResult());
    copy = copy.input('=');
    assertEquals("56", copy.getResult());
  }

  /**
   * Tests for multiple operators resulting in a multiplication equation.
   */
  @Test
  public void testMultipleOperatorsMultiplication() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('4');
    assertEquals("4", copy.getResult());
    copy = copy.input('+');
    assertEquals("4+", copy.getResult());
    copy = copy.input('*');
    assertEquals("4*", copy.getResult());
    copy = copy.input('2');
    assertEquals("4*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
  }

  /**
   * Tests for an equation starting with +.
   */
  @Test
  public void testBeginsWithOperator() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('+');
    assertEquals("", copy.getResult());
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('-');
    assertEquals("32-", copy.getResult());
    copy = copy.input('2');
    assertEquals("32-2", copy.getResult());
    copy = copy.input('4');
    assertEquals("32-24", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
  }

  /**
   * Tests for no operator.
   */
  @Test
  public void testNoOperator() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    try {
      copy = copy.input('=');
    } catch (Exception e) {
      assertEquals("Missing inputs", e.getMessage());
    }
  }

  /**
   * Tests for a missing an operand2.
   */
  @Test
  public void testMissingOperand2() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('+');
    assertEquals("32+", copy.getResult());
    copy = copy.input('=');
    assertEquals("64", copy.getResult());
    copy = copy.input('=');
    assertEquals("96", copy.getResult());
    copy = copy.input('=');
    assertEquals("128", copy.getResult());
  }

  /**
   * Tests for missing an operand2 and an operator.
   */
  @Test
  public void testMissingOperand2andOperator() {
    Calculator copy = new SmartCalculator();
    copy = copy.input('3');
    assertEquals("3", copy.getResult());
    copy = copy.input('2');
    assertEquals("32", copy.getResult());
    copy = copy.input('=');
    assertEquals("32", copy.getResult());
  }
}
