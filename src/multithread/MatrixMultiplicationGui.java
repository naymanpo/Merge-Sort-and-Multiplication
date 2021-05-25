package multithread;
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
import javax.swing.JTextArea;

public class MatrixMultiplicationGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTakeTime;
	private JTextArea textResult;
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
		lblNewLabel.setBounds(10, 0, 199, 30);
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
					long startTime =	System.nanoTime();
					MatrixMultiplication matrixMultiplication = new MatrixMultiplication(matrixA, matrixB);
					matrixC = matrixMultiplication.getResult();
					long endTime = System.nanoTime();
					long timeElapsed = endTime - startTime;
					lblTakeTime.setText(String.format("take time: %d nanoseconds", timeElapsed));
					String result = matrixToString(matrixC);
					textResult.setText(result);
				} catch (IOException e1) {
					textResult.setText(e1.toString());
					

				}
				
				
			}
		});
		btnNewButton.setBounds(399, 278, 87, 31);
		contentPanel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("លទ្ធផល");
		lblNewLabel_3.setBounds(199, 109, 52, 43);
		contentPanel.add(lblNewLabel_3);
		
		lblTakeTime = new JLabel("take time:");
		lblTakeTime.setBounds(10, 305, 476, 23);
		contentPanel.add(lblTakeTime);
		
		textResult = new JTextArea();
		textResult.setLineWrap(true);
		textResult.setWrapStyleWord(true);
		textResult.setBounds(261, 32, 225, 215);
		contentPanel.add(textResult);
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
			throw new IOException("គ្មានទិន្នន័យ");
		}
		
		if(data.charAt(0)!='[') {
			throw new IOException("នៅត្រង់ចំណុចាប់ផ្តើមគ្មានសញ្ញា [ ");
		}
	
		if(data.charAt(data.length() - 1)!=']') {
			throw new IOException("នៅត្រង់ចុងបញ្ចប់គ្មានសញ្ញា ]")	;
		}
		
		
		String[] rows = data.trim().replace("[", "").replace("]", "").split(";");
		if(rows.length == 0) {
			throw new IOException("គ្មានទិន្នន័យ")	;
		}
		
		matrix = new float[rows.length][];
		int lengthColumn = 0;
		for(int i=0;i<rows.length;i++) {
			String[] eachRow = rows[i].trim().split(",");
			if(i==0) {
				lengthColumn = eachRow.length;
			}
			if(lengthColumn!=eachRow.length) {
				throw new IOException("ទិន្នន័យរបស់អ្នកមិនមែនជាម៉ាទ្រីសទេ");
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
