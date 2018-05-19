package karmarkar.ui;

import com.wolfram.jlink.MathLinkException;

import java.io.IOException;

public class TestJLink {
    public static void main(String[] args) throws MathLinkException, IOException {
        MathService math = new MathService();
        Configuration config = new Configuration("config.properties");
        math.setKernelPath(config.getKernelPath());
        math.start();
//        math.solveKarmarkar();
        System.out.println("connection established");
        math.close();
    }
}
