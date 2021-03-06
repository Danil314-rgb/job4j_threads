package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class Wget implements Runnable {

    private final String url;
    private final int speed;
    private final String nameFile;

    public Wget(String url, int speed, String nameFile) {
        this.url = url;
        this.speed = speed;
        this.nameFile = nameFile;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(nameFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long start = currentTimeMillis();
            System.out.println("Стартовый момент: " + start + " миллисекунд");
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long finish = currentTimeMillis();
                    System.out.println("Текущий момент: " + finish + " миллисекунд");
                    long res = finish - start;
                    System.out.println("Разница: " + res + " миллисекунд");
                    if (res < 1000) {
                        Thread.sleep(1000 - res);
                    }
                    downloadData = 0;
                    start = currentTimeMillis();
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Программа должна принимать ровно два аргумента.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);

        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String nameFile = url.substring(url.lastIndexOf("/") + 1);

        Thread wget = new Thread(new Wget(url, speed, nameFile));
        wget.start();
        wget.join();
    }
}
