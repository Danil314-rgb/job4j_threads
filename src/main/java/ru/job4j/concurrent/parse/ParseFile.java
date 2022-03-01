package ru.job4j.concurrent.parse;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentUnicode(File file) {
        return content(n -> n < 0x80);
    }

    public String getContent(File file) {
        return content(n -> true);
    }
}
