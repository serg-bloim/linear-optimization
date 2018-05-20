package karmarkar.ui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearProblem {

    private String[] objective;
    private String[][] constraints;
    private int padding;

    public void load(File lpFile) {
        try {
            List<String> lines = FileUtils.readLines(lpFile, StandardCharsets.UTF_8).stream()
                    .filter(l -> !l.trim().startsWith("#"))
                    .collect(Collectors.toList());
            objective = parseLine(lines.remove(0));
            constraints = lines.stream().map(this::parseLine).toArray(String[][]::new);
            padding = 1 + Arrays.stream(constraints).flatMap(Arrays::stream).mapToInt(String::length).max().getAsInt();
        } catch(IOException e) {
            //TODO: handle error
        }
    }

    private String[] parseLine(String line) {
        return line.replaceAll("[\\s\\{\\}]+", "").split(",");
    }

    public String getObjectiveAsString() {
        return formatLine(this.objective);
    }

    private String formatLine(String[] objective) {
        return Arrays.stream(objective)
                .map(token -> StringUtils.leftPad(token, padding))
                .collect(Collectors.joining(","));
    }

    public String getConstraintsAsString() {
        return Arrays.stream(constraints)
                .map(this::formatLine)
                .collect(Collectors.joining("\n"));
    }
}