package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import operations.ArithmeticException;
import operations.ValueOverflowException;
import services.CalculatorImpl;
import services.Convert;
import services.MalformedExpressionException;
import services.NegativeValueException;

class JunitTest {

	@Test
	void testInfixToPostfix() throws MalformedExpressionException {
		Convert e = new Convert();
		String[] out = e.infixToPostfix("(1+4)*2");
		String[] ans = { "1", "4", "+", "2", "*", null, null };
		assertArrayEquals(ans, out);
	}

	@Test
	void testValuesCalculate() throws MalformedExpressionException, RemoteException, ArithmeticException, ValueOverflowException {
		Convert e = new Convert();
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("(1+4)*2", "10");
		values.put("2*59+(3+3)-(32+2)/4", "116");

		for (HashMap.Entry<String, String> entry : values.entrySet()) {
			int out = e.postfixCalculate(e.infixToPostfix(entry.getKey()));
			assertEquals(entry.getValue(), Integer.toString(out));
		}
	}

	@Test
	void testExpectedException() {
		Convert e = new Convert();
		assertThrows(MalformedExpressionException.class, () -> {
			e.postfixCalculate(e.infixToPostfix("283*23*"));
		});
	}

	@Test
	void testCalculatorImpl()
			throws NegativeValueException, MalformedExpressionException, RemoteException, ArithmeticException, ValueOverflowException {
		CalculatorImpl calcImpl = new CalculatorImpl();

		assertEquals("2", Integer.toString(calcImpl.Calculate("1+1")));
		assertEquals("74", Integer.toString(calcImpl.Calculate("1*90-4*4")));

		// Check big numbers
		assertThrows(ValueOverflowException.class, () -> {
			calcImpl.Calculate("1000*999999999");
		});
		
		// Check big negative number
		assertThrows(ValueOverflowException.class, () -> {
			calcImpl.Calculate("-1000*999999999");
		});
		
		// Check negative number
		assertThrows(NegativeValueException.class, () -> {
			calcImpl.Calculate("-3*4000000");
		});
		
		assertThrows(ValueOverflowException.class, () -> {
			calcImpl.Calculate("999999900000*2");
		});
		
		
		assertThrows(ValueOverflowException.class, () -> {
			calcImpl.Calculate("9999999000000/2");
		});
		
		// Check MalformedExpression exception
		assertThrows(MalformedExpressionException.class, () -> {
			calcImpl.Calculate("3*1+a-8");
		});
		assertThrows(MalformedExpressionException.class, () -> {
			calcImpl.Calculate("3.1-8");
		});
		assertThrows(MalformedExpressionException.class, () -> {
			Integer.toString(calcImpl.Calculate("8)7"));
		});

		// Check Negative values
		assertThrows(NegativeValueException.class, () -> {
			calcImpl.Calculate("3*1-48");
		});
		assertThrows(NegativeValueException.class, () -> {
			calcImpl.Calculate("49*3-4020");
		});
		assertThrows(NegativeValueException.class, () -> {
			calcImpl.Calculate("-1");
		});

		// Check ArithmeticException
		assertThrows(ArithmeticException.class, () -> {
			Integer.toString(calcImpl.Calculate("1/0"));
		});	
	}

}
