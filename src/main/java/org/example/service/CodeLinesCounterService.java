package org.example.service;

import java.io.IOException;
import java.util.List;

public interface CodeLinesCounterService {

    void startCalculation(String path, List<String> processableFileTypes) throws IOException;

}
