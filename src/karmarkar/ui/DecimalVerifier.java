package karmarkar.ui;

import javax.swing.*;

public class DecimalVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String val = ((JTextField) input).getText();
        return val.matches("[0-9]*\\.?[0-9]*");
    }
}
