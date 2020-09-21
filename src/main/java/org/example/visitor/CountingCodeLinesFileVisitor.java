package org.example.visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.counter.CodeLinesCounter;
import org.example.model.Directory;
import org.example.model.File;

public class CountingCodeLinesFileVisitor extends SimpleFileVisitor<Path> {

    public static final String ROOT_DIR = "root";
    private final Map<String, Directory> dirs;
    private final List<String> processableFileTypes;
    private final CodeLinesCounter codeLinesCounter;

    public CountingCodeLinesFileVisitor(Map<String, Directory> dirs,
                                        List<String> processableFileTypes, CodeLinesCounter codeLinesCounter) {
        this.dirs = dirs;
        this.processableFileTypes = processableFileTypes;
        this.codeLinesCounter = codeLinesCounter;
    }

    @Override
    public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
        String newFileName = filePath.getFileName().toString();
        String newFileFullPath = filePath.toString();
        if (processableFileTypes.stream().anyMatch(newFileName::endsWith)) {
            String parentDirFullPath = filePath.getParent().toString();
            Integer codeLines = getNumberOfLines(newFileFullPath);
            File newFile = new File(newFileFullPath, newFileName, codeLines);
            Directory parentDir = dirs.get(parentDirFullPath);
            parentDir.setCodeLines(parentDir.getCodeLines() + codeLines);
            parentDir.getFiles().add(newFile);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) throws IOException {
        String fileFullPath = dirPath.toString();
        String fileName = dirPath.getFileName().toString();
        Directory newDir = new Directory(fileFullPath, fileName, 0, new ArrayList<>());
        dirs.put(fileFullPath, newDir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dirPath, IOException exc) throws IOException {
        String dirFullPath = dirPath.toString();
        Directory dir = dirs.get(dirFullPath);
        Directory rootDir = dirs.get(ROOT_DIR);
        rootDir.setCodeLines(rootDir.getCodeLines() + dir.getCodeLines());
        return FileVisitResult.CONTINUE;
    }

    private Integer getNumberOfLines(String newFileFullPath) throws IOException {
        Integer codeLines;
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(newFileFullPath))) {
            codeLines = codeLinesCounter.getNumberOfLines(reader);
        }
        return codeLines;
    }
}
