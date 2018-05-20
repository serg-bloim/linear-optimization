package karmarkar.ui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class MainForm extends JFrame {

    private final Document doc;
    private JPanel contentPane;
    Configuration config = new Configuration("config.properties");
    private JTextField txtPrecision;
    private JTextField txtMaxIters;
    private final JButton btnLoad;
    private final JButton btnSolve;
    private final JFileChooser fileChooser = new JFileChooser("data");
    private MathService math;
    private final JTextPane txtpnProblemLoaded;
    private LinearProblem currentLP;
    private final JCheckBox chkPrecision;
    private final JCheckBox chkMaxIters;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm frame = new MainForm();
                    frame.initListeners();
                    frame.startMath();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startMath() {
        math = new MathService();
        Configuration config = new Configuration("config.properties");
        math.setKernelPath(config.getKernelPath());
        math.setAbsoluteMaxIters(config.getAbsMaxIterations());
        math.start();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                math.close();
                System.exit(0);
            }
        });
    }

    private void initListeners() {
        btnLoad.addActionListener(e -> {
            int status = fileChooser.showDialog(MainForm.this, "Open");
            if(status == JFileChooser.APPROVE_OPTION) {
                File lpFile = fileChooser.getSelectedFile();
                currentLP = new LinearProblem();
                currentLP.load(lpFile);
                displayLP(currentLP);
            }
        });
        btnSolve.addActionListener(e -> {
            if(currentLP != null) {
                math.setObjective(currentLP.getObjectiveAsMath());
                math.setConstraints(currentLP.getConstraintsAsMath());
                math.setPrecision(chkPrecision.isSelected()?txtPrecision.getText():"None");
                math.setMaxIters(chkMaxIters.isSelected()? Integer.parseInt(txtMaxIters.getText()) :Integer.MAX_VALUE);
                String status = math.runKarmarkar();
                writeLineToPane("");
                writeLineToPane("Status: " + status);
                writeLineToPane("Iterations: " +math.getIterNum());
                writeLineToPane("Result: " +math.getLastIteration());
            }
        });
    }

    private void displayLP(LinearProblem lp) {
        clearPane();
        writeLineToPane("Objective = " );
        writeLineToPane(lp.getObjectiveAsString());
        writeLineToPane("");
        writeLineToPane("Constraints, matrix A =");
        writeLineToPane(lp.getConstraintsAsString());
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

        btnLoad = new JButton("Load");

        btnSolve = new JButton("Solve");
        
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
        txtPrecision.setInputVerifier(new DecimalVerifier());
        
        txtMaxIters = new JTextField();
        txtMaxIters.setText("500");
        txtMaxIters.setHorizontalAlignment(SwingConstants.RIGHT);
        txtMaxIters.setColumns(10);
        txtMaxIters.setInputVerifier(new IntegerVerifier().setPositive(true));
        
        JLabel lblStopConditions = new JLabel("Stop Conditions :");

        chkPrecision = new JCheckBox("Precision");

        chkMaxIters = new JCheckBox("Max iterations");
        
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

        txtpnProblemLoaded = new JTextPane();
//        txtpnProblemLoaded.setContentType("text/html");
////        doc = new DefaultStyledDocument();
////        txtpnProblemLoaded.setDocument(doc);
        doc=txtpnProblemLoaded.getDocument();
        txtpnProblemLoaded.setFont(new Font("Consolas", Font.PLAIN, config.getFontSize()));
        writeLineToPane(config.getOutputText());
        txtpnProblemLoaded.setEditable(false);
        JPanel view = new JPanel();
        view.setLayout(new BorderLayout(0, 0));
        view.add(txtpnProblemLoaded);
        JScrollPane scrollPane = new JScrollPane(view);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }


    private void clearPane() {
        try {
            doc.remove(0, doc.getLength());
        } catch(BadLocationException e) {
            e.printStackTrace();
        }
    }
    private void writeLineToPane(String text) {
        try {
            text+="\n";
            doc.insertString(doc.getLength(), text, null);
        } catch(BadLocationException e) {

        }
    }
}
