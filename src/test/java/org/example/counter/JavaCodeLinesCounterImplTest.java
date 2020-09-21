package org.example.counter;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class JavaCodeLinesCounterImplTest {


    private CodeLinesCounter codeLinesCounter;

    @Before
    public void init() {
        codeLinesCounter = new JavaCodeLinesCounterImpl();
    }

    @Test
    public void getNumberOfLinesWithSpacesAndComments() throws IOException {
        Reader reader = new StringReader("    // This file contains 3 lines of code\n" +
                "     public interface Dave {\n" +
                "       /**\n" +
                "        * count the number of lines in a file\n" +
                "        */\n" +
                "       int countLines(File inFile); // not the real signature!\n" +
                "     }");

        BufferedReader bufferedReader = new BufferedReader(reader);

        Integer actualNumberOfLines = codeLinesCounter.getNumberOfLines(bufferedReader);
        Integer expectedNumberOfLines = 3;
        assertEquals(expectedNumberOfLines, actualNumberOfLines);

    }

    @Test
    public void getNumberOfLinesWithNestedComments() throws IOException {
        Reader reader = new StringReader(
                "     /*****\n" +
                        "     * This is a test program with 5 lines of code\n" +
                        "     *  \\/* no nesting allowed!\n" +
                        "     //*****//***/// Slightly pathological comment ending...\n" +
                        "  \n" +
                        "    public class Hello {\n" +
                        "        public static final void main(String [] args) { // gotta love Java\n" +
                        "            // Say hello\n" +
                        "          System./*wait*/out./*for*/println/*it*/(\"Hello/*\");\n" +
                        "        }\n" +
                        "  \n" +
                        "    }"
        );

        BufferedReader bufferedReader = new BufferedReader(reader);

        Integer actualNumberOfLines = codeLinesCounter.getNumberOfLines(bufferedReader);
        Integer expectedNumberOfLines = 5;
        assertEquals(expectedNumberOfLines, actualNumberOfLines);
    }

    @Test
    public void getNumberOfLinesWithoutComments() throws IOException {
        Reader reader = new StringReader(
                "package org.example;\n" +
                        "public class App {\n" +
                        "    public static void main(String[] args) throws IOException {\n" +
                        "       System.out.println(\"WORK!\");\n" +
                        "    }\n" +
                        "}\n"
        );
        BufferedReader bufferedReader = new BufferedReader(reader);

        Integer actualNumberOfLines = codeLinesCounter.getNumberOfLines(bufferedReader);
        Integer expectedNumberOfLines = 6;
        assertEquals(expectedNumberOfLines, actualNumberOfLines);
    }

    @Test
    public void getNumberOfLinesWithoutText() throws IOException {
        Reader reader = new StringReader(""
        );
        BufferedReader bufferedReader = new BufferedReader(reader);

        Integer actualNumberOfLines = codeLinesCounter.getNumberOfLines(bufferedReader);
        Integer expectedNumberOfLines = 0;
        assertEquals(expectedNumberOfLines, actualNumberOfLines);
    }
}
