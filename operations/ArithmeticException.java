package operations;

/**
 * Thrown to indicate the division by 0.
 */
public class ArithmeticException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArithmeticException(String message) {
		super(message);
	}
}