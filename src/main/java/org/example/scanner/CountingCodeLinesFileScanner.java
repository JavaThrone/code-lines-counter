package org.example.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.example.counter.CodeLinesCounter;
import org.example.model.Directory;
import org.example.visitor.CountingCodeLinesFileVisitor;

public class CountingCodeLinesFileScanner implements FileScanner {

    public static final String ROOT_DIR = "root";
    private final CodeLinesCounter codeLinesCounter;

    public CountingCodeLinesFileScanner(CodeLinesCounter codeLinesCounter) {
        this.codeLinesCounter = codeLinesCounter;
    }

    @Override
    public Map<String, Directory> scan(Path startScanningPoint, List<String> processableFileTypes) throws IOException {
        Map<String, Directory> fileCatalog = new LinkedHashMap<>();
        Directory rootDir = new Directory(ROOT_DIR, ROOT_DIR, 0, new ArrayList<>());
        fileCatalog.put(ROOT_DIR, rootDir);

        CountingCodeLinesFileVisitor fileVisitor = new CountingCodeLinesFileVisitor(fileCatalog,
                processableFileTypes, codeLinesCounter);
        Files.walkFileTree(startScanningPoint, fileVisitor);
        return fileCatalog;
    }

}
