package org.example.counter;

import java.io.BufferedReader;
import java.io.IOException;

public interface CodeLinesCounter {
    Integer getNumberOfLines(BufferedReader reader) throws IOException;
}
