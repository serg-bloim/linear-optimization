package karmarkar.ui;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;

public class MathService implements AutoCloseable {
    private String kernelPath;
    private KernelLink kernelLink;
    private int absMaxIterations = 42;

    public void setKernelPath(String kernelPath) {
        this.kernelPath = kernelPath;
    }

    public void start() {
        try {
            kernelLink = MathLinkFactory.createKernelLink("-linkmode launch -linkname \"" + kernelPath + "\"");
            init();
            validateConnection();
        } catch(MathLinkException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateConnection() throws MathLinkException {
        kernelLink.evaluate("TestFunc[]");
        kernelLink.waitForAnswer();
        if(kernelLink.getInteger() != 42) {
            throw new RuntimeException("Need to improve math skills");
        }
    }

    private void init() {
        try {
            kernelLink.discardAnswer();
            String initCmd = IOUtils.toString(getClass().getResourceAsStream("init.m"));
            sendCmd(initCmd);
            setVar("hardIterMax", String.valueOf(absMaxIterations));
        } catch(MathLinkException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        if(kernelLink != null) {
            kernelLink.close();
        }
    }

    public void setObjective(String objective) {
        setVar("cf", objective);
    }

    public void setConstraints(String constraints) {
        setVar("a", constraints);
    }

    private void setVar(String variable, String value) {
        try {
            sendCmd(MessageFormat.format(variable + "={0};", value));
        } catch(MathLinkException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendCmd(String cmd) throws MathLinkException {
        kernelLink.evaluate(cmd);
        kernelLink.discardAnswer();
    }

    public Expr eval(String cmd) {
        try {
            kernelLink.evaluate(cmd);
            kernelLink.waitForAnswer();
            return kernelLink.getExpr();
        } catch(MathLinkException e) {
            throw new RuntimeException(e);
        }
    }

    public String runKarmarkar() {
        try {
            return eval("KAlgorithmNew[]").asString();
        } catch(ExprFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getLastIteration() {
        return eval("iters[[-1]]").toString();
    }

    public int getIterNum() {
        try {
            return eval("iter").asInt();
        } catch(ExprFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrecision(String precision) {
        setVar("eps", precision);
    }

    public void setMaxIters(int maxIters) {
        setVar("itermax", String.valueOf(maxIters));
    }

    public void setAbsoluteMaxIters(int absMaxIterations) {
        this.absMaxIterations = absMaxIterations;
    }
}
