package multithread;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Main {

	private JFrame frame;
	private MatrixMultiplicationGui matrixMultiplication;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 557, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.matrixMultiplication = new MatrixMultiplicationGui();
		JButton btnNewButton = new JButton("Matrix Multiplication");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MatrixMultiplicationGui dialog = new MatrixMultiplicationGui();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(325, 19, 206, 31);
		frame.getContentPane().add(btnNewButton);
		String info =String.format("<html>%s<br/>%s<br/>%s<br/>%s</html>", "Welcome to our Program!","It has two function","1. Matrix Multiplication", "2. Sort Integer");
		JLabel lblMainInfo = new JLabel(info);
		lblMainInfo.setBounds(10, 19, 287, 102);
		frame.getContentPane().add(lblMainInfo);
		
		JButton btnSort = new JButton("Sort Number");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SortApplication sortApplication = new SortApplication();
					sortApplication.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sortApplication.setVisible(true);

				}catch(Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnSort.setBounds(325, 69, 206, 31);
		frame.getContentPane().add(btnSort);
	}
}
