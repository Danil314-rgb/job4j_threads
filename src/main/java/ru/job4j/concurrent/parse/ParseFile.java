package ru.job4j.concurrent.parse;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test()) {
                    output += (char) data;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentUnicode(File file) {
        return content(n -> n < 0x80);
    }

    public String getContent(File file) {
        return content(n -> n = n);
    }
}
