package services;

/**
 * Thrown to indicate the syntax error in the expression.
 */
public class MalformedExpressionException extends Exception {

	private static final long serialVersionUID = 1L;

	public MalformedExpressionException(String message) {
		super(message);
	}
}
