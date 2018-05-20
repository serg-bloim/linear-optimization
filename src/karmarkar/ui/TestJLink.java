package karmarkar.ui;

import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.MathLinkException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestJLink {

    private static MathService math;

    public static void main(String[] args) throws MathLinkException, IOException, ExprFormatException {
        math = new MathService();
        Configuration config = new Configuration("config.properties");
        math.setKernelPath(config.getKernelPath());
        math.start();
        System.out.println("connection established");
        math.setAbsoluteMaxIters(config.getAbsMaxIterations());
        testLP();
        System.out.println(math.eval("Directory[]").asString());
        math.close();
    }

    private static void testLP() throws IOException, MathLinkException {
        List<String> lines = FileUtils.readLines(new File("data/test1.lp")).stream()
                .filter(l -> !l.trim().startsWith("#"))
                .collect(Collectors.toList());
        String objective = lines.remove(0);
        String constraints = lines.stream().collect(Collectors.joining(""));
        math.setPrecision("0.00001");
        math.setMaxIters(400);
        math.setObjective(objective);
        math.setConstraints(constraints);
        String status = math.runKarmarkar();
        System.out.println("Status: " + status);
        int iters = math.getIterNum();
        System.out.println("iters "+iters);
        Object x = math.getLastIteration();
        System.out.println(x);
    }
}
