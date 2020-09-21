package org.example.scanner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.example.model.Directory;
import org.example.model.File;

public interface FileScanner {

    Map<String, Directory> scan(Path startScanningPoint, List<String> processableFileTypes) throws IOException;

}
