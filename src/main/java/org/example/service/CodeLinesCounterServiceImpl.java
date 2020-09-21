package org.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.example.counter.CodeLinesCounter;
import org.example.counter.JavaCodeLinesCounterImpl;
import org.example.model.Directory;
import org.example.model.File;
import org.example.printer.ConsolePrinter;
import org.example.printer.Printer;
import org.example.scanner.CountingCodeLinesFileScanner;

public class CodeLinesCounterServiceImpl implements CodeLinesCounterService {

    @Override
    public void startCalculation(String path, List<String> processableFileTypes) throws IOException {
        Path inputPath = Paths.get(path); //args[0]
        Printer printer = new ConsolePrinter();
        if (Files.isDirectory(inputPath)) {
            CountingCodeLinesFileScanner scanner = new CountingCodeLinesFileScanner(new JavaCodeLinesCounterImpl());
            Map<String, Directory> scannedDirs = scanner.scan(inputPath, processableFileTypes);
            printer.print(scannedDirs);
        } else {
            CodeLinesCounter codeLinesCounter = new JavaCodeLinesCounterImpl();
            Integer codeLines;
            String fileFullPath = inputPath.toString();
            try (BufferedReader reader =
                         new BufferedReader(new FileReader(fileFullPath))) {
                codeLines = codeLinesCounter.getNumberOfLines(reader);
            }
            File file = new File(fileFullPath, inputPath.getFileName().toString(), codeLines);
            printer.print(file);
        }
    }
}
