import java.io.IOException;

public class MatrixMultiplication {
	float[][] A;
	float[][] B;
	float[][] C;
	
	public MatrixMultiplication(float[][] A, float[][] B) throws IOException {
		this.A=A;
		this.B=B;
		//Matrix A mxn matrix B nxp 
		//check if A.n != B.n
		if(this.A[0].length != this.B.length) {
			throw new IOException("Column នៃម៉ាទ្រីស A មិនស្មើ Row នៃម៉ាទ្រីស B");
		}

		this.C = new float[this.A.length][this.B[0].length];
		this.compute();
	}
	
	
	private void compute() {
		for(int i=0; i< this.A.length;i++) {
			for(int j=0;j< this.B[0].length;j++) {
				float sum =0;
				for(int k=0;k<this.A[0].length;k++) {
					sum+=this.A[i][k] * this.B[k][j];
				}
				this.C[i][j] = sum;
			}
		}
	
	}
	
	public float[][] getResult(){
		return this.C;
	}
}
