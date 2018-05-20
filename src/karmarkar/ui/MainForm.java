package karmarkar.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.InputVerifier;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class MainForm extends JFrame {

    private JPanel contentPane;
    Configuration config = new Configuration("config.properties");
    private JTextField txtPrecision;
    private JTextField txtMaxIters;

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        	}
        });

        JButton btnSolve = new JButton("Solve");
        btnSolve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        
        JPanel panel_1 = new JPanel();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        			.addGap(20))
        		.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnSolve, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        			.addGap(20))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnLoad)
        			.addGap(18)
        			.addComponent(btnSolve)
        			.addGap(51)
        			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(84, Short.MAX_VALUE))
        );
        
        txtPrecision = new JTextField();
        txtPrecision.setText("0.0001");
        txtPrecision.setHorizontalAlignment(SwingConstants.RIGHT);
        txtPrecision.setColumns(10);
        
        txtMaxIters = new JTextField();
        txtMaxIters.setText("500");
        txtMaxIters.setHorizontalAlignment(SwingConstants.RIGHT);
        txtMaxIters.setColumns(10);
        
        JLabel lblStopConditions = new JLabel("Stop Conditions :");
        
        JCheckBox chkPrecision = new JCheckBox("Precision");
        
        JCheckBox chkMaxIters = new JCheckBox("Max iterations");
        
        JCheckBox chkOptimumFound = new JCheckBox("Optimum found");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        				.addComponent(chkMaxIters)
        				.addComponent(lblStopConditions, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtPrecision, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
        				.addComponent(chkPrecision)
        				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(chkOptimumFound, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(txtMaxIters, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblStopConditions)
        			.addGap(15)
        			.addComponent(chkPrecision)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtPrecision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(chkMaxIters)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtMaxIters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(chkOptimumFound)
        			.addContainerGap(46, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        panel.setLayout(gl_panel);

        JTextPane txtpnProblemLoaded = new JTextPane();
        txtpnProblemLoaded.setContentType("text/html");
        txtpnProblemLoaded.setText(config.getOutputText());
        txtpnProblemLoaded.setEditable(false);
        contentPane.add(txtpnProblemLoaded, BorderLayout.CENTER);
    }
}
