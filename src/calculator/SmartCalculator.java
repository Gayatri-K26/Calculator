package calculator;

/**
 * This class represents a SmartCalculator with 2 operands, an operator, and a result.
 * ASmartCalculator can solve simple arithmetic expressions such as addition,
 * subtraction, and multiplication.
 * It can also handle more complex cases than a SimpleCalculator.
 */
public class SmartCalculator extends ACalculator implements Calculator {

  /**
   * Constructs a SmartCalculator with default values.
   */
  public SmartCalculator() {
    super();
  }

  /**
   * Protected constructor for an SmartCalculator object.
   * Sets each field to avoid mutating fields.
   *
   * @param operand1 The first integer in the expression
   * @param operand2 The second integer in the expression
   * @param operator The operation in the expression (+, -, or *)
   * @param result   The result of the equation
   */
  private SmartCalculator(StringBuilder operand1, StringBuilder operand2,
                          char operator, String result) {
    super(operand1, operand2, operator, result);
  }

  @Override
  protected Calculator caseNumber(StringBuilder operand1, StringBuilder operand2,
                                  char operator, String result, char character) {
    if (this.result.length() == 0) {
      if (operator == 0) {
        appendValue(operand1, character);
        return new SmartCalculator(operand1, operand2, operator, result);
      } else {
        appendValue(operand2, character);
        return new SmartCalculator(operand1, operand2, operator, result);
      }
    }
    operand1 = new StringBuilder();
    appendValue(operand1, character);
    return new SmartCalculator(operand1, new StringBuilder(), '\0', "");
  }


  @Override
  protected Calculator caseOperator(StringBuilder operand1, StringBuilder operand2,
                                    char operator, String result, char character) {
    if (operand1.length() != 0 && operator != 0 && operand2.length() != 0) {
      int resultValue = performOperation(operand1, operand2, operator);
      String newResult = String.valueOf(resultValue);
      return new SmartCalculator(new StringBuilder(newResult),
              new StringBuilder(), character, "");
    }

    if (result.length() != 0) {
      return new SmartCalculator(new StringBuilder(result),
              new StringBuilder(), character, "");
    }

    if (operand1.length() == 0) {
      if (character != '+') {
        throw new IllegalArgumentException("Invalid inputs");
      }
      return new SmartCalculator(operand1, operand2, '\0', "");
    }

    return new SmartCalculator(operand1, operand2, character, result);
  }

  @Override
  protected Calculator caseEquals(StringBuilder operand1,
                                  StringBuilder operand2, char operator, String result) {
    if (operand1.length() != 0 && operator == 0) {
      return new SmartCalculator(operand1, operand2, operator, operand1.toString());

    } else if (operand1.length() != 0 && operand2.length() != 0
            && operator != 0 && this.result.length() == 0) {
      int resultValue = performOperation(operand1, operand2, operator);
      return new SmartCalculator(operand1, operand2, operator, String.valueOf(resultValue));

    } else if (!result.isEmpty()) {
      if (operand2.length() == 0) {
        int resultValue = performOperation(new StringBuilder(result), operand1, operator);
        return new SmartCalculator(new StringBuilder(String.valueOf(resultValue)),
                operand2, operator, String.valueOf(resultValue));
      }
      int resultValue = performOperation(new StringBuilder(result), operand2, operator);
      return new SmartCalculator(new StringBuilder(String.valueOf(resultValue)),
              operand2, operator, String.valueOf(resultValue));

    } else if (operand1.length() != 0 && operand2.length() == 0) {
      int resultValue = performOperation(operand1, operand1, operator);
      return new SmartCalculator(operand1, operand1, operator, String.valueOf(resultValue));

    } else {
      return null;
    }
  }
}