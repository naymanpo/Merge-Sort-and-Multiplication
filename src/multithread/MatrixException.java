package multithread;

import java.io.IOException;

public class MatrixException extends IOException{
	private String expression;
	public MatrixException(String expression){
		this.expression = expression;
	}
	public String toString() {
		return this.expression;
		
	}
}
