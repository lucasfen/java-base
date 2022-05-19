package createThread;

import java.util.concurrent.Callable;

public class ThreadCreate3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("正在执行新建线程任务");
        return Thread.currentThread().getName() + " is running";
    }
}
