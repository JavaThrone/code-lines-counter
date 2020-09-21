package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.service.CodeLinesCounterService;
import org.example.service.CodeLinesCounterServiceImpl;

/**
 * The code was written for test purposes by human
 * My solution is oriented on high performance and memory saving
 * You need to input an absolute folder or file path in the console
 * Good luck and have fun
 */
public class App {

    public static void main(String[] args) {
        try {
            String path = args[0];
            List<String> processableFileTypes = new ArrayList<>();
            processableFileTypes.add(".java");
            CodeLinesCounterService codeLinesCounterService = new CodeLinesCounterServiceImpl();
            codeLinesCounterService.startCalculation(path, processableFileTypes);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error happened: " + e.getMessage());
        }
    }

}

