package operations;

/**
 * Thrown to indicate the syntax error in the expression.
 */
public class ValueOverflowException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValueOverflowException(String message) {
		super(message);
	}
}
