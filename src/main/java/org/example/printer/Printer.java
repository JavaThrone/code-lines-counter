package org.example.printer;

import java.util.Map;

import org.example.model.Directory;
import org.example.model.File;

public interface Printer {

    void print(Map<String, Directory> dirs);
    void print(File file);

}
