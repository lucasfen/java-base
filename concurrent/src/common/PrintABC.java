package common;

import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {

    private static ReentrantLock lock = new ReentrantLock();
    private static int state = 0;

    public static class PrintNum implements Runnable {

        private int count;
        private char printChar;
        private int targetState;

        public PrintNum(int count, char printChar, int targetState) {
            this.count = count;
            this.printChar = printChar;
            this.targetState = targetState;
        }

        @Override
        public void run() {
            for (int i = 0; i < count;) {
                lock.lock();
                while (state % 3 == targetState) {
                    System.out.print(printChar);
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }
}
