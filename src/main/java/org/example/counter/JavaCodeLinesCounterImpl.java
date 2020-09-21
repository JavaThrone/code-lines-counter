package org.example.counter;

import java.io.BufferedReader;
import java.io.IOException;

public class JavaCodeLinesCounterImpl implements CodeLinesCounter {

    public Integer getNumberOfLines(BufferedReader reader) throws IOException {
        boolean commentStarted = false;
        int count = 0;
        String currentLine = null;

        while ((currentLine = reader.readLine()) != null) {
            currentLine = currentLine.trim();
            if ("".equals(currentLine)) {
                continue;
            }
            if (commentStarted) {
                if (commentFinished(currentLine)) {
                    currentLine = currentLine.substring(currentLine.indexOf("*/") + 2).trim();
                    commentStarted = false;
                    if ("".equals(currentLine) || currentLine.startsWith("//")) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (isSourceCodeLine(currentLine)) {
                count++;
            }
            if (commentStarted(currentLine)) {
                commentStarted = true;
            }
        }
        return count;
    }

    private boolean isSourceCodeLine(String line) {
        line = line.trim();
        if ("".equals(line) || line.startsWith("//")) {
            return false;
        }
        if (line.length() == 1) {
            return true;
        }
        int index = line.indexOf("/*");
        if (index != 0) {
            return true;
        } else {
            while (line.length() > 0) {
                line = line.substring(index + 2);
                int endCommentPosition = line.indexOf("*/");
                if (endCommentPosition < 0) {
                    return false;
                }
                if (endCommentPosition == line.length() - 2) {
                    return false;
                } else {
                    String subString = line.substring(endCommentPosition + 2)
                            .trim();
                    if ("".equals(subString) || subString.indexOf("//") == 0) {
                        return false;
                    } else {
                        if (subString.startsWith("/*")) {
                            line = subString;
                            continue;
                        }
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private boolean commentStarted(String line) {
        int index = line.indexOf("/*");
        if (index < 0) {
            return false;
        }
        int quoteStartIndex = line.indexOf("\"");
        if (quoteStartIndex != -1 && quoteStartIndex < index) {
            while (quoteStartIndex > -1) {
                line = line.substring(quoteStartIndex + 1);
                int quoteEndIndex = line.indexOf("\"");
                line = line.substring(quoteEndIndex + 1);
                quoteStartIndex = line.indexOf("\"");
            }
            return commentStarted(line);
        }
        return !commentFinished(line.substring(index + 2));
    }

    private boolean commentFinished(String line) {
        int index = line.indexOf("*/");
        if (index < 0) {
            return false;
        } else {
            String subString = line.substring(index + 2).trim();
            if ("".equals(subString) || subString.startsWith("//")) {
                return true;
            }
            return !commentStarted(subString);
        }
    }


}
