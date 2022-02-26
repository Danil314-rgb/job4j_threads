package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1600);
        progress.interrupt();
    }

    @Override
    public void run() {
        char[] arr = {'\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (char item : arr) {
                    Thread.sleep(500);
                    System.out.print("\rLoad: " + item);
                }
            } catch (InterruptedException e) {
                System.out.println("\nРабота потока была прервана");
                Thread.currentThread().interrupt();
            }
        }
    }
}
