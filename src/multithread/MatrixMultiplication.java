package multithread;

import java.io.IOException;


public class MatrixMultiplication {
	float[][] C;
	float[][] A;
	float[][] B;
	
	public MatrixMultiplication(float[][] A, float[][] B) throws IOException{
		this.A=A;
		this.B=B;
		this.compute();
	}
	
	public float[][] getResult(){
		return this.C;
	}
	
	
	private class TermOfCCalculator implements Runnable{
		float sum =0;
		int i_A;
		int i_B;
		float [][]A;
		float [][]B;
		public TermOfCCalculator(float[][] A, int i_A, float[][] B, int i_B) {
			this.A=A;
			this.i_A=i_A;
			this.B=B;
			this.i_B=i_B;
		}
		
		public float getResult() {
			return this.sum;
		}
		
		@Override
		public void run() {
			for(int i=0;i< this.A[0].length;i++) {
				sum+=A[i_A][i]*B[i][i_B];
			}
		}
		
	}
	
	
	private void compute() throws IOException{
		
		//Matrix A mxn matrix B nxp 
		//check if A.n != B.n
		if(this.A[0].length != this.B.length) {
			throw new MatrixException("Column នៃម៉ាទ្រីស A មិនស្មើ Row នៃម៉ាទ្រីស B");
		}
		
		this.C = new float[this.A.length][this.B[0].length];
		for(int i=0;i<this.A.length;i++) {
			for(int j=0;j<this.B[0].length;j++) {			
				TermOfCCalculator calculator = new TermOfCCalculator(this.A,i, this.B,j);
				Thread calculatorRunner = new Thread(calculator);
				calculatorRunner.start();
				try {
					calculatorRunner.join();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				this.C[i][j] = calculator.getResult();
				 
			}
		}
	}
	
	}
