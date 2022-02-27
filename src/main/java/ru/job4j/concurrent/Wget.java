package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp123.dat")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long time1 = currentTimeMillis();
            System.out.println("Стартовый момент: " + time1 + " миллисекунд");
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData == speed) {
                    long time2 = currentTimeMillis();
                    System.out.println("Текущий момент: " + time2 + " миллисекунд");
                    long res = time2 - time1;
                    System.out.println("Разница: " + res + " миллисекунд");
                    if (res < 1000) {
                        Thread.sleep(1000 - res);
                        downloadData = 0;
                        time1 = currentTimeMillis();
                    }
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                /*Thread.sleep(1000);*/
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://proof.ovh.net/files/10Mb.dat"; /*args[0];*/
        int speed = 1024; /*Integer.parseInt(args[1]);*/
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
