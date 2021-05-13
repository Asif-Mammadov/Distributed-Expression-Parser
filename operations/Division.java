package operations;

public class Division implements IOperator {
	/**
	 * Divides two numbers and returns result
	 * @param num1 first integer value
	 * @param num2 second integer value
	 * @return num1 / num2
	 * @throws ArithmeticException if there is a division by zero
	 */
	public static int execute(int num1, int num2) throws ArithmeticException {
		if (num2 == 0) {
			throw new ArithmeticException("Can't divide by zero");
		}
		return num1 / num2;
	}
}
