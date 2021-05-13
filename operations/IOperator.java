package operations;

import java.rmi.Remote;

/**
 * The interface provides method execute is implemented by the Addition,
 * Subtraction, Multiplication and Division classes.
 */
public interface IOperator extends Remote {
	/**
	 * Implements one of the main operations : +, -, *, /.
	 * @param num1 first integer value
	 * @param num2 second integer value
	 * @return num1 (operator) num2
	 * @author Nadir Shikhli
	 *
	 */
	public static int execute(int num1, int num2) {
		return 0;
	}
}
