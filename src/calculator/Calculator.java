package calculator;

/**
 * This represents the interface for a single "just in time" Calculator.
 * This calculator can compute basic math operations based on user input.
 */
public interface Calculator {

  /**
   * To take in user input.
   *
   * @param character represents the user input which can be an operator
   *                  (+, -, *, =) or an operand (integers).
   * @return based on user input, returns a new Calculator object.
   * @throws IllegalArgumentException when the input is invalid
   *                                  (a non-integer value or a character
   *                                  that is not +, -, *, or =).
   */
  Calculator input(char character);

  /**
   * To get the current result of the calculator in string format.
   *
   * @return the result of the calculator object as arithmetic expression or a value.
   */
  String getResult();
}
