
/**
 * Lisp.java
 * 
 * INF2010 - lab 2
 * 
 * @author Adam Martin-Côté & Pascal Lacasse
 *
 */
import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Lisp {

	/*
	 * cette fonction permet de resoudre une expresion Lisp le param�tre peut �tre transformer en token � l'aide de la fonction getTokens(expresion) NB une
	 * seule pile peut �tre utilis�e retourn "double" le resultat de l'expresion
	 */

	/**
	 * Évalue une s-expression et retourne le résultat
	 * 
	 * @param String
	 * @return double
	 */
	static public double solve(String expresion) {
		Stack<String> stack = new Stack<String>();

		double number = 0;

		Vector<String> vec = getTokens(expresion);

		// on inverse la chaine pour obtenir les opérateurs après les opérandes
		Collections.reverse(vec);
		for (String token : vec) {
			if (token.equals("+") && !stack.empty()) {
				while (!stack.peek().equals(")"))				// accounts for multiple operands multiplications
					number += Double.parseDouble(stack.pop());
				stack.pop(); 									// get rid of the ")"
				stack.push(Double.toString(number));
				number = 0;
			} else if (token.equals("-") && !stack.empty()) {
				number = Double.parseDouble(stack.pop());		// 1st operand
				number -= Double.parseDouble(stack.pop());		// 2nd operand
				stack.pop(); 									// get rid of the ")"
				stack.push(Double.toString(number));			// push result on stack
				number = 0;
			} else if (token.equals("*") && !stack.empty()) {
				number = Double.parseDouble(stack.pop());
				number *= Double.parseDouble(stack.pop());
				stack.pop(); // get rid of the ")"
				stack.push(Double.toString(number));
				number = 0;
			} else if (token.equals("/") && !stack.empty()) {
				number = Double.parseDouble(stack.pop());
				number /= Double.parseDouble(stack.pop());
				stack.pop(); // get rid of the ")"
				stack.push(Double.toString(number));
				number = 0;
			} else if (token.equals("("))
				;
			else
				stack.push(token);
		}
		return Double.parseDouble(stack.pop());
	}

	/**
	 * Évalue la validité d'une s-expression par une comparaison du nombre de parenthèses ouvrantes et fermantes
	 * 
	 * @param String
	 * @return boolean
	 */
	static public boolean isEquilibre(String expresion) {
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < expresion.length(); i++) {
			if (expresion.charAt(i) == '(')
				stack.push("(");
			else if (expresion.charAt(i) == ')' && stack.empty())
				return false;
			else if (expresion.charAt(i) == ')' && stack.peek() == "(")
				stack.pop();
		}
		return stack.empty();
	}

	/*
	 * fonction transforme une expresion (String) lisp en tokens (Vector<String>)
	 */
	static public Vector<String> getTokens(String expresion) {

		Vector<String> vectorOfTokens = new Vector<String>();
		int lenght = expresion.length();
		String token = "", number = "";
		// ==
		for (int i = lenght - 1; i >= 0; i--) {
			//
			token = String.valueOf(expresion.charAt(i));
			if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
				if (!number.isEmpty()) {
					vectorOfTokens.addElement(number);
					number = "";
				}
				vectorOfTokens.addElement(token);
			} else if (token.equals(")") || token.equals("(")) {
				if (!number.isEmpty()) {
					vectorOfTokens.addElement(number);
					number = "";
				}
				vectorOfTokens.addElement(token);
			} else if (token.equals(" ")) {
				if (!number.isEmpty()) {
					vectorOfTokens.addElement(number);
					number = "";
				}
			} else if (!token.equals(" ")) {
				number = token + number;
			}
		}
		// invert vectorOfTokens;
		Collections.reverse(vectorOfTokens);

		return vectorOfTokens;
	}

}
