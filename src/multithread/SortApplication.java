package multithread;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SortApplication extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTakeTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SortApplication dialog = new SortApplication();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SortApplication() {
		setBounds(100, 100, 450, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane textList = new JTextPane();
		textList.setBounds(92, 45, 314, 72);
		contentPanel.add(textList);
		
		JLabel lblNewLabel = new JLabel("Sorting Application");
		lblNewLabel.setBounds(0, 0, 112, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter List");
		lblNewLabel_1.setBounds(10, 72, 72, 23);
		contentPanel.add(lblNewLabel_1);
		
		JEditorPane textResult = new JEditorPane();
		textResult.setEditable(false);
		textResult.setBounds(92, 136, 314, 85);
		contentPanel.add(textResult);
		
		JLabel lblNewLabel_2 = new JLabel("Sorted list");
		lblNewLabel_2.setBounds(10, 167, 72, 23);
		contentPanel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Sort");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] stringList = textList.getText().trim().split(",");
				try {
					int[] listNumber = convertStringArrayToIntegerArray(stringList);
					long startTime= System.nanoTime();
					MergeSort mergeSort = new MergeSort(listNumber);
					long endTime = System.nanoTime();
					long timeElapsed = endTime - startTime;
					lblTakeTime.setText(String.format("take time: %d nanoseconds", timeElapsed));
					String result="";
					for(int i=0;i< listNumber.length;i++) {
						if(i==0) {
							result= String.format("%d", listNumber[i]);
						}else {
							result= String.format("%s, %d", result, listNumber[i]);
						}
					}
					textResult.setText(result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(337, 240, 87, 31);
		contentPanel.add(btnNewButton);
		
		lblTakeTime = new JLabel("take time:");
		lblTakeTime.setBounds(10, 263, 317, 23);
		contentPanel.add(lblTakeTime);
	}
	
	private int[] convertStringArrayToIntegerArray(String[] data) throws IOException {
		int[] numbers = new int[data.length];
		for(int i = 0;i < data.length;i++)        
		{
		    try 
		    {
		        numbers[i] = Integer.parseInt(data[i].trim());
		    }
		    catch (NumberFormatException nfe)   
		    {
		        throw new IOException("in this list contain none Integer");
		    }
		}
		return numbers;
	}
}
