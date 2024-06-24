package calculator;

/**
 * This class represents a SimpleCalculator with 2 operands, an operator, and a result.
 * ASimpleCalculator can solve simple arithmetic expressions such as addition,
 * subtraction, and multiplication.
 */
public class SimpleCalculator extends ACalculator implements Calculator {

  /**
   * Constructs a SimpleCalculator with default values.
   */
  public SimpleCalculator() {
    super();
  }

  /**
   * Protected constructor for a SimpleCalculator object.
   * Sets each field to avoid mutating fields.
   *
   * @param operand1 The first integer in the expression
   * @param operand2 The second integer in the expression
   * @param operator The operation in the expression (+, -, or *)
   * @param result   The result of the equation
   */
  private SimpleCalculator(StringBuilder operand1, StringBuilder operand2,
                           char operator, String result) {
    super(operand1, operand2, operator, result);
  }

  @Override
  protected Calculator caseNumber(StringBuilder operand1, StringBuilder operand2,
                                  char operator, String result, char character) {
    if (this.result.length() == 0) {
      if (operator == 0) {
        appendValue(operand1, character);
        return new SimpleCalculator(operand1, operand2, operator, result);
      } else {
        appendValue(operand2, character);
        return new SimpleCalculator(operand1, operand2, operator, result);
      }
    }
    operand1 = new StringBuilder();
    appendValue(operand1, character);
    return new SimpleCalculator(operand1, new StringBuilder(), '\0', "");
  }

  @Override
  protected Calculator caseOperator(StringBuilder operand1, StringBuilder operand2,
                                    char operator, String result, char character) {
    if (operand1.length() != 0 && operator != 0 && operand2.length() != 0) {
      int resultValue = performOperation(operand1, operand2, operator);
      String newResult = String.valueOf(resultValue);
      return new SimpleCalculator(new StringBuilder(newResult),
              new StringBuilder(), character, "");
    }

    if (operator != 0) {
      throw new IllegalArgumentException("Operator already exists");
    }

    if (result.length() != 0) {
      return new SimpleCalculator(new StringBuilder(result),
              new StringBuilder(), character, "");
    }

    if (operand1.length() == 0) {
      throw new IllegalArgumentException("Missing Inputs");
    }

    return new SimpleCalculator(operand1, operand2, character, result);
  }

  @Override
  protected Calculator caseEquals(StringBuilder operand1,
                                  StringBuilder operand2, char operator, String result) {
    if (operand1.length() != 0 && operand2.length() != 0 && operator != 0) {
      int resultValue = performOperation(operand1, operand2, operator);
      return new SimpleCalculator(new StringBuilder(),
              new StringBuilder(), '\0', String.valueOf(resultValue));
    }

    if (operand1.length() != 0 && operand2.length() == 0 && operator != 0) {
      int resultValue = performOperation(operand1, operand1, operator);
      return new SimpleCalculator(new StringBuilder(),
              new StringBuilder(), '\0', String.valueOf(resultValue));
    }

    if (!result.isEmpty()) {
      return this;
    }
    throw new IllegalArgumentException("Missing Inputs");
  }
}
