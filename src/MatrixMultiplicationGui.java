import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class MatrixMultiplicationGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel lblTakeTime;
	JEditorPane textResult;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MatrixMultiplicationGui dialog = new MatrixMultiplicationGui();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MatrixMultiplicationGui() {
		setBounds(100, 100, 512, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matrix Multiplication");
		lblNewLabel.setBounds(10, 0, 223, 30);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A");
		lblNewLabel_1.setBounds(20, 65, 16, 23);
		contentPanel.add(lblNewLabel_1);
		
		JTextPane textA = new JTextPane();
		textA.setBounds(46, 32, 126, 90);
		contentPanel.add(textA);
		
		JTextPane textB = new JTextPane();
		textB.setBounds(46, 157, 126, 90);
		contentPanel.add(textB);
		
		JLabel lblNewLabel_2 = new JLabel("B");
		lblNewLabel_2.setBounds(15, 192, 21, 23);
		contentPanel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textMatrixA = textA.getText();
				String textMatrixB = textB.getText();
				float[][] matrixA;
				float[][] matrixB;
				float[][] matrixC;
				try {
					matrixA = convertStringToMatrix(textMatrixA);
					matrixB = convertStringToMatrix(textMatrixB);
					long startTime = System.nanoTime();
					MatrixMultiplication matrixCalculator =  new MatrixMultiplication(matrixA, matrixB);
					matrixC = matrixCalculator.getResult();
					long endTime = System.nanoTime();
					long timeElapsed = endTime - startTime;
					lblTakeTime.setText(String.format("take time: %d nanoseconds", timeElapsed));
					String result ="";
					result = matrixToString(matrixC);
					textResult.setText(result);
				} catch (IOException e1) {
					System.out.println(e1.toString());
				}
				
				
			}
		});
		btnNewButton.setBounds(379, 278, 87, 31);
		contentPanel.add(btnNewButton);
		
		textResult = new JEditorPane();
		textResult.setBounds(261, 65, 205, 142);
		contentPanel.add(textResult);
		
		JLabel lblNewLabel_3 = new JLabel("??????????????????");
		lblNewLabel_3.setBounds(199, 109, 52, 43);
		contentPanel.add(lblNewLabel_3);
		
		lblTakeTime = new JLabel("take time: ");
		lblTakeTime.setBounds(20, 305, 466, 23);
		contentPanel.add(lblTakeTime);
	}
	
	
	private String matrixToString(float[][] matrix) {
		String result ="[";
		for(int i=0;i< matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				result = String.format("%s %f",result, matrix[i][j]);
				if(j<matrix[i].length-1) {
					result = String.format("%s %s", result, ", ");
				}
			}
			if(i< matrix.length-1) {
				result = String.format("%s%s", result, ";");
			}
		}
		result = String.format("%s%s", result, "]");
		return result;
	}
	
	private float[][] convertStringToMatrix(String data) throws IOException{
		float[][] matrix = null;
		
		// check null data 
		if(data==null) {
			throw new IOException("???????????????????????????????????????");
		}
		
		if(data.charAt(0)!='[') {
			throw new IOException("?????????????????????????????????????????????????????????????????????????????????????????? [ ");
		}
	
		if(data.charAt(data.length() - 1)!=']') {
			throw new IOException("?????????????????????????????????????????????????????????????????????????????? ]")	;
		}
		
		
		String[] rows = data.trim().replace("[", "").replace("]", "").split(";");
		if(rows.length == 0) {
			throw new IOException("???????????????????????????????????????")	;
		}
		
		matrix = new float[rows.length][];
		int lengthColumn = 0;
		for(int i=0;i<rows.length;i++) {
			String[] eachRow = rows[i].trim().split(",");
			if(i==0) {
				lengthColumn = eachRow.length;
			}
			if(lengthColumn!=eachRow.length) {
				throw new IOException("??????????????????????????????????????????????????????????????????????????????????????????????????????");
			}
			
			for(int j=0;j<eachRow.length;j++) {
				if(j==0) {
					matrix[i] = new float[eachRow.length];
				}
				matrix[i][j] = Float.parseFloat(eachRow[j].trim());
			}
			
		}
		
		return matrix;
	}
}
