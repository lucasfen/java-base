import common.PrintABC;
import common.ProducerAndCounsumer;
import createThread.ThreadCreate1;
import createThread.ThreadCreate2;
import createThread.ThreadCreate3;
import org.junit.Test;

import java.util.concurrent.*;

public class ConcurrentTest {

    @Test
    public void createThread() {
        ThreadCreate1 threadCreate1_1 = new ThreadCreate1();
        ThreadCreate1 threadCreate1_2 = new ThreadCreate1();
        threadCreate1_1.start();
        threadCreate1_2.start();
    }

    @Test
    public void createThread2() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ThreadCreate2 threadCreate2 = new ThreadCreate2(countDownLatch);
        Thread thread1 = new Thread(threadCreate2);
        Thread thread2 = new Thread(threadCreate2);
        thread1.start();
        thread2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }

    @Test
    public void createThread3() {
        ThreadCreate3 threadCreate3 = new ThreadCreate3();
        FutureTask<String> task = new FutureTask<String>(threadCreate3);
        Thread thread = new Thread(task);
        thread.start();
        String result = null;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void ThreadCreate4() {
        ExecutorService executorService = new ThreadPoolExecutor(5,10,1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(5, true), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ThreadCreate2(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }

    @Test
    public void PrintNumTest() {
        Thread threada = new Thread(new PrintABC.PrintNum(10, 'a', 0));
        Thread threadb = new Thread(new PrintABC.PrintNum(10, 'b', 1));
        Thread threadc = new Thread(new PrintABC.PrintNum(10, 'c', 2));
        threada.start();
        threadb.start();
        threadc.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void PACTest() {
        Thread producer = new Thread(new ProducerAndCounsumer.Producer());
        Thread consumer = new Thread(new ProducerAndCounsumer.Consumer());
        producer.start();
        consumer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
