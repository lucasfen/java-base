package createThread;

import java.util.concurrent.CountDownLatch;

public class ThreadCreate2 implements Runnable {

    private CountDownLatch countDownLatch;

    public ThreadCreate2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
        countDownLatch.countDown();
    }
}
