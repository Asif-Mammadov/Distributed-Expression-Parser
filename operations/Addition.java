package operations;

public class Addition implements IOperator {
	/**
	 * Adds two numbers and returns result
	 * @param num1 first integer value
	 * @param num2 second integer value
	 * @return num1 + num2
	 * @throws ValueOverflowException if there is integer overflow
	 */
	public static int execute(int num1, int num2) throws ValueOverflowException {
		try {
			return Math.addExact(num1, num2);
		} catch (java.lang.ArithmeticException ae) {
			throw new ValueOverflowException("Integer overflow");
		}
	}
}
