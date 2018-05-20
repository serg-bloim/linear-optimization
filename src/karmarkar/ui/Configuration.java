package karmarkar.ui;

import java.io.IOException;
import java.util.Properties;

public class Configuration extends Properties {
    private String outputText;

    public Configuration(String properties) {
        try {
            this.load(getClass().getClassLoader().getResourceAsStream(properties));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPrecision() {
        return getProperty("ui.precision");
    }


    public String getOutputText() {
        return getProperty("ui.output.text", "");
    }

    public String getMaxIterations() {
        return getProperty("ui.max-iterations");
    }

    public String getKernelPath() {
        return getProperty("kernel-path");
    }

    public int getAbsMaxIterations() {
        return Integer.parseInt(getProperty("math.absolute-maxiters"));
    }

    public int getFontSize() {
        return Integer.parseInt(getProperty("ui.font.size", "14"));
    }
}
