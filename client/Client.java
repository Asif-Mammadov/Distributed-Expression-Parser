package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.Calculator;

/**
 * Client is one of the main classes for the application which connects to the
 * Server and which includes Graphical User Interface along with the methods
 * needed for handling the inputs, removing last entered element, returning last
 * element, etc.
 */
public class Client {
	// Height and Width of the application window
	private static final int WIDTH = 282;
	private static final int HEIGHT = 388;

	/**
	 * isResult is used to track if the expression in the textfield is the result
	 * straight after calculating some expression. It basically helps to not append
	 * the newly entered character (unless it's an operator) to the result of the
	 * previous calculation
	 */
	private static boolean isResult;

	/**
	 * Main function connects to the server and calls the GUI function
	 * 
	 */
	public static void main(String args[]) {

		String machine = "localhost";
		int port = 1099;
		try {
			Registry registry = LocateRegistry.getRegistry(machine, port);
			Calculator obj = (Calculator) registry.lookup(" Calculator ");
			GUI(obj);
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}

	/**
	 * Creates the Calculator GUI and contains the Action Listeners, as well as Key
	 * Listeners that would write in the textfield the entered characters
	 * 
	 * @param obj the Calculator object
	 */
	public static void GUI(Calculator obj) {
		// Creating the frame of the calculator
		JFrame f = new JFrame("Calculator");
		f.setSize(WIDTH, HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(new Point(400, 160));
		f.setResizable(false);

		// Creating a panel and Setting the layout of the panel to GridBagLayout
		JPanel p = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		p.setLayout(gbl);

		// Setting the constraints of the GridBag
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3, 3, 3, 3);

		// Creating the textfield and Setting the font and the text alignment
		JTextField textField = new JTextField("");
		textField.setFont(new Font("Ubuntu Thin", 1, 22));
		textField.setHorizontalAlignment(JTextField.RIGHT);

		// Creating the buttons
		JButton AC = new JButton("AC");
		JButton backspace = new JButton("C");
		JButton leftBracket = new JButton("(");
		JButton rightBracket = new JButton(")");
		JButton seven = new JButton("7");
		JButton eight = new JButton("8");
		JButton nine = new JButton("9");
		JButton divide = new JButton("/");
		JButton four = new JButton("4");
		JButton five = new JButton("5");
		JButton six = new JButton("6");
		JButton multiply = new JButton("*");
		JButton one = new JButton("1");
		JButton two = new JButton("2");
		JButton three = new JButton("3");
		JButton subtract = new JButton("-");
		JButton zero = new JButton("0");
		JButton equals = new JButton("=");
		JButton add = new JButton("+");

		// Setting the text of/on the buttons
		AC.setText("AC");
		backspace.setText("C");
		leftBracket.setText("(");
		rightBracket.setText(")");
		seven.setText("7");
		eight.setText("8");
		nine.setText("9");
		divide.setText("/");
		four.setText("4");
		five.setText("5");
		six.setText("6");
		multiply.setText("*");
		one.setText("1");
		two.setText("2");
		three.setText("3");
		subtract.setText("-");
		zero.setText("0");
		equals.setText("=");
		add.setText("+");

		// Setting tje color of = button
		equals.setBackground(new Color(224, 94, 66));

		// Positioning and the Layout of the textField
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbl.setConstraints(textField, gbc);
		p.add(textField);

		// Postioning and the Layout of the button AC
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbl.setConstraints(AC, gbc);
		p.add(AC);

		// Postioning and the Layout of the button <--
		gbc.gridx = 1;
		gbl.setConstraints(backspace, gbc);
		p.add(backspace);

		// Postioning and the Layout of the button (
		gbc.gridx = 2;
		gbl.setConstraints(leftBracket, gbc);
		p.add(leftBracket);

		// Postioning and the Layout of the button )
		gbc.gridx = 3;
		gbl.setConstraints(rightBracket, gbc);
		p.add(rightBracket);

		// Postioning and the Layout of the button 7
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(seven, gbc);
		p.add(seven);

		// Postioning and the Layout of the button 8
		gbc.gridx = 1;
		gbl.setConstraints(eight, gbc);
		p.add(eight);

		// Postioning and the Layout of the button 9
		gbc.gridx = 2;
		gbl.setConstraints(nine, gbc);
		p.add(nine);

		// Postioning and the Layout of the button /
		gbc.gridx = 3;
		gbl.setConstraints(divide, gbc);
		p.add(divide);

		// Postioning and the Layout of the button 4
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbl.setConstraints(four, gbc);
		p.add(four);

		// Postioning and the Layout of the button 5
		gbc.gridx = 1;
		gbl.setConstraints(five, gbc);
		p.add(five);

		// Postioning and the Layout of the button 6
		gbc.gridx = 2;
		gbl.setConstraints(six, gbc);
		p.add(six);

		// Postioning and the Layout of the button *
		gbc.gridx = 3;
		gbl.setConstraints(multiply, gbc);
		p.add(multiply);

		// Postioning and the Layout of the button 1
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbl.setConstraints(one, gbc);
		p.add(one);

		// Postioning and the Layout of the button 2
		gbc.gridx = 1;
		gbl.setConstraints(two, gbc);
		p.add(two);

		// Postioning and the Layout of the button 3
		gbc.gridx = 2;
		gbl.setConstraints(three, gbc);
		p.add(three);

		// Postioning and the Layout of the button -
		gbc.gridx = 3;
		gbl.setConstraints(subtract, gbc);
		p.add(subtract);

		// Postioning and the Layout of the button 0
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbl.setConstraints(zero, gbc);
		p.add(zero);

		// Postioning and the Layout of the button =
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbl.setConstraints(equals, gbc);
		p.add(equals);

		// Postioning and the Layout of the button +
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbl.setConstraints(add, gbc);
		p.add(add);

		// adding the panel into the frame
		f.add(p);
		f.setVisible(true);

		// Adding ActionListeners to the buttons to show/display in the texField the
		// entered button With the constraint of string length less than 40
		// Removes all the entered characters
		AC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClearAll(textField);
			}
		});

		// Removes the last entered character
		backspace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveLastElement(textField);
			}
		});

		// Enters left bracket
		leftBracket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				isResultCheck(textField);
				if (length < 40)
					textField.setText(textField.getText() + "(");
			}
		});

		// Enter right bracket
		rightBracket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				isResultCheck(textField);
				if (length < 40)
					textField.setText(textField.getText() + ")");
			}
		});

		// Enters 7, if the first entered character was 0 and the next is 7, 0 gets
		// replaced by 7
		seven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "7");
			}
		});

		// Enters 8, if the first entered character was 0 and the next is 8, 0 gets
		// replaced by 8
		eight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "8");
			}
		});

		// Enters 9, if the first entered character was 0 and the next is 9, 0 gets
		// replaced by 9
		nine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "9");
			}
		});

		// Enters /, if the last entered character was an operator it gets replaced by /
		divide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				if (length < 40)
					CheckAndEnterOperator(textField, "/");
			}
		});

		// Enters 4, if the first entered character was 0 and the next is 4, 0 gets
		// replaced by 4
		four.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "4");
			}
		});

		// Enters 5, if the first entered character was 0 and the next is 5, 0 gets
		// replaced by 5
		five.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "5");
			}
		});

		// Enters 6, if the first entered character was 0 and the next is 6, 0 gets
		// replaced by 6
		six.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "6");
			}
		});

		// Enters *, if the last entered character was an operator it gets replaced by *
		multiply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				if (length < 40)
					CheckAndEnterOperator(textField, "*");
			}
		});

		// Enters 1, if the first entered character was 0 and the next is 1, 0 gets
		// replaced by 1
		one.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "1");
			}
		});

		// Enters 2, if the first entered character was 0 and the next is 2, 0 gets
		// replaced by 2
		two.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "2");
			}
		});

		// Enters 3, if the first entered character was 0 and the next is 3, 0 gets
		// replaced by 3
		three.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "3");
			}
		});

		// Enters -, if the last entered character was an operator it gets replaced by -
		subtract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				if (length < 40)
					CheckAndEnterOperator(textField, "-");
			}
		});

		// Enters 0, it does not enter 0 twice if the first character was also 0
		zero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				EnterNumber(textField, "0");
			}
		});

		// Sends the entered expression to ??? and shows the result or the error on the
		// screen
		equals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResultCheck(textField);
				ShowResult(textField, obj);
			}
		});

		// Enters +, if the last entered character was an operator it gets replaced by +
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = textField.getText().length();
				if (length < 40)
					CheckAndEnterOperator(textField, "+");
			}
		});

		// Does everything mentioned above, but for inputs through keyboard
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				int length = textField.getText().length();
				if (!(ch >= '0' && ch <= '9') || ch != '0' || ch != '+' || ch != '-' || ch != '*' || ch != '/' || ch != '('
						|| ch != ')' || ch != KeyEvent.VK_ENTER || ch != KeyEvent.VK_BACK_SPACE || ch != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if ((ch == '(' || ch == ')') && length < 40) {
					isResultCheck(textField);
					textField.setText(textField.getText() + ch);
				}
				if ((ch >= '0' && ch <= '9') && length < 40) {
					isResultCheck(textField);
					EnterNumber(textField, Character.toString(ch));
				}
				if ((ch == '+' || ch == '-' || ch == '*' || ch == '/') && length < 40) {
					CheckAndEnterOperator(textField, Character.toString(ch));
				}
				if (ch == KeyEvent.VK_DELETE) {
					ClearAll(textField);
				}
				if (ch == KeyEvent.VK_ENTER) {
					ShowResult(textField, obj);
				}
			}
		});
	}

	/**
	 * Clears All the entered elements
	 * 
	 * @param textField for modifying the entered expression in it
	 */
	private static void ClearAll(JTextField textField) {
		textField.setText("");
	}

	/**
	 * Removes the last element from the textField
	 * 
	 * @param textField for modifying the entered expression in it
	 */
	private static void RemoveLastElement(JTextField textField) {
		int length = textField.getText().length();
		if (length > 0) {
			String str = textField.getText();
			String substring = str.substring(0, str.length() - 1);
			textField.setText(substring);
		}
	}

	/**
	 * Checks the isResult, i.e. If the isResult is one it means that the expression
	 * Is the return of the calculation made before and as we don't want to append
	 * the new entered characters Unless the new character is an operator The
	 * isResult is being set to equal to zero and The texField is being cleared
	 * 
	 * @param textField for modifying the entered expression in it
	 */
	private static void isResultCheck(JTextField textField) {
		if (isResult) {
			isResult = false;
			ClearAll(textField);
		}
	}

	/**
	 * Returns the last element in the textField
	 * 
	 * @param textField for modifying the entered expression in it
	 * @return if length == 0, return is an empty string, otherwise the last element
	 * 
	 */
	private static String returnLastElement(JTextField textField) {
		int length = textField.getText().length();
		if (length > 0) {
			String str = textField.getText();
			return str.substring(str.length() - 1);
		} else
			return "";
	}

	/**
	 * Checks if the first entered character is zero and enters the character.
	 * Otherwise appends the entered character Example: 07 will be replaced by 7, 00
	 * by 0 Otherwise appends the entered character
	 * 
	 * @param textField for modifying the entered expression in it
	 * @param s         of type String and which is the entered number
	 */
	private static void EnterNumber(JTextField textField, String s) {
		int length = textField.getText().length();
		if (length < 40) {
			if ("0".equals(textField.getText()))
				textField.setText(s);
			else
				textField.setText(textField.getText() + s);
		}
	}

	/**
	 * Checks different cases before entering an operator
	 * 
	 * First it checks if the isResult is 1 (which means that it's result of the
	 * calculation made before) if Yes, then it check whether the expression in
	 * textfield consists of only digits as there can be an expression like
	 * "Malformed Expression" or "Negative result" If there are other characters
	 * other than (0,1,2...,9) then it clears the textField and enters the entered
	 * vharacter if the isResult is 1 and the onlyDigits() is true, then it appends
	 * the operator Example: 1. The result of Calculation 6+5 is 11 if we enter +
	 * sign after getting the result it will give us 11+ 2. The result of 6/0 is
	 * Division by zero if we enter - after calculation, then it clears the
	 * textfield and enters - So we don't get Division By Zero- etc In the end, if
	 * the condition above were false It checks if the last entered element is an
	 * operator If yes, then it check replaces the last element with entered one
	 * Example: if - is entered and the last element is + Then instead of appending
	 * (+-), it will replace the last element In the textField with the last entered
	 * operator
	 * 
	 * Also it does not allow to re-enter the same operator Example: -----, ++, ///.
	 * **** are not possible
	 * 
	 * @param textField for modifying the entered expression in it
	 * @param operator  is of type String and corresponds to the entered operator
	 */
	private static void CheckAndEnterOperator(JTextField textField, String operator) {
		int length = textField.getText().length();
		if (length > 0) {
			if (isResult && onlyDigits(textField)) {
				isResult = false;
				textField.setText(textField.getText() + operator);
			} else if (isResult && !onlyDigits(textField)) {
				isResult = false;
				textField.setText(operator);
			} else {
				String lastChar = returnLastElement(textField);
				if ("/".equals(lastChar) || "+".equals(lastChar) || "-".equals(lastChar) || "*".equals(lastChar)) {
					RemoveLastElement(textField);
				}
				textField.setText(textField.getText() + operator);
			}
		} else
			textField.setText(operator);
	}

	/**
	 * Prints the result of calculation or the exception message in the textfield
	 * 
	 * @param textField for modifying the entered expression in it
	 * @param obj       the Calculator object
	 */
	private static void ShowResult(JTextField textField, Calculator obj) {
		int length = textField.getText().length();
		try {
			// this condition checks whether an error message is in the textfield
			if (isResult && !onlyDigits(textField)) {
				isResult = false;
				ClearAll(textField);
			} else if (length > 0 && length < 40) {
				isResult = true;
				int result = obj.Calculate(textField.getText());
				textField.setText(String.valueOf(result));
			}
		} catch (RemoteException exp) {
			isResult = true;
			textField.setText("Connection refused");
		} catch (Exception exp) {
			isResult = true;
			textField.setText(exp.getMessage());
		}
	}

	/**
	 * Checks whether the expression in the textfield consist of digits only
	 * 
	 * @param textField for checking the expression in it
	 * @return true or false
	 */
	private static boolean onlyDigits(JTextField textField) {
		String str = textField.getText();
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}
