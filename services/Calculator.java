package services;

import java.rmi.Remote;

import operations.ArithmeticException;
import operations.ValueOverflowException;
/**
 * Calculator Interface
 * 
 * Method Calculate will be remotely invoked in the Client after entering the arithmetic expression
 * and pressing the “=” button on the Calculator GUI or Enter button on keyboard.
 */
public interface Calculator extends Remote {
	/**
	 * Calculates the result of the expression entered by the user
	 * 
	 * @param expression is of type String and it's the expression entered by the user
	 * @return the result of the calculation of the expression
	 *
	 * @throws MalformedExpressionException 	if the expression has syntax mistakes.
	 * @throws NegativeValueException 		if the result value is less than 0.
	 * @throws ArithmeticException 			if the expression contains division by 0.
	 * @throws ValueOverflowException		if the operand(s) is/are greater than the MAX_VALUE 
	 */
	public int Calculate(String expression)
			throws java.rmi.RemoteException, MalformedExpressionException, NegativeValueException,
			operations.ArithmeticException, ValueOverflowException;
}
