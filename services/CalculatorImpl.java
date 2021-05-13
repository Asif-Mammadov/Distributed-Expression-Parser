package services;

import java.rmi.RemoteException;

import operations.ArithmeticException;
import operations.ValueOverflowException;

/**
 * Contains the method Calculate that will be called remotely by the Client. 
 * 
 * @see client.Client
 */
public class CalculatorImpl implements Calculator {

	/**
	 * Remotely invoked by the end user after entering the arithmetic expression.
	 * and pressing the “=” button in the Client class
	 * 
	 * @param expression is a String that is defined as the arithmetic expression.
	 * @return integer which is the result of the calculation of the expression
	 *          entered by the end-user.
	 * @throws MalformedExpressionException if there is a syntax mistake in
	 *                                      expression.
	 * @throws NegativeValueException       if the result value is less than 0.
	 * @throws ArithmeticException          if there is division by 0.
	 * @throws ValueOverflowException       if the Operand value is too big
	 * @see client.Client
	 */
	public int Calculate(String expression) throws RemoteException, MalformedExpressionException, NegativeValueException,
			ArithmeticException, ValueOverflowException {
		this.hasUnknownChar(expression);
		Convert e = new Convert();
		int res = e.postfixCalculate(e.infixToPostfix(expression));
		this.isNegative(res);
		return res;

	}

	/**
	 * Checks whether there are not supported characters in the expression.
	 * 
	 * @param expression is a String representing the arithmetic expression
	 * @throws MalformedExpressionException if expression has at least one
	 *                                      unsupported character.
	 */
	private void hasUnknownChar(String expression) throws MalformedExpressionException {
		char c;
		for (int i = 0; i < expression.length(); i++) {
			c = expression.charAt(i);
			if (c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')' && !(c >= '0' && c <= '9'))
				throw new MalformedExpressionException(c + " is unknown character");
		}
	}

	/**
	 * Checks whether the integer is negative.
	 * 
	 * @param n is an integer.
	 * @throws NegativeValueException if integer is less than 0.
	 */
	private void isNegative(int n) throws NegativeValueException {
		if (n < 0)
			throw new NegativeValueException("Negative result");
	}
}
