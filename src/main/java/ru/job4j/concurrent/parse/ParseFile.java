package ru.job4j.concurrent.parse;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized StringBuilder content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public synchronized StringBuilder getContentUnicode(File file) {
        return content(n -> n < 0x80);
    }

    public synchronized StringBuilder getContent(File file) {
        return content(n -> true);
    }
}
