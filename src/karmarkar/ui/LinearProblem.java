package karmarkar.ui;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class LinearProblem {

    private String objective;
    private String constraints;

    public void load(File lpFile) {
        try {
            List<String> lines = FileUtils.readLines(lpFile, StandardCharsets.UTF_8).stream()
                    .filter(l -> !l.trim().startsWith("#"))
                    .collect(Collectors.toList());
            objective = lines.remove(0);
            constraints = lines.stream().collect(Collectors.joining(""));
        } catch(IOException e) {
            //TODO: handle error
        }
    }
}
