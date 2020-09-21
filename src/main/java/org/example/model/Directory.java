package org.example.model;

import java.util.List;

public class Directory {

    private final String fullPath;
    private final String name;
    private Integer codeLines;
    private final List<File> files;

    public Directory(String fullPath, String name, Integer codeLines, List<File> files) {
        this.fullPath = fullPath;
        this.name = name;
        this.codeLines = codeLines;
        this.files = files;
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

    public void setCodeLines(Integer codeLines) {
        this.codeLines = codeLines;
    }

    public List<File> getFiles() {
        return files;
    }
}
