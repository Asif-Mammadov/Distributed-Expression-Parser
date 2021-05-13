package services;

import java.rmi.RemoteException;

import java.util.*;

import operations.Subtraction;
import operations.ValueOverflowException;
import operations.Addition;
import operations.ArithmeticException;
import operations.Division;
import operations.Multiplication;

/**
 * Convert is class that has methods needed to translate the given input to the
 * specified format as output.
 */
public class Convert {
	/**
	 * Converts the infix format to postfix format.
	 * 
	 * @param infix arithmetic expression in infix format. <br>
	 *              Example: 12*(11+4)
	 * @return arithmetic expression in postfix format represented as array of
	 *         strings. <br>
	 *         Example: {"12","11","4","+","*"}.
	 * @throws MalformedExpressionException if there is a syntax mistake in
	 *                                      expression
	 */
	public String[] infixToPostfix(String infix) throws MalformedExpressionException {
		infix = this.convertFront(infix);
		Stack<String> stack = new Stack<String>();
		String[] postfix = new String[infix.length()];
		String digitStr = "";
		char c;
		int i = 0;
		int j = 0;
		try {
			while (i < infix.length()) {
				c = infix.charAt(i);

				if (isOperand(c)) {
					digitStr += c;
					i++;
					continue;
				} else if (!digitStr.equals("")) {
					postfix[j++] = digitStr;
					digitStr = "";
				}

				if (c == '(') {
					stack.push(Character.toString(c));
					i++;
				} else if (c == ')') {
					while (!stack.empty() && !stack.peek().equals("("))
						postfix[j++] = stack.pop();
					stack.pop();
					i++;
				} else {
					while (!stack.empty() && this.getPriority(Character.toString(c)) <= this.getPriority(stack.peek()))
						postfix[j++] = stack.pop();
					stack.push(Character.toString(c));
					i++;
				}
			}
			if (!digitStr.equals("")) {
				postfix[j++] = digitStr;
				digitStr = "";
			}

		} catch (EmptyStackException ese) {
			throw new MalformedExpressionException("Malformed Expression");
		}

		while (!stack.empty()) {
			if (stack.peek().equals("("))
				throw new MalformedExpressionException("Can't resolve ( sign");
			postfix[j++] = stack.pop();
		}

		return postfix;
	}

	/**
	 * Calculates the arithmetic value of postfix expression.
	 * 
	 * @param postfix arithmetic expression in postfix format represented as array
	 *                of strings<br>
	 *                Example: {"12","11","4","+","*"}
	 * @return result of calculation as integer
	 * @throws MalformedExpressionException if the expression has syntax mistakes.
	 * @throws ArithmeticException          if the expression has arithmetic
	 *                                      mistakes like division by 0.
	 * @throws ValueOverflowException       if there is integer overflow
	 *
	 */
	public int postfixCalculate(String[] postfix)
			throws RemoteException, MalformedExpressionException, ArithmeticException, ValueOverflowException {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < postfix.length && postfix[i] != null; i++) {
			String str = postfix[i];
			if (this.isInteger(str))
				stack.push(Integer.parseInt(str));
			else if (str.length() == 1) {
				try {

					int val2 = stack.pop();
					int val1 = stack.pop();

					if (str.equals("+"))
						stack.push(Addition.execute(val1, val2));
					else if (str.equals("-"))
						stack.push(Subtraction.execute(val1, val2));
					else if (str.equals("*"))
						stack.push(Multiplication.execute(val1, val2));
					else if (str.equals("/"))
						stack.push(Division.execute(val1, val2));
				} catch (EmptyStackException e) {
					throw new MalformedExpressionException("Can't resolve " + str + " sign");
				}
			} else
				throw new operations.ValueOverflowException("Integer overflow");
		}
		return stack.pop();
	}

	/**
	 * Checks whether the character is operand or not
	 * 
	 * @param c character to pass
	 * @return boolean. True if it is operand, otherwise returns False
	 */
	private boolean isOperand(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
			return false;
		return true;
	}

	/**
	 * Provides the priority level of the given input. Bigger the value higher the
	 * priority. Mainly considered for the operators. <br>
	 * Priority levels : <br>
	 * 2 : <b>*</b> and <b>/</b> operators <br>
	 * 1 : <b>+</b> and <b>-</b> operators 0 : everything else.
	 * 
	 * @param s is a string.
	 * @return integer representing the priority level (level of importance).
	 */
	private int getPriority(String s) {
		if (s.equals("+") || s.equals("-"))
			return 1;
		else if (s.equals("*") || s.equals("/"))
			return 2;
		return 0;
	}

	/**
	 * Checks whether the provided string is an integer.
	 * 
	 * @param s is a String.
	 * @return <b>True</b> if it is integer, otherwise <b>False</b>.
	 */
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Helps to handle special case of - sign being at the front of the expression.
	 * <br>
	 * For example : "-3+5". The method will convert it into "0-3+5".
	 * 
	 * @param s is a String representing the expression.
	 * @return adjusted String with desired output.
	 */
	private String convertFront(String s) {
		if (s.charAt(0) == '-')
			s = "0" + s;
		return s;
	}
}
