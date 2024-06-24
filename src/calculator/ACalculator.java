package calculator;

/**
 * This is an abstract class that is extended by a Calculator.
 * It has 2 operands, 1 operator, and a result.
 */
public abstract class ACalculator {
  protected final StringBuilder operand1;
  protected final StringBuilder operand2;
  protected final char operator;
  protected final String result;

  /**
   * Default constructor to create an ACalculator object.
   */
  public ACalculator() {
    this.operand1 = new StringBuilder();
    this.operand2 = new StringBuilder();
    this.operator = 0;
    this.result = "";
  }

  /**
   * Protected constructor for an ACalculator object.
   * Sets each field to avoid mutating fields.
   *
   * @param operand1 The first integer in the expression
   * @param operand2 The second integer in the expression
   * @param operator The operation in the expression (+, -, or *)
   * @param result   The result of the equation
   */
  protected ACalculator(StringBuilder operand1, StringBuilder operand2,
                        char operator, String result) {
    this.operand1 = new StringBuilder(operand1);
    this.operand2 = new StringBuilder(operand2);
    this.operator = operator;
    this.result = result;
  }

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
  public Calculator input(char character) {
    StringBuilder tempOperand1 = new StringBuilder(this.operand1);
    StringBuilder tempOperand2 = new StringBuilder(this.operand2);
    char tempOperator = this.operator;
    String tempResult = this.result;

    if (Character.isDigit(character)) {
      return caseNumber(tempOperand1, tempOperand2, tempOperator, tempResult, character);
    }
    if (character == '+' || character == '-' || character == '*') {
      return caseOperator(tempOperand1, tempOperand2, tempOperator, tempResult, character);
    }
    if (character == '=') {
      return caseEquals(tempOperand1, tempOperand2, tempOperator, tempResult);
    }
    if (character == 'C') {
      return caseClear();
    }
    throw new IllegalArgumentException("Invalid Character");
  }

  /**
   * To append the inputted value to an operand based on if it overflows or not.
   *
   * @param operand   the current integer in the expression
   * @param character the user input
   * @throws IllegalArgumentException when the value will overflow after appending the input
   */
  protected void appendValue(StringBuilder operand, char character) {
    StringBuilder temp = new StringBuilder(operand).append(character);
    try {
      Integer.parseInt(temp.toString());
      operand.append(character);
    } catch (Exception e) {
      throw new IllegalArgumentException("Input Overflows");
    }
  }

  /**
   * This method performs the operation based on the operator (+, -, *).
   *
   * @return the int value after the operation
   */
  protected int performOperation(StringBuilder operand1,
                                 StringBuilder operand2, char operator) {
    int var1 = Integer.parseInt(operand1.toString());
    int var2 = Integer.parseInt(operand2.toString());
    try {
      if (operator == '+') {
        return Math.addExact(var1, var2);
      }
      if (operator == '-') {
        return Math.subtractExact(var1, var2);
      }
      if (operator == '*') {
        return Math.multiplyExact(var1, var2);
      }
    } catch (Exception e) {
      System.out.println("Exception Error" + e.getMessage());
    }
    return 0;
  }

  /**
   * This method handles the case where the input is C. It will clear all the values of the fields.
   *
   * @return a new calculator with default values
   */
  protected Calculator caseClear() {
    return new SimpleCalculator();
  }

  /**
   * This method handles the case where the input is a number.
   *
   * @param operand1  The first integer in the expression
   * @param operand2  The second integer in the expression
   * @param operator  The operation in the expression (+, -, or *)
   * @param result    The result of the equation
   * @param character The inputted value
   * @return a new calculator object with the character inputted appropriately.
   */
  protected abstract Calculator caseNumber(StringBuilder operand1, StringBuilder operand2,
                                           char operator, String result, char character);

  /**
   * This method handles the case where the input is an operator (+, *, -).
   *
   * @param operand1  The first integer in the expression
   * @param operand2  The second integer in the expression
   * @param operator  The operation in the expression (+, -, or *)
   * @param result    The result of the equation
   * @param character The inputted value
   * @return a new calculator object with the character inputted appropriately.
   */
  protected abstract Calculator caseOperator(StringBuilder operand1, StringBuilder operand2,
                                             char operator, String result, char character);

  /**
   * This method handles the case where the input is a "-".
   *
   * @param operand1 The first integer in the expression
   * @param operand2 The second integer in the expression
   * @param operator The operation in the expression (+, -, or *)
   * @param result   The result of the equation
   * @return a new calculator object with the result computed and object updated appropriately.
   */
  protected abstract Calculator caseEquals(StringBuilder operand1, StringBuilder operand2,
                                           char operator, String result);

  /**
   * To get the current result of the calculator in string format.
   *
   * @return the result of the calculator object as arithmetic expression or a value.
   */
  public String getResult() {
    if (this.result.length() != 0) {
      return this.result;
    }
    if (this.operand1.length() != 0) {
      if (this.operator != 0) {
        if (this.operand2.length() != 0) {
          return this.operand1.toString() + this.operator + this.operand2.toString();
        }
        return this.operand1.toString() + this.operator;
      }
      return this.operand1.toString();
    }
    return this.result;
  }
}
