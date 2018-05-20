package karmarkar.ui;

import javax.swing.*;

public class IntegerVerifier extends InputVerifier {
    boolean positive;

    @Override
    public boolean verify(JComponent input) {
        String val = ((JTextField) input).getText();
        try {
            int num = Integer.parseInt(val);
            if(positive)
                return num > 0;
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public IntegerVerifier setPositive(boolean positive) {
        this.positive = positive;
        return this;
    }
}
