package laboration8;

import java.io.Serializable;

public class Expression implements Serializable {
	private static final long serialVersionUID = 1732301553753948555L;
	private double nbr1;
	private double nbr2;
	private char operation;
	
	public Expression(double nbr1, double nbr2, char operation) {
		this.nbr1 = nbr1;
		this.nbr2 = nbr2;
		this.operation = operation;
	}

	public double getNbr1() {
		return nbr1;
	}

	public double getNbr2() {
		return nbr2;
	}

	public char getOperation() {
		return operation;
	}
	
	public String toString() {
		return String.valueOf(nbr1) + operation + nbr2;
	}
}
