package ru.job4j.concurrent.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ContentUnicode implements Parse {

    @Override
    public String getContent(File file) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
