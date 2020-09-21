package org.example.model;

import java.util.List;
import java.util.Objects;

public class File {

    private final String fullPath;
    private final String name;
    private final Integer codeLines;

    public File(String fullPath, String name, Integer codeLines) {
        this.fullPath = fullPath;
        this.name = name;
        this.codeLines = codeLines;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getName() {
        return name;
    }

    public Integer getCodeLines() {
        return codeLines;
    }
}
