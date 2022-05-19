package common;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndCounsumer {

    public static Queue<Integer> queue = new LinkedList<>();
    public static int maxNum = 10;

    public static class Producer implements Runnable {
        @Override
        public void run() {
            synchronized (queue) {
                if (queue.size() == maxNum) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(1);
                queue.notifyAll();
                System.out.println("produce one element");
            }
        }
    }

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.poll();
                queue.notifyAll();
                System.out.println("consumer one element");
            }
        }
    }
}
