package karmarkar.ui;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class TestJLink {
    public static void main(String[] args) throws MathLinkException, IOException {
        MathService math = new MathService();
        Configuration config = new Configuration("config.properties");
        math.setKernelPath(config.getKernelPath());
        math.start();
        System.out.println("connection established");
        math.close();
    }
}
