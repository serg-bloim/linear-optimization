package karmarkar.ui;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class MathService implements AutoCloseable {
    private String kernelPath;
    private KernelLink kernelLink;

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
            kernelLink.evaluate(IOUtils.toString(getClass().getResourceAsStream("init.m")));
            kernelLink.discardAnswer();
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
}
