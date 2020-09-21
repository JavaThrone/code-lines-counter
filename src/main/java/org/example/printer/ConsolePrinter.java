package org.example.printer;

import java.util.Map;

import org.example.model.Directory;
import org.example.model.File;
import org.example.printer.Printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(Map<String, Directory> dirs) {
        for (Map.Entry<String, Directory> dirEntry : dirs.entrySet()) {
            Directory dir = dirEntry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append(dir.getName());
            builder.append(" : ");
            builder.append(dir.getCodeLines());
            for (File file : dir.getFiles()) {
                builder.append(" ");
                builder.append(file.getName());
                builder.append(" : ");
                builder.append(file.getCodeLines());
            }
            System.out.println(builder.toString());
        }
    }

    @Override
    public void print(File file) {
        System.out.println(file.getName() + " : " + file.getCodeLines());
    }
}
