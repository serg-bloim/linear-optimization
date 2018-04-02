package karmarkar.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		contentPane.add(panel, BorderLayout.WEST);

		JButton btnNewButton = new JButton("Load");

		JButton btnNewButton_1 = new JButton("New button");

		JButton btnNewButton_2 = new JButton("New button");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Presicion");

		JLabel lblNewLabel_1 = new JLabel("Max iterations");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
												.addGap(20))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
												.addGap(20))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel)
												.addContainerGap(72, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblNewLabel_1)
												.addContainerGap(72, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
														.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
														.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
												.addGap(20))))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewButton_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewButton_2)
								.addGap(21)
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(232, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		JTextPane txtpnProblemLoaded = new JTextPane();
		txtpnProblemLoaded.setContentType("text/html");
		txtpnProblemLoaded.setText("Problem loaded<br/>....<br/>Iteration 1<br/>Iteration 2<br/><br/>Problem solved!");
		txtpnProblemLoaded.setEditable(false);
		contentPane.add(txtpnProblemLoaded, BorderLayout.CENTER);
	}
}
