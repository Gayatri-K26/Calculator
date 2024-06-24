import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;

import static org.junit.Assert.assertEquals;

/**
 * Represents the abstract class for tests that test Calculator objects.
 */
public abstract class ACalculatorTest {
  protected Calculator calc1;
  protected Calculator calc2;

  /**
   * Helper method to create a new type of Calculator for tests.
   *
   * @return a new type of Calculator (either a SimpleCalculator or a SmartCalculator
   */
  protected abstract Calculator createCalc();

  /**
   * Represents the setup for tests.
   */
  @Before
  public void setUp() throws Exception {
    calc1 = this.createCalc();
    calc2 = this.createCalc();
  }

  /**
   * Tests the constructor.
   */
  @Test
  public void testConstructor() {
    assertEquals("", calc1.getResult());
    assertEquals("", calc2.getResult());
  }

  /**
   * Tests for an empty string when no inputs are given.
   */
  @Test
  public void testNoArguments() {
    assertEquals("", calc1.getResult());
  }

  /**
   * Tests for the expression 5 + 2 = 7.
   */
  @Test
  public void testSimpleAddition() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('+');
    assertEquals("5+", copy.getResult());
    copy = copy.input('2');
    assertEquals("5+2", copy.getResult());
    copy = copy.input('=');
    assertEquals("7", copy.getResult());
  }

  /**
   * Tests for the expression 5 - 2 = 3.
   */
  @Test
  public void testSimpleSubtraction() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('-');
    assertEquals("5-", copy.getResult());
    copy = copy.input('2');
    assertEquals("5-2", copy.getResult());
    copy = copy.input('=');
    assertEquals("3", copy.getResult());
  }

  /**
   * Tests for the expression 5 * 2 = 10.
   */
  @Test
  public void testSimpleMultiplication() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('*');
    assertEquals("5*", copy.getResult());
    copy = copy.input('2');
    assertEquals("5*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("10", copy.getResult());
  }

  /**
   * Tests for clearing the calculator.
   */
  @Test
  public void testResultClear() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('+');
    assertEquals("5+", copy.getResult());
    copy = copy.input('2');
    assertEquals("5+2", copy.getResult());
    copy = copy.input('=');
    assertEquals("7", copy.getResult());
    copy = copy.input('C');
    assertEquals("", copy.getResult());
  }

  /**
   * Tests for negative results.
   */
  @Test
  public void testNegativeResult() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('-');
    assertEquals("5-", copy.getResult());
    copy = copy.input('9');
    assertEquals("5-9", copy.getResult());
    copy = copy.input('+');
    assertEquals("-4+", copy.getResult());
    copy = copy.input('1');
    assertEquals("-4+1", copy.getResult());
    copy = copy.input('=');
    assertEquals("-3", copy.getResult());
  }

  /**
   * Tests for a valid expression with multiple operands and operators.
   */
  @Test
  public void testMultipleOperandsAndOperators() {
    Calculator copy = this.createCalc();
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
    copy = copy.input('-');
    assertEquals("56-", copy.getResult());
    copy = copy.input('1');
    assertEquals("56-1", copy.getResult());
    copy = copy.input('0');
    assertEquals("56-10", copy.getResult());
    copy = copy.input('*');
    assertEquals("46*", copy.getResult());
    copy = copy.input('2');
    assertEquals("46*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("92", copy.getResult());
  }

  /**
   * Tests whether inputting the max value results in the most recent valid value.
   */
  @Test
  public void testInputMaxValue() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    try {
      copy = copy.input('8');
    } catch (Exception e) {
      assertEquals("Input Overflows", e.getMessage());
    }
    assertEquals("214748364", copy.getResult());
  }

  /**
   * Tests for a zero when inputting an expression that overflows.
   */
  @Test
  public void testPositiveOutflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    copy = copy.input('7');
    assertEquals("2147483647", copy.getResult());
    copy = copy.input('+');
    assertEquals("2147483647+", copy.getResult());
    copy = copy.input('1');
    assertEquals("2147483647+1", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

  /**
   * Tests for adding given an operand that overflows.
   */
  @Test
  public void testAddingWithOverflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('3');
    try {
      for (int i = 0; i < 10; i++) {
        copy = copy.input('0');
      }
    } catch (Exception e) {
      assertEquals("Input Overflows", e.getMessage());
    }
    assertEquals("300000000", copy.getResult());
    copy = copy.input('+');
    assertEquals("300000000+", copy.getResult());
    copy = copy.input('1');
    assertEquals("300000000+1", copy.getResult());
    copy = copy.input('=');
    assertEquals("300000001", copy.getResult());
  }

  /**
   * Tests for subtracting given an operand that overflows.
   */
  @Test
  public void testSubtractingWithOverflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('7');
    assertEquals("7", copy.getResult());
    copy = copy.input('-');
    assertEquals("7-", copy.getResult());
    copy = copy.input('3');
    try {
      for (int i = 0; i < 10; i++) {
        copy = copy.input('0');
      }
    } catch (Exception e) {
      assertEquals("Input Overflows", e.getMessage());
    }
    assertEquals("7-300000000", copy.getResult());
    copy = copy.input('=');
    assertEquals("-299999993", copy.getResult());
  }

  /**
   * Tests for a zero when multiplying max value by 2 to get an overflow.
   */
  @Test
  public void testMultiplyOverflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    copy = copy.input('7');
    assertEquals("2147483647", copy.getResult());
    copy = copy.input('*');
    assertEquals("2147483647*", copy.getResult());
    copy = copy.input('2');
    assertEquals("2147483647*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

  /**
   * Tests for a negative overflow value.
   */
  @Test
  public void testNegativeOverflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('1');
    assertEquals("1", copy.getResult());
    copy = copy.input('-');
    assertEquals("1-", copy.getResult());
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    copy = copy.input('7');
    assertEquals("1-2147483647", copy.getResult());
    copy = copy.input('-');
    assertEquals("-2147483646-", copy.getResult());
    copy = copy.input('3');
    assertEquals("-2147483646-3", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

  /**
   * Tests for negative overflow in the output.
   */
  @Test
  public void testMultiplyNegativeOverflow() {
    Calculator copy = this.createCalc();
    copy = copy.input('1');
    assertEquals("1", copy.getResult());
    copy = copy.input('-');
    assertEquals("1-", copy.getResult());
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    copy = copy.input('7');
    assertEquals("1-2147483647", copy.getResult());
    copy = copy.input('*');
    assertEquals("-2147483646*", copy.getResult());
    copy = copy.input('2');
    assertEquals("-2147483646*2", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

  /**
   * Tests for a+b-c, where a+b results in overflow.
   */
  @Test
  public void testOutflowAndSubtract() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    copy = copy.input('1');
    copy = copy.input('4');
    copy = copy.input('7');
    copy = copy.input('4');
    copy = copy.input('8');
    copy = copy.input('3');
    copy = copy.input('6');
    copy = copy.input('4');
    copy = copy.input('7');
    assertEquals("2147483647", copy.getResult());
    copy = copy.input('+');
    assertEquals("2147483647+", copy.getResult());
    copy = copy.input('1');
    assertEquals("2147483647+1", copy.getResult());
    copy = copy.input('-');
    assertEquals("0-", copy.getResult());
    copy = copy.input('9');
    assertEquals("0-9", copy.getResult());
    copy = copy.input('=');
    assertEquals("-9", copy.getResult());
  }

  /**
   * Tests for an invalid input - equals sign.
   */
  @Test
  public void testInvalidFirstInputEquals() {
    Calculator copy = this.createCalc();
    try {
      copy = copy.input('=');
    } catch (Exception e) {
      assertEquals("Missing Inputs", e.getMessage());
    }
  }

  /**
   * Tests for an invalid input - letter.
   */
  @Test
  public void testInvalidFirstInputLetter() {
    Calculator copy = this.createCalc();
    try {
      copy = copy.input('a');
    } catch (Exception e) {
      assertEquals("Invalid Character", e.getMessage());
    }
  }

  /**
   * Tests for an invalid input - special character.
   */
  @Test
  public void testInvalidFirstInputSpecialCharacter() {
    Calculator copy = this.createCalc();
    try {
      copy = copy.input('!');
    } catch (Exception e) {
      assertEquals("Invalid Character", e.getMessage());
    }
  }

  /**
   * Tests for a new operator after result.
   */
  @Test
  public void testOperatorOperandAfterResult() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    assertEquals("2", copy.getResult());
    copy = copy.input('+');
    assertEquals("2+", copy.getResult());
    copy = copy.input('3');
    assertEquals("2+3", copy.getResult());
    copy = copy.input('=');
    assertEquals("5", copy.getResult());
    copy = copy.input('+');
    assertEquals("5+", copy.getResult());
    copy = copy.input('3');
    assertEquals("5+3", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
  }

  /**
   * Tests for a new operand after result.
   */
  @Test
  public void testNewOperandAfterEquals() {
    Calculator copy = this.createCalc();
    copy = copy.input('2');
    assertEquals("2", copy.getResult());
    copy = copy.input('+');
    assertEquals("2+", copy.getResult());
    copy = copy.input('3');
    assertEquals("2+3", copy.getResult());
    copy = copy.input('=');
    assertEquals("5", copy.getResult());
    copy = copy.input('6');
    assertEquals("6", copy.getResult());
    copy = copy.input('+');
    assertEquals("6+", copy.getResult());
    copy = copy.input('2');
    assertEquals("6+2", copy.getResult());
    copy = copy.input('=');
    assertEquals("8", copy.getResult());
  }

  /**
   * Tests for a sequence with just zeroes.
   */
  @Test
  public void testZeroInput() {
    Calculator copy = this.createCalc();
    copy = copy.input('0');
    assertEquals("0", copy.getResult());
    copy = copy.input('+');
    assertEquals("0+", copy.getResult());
    copy = copy.input('0');
    assertEquals("0+0", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

  /**
   * Tests for adding with zeroes.
   */
  @Test
  public void testAddingWithZeros() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('+');
    assertEquals("5+", copy.getResult());
    copy = copy.input('0');
    assertEquals("5+0", copy.getResult());
    copy = copy.input('=');
    assertEquals("5", copy.getResult());
  }

  /**
   * Tests for subtracting with zeroes.
   */
  @Test
  public void testAddingWithSubtract() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('-');
    assertEquals("5-", copy.getResult());
    copy = copy.input('0');
    assertEquals("5-0", copy.getResult());
    copy = copy.input('=');
    assertEquals("5", copy.getResult());
  }

  /**
   * Tests for multiplying with zeroes.
   */
  @Test
  public void testAddingWithMultiplication() {
    Calculator copy = this.createCalc();
    copy = copy.input('5');
    assertEquals("5", copy.getResult());
    copy = copy.input('*');
    assertEquals("5*", copy.getResult());
    copy = copy.input('0');
    assertEquals("5*0", copy.getResult());
    copy = copy.input('=');
    assertEquals("0", copy.getResult());
  }

}
